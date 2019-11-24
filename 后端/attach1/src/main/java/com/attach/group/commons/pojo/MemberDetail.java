package com.attach.group.commons.pojo;

import java.util.Date;

public class MemberDetail {
    private Double totalComplete;
    private int[][] arr;
    private int taskNum;
    private int dayNum;
    private Date startDate;

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getTotalComplete() {
        return totalComplete;
    }

    public void setTotalComplete(Double totalComplete) {
        this.totalComplete = totalComplete;
    }

    public int[][] getArr() {
        return arr;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }
}
