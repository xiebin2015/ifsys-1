package com.gigold.pay.ifsys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.InterFaceDicType;
import com.gigold.pay.ifsys.service.InterFaceDicTypeService;

@Controller
public class InterFaceDicTypeController extends BaseController {
    @Autowired
    InterFaceDicTypeService interFaceDicTypeService;
    @RequestMapping(value = "/getDicInfoByIfId.do")
    public @ResponseBody InterFaceDicTypeResponseDto getDicInfoByIfId( @RequestBody InterFaceDicTypeRequestDto qdto) {
        InterFaceDicType interFaceDicType = qdto.getInterFaceDicType();
        InterFaceDicTypeResponseDto dto = new InterFaceDicTypeResponseDto();
        List<InterFaceDicType> dicTypeList = new ArrayList<InterFaceDicType>();
        interFaceDicType = interFaceDicTypeService.getDicInfoById(interFaceDicType);
        if (interFaceDicType != null) {
            dicTypeList.add(interFaceDicType);
            dto.setList(dicTypeList);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }

        return dto;

    }
    
    

    @RequestMapping(value = "/getAllDicInfo.do")
    public @ResponseBody InterFaceDicTypeResponseDto getAllDicInfo( ) {
        InterFaceDicTypeResponseDto dto = new InterFaceDicTypeResponseDto();
        List<InterFaceDicType> dicTypeList= interFaceDicTypeService.getAllDicInfo();
        if (dicTypeList != null) {
            dto.setList(dicTypeList);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }

        return dto;

    }

}
