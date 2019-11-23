package com.attach.sign_in.controller;

import com.attach.sign_in.commons.utils.JsonUtils;
import com.attach.sign_in.pojo.User;
import com.attach.sign_in.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String login(User user, HttpServletRequest req, HttpServletResponse resp){
        //设置响应头
        resp.setHeader("Access-Control-Allow-Origin", "*");
        return userServiceImpl.login(user, req, resp);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public String register(User user, HttpServletResponse response){
        try{
            //设置响应头
            response.setHeader("Access-Control-Allow-Origin", "*");
            return userServiceImpl.register(user);
        }catch (Exception e){
            return JsonUtils.objectToJson("msg:[用户名已存在]");
        }
    }

    /**
     * 绑定小程序id
     * @param user
     * @return
     */
    @RequestMapping("binding")
    @ResponseBody
    public String binding(User user, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userServiceImpl.binding(user);
    }


    @RequestMapping("get-userid")
    @ResponseBody
    public int get(String userName){
        //设置响应头
        return userServiceImpl.get(userName);
    }

}
