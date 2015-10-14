package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.dao.InterFaceSystemDao;

@Service
public class InterFaceSysService {

    @Autowired
    InterFaceSystemDao interFaceSystemDao;

    public List<InterFaceSysTem> getAllSysInfo() {
        return interFaceSystemDao.getAllSysInfo();
    }

    public InterFaceSysTem getSysInfoById(InterFaceSysTem interFaceSystem) {
        return interFaceSystemDao.getSysInfoById(interFaceSystem);
    }

}
