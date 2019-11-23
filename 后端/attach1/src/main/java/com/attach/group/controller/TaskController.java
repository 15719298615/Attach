package com.attach.group.controller;

import com.attach.group.pojo.Groups;
import com.attach.group.pojo.Task;
import com.attach.group.service.TaskService;
import com.attach.sign_in.commons.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TaskController {
    @Resource
    private TaskService taskServiceImpl;
    @RequestMapping(value ="create-group" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String create_group(Groups groups){
        try {
            return taskServiceImpl.create_group(groups);
        }catch (Exception e){
            return JsonUtils.objectToJson("status:[fail]");
        }
    }

    @RequestMapping("create-task")
    @ResponseBody
    public String create_task(Task task){
        return taskServiceImpl.create_task(task);
    }

    @RequestMapping("is-his")
    @ResponseBody
    public String is_his(Integer userId,Integer groupId){
        return taskServiceImpl.is_his(userId,groupId);
    }



    @RequestMapping(value ="join-group" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String join_group(Integer userId,String groupNumber,String groupPassowrd){
        return taskServiceImpl.join_group(userId,groupNumber,groupPassowrd);
    }
    @RequestMapping(value ="get-my-group" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_my_group(Integer userId){
        return taskServiceImpl.get_my_group(userId);
    }
    @RequestMapping("finish-task")
    @ResponseBody
    public String finish_task(Integer userId,Integer taskId){
        return taskServiceImpl.finish_task(userId,taskId);
    }
    @RequestMapping("unfinish-task")
    @ResponseBody
    public String unfinish_task(Integer userId,Integer taskId){
        return taskServiceImpl.unfinish_task(userId,taskId);
    }
    @RequestMapping(value ="member-detail" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String member_detail(Integer userId, Integer groupId, HttpServletRequest req, HttpServletResponse resp){
        return taskServiceImpl.member_detail(userId,groupId,req,resp);
    }


    @RequestMapping(value ="get-task-detail" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_task_detail(Integer groupId){
        return taskServiceImpl.get_task_detail(groupId);
    }

    @RequestMapping(value ="get-member" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_member(Integer groupId){
        return taskServiceImpl.get_member(groupId);
    }

}
