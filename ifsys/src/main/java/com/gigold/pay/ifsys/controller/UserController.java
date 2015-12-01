package com.gigold.pay.ifsys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.UserInfoService;

@Controller
@RequestMapping("/")
public class UserController extends BaseController{
	@Autowired
	UserInfoService userInfoService;

	
	@RequestMapping(value = "/login.do")
	public @ResponseBody UserResDto login2(@RequestBody  UserIReqDto rdto,HttpSession sessin) {
		UserInfo user = userInfoService.login(rdto.getUserInfo());
		UserResDto dto=new UserResDto();
		if (user == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
		} else {
		    sessin.setAttribute(SystemPropertyConfigure.getLoginKey(), user);
			dto.setUserInfo(user);
			dto.setRspCd(SysCode.SUCCESS);
		}

		return dto;
	}
	
	
	
	@RequestMapping("/addUser")
	public String addUser(UserInfo userInfo) {
		boolean flag = userInfoService.addUser(userInfo);
		if (flag) {
			return "main";
		}
		return "index";
	}

	

}
