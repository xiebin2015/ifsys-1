/**
 * Title: RetrunCodeService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.dao.IfSysReferDAO;
import com.gigold.pay.framework.core.Domain;

/**
 * Title: RetrunCodeService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午9:46:14
 *
 */
@Service
public class IfSysReferService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	IfSysReferDAO ifSysReferDAO;
    /**
     * 
     * Title: getReferList<br/>
     * Description: 获取测试用例的依赖列表<br/>
     * @author xiebin
     * @date 2015年12月10日上午10:15:30
     *
     * @param ifId
     * @return
     */
	public List<IfSysRefer> getReferList(int mockId) {
		List<IfSysRefer> list = null;
		try {
			list =ifSysReferDAO.getReferList(mockId);
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferList 数据库发送异常");
		}
		return list;
	}
	
	public IfSysRefer getReferById(int mockId) {
		IfSysRefer ifSysRefer = null;
		try {
			ifSysRefer =ifSysReferDAO.getReferById(mockId);
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferById 数据库发送异常");
		}
		return ifSysRefer;
	}
	
	
	/**
	 * 
	 * Title: getReferList<br/>
	 * Description:新增测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午5:58:00
	 *
	 * @param ifSysRefer
	 * @return
	 */
	public boolean  addMockRefer(IfSysRefer ifSysRefer) {
		boolean flag=false;
		try {
			IfSysRefer ref =ifSysReferDAO.getReferById(ifSysRefer.getId());
			 int count=-1;
			if(ref==null){
				count=	ifSysReferDAO.addMockRefer(ifSysRefer);
			}else{
				ifSysReferDAO.updateMockRefer(ifSysRefer);
			}
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 addMockRefer 数据库发送异常");
		}
		return flag;
	}
	/**
	 * 
	 * Title: deleteMockRefer<br/>
	 * Description: 删除测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:01:13
	 *
	 * @param id
	 * @return
	 */
	public boolean  deleteMockRefer(int id) {
		boolean flag=false;
		try {
			int count =ifSysReferDAO.deleteMockRefer(id);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferList 数据库发送异常");
		}
		return flag;
	}
	/**
	 * 
	 * Title: updateMockRefer<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:25:43
	 *
	 * @param id
	 * @return
	 */
	public boolean  updateMockRefer(IfSysRefer ifSysRefer) {
		boolean flag=false;
		try {
			int count =ifSysReferDAO.updateMockRefer(ifSysRefer);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 updateMockRefer 数据库发送异常");
		}
		return flag;
	}
}
