package com.attach.group.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserDayTask implements Serializable {
    private Integer id;

    private Integer taskId;

    private Integer userId;

    private Date time;

    private Byte complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Byte getComplete() {
        return complete;
    }

    public void setComplete(Byte complete) {
        this.complete = complete;
    }
}