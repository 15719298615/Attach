package com.attach.sign_in.service.impl;

import com.attach.sign_in.commons.utils.JsonUtils;
import com.attach.sign_in.mapper.UserMapper;
import com.attach.sign_in.pojo.User;
import com.attach.sign_in.pojo.UserExample;
import com.attach.sign_in.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String login(User user) {

        if(user.getUserName()==null||user.getUserPassword()==null){
            return JsonUtils.objectToJson("fail");
        }

        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName()).andUserPasswordEqualTo(user.getUserPassword());
        List<User> users = userMapper.selectByExample(example);
        if(user!=null&&users.size()>0){
            return JsonUtils.objectToJson("succeed");
        }

        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String register(User user) {

        int index = userMapper.insert(user);
        if(index>0){
            return JsonUtils.objectToJson("succeed");
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String binding(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName()).andUserPasswordEqualTo(user.getUserPassword());
        List<User> users = userMapper.selectByExample(example);
        if(users!=null&&users.size()>0){
            Integer userId = users.get(0).getUserId();
            user.setUserId(userId);
            int index = userMapper.updateByExample(user, example);
            if(index>0){
                return JsonUtils.objectToJson("succeed");
            }else {
                return JsonUtils.objectToJson("fail");
            }
        }
        return JsonUtils.objectToJson("fail");
    }


}
