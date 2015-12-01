package com.gigold.pay.ifsys.controller;

import java.util.List;
import java.util.Map;

import com.gigold.pay.framework.web.ResponseDto;

public class InterFaceInvokerResDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<Map<String,Object>> list;

	/**
	 * @return the list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	

}
