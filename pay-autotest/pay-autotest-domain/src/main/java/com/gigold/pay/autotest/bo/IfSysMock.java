/**
 * Title: IfSysMock.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import org.omg.CORBA.portable.Streamable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Title: IfSysMock<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月26日下午4:04:37
 *
 */
@Component
@Scope("prototype")
public class IfSysMock {
	
	private  int id;
	private int ifId;
	private int rspCodeId;
	private String rspCode;
	private String rspCodeDesc;
	private String requestJson;
	private String responseJson;
	private String testResult;
	private String realRspCode;
	private String realResponseJson;
	
	private String jrn;

	//所属接口信息
	private String ifName;
	private String ifURL;
	private String ifPROTOCOL;
	private String ifDESC;
	private String addressUrl;

	//接口关注者信息
	private int followedId;
	private int followId;
	private int Uid;
	private String status;
	private String remark;
	private String email;
	private String username;
	
	
	private int ifSysId;
	private int ifProId;
	
	
	
	/**
	 * @return the ifSysId
	 */
	public int getIfSysId() {
		return ifSysId;
	}

	/**
	 * @param ifSysId the ifSysId to set
	 */
	public void setIfSysId(int ifSysId) {
		this.ifSysId = ifSysId;
	}

	/**
	 * @return the ifProId
	 */
	public int getIfProId() {
		return ifProId;
	}

	/**
	 * @param ifProId the ifProId to set
	 */
	public void setIfProId(int ifProId) {
		this.ifProId = ifProId;
	}

	/**
	 * @return the jrn
	 */
	public String getJrn() {
		return jrn;
	}

	/**
	 * @param jrn the jrn to set
	 */
	public void setJrn(String jrn) {
		this.jrn = jrn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFollowedId() {
		return followedId;
	}

	public void setFollowedId(int followdid) {
		this.followedId = followdid;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		Uid = uid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the rspCodeDesc
	 */
	public String getRspCodeDesc() {
		return rspCodeDesc;
	}
	/**
	 * @param rspCodeDesc the rspCodeDesc to set
	 */
	public void setRspCodeDesc(String rspCodeDesc) {
		this.rspCodeDesc = rspCodeDesc;
	}
	/**
	 * @return the rspCodeId
	 */
	public int getRspCodeId() {
		return rspCodeId;
	}
	/**
	 * @param rspCodeId the rspCodeId to set
	 */
	public void setRspCodeId(int rspCodeId) {
		this.rspCodeId = rspCodeId;
	}
	/**
	 *接口信息的setter@chenkuan
	 */
	public void setIfName(String ifName){
		this.ifName=ifName;
	}
	/**
	 *接口信息的setter@chenkuan
	 */
	public void setIfURL(String ifURL){
		this.ifURL=ifURL;
	}
	/**
	 *接口信息的setter@chenkuan
	 */
	public void setIfDESC(String ifDESC){
		this.ifDESC=ifDESC;
	}
	/**
	 *接口信息的setter@chenkuan
	 */
	public void setIfPROTOCOL(String ifPROTOCOL){
		this.ifPROTOCOL=ifPROTOCOL;
	}

	/**
	 *接口信息的getter@chenkuan
	 */
	public String getIfName(){
		return ifName;
	}

	/**
	 *接口信息的getter@chenkuan
	 */
	public String getIfURL(){
		return ifURL;
	}

	/**
	 *接口信息的getter@chenkuan
	 */
	public String getIfPROTOCOL(){
		return ifPROTOCOL;
	}

	/**
	 *接口信息的getter@chenkuan
	 */
	public String getIfDESC(){
		return ifDESC;
	}

	/**
	 * @return the realRspCode
	 */
	public String getRealRspCode() {
		return realRspCode;
	}
	/**
	 * @param realRspCode the realRspCode to set
	 */
	public void setRealRspCode(String realRspCode) {
		this.realRspCode = realRspCode;
	}
	/**
	 * @return the realResponseJson
	 */
	public String getRealResponseJson() {
		return realResponseJson;
	}
	/**
	 * @param realResponseJson the realResponseJson to set
	 */
	public void setRealResponseJson(String realResponseJson) {
		this.realResponseJson = realResponseJson;
	}
	/**
	 * @return the testResult
	 */
	public String getTestResult() {
		return testResult;
	}
	/**
	 * @param testResult the testResult to set
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	/**
	 * @return the ifId
	 */
	public int getIfId() {
		return ifId;
	}
	/**
	 * @param ifId the ifId to set
	 */
	public void setIfId(int ifId) {
		this.ifId = ifId;
	}
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
	 * @return the rspCode
	 */
	public String getRspCode() {
		return rspCode;
	}
	/**
	 * @param rspCode the rspCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	/**
	 * @return the requestJson
	 */
	public String getRequestJson() {
		return requestJson;
	}
	/**
	 * @param requestJson the requestJson to set
	 */
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	/**
	 * @return the responseJson
	 */
	public String getResponseJson() {
		return responseJson;
	}
	/**
	 * @param responseJson the responseJson to set
	 */
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	

}
