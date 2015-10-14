package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceDicType;

public class InterFaceDicTypeResponseDto extends ResponseDto {

    private List<InterFaceDicType> list;

    /**
     * @return the dicTypeList
     */
    public List<InterFaceDicType> getList() {
        return list;
    }

    /**
     * @param dicTypeList the dicTypeList to set
     */
    public void setList(List<InterFaceDicType> list) {
        this.list = list;
    }

    

}
