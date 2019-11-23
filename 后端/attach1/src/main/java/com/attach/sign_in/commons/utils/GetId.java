package com.attach.sign_in.commons.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Random random = new Random();
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

    /**
     * 获取现在时间
     * @return
     */
    public static Date getNowTime(){
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        return date;
    }

    /**
     * 获取两天之间间隔
     * @return
     */
    public static int getTimeDistance(Date beginDate , Date endDate ) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int)((endTime - beginTime) / (1000 * 60 * 60 *24));//先算出两时间的毫秒数之差大于一天的天数

        endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);//使endCalendar减去这些天数，将问题转换为两时间的毫秒数之差不足一天的情况
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);//再使endCalendar减去1天
        if(beginCalendar.get(Calendar.DAY_OF_MONTH)==endCalendar.get(Calendar.DAY_OF_MONTH))//比较两日期的DAY_OF_MONTH是否相等
            return betweenDays + 1;    //相等说明确实跨天了
        else
            return betweenDays + 0;    //不相等说明确实未跨天
    }

    /**
     * 把时间前后推
     * @param beginDate 开始时间
     * @param day 天数
     * @return
     */
    public static Date addDate(Date beginDate,int day){
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(beginDate);
        calendar.add(calendar.DATE,day); //把日期往后增加day天,整数  往后推,负数往前移动
        beginDate=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return beginDate;
    }
}
