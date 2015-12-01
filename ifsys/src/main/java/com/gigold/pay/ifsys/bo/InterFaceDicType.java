package com.gigold.pay.ifsys.bo;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Title: InterFaceDicType<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xb
 * @date 2015年10月12日下午2:16:38
 *
 */
@Component
@Scope("prototype")
public class InterFaceDicType {
    
    
    
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int id;
    private String value;
    private String name;
    private String desc;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

   


    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
