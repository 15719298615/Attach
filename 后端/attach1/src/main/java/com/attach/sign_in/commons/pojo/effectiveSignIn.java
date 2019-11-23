package com.attach.sign_in.commons.pojo;

import java.util.Date;

public class effectiveSignIn {
    private Integer signInId;
    private String signInName;
    private Date signInStartTime;
    private Date signInEndTime;

    public Integer getSignInId() {
        return signInId;
    }

    public void setSignInId(Integer signInId) {
        this.signInId = signInId;
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public Date getSignInStartTime() {
        return signInStartTime;
    }

    public void setSignInStartTime(Date signInStartTime) {
        this.signInStartTime = signInStartTime;
    }

    public Date getSignInEndTime() {
        return signInEndTime;
    }

    public void setSignInEndTime(Date signInEndTime) {
        this.signInEndTime = signInEndTime;
    }
}
