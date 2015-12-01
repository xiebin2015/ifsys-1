package com.gigold.pay.ifsys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.dao.InterFaceDao;

@Service
public class InterFaceService {

    @Autowired
    InterFaceDao interFaceDao;

    /**
     * 
     * Title: getInterFaceById<br/>
     * Description:根据ID查询接口信息<br/>
     * 
     * @author xb
     * @date 2015年10月8日上午11:12:48
     *
     * @param id
     * @return
     */
    public InterFaceInfo getInterFaceById(InterFaceInfo interFaceInfo) {
        return interFaceDao.getInterFaceById(interFaceInfo);
    }

    /**
     * 
     * Title: getInterFaceByProId<br/>
     * 根据产品Id查询接口: <br/>
     * @author xb
     * @date 2015年10月15日下午12:45:41
     *
     * @param id
     * @return
     */
    public List<InterFaceInfo> getInterFaceByProId(int id) {
        return null;
    }

    /**
     * 
     * Title: getAllInterFace<br/>
     * 分页获取所有接口信息: <br/>
     * @author xb
     * @date 2015年10月15日下午12:46:05
     *
     * @return
     */
    public List<InterFaceInfo> getAllInterFaceByPage() {

        return interFaceDao.getAllInterFaceByPage();
    }
    
    
    public List<InterFaceInfo> queryInterFaceByPage(InterFaceInfo interFaceInfo) {

        return interFaceDao.queryInterFaceByPage(interFaceInfo);
    }
    
    
    
    /**
     * 
     * Title: addInterFace<br/>
     * 添加接口: <br/>
     * @author xb
     * @date 2015年10月15日下午12:46:44
     *
     * @param interFaceInfo
     * @return
     */
    public boolean addInterFace(InterFaceInfo interFaceInfo) {
        return interFaceDao.addInterFace(interFaceInfo);
    }
   /**
    * 
    * Title: deleteInterFaceById<br/>
    * 删除接口: <br/>
    * @author xb
    * @date 2015年10月15日下午12:47:06
    *
    * @param interFaceInfo
    * @return
    */
    public boolean deleteInterFaceById(InterFaceInfo interFaceInfo) {
        return interFaceDao.deleteInterFaceById(interFaceInfo);
    }
    /**
     * 
     * Title: updateInterFace<br/>
     * 修改接口: <br/>
     * @author xb
     * @date 2015年10月15日下午12:47:26
     *
     * @param interFaceInfo
     * @return
     */
    public boolean updateInterFace(InterFaceInfo interFaceInfo) {
        boolean flag = interFaceDao.updateInterFace(interFaceInfo);
        return flag;
    }

}
