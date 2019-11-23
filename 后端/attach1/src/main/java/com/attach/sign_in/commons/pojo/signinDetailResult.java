package com.attach.sign_in.commons.pojo;

import com.attach.sign_in.pojo.User;

import java.util.Arrays;
import java.util.List;

public class signinDetailResult {

    private  String[] sign_in_detail_list;
    private double sign_in_rate;      //总签到率
    private int[] everyday_number;   //周期内每天签到次数列表

    public int[] getEveryday_number() {
        return everyday_number;
    }

    public void setEveryday_number(int[] everyday_number) {
        this.everyday_number = everyday_number;
    }

    public String[] getSign_in_detail_list() {
        return sign_in_detail_list;
    }

    public void setSign_in_detail_list(String[] sign_in_detail_list) {
        this.sign_in_detail_list = sign_in_detail_list;
    }

    public double getSign_in_rate() {
        return sign_in_rate;
    }

    public void setSign_in_rate(double sign_in_rate) {
        this.sign_in_rate = sign_in_rate;
    }

}
