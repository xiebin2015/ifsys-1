/**
 * Title: InterFaceInvokerController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceInvokerService;

/**
 * Title: InterFaceInvokerController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月23日下午3:27:08
 *
 */
@Controller
public class InterFaceInvokerController extends BaseController {
	@Autowired
	InterFaceInvokerService interFaceInvokerService;

	@RequestMapping("/addinvoker.do")
	public @ResponseBody InterFaceInvokerAddResDto addInterFaceInvoker(@RequestBody InterFaceInvokerReqDto dto, HttpSession session) {
		debug("调用 addInterFaceInvoker");
		InterFaceInvokerAddResDto rdto = new InterFaceInvokerAddResDto();
		String recode = dto.vaildate();
		if (!SysCode.SUCCESS.equals(recode)) {
			rdto.setRspCd(recode);
			return rdto;
		}
		// 在session中取uid
		UserInfo userInfo = (UserInfo) session.getAttribute(SystemPropertyConfigure.getLoginKey());
		if (userInfo == null) {
			rdto.setRspCd(SysCode.SYS_FAIL);
			rdto.setRspInf("用户未登录");
			return rdto;
		}
		InterFaceInvoker invoker=null;
		try {
			invoker = createBO(dto, InterFaceInvoker.class);
		} catch (PendingException e) {
			debug("创建BO失败");
			e.printStackTrace();
		}
		
		invoker.setuId(userInfo.getId());
		// 添加关注信息
		invoker = interFaceInvokerService.addInterFaceInvoker(invoker);
		if (invoker!=null) {
			rdto.setInvoker(invoker);
			rdto.setRspCd(SysCode.SUCCESS);
			rdto.setRspInf("关注成功");
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			rdto.setRspInf("关注失败");
		}

		return rdto;

	}
	
	/**
	 * 
	 * Title: getInvokerList<br/>
	 * Description: 获取接口关注列表<br/>
	 * @author xiebin
	 * @date 2015年11月23日下午5:06:49
	 *
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping("/getinvokerlist.do")
	public @ResponseBody InterFaceInvokerResDto getInvokerListByFollowId(@RequestBody InterFaceInvokerReqDto dto, HttpSession session) {
		debug("调用 getInvokerListByFollowId");
		InterFaceInvokerResDto rdto=new InterFaceInvokerResDto();
		InterFaceInvoker invoker = (InterFaceInvoker) SpringContextHolder.getBean(InterFaceInvoker.class);
		invoker.setIfFollowedId(dto.getIfFollowedId());
		List<InterFaceInvoker> list=interFaceInvokerService.getInvokerList(invoker);
		if(list!=null){
			rdto.setList(list);
			rdto.setRspCd(SysCode.SUCCESS);
		}else{
			rdto.setRspCd(CodeItem.IF_FAILURE);
			rdto.setRspInf("获取失败");
		}
		
		return rdto;
	}
}
