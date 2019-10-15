package com.attach.sign_in.commons.pojo;

import com.attach.sign_in.pojo.SignIn;

public class signInResult extends SignIn {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
