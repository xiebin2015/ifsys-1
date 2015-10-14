package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.dao.InterFaceProDao;

@Service
public class InterFaceProService {

    @Autowired
    InterFaceProDao InterFaceProDao;

    public List<InterFacePro> getAllProInfo() {
        return InterFaceProDao.getAllProInfo();
    }

    public InterFacePro getProInfoById(InterFacePro interFacePro) {
        return InterFaceProDao.getProInfoById(interFacePro);
    }

}
