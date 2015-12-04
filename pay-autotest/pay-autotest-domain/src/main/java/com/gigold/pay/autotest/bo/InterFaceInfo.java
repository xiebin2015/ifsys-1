/**
 * Title: InterFaceInfo.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import java.util.List;

/**
 * Title: InterFaceInfo<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月3日下午4:49:42
 *
 */
public class InterFaceInfo {
	
	private  int id;
	private String ifName;
	private String sysName;
	private String proName;
	private String method;
	private String methodVersion;
	private String reqJsonStr;
	
	
	
	
	
	private List<IfSysMock> mockList;

	/**
	 * @return the mockList
	 */
	public List<IfSysMock> getMockList() {
		return mockList;
	}

	/**
	 * @param mockList
	 *            the mockList to set
	 */
	public void setMockList(List<IfSysMock> mockList) {
		this.mockList = mockList;
	}

}
