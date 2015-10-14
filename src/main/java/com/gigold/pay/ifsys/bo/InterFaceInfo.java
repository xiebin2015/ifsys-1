package com.gigold.pay.ifsys.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InterFaceInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String ifName;
    private String ifDesc;
    private int uid;
    private Timestamp ifCreateTime;
    private int ifSysId;
    private int ifCreateBy;
    private int ifProId;
    private String ifRequest;
    private String ifResponseSuccuss;
    private String ifResponseFailure;
    private String ifStatus;
    private String ifType;
    private String ifProtocol;
     
    private InterFaceSysTem interFaceSysTem;
    private InterFacePro interFacePro;
    private UserInfo userInfo;
    private List<InterFaceField> fieldInfoList;
    

    /**
     * @return the fieldInfoList
     */
    public List<InterFaceField> getFieldInfoList() {
        return fieldInfoList;
    }

    /**
     * @param fieldInfoList the fieldInfoList to set
     */
    public void setFieldInfoList(List<InterFaceField> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    /**
     * @return the interFacePro
     */
    public InterFacePro getInterFacePro() {
        return interFacePro;
    }

    /**
     * @param interFacePro the interFacePro to set
     */
    public void setInterFacePro(InterFacePro interFacePro) {
        this.interFacePro = interFacePro;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the interFaceSysTem
     */
    public InterFaceSysTem getInterFaceSysTem() {
        return interFaceSysTem;
    }

    /**
     * @param interFaceSysTem the interFaceSysTem to set
     */
    public void setInterFaceSysTem(InterFaceSysTem interFaceSysTem) {
        this.interFaceSysTem = interFaceSysTem;
    }

    /**
     * @return the ifType
     */
    public String getIfType() {
        return ifType;
    }

    /**
     * @param ifType the ifType to set
     */
    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    /**
     * @return the ifProtocol
     */
    public String getIfProtocol() {
        return ifProtocol;
    }

    /**
     * @param ifProtocol the ifProtocol to set
     */
    public void setIfProtocol(String ifProtocol) {
        this.ifProtocol = ifProtocol;
    }

    public int getIfCreateBy() {
        return ifCreateBy;
    }

    public void setIfCreateBy(int ifCreateBy) {
        this.ifCreateBy = ifCreateBy;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getIfDesc() {
        return ifDesc;
    }

    public void setIfDesc(String ifDesc) {
        this.ifDesc = ifDesc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Timestamp getIfCreateTime() {
        return ifCreateTime;
    }

    public void setIfCreateTime(Timestamp ifCreateTime) {
        this.ifCreateTime = ifCreateTime;
    }

    public int getIfSysId() {
        return ifSysId;
    }

    public void setIfSysId(int ifSysId) {
        this.ifSysId = ifSysId;
    }

    public int getIfProId() {
        return ifProId;
    }

    public void setIfProId(int ifProId) {
        this.ifProId = ifProId;
    }

    public String getIfRequest() {
        return ifRequest;
    }

    public void setIfRequest(String ifRequest) {
        this.ifRequest = ifRequest;
    }

    public String getIfResponseSuccuss() {
        return ifResponseSuccuss;
    }

    public void setIfResponseSuccuss(String ifResponseSuccuss) {
        this.ifResponseSuccuss = ifResponseSuccuss;
    }

    public String getIfResponseFailure() {
        return ifResponseFailure;
    }

    public void setIfResponseFailure(String ifResponseFailure) {
        this.ifResponseFailure = ifResponseFailure;
    }

    public String getIfStatus() {
        return ifStatus;
    }

    public void setIfStatus(String ifStatus) {
        this.ifStatus = ifStatus;
    }

    public String getIfUrl() {
        return ifUrl;
    }

    public void setIfUrl(String ifUrl) {
        this.ifUrl = ifUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private String ifUrl;

}
