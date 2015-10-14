package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.InterFaceDicType;
import com.gigold.pay.ifsys.dao.InterFaceDicTypeDao;

@Service
public class InterFaceDicTypeService {

    @Autowired
    InterFaceDicTypeDao interFaceDicTypeDao;

    public List<InterFaceDicType> getAllDicInfo() {
        return interFaceDicTypeDao.getAllDicInfo();
    }

    public InterFaceDicType getDicInfoById(InterFaceDicType interFaceDicType) {
        return interFaceDicTypeDao.getDicInfoById(interFaceDicType);
    }

}
