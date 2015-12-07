/**
 * Title: IfSysMockPageDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: IfSysMockPageDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月2日下午1:40:56
 *
 */
public class IfSysMockPageDto extends RequestDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int pageNum;

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	

}
