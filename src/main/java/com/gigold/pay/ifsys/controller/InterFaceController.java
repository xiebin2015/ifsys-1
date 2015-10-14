package com.gigold.pay.ifsys.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.log.DomainLogger;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class InterFaceController extends BaseController{
    @Autowired
    InterFaceService interFaceService;

    /**
     * 根据Id获取接口明细信息
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryInterFaceById")
    public @ResponseBody InterFaceResponseDto getInterFaceById(@RequestBody InterFaceRequestDto qdto) {
        debug("调用getInterFaceById：");
        InterFaceInfo interFaceInfo = interFaceService.getInterFaceById(qdto.getInterFaceInfo());
        InterFaceResponseDto dto = new InterFaceResponseDto();
       if (interFaceInfo != null) {
            dto.setInterFaceInfo(interFaceInfo);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }
        return dto;
    }

    /**
     * 根据名称模糊查询
     * 
     * @param interFaceInfo
     * @return
     */
    @RequestMapping("/fuzzyQuery")
    public @ResponseBody InterFacePageResponseDto fuzzyQuery(@RequestBody InterFaceFuzzyQueryRequestDto qdto) {
        debug("调用fuzzyQuery：");
        PageHelper.startPage(qdto.getPageInfo().getPageNum(),qdto.getPageInfo().getPageSize());
        List<InterFaceInfo> list = interFaceService.fuzzyQuery(qdto.getInterFaceInfo());
        debug("传入的参数：" + qdto.getPageInfo().getPageNum() + "====" + qdto.getInterFaceInfo().getIfName());
        InterFacePageResponseDto dto = new InterFacePageResponseDto();
        if (list != null) {
            PageInfo<InterFaceInfo> pageInfo = new PageInfo<InterFaceInfo>(list);
            dto.setPageInfo(pageInfo);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }
        return dto;

    }

    /**
     * 分页查询
     * 
     * @param qdto
     * @return
     */

    @RequestMapping("/getInterFaceByPage")
    public @ResponseBody InterFacePageResponseDto getAllInterFace(@RequestBody InterFacePageRequestDto qdto) {
        debug("调用getInterFaceByPage：");
        PageHelper.startPage(qdto.getPageInfo().getPageNum(),qdto.getPageInfo().getPageSize());
        List<InterFaceInfo> list = interFaceService.getAllInterFace();
        debug("传入的参数：" + qdto.getPageInfo().getPageNum() + "====" + qdto.getPageInfo().getPageSize());
        InterFacePageResponseDto dto = new InterFacePageResponseDto();
        if (list != null) {
            PageInfo<InterFaceInfo> pageInfo = new PageInfo<InterFaceInfo>(list);
            dto.setPageInfo(pageInfo);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }
        return dto;

    }

    /**
     * 新增接口纪录
     * 
     * @param interFaceInfo
     * @return
     */

    @RequestMapping("/addInterface")
    public @ResponseBody InterFaceResponseDto addInterface(@RequestBody InterFaceRequestDto qdto,HttpSession session) {
        InterFaceInfo interFaceInfo=qdto.getInterFaceInfo();
        UserInfo user=(UserInfo)session.getAttribute("userInfo");
        interFaceInfo.setIfCreateBy(user.getId());
        interFaceInfo.setIfCreateTime(new Timestamp((new Date()).getTime()));
        boolean flag = interFaceService.addInterFace(interFaceInfo);
        InterFaceResponseDto dto = new InterFaceResponseDto();
        if (flag) {
            dto.setRspCd(SysCode.SUCCESS);
            dto.setInterFaceInfo(interFaceInfo);

        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }

        return dto;

    }

    /**
     * 删除接口
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteInterFaceById")
    public @ResponseBody InterFaceResponseDto deleteInterFace(@RequestBody InterFaceRequestDto qdto) {
        boolean flag = interFaceService.deleteInterFaceById(qdto.getInterFaceInfo());
        InterFaceResponseDto dto = new InterFaceResponseDto();
        if (flag) {
            dto.setRspCd(SysCode.SUCCESS);

        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }
        return dto;

    }

    /**
     * 修改接口
     * 
     * @param interFaceInfo
     * @return
     */
    @RequestMapping("/updateInterFace")
    public @ResponseBody InterFaceResponseDto updateInterFace(@RequestBody InterFaceRequestDto qdto) {
        InterFaceInfo interFaceInfo=qdto.getInterFaceInfo();
        boolean flag = interFaceService.updateInterFace(interFaceInfo);
        InterFaceResponseDto dto = new InterFaceResponseDto();
        if (flag) {
            dto.setRspCd(SysCode.SUCCESS);   
            dto.setInterFaceInfo(interFaceInfo);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }
        return dto;

    }

}
