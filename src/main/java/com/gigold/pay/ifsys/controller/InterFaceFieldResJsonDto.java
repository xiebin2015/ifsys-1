/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;

public class InterFaceFieldResJsonDto extends ResponseDto {
    private String jsonStr;

    /**
     * @return the jsonStr
     */
    public String getJsonStr() {
        return jsonStr;
    }

    /**
     * @param jsonStr the jsonStr to set
     */
    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

}
