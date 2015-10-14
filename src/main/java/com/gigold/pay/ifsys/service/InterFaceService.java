package com.gigold.pay.ifsys.service;

import java.util.List;

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

    public List<InterFaceInfo> getInterFaceByProId(int id) {
        return null;
    }

    public List<InterFaceInfo> fuzzyQuery(InterFaceInfo interFaceInfo) {

        return interFaceDao.getInterFace(interFaceInfo);
    }

    public List<InterFaceInfo> getAllInterFace() {

        return interFaceDao.getAllInterFace();
    }

    public boolean addInterFace(InterFaceInfo interFaceInfo) {
        return interFaceDao.addInterFace(interFaceInfo);
    }

    public boolean deleteInterFaceById(InterFaceInfo interFaceInfo) {
        return interFaceDao.deleteInterFaceById(interFaceInfo);
    }

    public boolean updateInterFace(InterFaceInfo interFaceInfo) {
        boolean flag = interFaceDao.updateInterFace(interFaceInfo);
        return flag;
    }

}
