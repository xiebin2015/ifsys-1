/**
 * Title: IfSysMockHistoryDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import com.gigold.pay.autotest.bo.IfSysMockHistory;
import java.util.List;
/**
 * Title: IfSysMockHistoryDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月16日下午1:20:48
 *
 */
public interface IfSysMockHistoryDAO {
	/**
	 * 
	 * Title: addIfSysMockHistory<br/>
	 * Description: 添加测试历史数据记录<br/>
	 * @author xiebin
	 * @date 2015年12月16日下午1:33:17
	 *
	 * @param ifSysMockHistory
	 * @return
	 */
   	public int addIfSysMockHistory(IfSysMockHistory ifSysMockHistory);

	/**
	 *
	 * Title: addIfSysMockHistory<br/>
	 * Description: 添加测试历史数据记录<br/>
	 * @author xiebin
	 * @date 2015年12月16日下午1:33:17
	 *
	 * @param
	 * @return
	 */
	public List<IfSysMockHistory> getNewestReslutOf(int limit);
}
