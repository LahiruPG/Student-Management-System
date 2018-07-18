/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.core.dto;

import java.math.BigDecimal;

/**
 *
 * @author LahiruPG
 */
public class CourseDTO extends SuperDTO{
    private String cid;
    private String name;
    private BigDecimal fee;
    private String duration;

    public CourseDTO() {
    }

    public CourseDTO(String cid, String name, BigDecimal fee, String duration) {
        this.cid = cid;
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
}
