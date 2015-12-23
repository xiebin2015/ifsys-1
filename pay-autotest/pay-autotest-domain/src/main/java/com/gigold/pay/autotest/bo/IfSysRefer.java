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
  //测试用例ID
  private int mockId;
  //被依赖测试用例ID
  private int refMockId;
  //依赖序号
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
 * @return the mockId
 */
public int getMockId() {
	return mockId;
}
/**
 * @param mockId the mockId to set
 */
public void setMockId(int mockId) {
	this.mockId = mockId;
}
/**
 * @return the refMockId
 */
public int getRefMockId() {
	return refMockId;
}
/**
 * @param refMockId the refMockId to set
 */
public void setRefMockId(int refMockId) {
	this.refMockId = refMockId;
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
