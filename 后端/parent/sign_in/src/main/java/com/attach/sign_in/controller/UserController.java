package com.attach.sign_in.controller;

import com.attach.sign_in.pojo.User;
import com.attach.sign_in.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userServiceImpl;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(User user){
       return userServiceImpl.login(user);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public String register(User user){
        return userServiceImpl.register(user);
    }

    /**
     * 绑定小程序id
     * @param user
     * @return
     */
    @RequestMapping("binding")
    @ResponseBody
    public String binding(User user){
        return userServiceImpl.binding(user);
    }


}
