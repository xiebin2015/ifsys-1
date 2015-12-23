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

	public void writeBackContent(IfSysMock mock, String responseJson) {
		JSONObject jsonObject = null;
		IfSysMock ifsysmock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
		ifsysmock.setId(mock.getId());
		ifsysmock.setIfId(mock.getIfId());
		ifsysmock.setRealResponseJson(responseJson);
		try {
			jsonObject = JSONObject.fromObject(responseJson);
		} catch (Exception e) {
			jsonObject = new JSONObject();
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
	 * Description: 自动化测试核心代码 测试用例之间的依赖<b/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:50:15
	 *
	 * @param interFaceInfo
	 */
	public void autoTest(InterFaceInfo interFaceInfo) {
		// 获取接口访问的完整地址
		String url = getAddressUrl(interFaceInfo.getAddressUrl(), interFaceInfo.getIfUrl());
		// 调用接口所有的测试用例
		for (IfSysMock mock : interFaceInfo.getMockList()) {
			// 设置接口访问的完整地址
			mock.setAddressUrl(url);
			// 1、获取该测试用例调用时依赖的其他用例的调用列表
			List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
			invokerOrder(invokerOrderList, mock.getId());
			// 2、 按照调用序号依次调用被依赖测试用例
			invokRefCase(invokerOrderList);
			// 3、最后调用目标接口
			invokCase(mock);

		}

	}

	/**
	 * 
	 * Title: invokRefCase<br/>
	 * Description:按调用序号依次调用被依赖测试用例 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:51:14
	 *
	 * @param invokerOrderList
	 */
	public void invokRefCase(List<IfSysMock> invokerOrderList) {
		for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
			IfSysMock refmock = invokerOrderList.get(i);
			/**
			 * 调用HTTP请求
			 */
			// 期望请求报文
			String postData = refmock.getRequestJson();
			// 实际请求后，返回的报文（返回码和返回实体）
			try {
				httpClientService.httpPost(refmock.getAddressUrl(), postData);
			} catch (Exception e) {
				debug("调用失败   调用被依赖测试用例过程中出现异常");
			}

		}
	}

	/**
	 * 
	 * Title: invokCase<br/>
	 * Description: 调用目标测试用例<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:49:39
	 *
	 * @param mock
	 */
	public void invokCase(IfSysMock mock) {
		// 期望请求报文
		String postData = mock.getRequestJson();
		// 实际请求后，返回的报文（返回码和返回实体）
		String responseJson = "";
		try {
			responseJson = httpClientService.httpPost(mock.getAddressUrl(), postData);
		} catch (Exception e) {
			responseJson = "";
			debug("调用失败 调用目标测试用例过程中出现异常");
		}
		// 实际结果回写
		writeBackContent(mock, responseJson);
	}

	/**
	 * 
	 * Title: autoTest<br/>
	 * Description: 自动化测试核心代码 接口哦间的依赖<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:50:15
	 *
	 * @param interFaceInfo
	 */
	// public void autoTest(InterFaceInfo interFaceInfo) {
	// //设置接口访问的完整地址
	// String url = getAddressUrl(interFaceInfo.getAddressUrl(),
	// interFaceInfo.getIfUrl());
	// // 1、获取接口调用时依赖的接口调用列表
	// List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
	// invokerOrder(invokerOrderList, interFaceInfo.getId());
	// // 2、依次调用被依赖的接口
	// for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
	// IfSysMock mock = invokerOrderList.get(i);
	// /**
	// * 调用HTTP请求
	// */
	// // 期望请求报文
	// String postData = mock.getRequestJson();
	// // 实际请求后，返回的报文（返回码和返回实体）
	// try{
	// httpClientService.httpPost(mock.getAddressUrl(), postData);
	// }catch(Exception e){
	// debug("调用失败");
	// }
	//
	// }
	// // 3、最后调用目标接口
	// for (IfSysMock mock : interFaceInfo.getMockList()) {
	// /**
	// * 调用HTTP请求
	// */
	// // 期望请求报文
	// String postData = mock.getRequestJson();
	// // 实际请求后，返回的报文（返回码和返回实体）
	// String responseJson="";
	// try{
	// responseJson=httpClientService.httpPost(url, postData);
	// }catch(Exception e){
	// responseJson="";
	// debug("调用失败");
	// }
	// // 实际结果回写
	// writeBackContent(mock, responseJson);
	//
	// }
	// }
	//

	/**
	 * 
	 * Title: invokerOrder<br/>
	 * Description: 获取该测试用例调用时依赖的其他用例的调用列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:14:44
	 *
	 * @param invokerOrderList
	 * @param ifId
	 */
	public void invokerOrder(List<IfSysMock> invokerOrderList, int mockId) {
		// 获取被测用例依赖其他用例的列表
		List<IfSysRefer> referList = ifSysReferService.getReferList(mockId);
		// 如果有依赖 遍历依赖 列表
		for (int i = referList.size() - 1; i >= 0; i--) {
			IfSysRefer refer = referList.get(i);
			// 获取被依赖的用例数据
			IfSysMock mock = ifSysMockService.getReferByIfId(refer.getRefMockId());
			String url = getAddressUrl(mock.getAddressUrl(), mock.getIfURL());
			mock.setAddressUrl(url);
			invokerOrderList.add(mock);
			// 如果被依赖测试用例还依赖其他测试用例
			invokerOrder(invokerOrderList, refer.getRefMockId());
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
	// public void invokerOrder(List<IfSysMock> invokerOrderList, int ifId) {
	// // 获取被测接口依赖其他接口的列表
	// List<IfSysRefer> referList = ifSysReferService.getReferList(ifId);
	// // 如果有依赖 遍历依赖 列表
	// for (int i = referList.size() - 1; i >= 0; i--) {
	// IfSysRefer refer = referList.get(i);
	// // 获取被依赖的接口
	// IfSysMock mock = ifSysMockService.getReferByIfId(refer.getRefId());
	// String url = getAddressUrl(mock.getAddressUrl(), mock.getIfURL());
	// mock.setAddressUrl(url);
	// invokerOrderList.add(mock);
	// invokerOrder(invokerOrderList, mock.getIfId());
	// }
	// }

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
