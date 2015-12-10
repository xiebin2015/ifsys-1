/**
 * Title: IfSysRefer.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

/**
 * Title: IfSysRefer<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月9日下午5:07:23
 *
 */
@Component
@Scope("prototype")
public class IfSysRefer extends Domain{
  private int id;
  private int ifId;
  private int refId;
  private int ordNum;
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
 * @return the refId
 */
public int getRefId() {
	return refId;
}
/**
 * @param refId the refId to set
 */
public void setRefId(int refId) {
	this.refId = refId;
}
/**
 * @return the ordNum
 */
public int getOrdNum() {
	return ordNum;
}
/**
 * @param ordNum the ordNum to set
 */
public void setOrdNum(int ordNum) {
	this.ordNum = ordNum;
}
  
}
