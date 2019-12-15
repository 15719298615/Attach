package com.attach.sign_in.commons.pojo;

import java.util.List;
import java.util.Map;

public class SignInDetailListResult {
    private String day;
    private List<String> signInList;
    private List<String> UnsignInList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getSignInList() {
        return signInList;
    }

    public void setSignInList(List<String> signInList) {
        this.signInList = signInList;
    }

    public List<String> getUnsignInList() {
        return UnsignInList;
    }

    public void setUnsignInList(List<String> unsignInList) {
        UnsignInList = unsignInList;
    }
}
