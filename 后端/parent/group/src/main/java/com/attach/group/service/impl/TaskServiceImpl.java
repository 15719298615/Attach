package com.attach.group.service.impl;

import com.attach.group.commons.utils.GetId;
import com.attach.group.commons.utils.JsonUtils;
import com.attach.group.mapper.GroupsMapper;
import com.attach.group.mapper.UserGroupMapper;
import com.attach.group.pojo.Groups;
import com.attach.group.pojo.GroupsExample;
import com.attach.group.pojo.UserGroup;
import com.attach.group.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private GroupsMapper groupsMapper;
    @Resource
    private UserGroupMapper userGroupMapper;
    @Override
    public String create_group(Groups group) {
        if(group!=null){
            if(group.getUserId()!=null&&group.getGroupName()!=null&&group.getStartTime()!=null&&group.getEndTime()!=null){
                String group_number = GetId.getSignInPassword(8);
                String group_passowrd = GetId.getSignInPassword(8);
                Groups group1 = new Groups();
                group1.setUserId(group.getUserId());
                group1.setGroupName(group.getGroupName());
                group1.setGroupNumber(group_number);
                group1.setGroupPassword(group_passowrd);
                group1.setStartTime(group.getStartTime());
                group1.setEndTime(group.getEndTime());
                group1.setEffective((byte)1);
                int index = groupsMapper.insert(group1);
                if(index>0){
                    return JsonUtils.objectToJson("status:[success]"+"group_number"+group_number+"group_passowrd:"+group_passowrd);
                }
            }else {
                return JsonUtils.objectToJson("2fail");
            }
        }
        return JsonUtils.objectToJson("1fail");
    }

    @Override
    public String join_group(Integer userId, String groupNumber, String groupPassowrd) {
        if(userId!=null&&groupNumber!=null&&groupPassowrd!=null){
            GroupsExample example = new GroupsExample();
            example.createCriteria().andGroupNumberEqualTo(groupNumber).andGroupPasswordEqualTo(groupPassowrd);
            List<Groups> groups = groupsMapper.selectByExample(example);
            if (groups!=null&&groups.size()>0){
                if(groups.get(0).getGroupNumber().equals(groupNumber)){
                    UserGroup userGroup = new UserGroup();
                    userGroup.setUserId(userId);
                    userGroup.setGroupId(Integer.parseInt(groupNumber));
                    int index = userGroupMapper.insert(userGroup);
                    if(index>0){
                        return "success";
                    }else{
                        return "3fail";
                    }
                }
            }
            return "2fail";
        }

        return "1fail";
    }
}
