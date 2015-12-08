/**
 * Title: IfSysAutoTestService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.httpclient.HttpClientService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.util.common.StringUtil;

import net.sf.json.JSONObject;

/**
 * Title: IfSysAutoTestService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月5日下午4:56:57
 *
 */
@Service
public class IfSysAutoTestService extends Domain{
    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
    HttpClientService httpClientService;
	@Autowired
	IfSysMockService ifSysMockService;
	
	
	public void writeBackContent(IfSysMock mock,String responseJson){
		JSONObject jsonObject = null;
		IfSysMock ifsysmock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
		ifsysmock.setId(mock.getId());
		ifsysmock.setRealResponseJson(responseJson);
		try{
			 jsonObject = JSONObject.fromObject(responseJson);
		}catch(Exception e){
			
		}
		String relRspCode=String.valueOf(jsonObject.get("rspCd"));
		ifsysmock.setRealRspCode(relRspCode);
		
		// 1-正常  2-失败  3-请求或响应存在其他异常
		if(relRspCode == mock.getRspCode()){
			//实际响应返回码与预期的一致的情况
			ifsysmock.setTestResult("1");
			
		}else 
			
			if(StringUtil.isBlank(responseJson)){
			//实际响应报文为空的情况
			ifsysmock.setTestResult("0");
			
		}else {
			ifsysmock.setTestResult("-1");
		}
		ifSysMockService.writeBackRealRsp(ifsysmock);
	}
	
	public void autoTest(InterFaceInfo interFaceInfo){
		String url=interFaceInfo.getAddressUrl()+"/"+interFaceInfo.getIfUrl();
		for(IfSysMock mock :interFaceInfo.getMockList()){
			// 期望请求报文
			String postData=mock.getRequestJson();
			// 实际请求后，返回的报文（返回码和返回实体）
			String responseJson = httpClientService.httpPost(url, postData);
			// 实际结果回写
			writeBackContent(mock,responseJson);			
		}
	}
	
}
