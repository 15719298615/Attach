package com.attach.sign_in.commons.pojo;

import java.util.Arrays;
import java.util.Date;

public class SignDetailResult {
    private int SignInNum;
    private int TotalNum;
    private Date[] EffectiveDateList;
    private Date[] TotalDateList;

    public int getSignInNum() {
        return SignInNum;
    }

    public void setSignInNum(int signInNum) {
        SignInNum = signInNum;
    }

    public int getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(int totalNum) {
        TotalNum = totalNum;
    }

    public Date[] getEffectiveDateList() {
        return EffectiveDateList;
    }

    public void setEffectiveDateList(Date[] effectiveDateList) {
        EffectiveDateList = effectiveDateList;
    }

    public Date[] getTotalDateList() {
        return TotalDateList;
    }

    public void setTotalDateList(Date[] totalDateList) {
        TotalDateList = totalDateList;
    }
}
