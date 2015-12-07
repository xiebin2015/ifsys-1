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
	 * @return the ifSysMockDao
	 */
	public IfSysMockDAO getIfSysMockDao() {
		return ifSysMockDao;
	}

	/**
	 * @param ifSysMockDao the ifSysMockDao to set
	 */
	public void setIfSysMockDao(IfSysMockDAO ifSysMockDao) {
		this.ifSysMockDao = ifSysMockDao;
	}

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
	 * Title: getMockInfoByIfId<br/>
	 * Description: 根据接口ID获取所有测试信息<br/>
	 * @author xiebin
	 * @date 2015年12月1日下午3:02:46
	 *
	 * @param ifId
	 * @return
	 */
	public List<IfSysMock> getMockInfoByIfId(int ifId){
		List<IfSysMock> list =null;
		try {
			list = ifSysMockDao.getMockInfoByIfId(ifId);
		} catch (Exception e) {
			list=null;
		}
		return list;
	}
	/**
	 * 
	 * Title: updateIfSysMock<br/>
	 * Description:修改模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:54:05
	 *
	 * @param ifSysMock
	 * @return
	 */
	public boolean updateIfSysMock(IfSysMock ifSysMock){
		boolean flag=false;
		try {
			int count = 0;
			int id=ifSysMock.getId();
			if(id<=0){
				count=ifSysMockDao.addIfSysMock(ifSysMock);
			}else{
				count=ifSysMockDao.updateIfSysMock(ifSysMock);
			}
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	/**
	 * 
	 * Title: deleteIfSysMockById<br/>
	 * Description: 根据ID删除模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:56:13
	 *
	 * @param id
	 * @return
	 */
	public boolean  deleteIfSysMockById(int id){
		boolean flag=false;
		try {
			int count = ifSysMockDao.deleteIfSysMockById(id);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	/**
	 * 
	 * Title: deleteIfSysMockByIfId<br/>
	 * Description: 根据IF_ID删除模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:56:31
	 *
	 * @param ifId
	 * @return
	 */
    public boolean  deleteIfSysMockByIfId(int ifId){
    	boolean flag=false;
		try {
			int count = ifSysMockDao.deleteIfSysMockByIfId(ifId);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

}
