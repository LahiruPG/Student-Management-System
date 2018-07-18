/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.core.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LahiruPG
 */
public class PaymentDTO extends SuperDTO{
    private String pid;
    private String Rid;
    private String cid;
    private BigDecimal amount;
    private String Date;

    public PaymentDTO() {
    }

    public PaymentDTO(String pid, String Rid, String cid, BigDecimal amount, String Date) {
        this.pid = pid;
        this.Rid = Rid;
        this.cid = cid;
        this.amount = amount;
        this.Date = Date;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the Rid
     */
    public String getRid() {
        return Rid;
    }

    /**
     * @param Rid the Rid to set
     */
    public void setRid(String Rid) {
        this.Rid = Rid;
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
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    
    
}
