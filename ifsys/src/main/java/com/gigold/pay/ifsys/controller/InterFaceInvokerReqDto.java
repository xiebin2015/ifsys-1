/**
 * Title: InterFaceInvokerAddDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import org.springframework.util.StringUtils;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: InterFaceInvokerAddDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月23日下午3:29:39
 *
 */
public class InterFaceInvokerReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long ifFollowId;
	private long ifFollowedId;
	private String remark;

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the ifFollowId
	 */
	public long getIfFollowId() {
		return ifFollowId;
	}

	/**
	 * @param ifFollowId
	 *            the ifFollowId to set
	 */
	public void setIfFollowId(long ifFollowId) {
		this.ifFollowId = ifFollowId;
	}

	/**
	 * @return the ifFollowedId
	 */
	public long getIfFollowedId() {
		return ifFollowedId;
	}

	/**
	 * @param ifFollowedId the ifFollowedId to set
	 */
	public void setIfFollowedId(long ifFollowedId) {
		this.ifFollowedId = ifFollowedId;
	}

	public String vaildate() {
		if(StringUtils.isEmpty(this.remark)){
			return CodeItem.REMARK_FAILURE;
		}
		return SysCode.SUCCESS;
	}

}
