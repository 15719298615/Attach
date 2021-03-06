package com.attach.group.service.impl;

import com.attach.group.commons.pojo.*;
import com.attach.group.commons.utils.GetId;
import com.attach.group.commons.utils.JsonUtils;
import com.attach.group.mapper.*;
import com.attach.group.pojo.*;
import com.attach.group.service.TaskService;
import com.attach.sign_in.mapper.UserMapper;
import com.attach.sign_in.pojo.User;
import com.attach.sign_in.pojo.UserExample;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LogManager.getLogger(TaskServiceImpl.class);
    @Resource
    private GroupsMapper groupsMapper;
    @Resource
    private UserGroupMapper userGroupMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserDayTaskMapper userDayTaskMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public String create_group(Integer userId,String groupName,Long startTime,Long endTime) {
        if (userId != null && groupName.length() > 0 && startTime != null && endTime != null) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if (users == null || users.size() <= 0) {//判断这个人是否存在
                return JsonUtils.objectToJson("fail");
            }
            String group_number = GetId.getSignInPassword(8);
            String group_password = GetId.getSignInPassword(8);
            Groups group1 = new Groups();
            group1.setUserId(userId);
            group1.setGroupName(groupName);
            group1.setGroupNumber(group_number);
            group1.setGroupPassword(group_password);
            Date date = new Date(startTime);
            group1.setStartTime(date);
            date = new Date(endTime);
            group1.setEndTime(date);
            group1.setEffective((byte) 1);
            int index = groupsMapper.insert(group1);
            if (index > 0) {

                UserGroup userGroup = new UserGroup();//插入userGreoup，让他参加
                userGroup.setUserId(userId);
                GroupsExample groupsExample = new GroupsExample();
                groupsExample.createCriteria().andGroupNumberEqualTo(group_number).andGroupPasswordEqualTo(group_password);
                userGroup.setGroupId(groupsMapper.selectByExample(groupsExample).get(0).getId());
                userGroup.setUserId(userId);
                int insert = userGroupMapper.insert(userGroup);

                CreateGroup createGroup = new CreateGroup();
                createGroup.setStatus("success");
                createGroup.setGroup_number(group_number);
                createGroup.setGroup_passowrd(group_password);
                return JsonUtils.objectToJson(createGroup);
            }else {
                return JsonUtils.objectToJson("fail");
            }
        } else {

            return JsonUtils.objectToJson("fail");
        }
    }



    @Override
    public String my_task(Integer groupId,Integer userId) {
        if (groupId==null||userId==null){
            return JsonUtils.objectToJson("fail");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null&&users.size()>0){
            return JsonUtils.objectToJson("fail");
        }

        //判断groupId
        GroupsExample groupsExample = new GroupsExample();
        groupsExample.createCriteria().andIdEqualTo(groupId);
        List<Groups> groups = groupsMapper.selectByExample(groupsExample);
        if (groups==null&&groups.size()<=0){
            return JsonUtils.objectToJson("fail");
        }

        if(groups.get(0).getEndTime().getTime()<GetId.getNowTime().getTime()){
            return JsonUtils.objectToJson("fail");
        }

        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andGroupIdEqualTo(groupId);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        if(tasks==null&&tasks.size()<=0){
            return JsonUtils.objectToJson("fail");
        }

        List<TaskIdAndName> taskIdAndNames = new ArrayList<>();
        for(Task task:tasks){
            TaskIdAndName taskIdAndName = new TaskIdAndName();
            UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
            userDayTaskExample.createCriteria().andTaskIdEqualTo(task.getId()).andUserIdEqualTo(userId).andTimeEqualTo(GetId.getNowTime()).andCompleteEqualTo((byte)1);
            List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
            if(userDayTasks!=null&&userDayTasks.size()>0){//判断今天签到状态
                taskIdAndName.setStatus(true);
            }else {
                taskIdAndName.setStatus(false);
            }
            taskIdAndName.setTaskId(task.getId());
            taskIdAndName.setTaskName(task.getTaskName());
            taskIdAndNames.add(taskIdAndName);
        }

        return JsonUtils.objectToJson(taskIdAndNames);
    }

    @Override
    public String is_his(Integer userId, Integer groupId) {
        if(userId==null||groupId==null){
            return JsonUtils.objectToJson("false");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            GroupsExample groupsExample = new GroupsExample();
            groupsExample.createCriteria().andIdEqualTo(groupId).andUserIdEqualTo(userId);
            List<Groups> groups = groupsMapper.selectByExample(groupsExample);
            if (groups!=null&&groups.size()>0){
                return JsonUtils.objectToJson("true");
            }
            return JsonUtils.objectToJson("false");
        }

        return JsonUtils.objectToJson("false");
    }

    @Override
    public String create_task(Task task) {
        if(task.getGroupId()!=null&&task.getTaskName()!=null&&task.getTaskName().length()>0){
            int index = taskMapper.insert(task);
            if(index>0){
                return "success";
            }else {
                return "fail";
            }
        }
        return "fail";
    }

    @Override
    public String join_group(Integer userId, String groupNumber, String groupPassword) {
        if(userId!=null&&groupNumber!=null&&groupPassword!=null){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }
            GroupsExample example = new GroupsExample();
            example.createCriteria().andGroupNumberEqualTo(groupNumber).andGroupPasswordEqualTo(groupPassword);
            List<Groups> groups = groupsMapper.selectByExample(example);
            if (groups!=null&&groups.size()>0){
                if(groups.get(0).getGroupNumber().equals(groupNumber)){
                    UserGroupExample example1 = new UserGroupExample();
                    example1.createCriteria().andUserIdEqualTo(userId).andGroupIdEqualTo(groups.get(0).getId());
                    List<UserGroup> userGroups = userGroupMapper.selectByExample(example1);
                    if(userGroups.size()>0){
                        return JsonUtils.objectToJson("fail");
                    }
                    UserGroup userGroup = new UserGroup();
                    userGroup.setUserId(userId);
                    userGroup.setGroupId(groups.get(0).getId());
                    int index = userGroupMapper.insert(userGroup);
                    if(index>0){
                        return JsonUtils.objectToJson("success");
                    }else{
                        return JsonUtils.objectToJson("fail");
                    }
                }
            }
            return JsonUtils.objectToJson("fail");
        }

        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_my_group(Integer userId) {

        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.createCriteria().andUserIdEqualTo(userId);
        List<UserGroup> userGroups = userGroupMapper.selectByExample(userGroupExample);
        List<getMyGroup> myGroups = new ArrayList<>();
        if(userGroups!=null&&userGroups.size()>0){
            for(UserGroup userGroup:userGroups){
                GroupsExample groupsExample = new GroupsExample();
                groupsExample.createCriteria().andIdEqualTo(userGroup.getGroupId());
                List<Groups> groups = groupsMapper.selectByExample(groupsExample);
                if(groups!=null&&groups.size()>0){
                    if (groups.get(0).getEndTime().getTime()<GetId.getNowTime().getTime()){
                        continue;
                    }
                    getMyGroup myGroup = new getMyGroup();
                    myGroup.setGroupId(groups.get(0).getId());
                    myGroup.setGroupName(groups.get(0).getGroupName());
                    myGroups.add(myGroup);
                }else {
                    return JsonUtils.objectToJson("fail");
                }
            }
            return JsonUtils.objectToJson(myGroups);
        }

        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String finish_task(Integer userId, Integer taskId) {
        if (userId != null && taskId != null) {//验证userid
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }
            TaskExample taskExample = new TaskExample();
            taskExample.createCriteria().andIdEqualTo(taskId);
            List<Task> tasks = taskMapper.selectByExample(taskExample);
            if(tasks==null||tasks.size()<=0){//验证taskId是否存在
                return JsonUtils.objectToJson("fail");
            }
            GroupsExample groupsExample = new GroupsExample();
            groupsExample.createCriteria().andIdEqualTo(tasks.get(0).getGroupId());
            List<Groups> groups = groupsMapper.selectByExample(groupsExample);
            if (groups==null||groups.size()<=0){//查看是否有这个任务
                return JsonUtils.objectToJson("fail");
            }
            if(GetId.getNowTime().getTime()>groups.get(0).getEndTime().getTime()){
                return JsonUtils.objectToJson("fail");
            }
            UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
            userDayTaskExample.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId).andTimeEqualTo(GetId.getNowTime());
            List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
            if (userDayTasks != null && userDayTasks.size() > 0) {
                userDayTaskExample.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId);
                userDayTasks.get(0).setComplete((byte) 1);
                int index = userDayTaskMapper.updateByExample(userDayTasks.get(0), userDayTaskExample);
                if (index > 0) {
                    return JsonUtils.objectToJson("success");
                } else {
                    return JsonUtils.objectToJson("fail");
                }
            }
            UserDayTask userDayTask = new UserDayTask();
            userDayTask.setTaskId(taskId);
            userDayTask.setUserId(userId);
            userDayTask.setTime(GetId.getNowTime());
            userDayTask.setComplete((byte) 1);
            int index = userDayTaskMapper.insert(userDayTask);
            if (index > 0) {
                return JsonUtils.objectToJson("success");
            } else {
                return JsonUtils.objectToJson("fail");
            }
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String unfinish_task(Integer userId, Integer taskId) {
        if(userId!=null&&taskId!=null){

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }

            TaskExample taskExample = new TaskExample();
            taskExample.createCriteria().andIdEqualTo(taskId);
            List<Task> tasks = taskMapper.selectByExample(taskExample);
            if(tasks==null||tasks.size()<=0){
                return JsonUtils.objectToJson("fail");
            }


                UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
                userDayTaskExample.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId).andCompleteEqualTo((byte)1);
            List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
            if(userDayTasks!=null&&userDayTasks.size()>0){
                userDayTasks.get(0).setComplete((byte)0);
                int index = userDayTaskMapper.updateByExample(userDayTasks.get(0), userDayTaskExample);
                if(index>0){
                    return JsonUtils.objectToJson("success");
                }else {
                    return JsonUtils.objectToJson("fail");
                }
            }else {
                return JsonUtils.objectToJson("fail");
            }
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String member_detail(Integer userId, Integer groupId, HttpServletRequest req, HttpServletResponse resp) {
        if(userId!=null&&groupId!=null){
            TaskExample taskExample = new TaskExample();
            taskExample.createCriteria().andGroupIdEqualTo(groupId);
            List<Task> tasks = taskMapper.selectByExample(taskExample);
            if(tasks!=null&&tasks.size()>0){
                GroupsExample groupsExample = new GroupsExample();
                groupsExample.createCriteria().andIdEqualTo(groupId);
                List<Groups> groups = groupsMapper.selectByExample(groupsExample);
                if(groups!=null&&groups.size()>0){
                    Double num = 0.0;
                    Double totalComplete = null;
                    int timeDistance = GetId.getTimeDistance(groups.get(0).getStartTime(), GetId.getNowTime())+1;
                    ArrayList<String> taskName = new ArrayList<>();
                    for(Task task:tasks){
                        taskName.add(task.getTaskName());
                        UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
                        userDayTaskExample.createCriteria().andTaskIdEqualTo(task.getId()).andCompleteEqualTo((byte)1).andUserIdEqualTo(userId);
                        List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
                        if(userDayTasks!=null&&userDayTasks.size()>0){
                            num+=userDayTasks.size();
                        }
                    }
                    totalComplete = num/(timeDistance*tasks.size());


                    int[][] arr = new int[tasks.size()][timeDistance];
                    for (int i=1;i<=timeDistance;i++){
                        for (int j=0;j<tasks.size();j++){
                            UserDayTaskExample userDayTaskExample1 = new UserDayTaskExample();
                            userDayTaskExample1.createCriteria().andTaskIdEqualTo(tasks.get(j).getId()).andCompleteEqualTo((byte)1).andUserIdEqualTo(userId).andTimeEqualTo(GetId.addDate(groups.get(0).getStartTime(),(i-1)));
                            List<UserDayTask> userDayTasks2 = userDayTaskMapper.selectByExample(userDayTaskExample1);
                            if(userDayTasks2!=null&&userDayTasks2.size()>0){
                                arr[j][i-1] = 1;
                            }else {
                                arr[j][i-1] = 0;
                            }
                        }
                    }
                    MemberDetail memberDetail = new MemberDetail();
                    memberDetail.setTaskName(taskName);
                    memberDetail.setTaskNum(tasks.size());
                    memberDetail.setDayNum(timeDistance);
                    memberDetail.setStartDate(groups.get(0).getStartTime());
                    memberDetail.setTotalComplete(totalComplete);
                    memberDetail.setArr(arr);
                    return JsonUtils.objectToJson(memberDetail);
                }
                return "fail";
            }
            return "fail";
        }
        return "fail";
    }

    @Override
    public String get_task_detail(Integer groupId) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andGroupIdEqualTo(groupId);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        if(tasks!=null&&tasks.size()>0){
            List<TaskDetailList> taskDetailLists = new ArrayList<>();
            for(Task task:tasks){
                UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
                TaskDetailList taskDetailList = new TaskDetailList();
                userDayTaskExample.createCriteria().andTaskIdEqualTo(task.getId()).andCompleteEqualTo((byte)1);
                List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
                if(userDayTasks!=null&&userDayTasks.size()>0){
                    taskDetailList.setTaskId(task.getId());
                    taskDetailList.setTaskCount(userDayTasks.size());
                    taskDetailList.setTaskName(task.getTaskName());
                    taskDetailLists.add(taskDetailList);
                }else {
                    continue;
                }
            }
            return JsonUtils.objectToJson(taskDetailLists);
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_member(Integer groupId) {
        if(groupId!=null){
            GroupsExample groupsExample = new GroupsExample();
            groupsExample.createCriteria().andIdEqualTo(groupId);
            List<Groups> groups = groupsMapper.selectByExample(groupsExample);
            if(groups!=null&&groups.size()>0){
                UserGroupExample userGroupExample = new UserGroupExample();
                userGroupExample.createCriteria().andGroupIdEqualTo(groupId);
                List<UserGroup> userGroups = userGroupMapper.selectByExample(userGroupExample);
                if(userGroups!=null&&userGroups.size()>0){
                    List<members> members = new ArrayList<>();
                    for (UserGroup userGroup:userGroups){
                        members member = new members();
                        member.setMember_id(userGroup.getUserId());
                        UserExample userExample = new UserExample();
                        userExample.createCriteria().andUserIdEqualTo(userGroup.getUserId());
                        List<User> users = userMapper.selectByExample(userExample);
                        member.setMember_name(users.get(0).getUserName());
                        /**
                         * 添加打卡率
                         */
                        TaskExample taskExample = new TaskExample();
                        taskExample.createCriteria().andGroupIdEqualTo(groupId);
                        List<Task> tasks = taskMapper.selectByExample(taskExample);
                        Double num = 0.0;
                        Double totalComplete = null;
                        int timeDistance = GetId.getTimeDistance(groups.get(0).getStartTime(), GetId.getNowTime())+1;
                        for(Task task:tasks){
                            UserDayTaskExample userDayTaskExample = new UserDayTaskExample();
                            userDayTaskExample.createCriteria().andTaskIdEqualTo(task.getId()).andCompleteEqualTo((byte)1).andUserIdEqualTo(userGroup.getUserId());
                            List<UserDayTask> userDayTasks = userDayTaskMapper.selectByExample(userDayTaskExample);
                            if(userDayTasks!=null&&userDayTasks.size()>0){
                                num+=userDayTasks.size();
                            }
                        }
                        totalComplete = num/(timeDistance*tasks.size());
                        member.setMember_task_rate(totalComplete);

                        members.add(member);
                    }
                    return JsonUtils.objectToJson(members);
                }else {
                    return JsonUtils.objectToJson("fail");
                }
            }else {
                return JsonUtils.objectToJson("fail");
            }
        }
        return JsonUtils.objectToJson("fail");
    }
}
