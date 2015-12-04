/**
 * Title: IfStsMockRspListDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.List;
import java.util.Map;

import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: IfStsMockRspListDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月2日下午3:30:57
 *
 */
public class IfStsMockRspListDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<Map<String, Object>> list;

	/**
	 * @return the list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

}
