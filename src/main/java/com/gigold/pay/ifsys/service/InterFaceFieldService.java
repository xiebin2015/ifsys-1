package com.gigold.pay.ifsys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.dao.InterFaceFieldDao;

@Service
public class InterFaceFieldService {

    @Autowired
    InterFaceFieldDao interFaceFieldDao;

    public List<InterFaceField>  getFirstReqFieldByIfId(InterFaceField interFaceField) {
        return interFaceFieldDao.getFirstReqFieldByIfId(interFaceField);
    }

    public List<InterFaceField>  getFirstResFieldByIfId(InterFaceField interFaceField){
        return interFaceFieldDao.getFirstResFieldByIfId(interFaceField);
    }
    
    public List<InterFaceField> getFieldByparentId(InterFaceField interFaceField){
        return interFaceFieldDao.getFieldByparentId(interFaceField);
    }
    public  List<InterFaceField> getFieldByIfId(InterFaceField interFaceField){
        return interFaceFieldDao.getFieldByIfId(interFaceField);
    }
    
    public boolean addInterFaceField(InterFaceField interFaceField){
        List<InterFaceField> list=interFaceFieldDao.getFieldById(interFaceField);
        if(list!=null&&list.size()>0){
            InterFaceField ifield=list.get(0);
            interFaceField.setLevel(ifield.getLevel()+ifield.getId()+"-");
        }else{
            
            interFaceField.setLevel(interFaceField.getParentId()+"-"+new Date().getTime()+"-");
        }
        return interFaceFieldDao.addInterFaceField(interFaceField);
    }
    
    
    public boolean deleteFieldByLevel(InterFaceField interFaceField){
        
           return interFaceFieldDao.deleteFieldByLevel(interFaceField);
    }
    
    
    public boolean deleteFieldByParentId(InterFaceField interFaceField){
         return  interFaceFieldDao.deleteFieldByLevel(interFaceField);
    }
    public boolean updateInterFaceField(InterFaceField interFaceField){
        return interFaceFieldDao.updateInterFaceField(interFaceField);
    }
    
    
    
}
