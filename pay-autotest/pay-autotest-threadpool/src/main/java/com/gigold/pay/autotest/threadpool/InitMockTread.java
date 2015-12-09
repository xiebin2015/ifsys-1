/**
 * Title: InitMockTread.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.service.RetrunCodeService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.util.common.StringUtil;

/**
 * Title: InitMockTread<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月9日下午3:14:02
 *
 */
public class InitMockTread implements Runnable {
	InterFaceFieldService interFaceFieldService;
	RetrunCodeService retrunCodeService;
	IfSysMockService ifSysMockService;
	// 存放接口列表
	private List<InterFaceInfo> ifsyslist;

	public InitMockTread(List<InterFaceInfo> ifsyslist) {
		this.ifsyslist = ifsyslist;
		this.interFaceFieldService = (InterFaceFieldService) SpringContextHolder.getBean(InterFaceFieldService.class);
		this.retrunCodeService = (RetrunCodeService) SpringContextHolder.getBean(RetrunCodeService.class);
		this.ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
	}

	@Override
	public void run() {
		processCommand();
	}

	// 处理接口测试
	private void processCommand() {
		// 遍历接口信息 获取其对应的返回码信息
		for (InterFaceInfo interFaceInfo : ifsyslist) {
			// 获取接口请求字段的JSON展示字符串
			InterFaceField interFaceField = (InterFaceField) SpringContextHolder.getBean(InterFaceField.class);
			interFaceField.setIfId(interFaceInfo.getId());
			interFaceField.setFieldFlag("1");
			String reqJson = interFaceFieldService.getJsonStr(interFaceField);
			if (StringUtil.isNotBlank(reqJson)) {
				interFaceInfo.setReqJsonStr(reqJson);
			}
			// 获取接口响应字段的JSON展示字符串
			interFaceField.setFieldFlag("2");
			String rspJson = interFaceFieldService.getJsonStr(interFaceField);
			if (StringUtil.isNotBlank(rspJson)) {
				interFaceInfo.setRspJsonStr(rspJson);
			}
			List<ReturnCode> returnList = retrunCodeService.getReturnCodeByIfId(interFaceInfo.getId());
			// 遍历返回码 更新测试数据
			for (ReturnCode rscdObj : returnList) {
				IfSysMock ifSysMock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
				ifSysMock.setIfId(interFaceInfo.getId());
				ifSysMock.setRspCodeId(rscdObj.getId());
				ifSysMock.setRequestJson(interFaceInfo.getReqJsonStr());
				ifSysMock.setResponseJson(interFaceInfo.getRspJsonStr());
				ifSysMockService.createIfSysMock(ifSysMock);
			}

		}
	}
}
