package com.attach.learn.pojo;

public class DayLearningTime {
    private Integer id;

    private Integer userId;

    private String today;

    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today == null ? null : today.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}