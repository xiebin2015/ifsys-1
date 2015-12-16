/**
 * Title: IfSysAutoTestService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.httpclient.HttpClientService;
import com.gigold.pay.autotest.jrn.JrnGeneratorService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.util.common.StringUtil;

import net.sf.json.JSONObject;

/**
 * Title: IfSysAutoTestService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日下午4:56:57
 *
 */
@Service
public class IfSysAutoTestService extends Domain {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	HttpClientService httpClientService;
	@Autowired
	IfSysMockService ifSysMockService;
	@Autowired
	IfSysReferService ifSysReferService;
	@Autowired
	JrnGeneratorService jrnGrneratorService;

	public void writeBackContent(IfSysMock mock, String responseJson,String jrn) {
		JSONObject jsonObject = null;
		IfSysMock ifsysmock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
		ifsysmock.setId(mock.getId());
		ifsysmock.setIfId(mock.getIfId());
		ifsysmock.setJrn(jrn);
		ifsysmock.setRealResponseJson(responseJson);
		try {
			jsonObject = JSONObject.fromObject(responseJson);
		} catch (Exception e) {
			jsonObject=new JSONObject();
		}
		String relRspCode = String.valueOf(jsonObject.get("rspCd"));
		ifsysmock.setRealRspCode(relRspCode);

		// 1-正常 0-失败 -1-请求或响应存在其他异常
		if (relRspCode.equalsIgnoreCase(mock.getRspCode())) {
			// 实际响应返回码与预期的一致的情况
			ifsysmock.setTestResult("1");

		} else if (StringUtil.isBlank(responseJson)) {
			// 实际响应报文为空的情况
			ifsysmock.setTestResult("0");

		} else {
			ifsysmock.setTestResult("-1");
		}
		ifSysMockService.writeBackRealRsp(ifsysmock);
	}

	/**
	 * 
	 * Title: autoTest<br/>
	 * Description: 自动化测试核心代码<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:50:15
	 *
	 * @param interFaceInfo
	 */
	public void autoTest(InterFaceInfo interFaceInfo) {
		String url = getAddressUrl(interFaceInfo.getAddressUrl(), interFaceInfo.getIfUrl());
		// 1、获取接口调用时依赖的接口调用列表
		List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
		invokerOrder(invokerOrderList, interFaceInfo.getId());
		// 2、依次调用被依赖的接口
		for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
			IfSysMock mock = invokerOrderList.get(i);
			/**
			 * 调用HTTP请求
			 */
			// 期望请求报文
			String postData = mock.getRequestJson();
			// 实际请求后，返回的报文（返回码和返回实体）
			try{
				httpClientService.httpPost(mock.getAddressUrl(), postData);
			}catch(Exception e){
				debug("调用失败");
			}

		}
		String jrn = null;
		try {
			jrn = jrnGrneratorService.generateJrn();
		} catch (Exception e) {
			debug("ifSysMockHistoryServiceAspect doBefore 生成批次号有异常");
			e.printStackTrace();
		}

		// 3、最后调用目标接口
		for (IfSysMock mock : interFaceInfo.getMockList()) {
			/**
			 * 调用HTTP请求
			 */
			// 期望请求报文
			String postData = mock.getRequestJson();
			// 实际请求后，返回的报文（返回码和返回实体）
			String responseJson="";
			try{
				 responseJson=httpClientService.httpPost(url, postData);
			}catch(Exception e){
				responseJson="";
				debug("调用失败");
			}
			// 实际结果回写
			writeBackContent(mock, responseJson,jrn);

		}
	}

	/**
	 * 
	 * Title: invokerOrder<br/>
	 * Description: 获取接口调用时依赖的接口调用列表 确定接口调用顺序<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日下午4:06:23
	 *
	 * @param invokerOrderList
	 * @param ifId
	 */
	public void invokerOrder(List<IfSysMock> invokerOrderList, int ifId) {
		// 获取被测接口依赖其他接口的列表
		List<IfSysRefer> referList = ifSysReferService.getReferList(ifId);
		// 如果有依赖 遍历依赖 列表
		for (int i = referList.size() - 1; i >= 0; i--) {
			IfSysRefer refer = referList.get(i);
			// 获取被依赖的接口
			IfSysMock mock = ifSysMockService.getReferByIfId(refer.getRefId());
			String url = getAddressUrl(mock.getAddressUrl(), mock.getIfURL());
			mock.setAddressUrl(url);
			invokerOrderList.add(mock);
			invokerOrder(invokerOrderList, mock.getIfId());
		}
	}

	/**
	 * 
	 * Title: getAddressUrl<br/>
	 * Description: 获取接口完整地址<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:43:23
	 *
	 * @param interFaceInfo
	 * @return
	 */
	public String getAddressUrl(String url, String action) {
		String addressUrl = "";
		if (StringUtil.isNotBlank(url) && StringUtil.isNotBlank(action)) {
			if (url.endsWith("/")) {
				addressUrl = url + action;
			} else {
				addressUrl = url + "/" + action;
			}
		}
		return addressUrl;
	}

}
