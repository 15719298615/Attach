package com.attach.sign_in.commons.utils;

import java.util.Random;

public class GetId {
    /**
     * 生成l位的String类型的字符串。
     * @param l
     * @return pwd
     */
    public static String getSignInPassword(int l) {
        String pwd = "";
        int number = 0;
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < l; i++) {
            number = Math.abs(random.nextInt()) % 10;
            pwd += number;
        }
        return pwd;
    }

    /**
     * 生成8位的房间号。
     * @return id
     */
    public static int  getSigInId() {
       int id = (int) ((Math.random()*9+1)*10000000);
        return id;
    }
}
