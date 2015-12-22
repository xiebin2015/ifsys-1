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
}
