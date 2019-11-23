package com.attach.sign_in.pojo;

import java.io.Serializable;
import java.util.Date;

public class SignIn implements Serializable {
    private Integer id;

    private Integer signInId;

    private String signInPassword;

    private String signInName;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private Double siteLo;

    private Double siteLa;

    private Integer maxNumber;

    private Byte effective;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignInId() {
        return signInId;
    }

    public void setSignInId(Integer signInId) {
        this.signInId = signInId;
    }

    public String getSignInPassword() {
        return signInPassword;
    }

    public void setSignInPassword(String signInPassword) {
        this.signInPassword = signInPassword == null ? null : signInPassword.trim();
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName == null ? null : signInName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getSiteLo() {
        return siteLo;
    }

    public void setSiteLo(Double siteLo) {
        this.siteLo = siteLo;
    }

    public Double getSiteLa() {
        return siteLa;
    }

    public void setSiteLa(Double siteLa) {
        this.siteLa = siteLa;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Byte getEffective() {
        return effective;
    }

    public void setEffective(Byte effective) {
        this.effective = effective;
    }
}