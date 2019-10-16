package com.attach.group.controller;

import com.attach.group.pojo.Groups;
import com.attach.group.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TaskController {
    @Resource
    private TaskService taskServiceImpl;
    @RequestMapping(value ="create-group" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String create_group(Groups groups){
        return taskServiceImpl.create_group(groups);
    }

    @RequestMapping(value ="join-group" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String join_group(Integer userId,String groupNumber,String groupPassowrd){
        return taskServiceImpl.join_group(userId,groupNumber,groupPassowrd);
    }

}
