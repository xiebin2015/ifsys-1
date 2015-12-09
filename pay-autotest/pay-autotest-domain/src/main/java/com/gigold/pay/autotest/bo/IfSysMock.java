/**
 * Title: IfSysMock.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

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
	private String rspCode;
	private String requestJson;
	private String responseJson;
	private String testResult;
	private String realRspCode;
	private String realResponseJson;

	//所属接口信息
	private String ifName;
	private String ifURL;
	private String ifPROTOCOL;
	private String ifDESC;

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
