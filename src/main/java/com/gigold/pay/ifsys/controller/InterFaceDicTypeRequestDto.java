/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.ifsys.bo.InterFaceDicType;

public class InterFaceDicTypeRequestDto extends RequestDto {
    private InterFaceDicType interFaceDicType;

    /**
     * @return the interFaceDicType
     */
    public InterFaceDicType getInterFaceDicType() {
        return interFaceDicType;
    }

    /**
     * @param interFaceDicType the interFaceDicType to set
     */
    public void setInterFaceDicType(InterFaceDicType interFaceDicType) {
        this.interFaceDicType = interFaceDicType;
    }



}
