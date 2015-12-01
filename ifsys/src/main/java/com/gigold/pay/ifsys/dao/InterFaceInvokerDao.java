/**
 * Title: InterFaceInvokerDao.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.dao;

import java.util.List;
import java.util.Map;

import com.gigold.pay.ifsys.bo.InterFaceInvoker;

/**
 * Title: InterFaceInvokerDao<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月23日下午3:16:20
 *
 */
public interface InterFaceInvokerDao {
	/**
	 * 
	 * Title: addInterFaceInvoker<br/>
	 * Description: 新增接口关注者<br/>
	 * @author xiebin
	 * @date 2015年11月23日下午3:21:38
	 *
	 * @param invoker
	 * @return
	 */
	public boolean addInterFaceInvoker(InterFaceInvoker invoker);
	/**
	 * 
	 * Title: getInvokerList<br/>
	 * Description:获取接口关注列表 <br/>
	 * @author xiebin
	 * @date 2015年11月23日下午4:59:00
	 *
	 * @param invoker
	 * @return
	 */
	public List<Map<String,Object>> getInvokerList(InterFaceInvoker invoker);

}
