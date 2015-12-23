/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.IfSysRefer;

/**
 * Title: IfSysMockDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysReferDAO {
	/**
	 * 
	 * Title: getReferList<br/>
	 * Description: 获取测试用例的依赖列表 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日下午5:25:59
	 *
	 * @param ifId
	 * @return
	 */
	public List<IfSysRefer> getReferList(int mockId);

}
