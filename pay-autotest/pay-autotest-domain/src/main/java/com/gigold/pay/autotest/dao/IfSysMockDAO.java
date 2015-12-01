/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;
import java.util.Map;

import com.gigold.pay.autotest.bo.IfSysMock;

/**
 * Title: IfSysMockDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysMockDAO {

	/**
	 * 
	 * Title: addIfSysMock<br/>
	 * Description: 新增接口测试数据<br/>
	 * @author xiebin
	 * @date 2015年11月30日上午11:15:29
	 *
	 * @param ifSysMock
	 * @return
	 */
	public int addIfSysMock(IfSysMock ifSysMock);
	/**
	 * 
	 * Title: getIfSysMock<br/>
	 * Description: 获取所有的测试数据<br/>
	 * @author xiebin
	 * @date 2015年11月30日上午11:15:51
	 *
	 * @return
	 */
	public List<Map<String,Object>>getIfSysMock();
}
