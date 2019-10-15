package com.attach.group.pojo;

import java.util.Date;

public class Group {
    private Integer id;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private String groupName;

    private String groupPassword;

    private Byte effective;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupPassword() {
        return groupPassword;
    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword == null ? null : groupPassword.trim();
    }

    public Byte getEffective() {
        return effective;
    }

    public void setEffective(Byte effective) {
        this.effective = effective;
    }
}