/**
 * Title: IfSysMockController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: IfSysMockController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:37:39
 *
 */
@Controller

public class IfSysMockController extends BaseController{

	@Autowired
	IfSysMockService ifSysMockService;
	public @ResponseBody ResponseDto addIfSysMock(@RequestBody IfSysMockReqaDto dto){
		ResponseDto reDto=new ResponseDto();
		
		IfSysMock ifSysMock=null;
		try {
			ifSysMock = createBO(dto, IfSysMock.class);
		} catch (PendingException e) {
			 ifSysMock=(IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
			e.printStackTrace();
		}
		ifSysMockService.addIfSysMock(ifSysMock);
		return reDto;
	}
}
