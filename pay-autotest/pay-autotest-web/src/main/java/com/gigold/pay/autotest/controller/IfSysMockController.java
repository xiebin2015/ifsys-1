/**
 * Title: IfSysMockController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageInfo;

/**
 * Title: IfSysMockController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月30日上午11:37:39
 *
 */
@Controller
@RequestMapping("/autotest")
public class IfSysMockController extends BaseController {

	@Autowired
	IfSysMockService ifSysMockService;

	@RequestMapping("/addifsysmock.do")
	public @ResponseBody ResponseDto addIfSysMock(@RequestBody IfSysMockAddReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		// 验证请求参数合法性
		String code = dto.validation();
		// 没有通过则返回对应的返回码
		if (!"00000".equals(code)) {
			reDto.setRspCd(code);
			return reDto;
		}
		IfSysMock ifSysMock = null;
		try {
			ifSysMock = createBO(dto, IfSysMock.class);
		} catch (PendingException e) {
			reDto.setRspCd(CodeItem.CREATE_BO_FAILURE);
			return reDto;
		}
		boolean flag = ifSysMockService.addIfSysMock(ifSysMock);
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	@RequestMapping("/deleteifsysmockbyid.do")
	public @ResponseBody ResponseDto deleteIfSysMockById(@RequestBody IfSysMockDelReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		boolean flag = ifSysMockService.deleteIfSysMockById(dto.getId());
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	@RequestMapping("/deleteifsysmockbyifId.do")
	public @ResponseBody ResponseDto deleteIfSysMockByIfId(@RequestBody IfSysMockDelReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		boolean flag = ifSysMockService.deleteIfSysMockByIfId(dto.getIfId());
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	@RequestMapping("/updateifsysmock.do")
	public @ResponseBody ResponseDto updateIfSysMock(@RequestBody IfSysMockAddReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		// 验证请求参数合法性
		String code = dto.validation();
		// 没有通过则返回对应的返回码
		if (!"00000".equals(code)) {
			reDto.setRspCd(code);
			return reDto;
		}
		IfSysMock ifSysMock = null;
		try {
			ifSysMock = createBO(dto, IfSysMock.class);
		} catch (PendingException e) {
			reDto.setRspCd(CodeItem.CREATE_BO_FAILURE);
			return reDto;
		}
		boolean flag = ifSysMockService.updateIfSysMock(ifSysMock);
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description: 分页获取接口基本信息 列表页<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日下午1:47:35
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/getallifsys.do")
	public @ResponseBody IfSysMockRspDto getAllIfSys(@RequestBody IfSysMockPageDto dto) {
		IfSysMockRspDto reDto = new IfSysMockRspDto();
		int pageNum = dto.getPageNum();
		PageInfo<Map<String, Object>> pageInfo = ifSysMockService.getAllIfSys(pageNum);
		if (pageInfo != null) {
			reDto.setPageInfo(pageInfo);
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: getIfSysMockByIfId<br/>
	 * Description: 根据接口ID获取测试数据信息  编辑页,新增页<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日下午3:08:21
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/getifsysmockbyifid.do")
	public @ResponseBody IfStsMockRspListDto getIfSysMockByIfId(@RequestBody IfSysMockAddReqDto dto) {
		IfStsMockRspListDto reDto = new IfStsMockRspListDto();
		int ifId = dto.getId();
		List<Map<String, Object>> list = ifSysMockService.getMockInfoByIfId(ifId);
		reDto.setList(list);
		if (list != null) {
			reDto.setList(list);
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

}
