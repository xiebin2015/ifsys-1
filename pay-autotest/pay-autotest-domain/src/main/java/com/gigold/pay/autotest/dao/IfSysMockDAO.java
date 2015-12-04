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
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysMockDAO {

	/**
	 * 
	 * Title: addIfSysMock<br/>
	 * Description: 新增接口测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月30日上午11:15:29
	 *
	 * @param ifSysMock
	 * @return
	 */
	public int addIfSysMock(IfSysMock ifSysMock);

	/**
	 * 
	 * Title: getMockInfoByIfId<br/>
	 * Description: 根据接口ID获取所有测试信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午2:59:28
	 *
	 * @return
	 */
	public List<IfSysMock> getMockInfoByIfId(int ifId);
	
	/**
	 * 
	 * Title: updateIfSysMock<br/>
	 * Description: 修改模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:50:33
	 *
	 * @param ifSysMock
	 * @return
	 */
	public int updateIfSysMock(IfSysMock ifSysMock);
	/**
	 * 
	 * Title: deleteIfSysMockById<br/>
	 * Description: 根据ID删除模拟数据 <br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:51:18
	 *
	 * @param id
	 * @return
	 */
	public int deleteIfSysMockById(int id);
	/**
	 * 
	 * Title: deleteIfSysMockByIfId<br/>
	 * Description:  根据IF_ID删除模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:51:43
	 *
	 * @param id
	 * @return
	 */
	public int deleteIfSysMockByIfId(int id);
}
