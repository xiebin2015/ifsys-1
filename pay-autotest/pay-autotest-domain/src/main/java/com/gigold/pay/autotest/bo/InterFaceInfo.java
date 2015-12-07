/**
 * Title: InterFaceInfo.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import java.util.ArrayList;
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
	private String ifUrl;
	private String addressUrl;
	private String method;
	private String methodVersion;
	private String reqJsonStr;
	private String rspJsonStr;
	
	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ifName
	 */
	public String getIfName() {
		return ifName;
	}

	/**
	 * @param ifName the ifName to set
	 */
	public void setIfName(String ifName) {
		this.ifName = ifName;
	}

	/**
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the proName
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * @param proName the proName to set
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}

	/**
	 * @return the ifUrl
	 */
	public String getIfUrl() {
		return ifUrl;
	}

	/**
	 * @param ifUrl the ifUrl to set
	 */
	public void setIfUrl(String ifUrl) {
		this.ifUrl = ifUrl;
	}

	/**
	 * @return the addressUrl
	 */
	public String getAddressUrl() {
		return addressUrl;
	}

	/**
	 * @param addressUrl the addressUrl to set
	 */
	public void setAddressUrl(String addressUrl) {
		this.addressUrl = addressUrl;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the methodVersion
	 */
	public String getMethodVersion() {
		return methodVersion;
	}

	/**
	 * @param methodVersion the methodVersion to set
	 */
	public void setMethodVersion(String methodVersion) {
		this.methodVersion = methodVersion;
	}

	/**
	 * @return the reqJsonStr
	 */
	public String getReqJsonStr() {
		return reqJsonStr;
	}

	/**
	 * @param reqJsonStr the reqJsonStr to set
	 */
	public void setReqJsonStr(String reqJsonStr) {
		this.reqJsonStr = reqJsonStr;
	}

	/**
	 * @return the rspJsonStr
	 */
	public String getRspJsonStr() {
		return rspJsonStr;
	}

	/**
	 * @param rspJsonStr the rspJsonStr to set
	 */
	public void setRspJsonStr(String rspJsonStr) {
		this.rspJsonStr = rspJsonStr;
	}

	private List<IfSysMock> mockList=new ArrayList<IfSysMock>();

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
