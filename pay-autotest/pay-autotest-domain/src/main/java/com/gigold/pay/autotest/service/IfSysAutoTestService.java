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
	public void autoTest(InterFaceInfo interFaceInfo){
		String url=interFaceInfo.getAddressUrl()+"/"+interFaceInfo.getIfUrl();
		
		for(IfSysMock mock :interFaceInfo.getMockList()){
			//期望请求报文
			String postData=mock.getRequestJson();
			
			String responseJson = httpClientService.httpPost(url, postData);
			if(StringUtil.isBlank(responseJson)){
				debug("接口返回报文为空");
				continue;
				// 发邮件
				//接口测试数据 状态回写 异常
			}
			JSONObject jsonObject = JSONObject.fromObject(responseJson);
			String relRspCode=String.valueOf(jsonObject.get("rspCd"));
			//进行比对 并发邮件
			//接口测试数据 状态回写 异常
			
			
			
			//
			//接口测试数据 状态回写 成功
		}
	}
	
	
}
