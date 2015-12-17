package com.gigold.pay.ifsys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.dao.InterFaceFieldDao;

@Service
public class InterFaceFieldService extends Domain {

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
    InterFaceFieldDao interFaceFieldDao;
    
    
    

    /**
	 * @return the interFaceFieldDao
	 */
	public InterFaceFieldDao getInterFaceFieldDao() {
		return interFaceFieldDao;
	}

	/**
	 * @param interFaceFieldDao the interFaceFieldDao to set
	 */
	public void setInterFaceFieldDao(InterFaceFieldDao interFaceFieldDao) {
		this.interFaceFieldDao = interFaceFieldDao;
	}
    /**
     * 
     * Title: getFirstReqFieldByIfId<br/>
     * Description: <br/>
     * @author xiebin
     * @date 2015年12月17日下午3:13:54
     *
     * @param interFaceField
     * @return
     */
	public List<InterFaceField>  getFirstReqFieldByIfId(InterFaceField interFaceField) {
		List<InterFaceField> list=null;
		try{
			list=interFaceFieldDao.getFirstReqFieldByIfId(interFaceField);
		}catch(Exception e){
			debug("调用 interFaceFieldDao.getFirstReqFieldByIfId 出现异常");
		}
        return list;
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
            interFaceField.setLevel(ifield.getLevel()+new Date().getTime()+"-"+ifield.getId()+"-");
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
