/**
 * Title: IfSysMockService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.annotation.IfSysMockHistoryAnnotation;
import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.dao.IfSysMockDAO;
import com.gigold.pay.autotest.util.ForMatJSONStr;
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
	 * @param ifSysMockDao
	 *            the ifSysMockDao to set
	 */
	public void setIfSysMockDao(IfSysMockDAO ifSysMockDao) {
		this.ifSysMockDao = ifSysMockDao;
	}

	/**
	 * 
	 * Title: addIfSysMock<br/>
	 * Description: 新增接口测试数据<br/>
	 * 
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
			if (count > 0) {
				flag = true;
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
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午3:02:46
	 *
	 * @param ifId
	 * @return
	 */
	public List<IfSysMock> getMockInfoByIfId(int ifId) {
		List<IfSysMock> list = null;
		try {
			list = ifSysMockDao.getMockInfoByIfId(ifId);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 * 
	 * Title: updateIfSysMock<br/>
	 * Description:修改模拟数据
	 *  如果已经存在则修改
	 *  如果不存在则新增
	 * <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日上午10:54:05
	 *
	 * @param ifSysMock
	 * @return
	 */
	public boolean updateIfSysMock(IfSysMock ifSysMock) {
		boolean flag = false;
		try {
			int count = 0;
			IfSysMock ifMock=ifSysMockDao.getMockInfoById(ifSysMock);
			if (ifMock==null) {
				count = ifSysMockDao.addIfSysMock(ifSysMock);
			} else {
				ifSysMock.setId(ifMock.getId());
				count = ifSysMockDao.updateIfSysMock(ifSysMock);
			}
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * Title: createIfSysMock<br/>
	 * Description:初始化测试数据
	 * <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日上午10:54:05
	 *
	 * @param ifSysMock
	 * @return
	 */
	public boolean createIfSysMock(IfSysMock ifSysMock) {
		boolean flag = false;
		try {
			int count = 0;
			IfSysMock ifMock=getMockInfoByIfIdAndRspCdId(ifSysMock);
			if (ifMock==null) {
				count = ifSysMockDao.addIfSysMock(ifSysMock);
			} 
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	

	/**
	 * 
	 * Title: deleteIfSysMockById<br/>
	 * Description: 根据ID删除模拟数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日上午10:56:13
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteIfSysMockById(int id) {
		boolean flag = false;
		try {
			int count = ifSysMockDao.deleteIfSysMockById(id);
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * Title: deleteIfSysMockByIfId<br/>
	 * Description: 根据IF_ID删除模拟数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日上午10:56:31
	 *
	 * @param ifId
	 * @return
	 */
	public boolean deleteIfSysMockByIfId(int ifId) {
		boolean flag = false;
		try {
			int count = ifSysMockDao.deleteIfSysMockByIfId(ifId);
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * Title: getMockInfoById<br/>
	 * Description: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日上午11:12:43
	 *
	 * @param
	 * @return
	 */
	public IfSysMock getMockInfoById(IfSysMock ifSysMock) {
		IfSysMock ifmock = null;
		try {
			ifmock = ifSysMockDao.getMockInfoById(ifSysMock);
		} catch (Exception e) {
			ifmock = null;
		}
		return ifmock;
	}
	/**
	 * 
	 * Title: getReferByIfId<br/>
	 * Description: 根据接口ID获取依赖接口的测试数据<br/>
	 * @author xiebin
	 * @date 2015年12月10日上午10:26:03
	 *
	 * @param ifId
	 * @return
	 */
	public IfSysMock getReferByIfId(int ifId){
		IfSysMock ifmock = null;
		try {
			ifmock = ifSysMockDao.getReferByIfId(ifId);
		} catch (Exception e) {
			ifmock = null;
		}
		return ifmock;
	}
	
	/**
	 * 
	 * Title: getMockInfoById<br/>
	 * Description: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日上午11:12:43
	 *
	 * @param
	 * @return
	 */
	public IfSysMock getMockInfoByIfIdAndRspCdId(IfSysMock ifSysMock) {
		IfSysMock ifmock = null;
		try {
			ifmock = ifSysMockDao.getMockInfoByIfIdAndRspCdId(ifSysMock);
		} catch (Exception e) {
			ifmock = null;
		}
		return ifmock;
	}
	
	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 根据条件筛选结果数据<br/>
	 * 
	 * @author chenkuan
	 * @date 2015年12月8日上午11:56:31
	 *
	 * @param flag
	 * @return
	 */
	public List<IfSysMock> filterMocksByStatus(String flag) {
		// 筛选数据
		List<IfSysMock> list = null;
		try {
			list = ifSysMockDao.filterMocksByStatus(flag);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 筛选出所有没有通过测试的结果<br/>
	 * 
	 * @author chenkuan
	 * @date 2015年12月8日上午11:56:31
	 *
	 * @return list
	 */
	public List<IfSysMock> filterMocksByFailed() {
		// 筛选数据
		List<IfSysMock> list = null;
		try {
			list = ifSysMockDao.filterMocksByFailed();
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 筛选出所有测试过的结果<br/>
	 * 
	 * @author chenkuan
	 * @date 2015年12月8日上午11:56:31
	 *
	 * @return list
	 */
	public List<IfSysMock> filterAllTestedMocks() {
		// 筛选数据
		List<IfSysMock> list = null;
		try {
			list = ifSysMockDao.filterAllTestedMocks();
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 根据id查询接口的关注列表<br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月11日上午11:56:31
	 *
	 * @return list
	 */
	public List<IfSysMock> getInterfaceFollowShipById(int id) {
		// 筛选数据
		List<IfSysMock> list = null;
		try {
			list = ifSysMockDao.getInterfaceFollowShipById(id);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 * 
	 * Title: writeBackRealRsp<br/>
	 * Description: 根据实际响应报文，回写该数据成功后进行计数<br/>
	 * 
	 * @author chenhl
	 * @date 2015年12月2日上午10:56:31
	 *
	 * @param ifsysmock
	 * @return
	 */
	@IfSysMockHistoryAnnotation("记录测试历史")
	public boolean writeBackRealRsp(IfSysMock ifsysmock) {
		String realResponseJson=ForMatJSONStr.format(ifsysmock.getRealResponseJson());
		ifsysmock.setRealResponseJson(realResponseJson);
		boolean flag = false;
		try {
			int count = ifSysMockDao.writeBack(ifsysmock);
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
