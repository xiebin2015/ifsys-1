/**
 * Title: IfSysMockService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.dao.IfSysMockDAO;

/**
 * Title: IfSysMockService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:22:09
 *
 */
@Service
public class IfSysMockService {

	@Autowired
	IfSysMockDAO ifSysMockDao;
  /**
   * 
   * Title: addIfSysMock<br/>
   * Description: 新增接口测试数据<br/>
   * @author xiebin
   * @date 2015年11月30日上午11:16:44
   *
   * @param ifSysMock
   * @return
   */
	public boolean addIfSysMock(IfSysMock ifSysMock) {
		boolean flag = false;
		try {
			int count = ifSysMockDao.addIfSysMock(ifSysMock);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}
	/**
	 * 
	 * Title: getIfSysMock<br/>
	 * Description: 获取所有的测试数据<br/>
	 * @author xiebin
	 * @date 2015年11月30日上午11:22:45
	 *
	 * @return
	 */
	public List<Map<String,Object>> getIfSysMock(){
		List<Map<String,Object>> list =null;
		try {
			list = ifSysMockDao.getIfSysMock();
		} catch (Exception e) {
			list=null;
		}
		return list;
	}
	
	

}
