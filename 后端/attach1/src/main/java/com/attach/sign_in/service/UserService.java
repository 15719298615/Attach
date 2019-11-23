package com.attach.sign_in.service;

import com.attach.sign_in.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    /**
     * 登录
     * @return 登陆状态status:[succeed,fail]
     */
    String login(User user, HttpServletRequest req, HttpServletResponse resp);


    /**
     * 注册
     * @param user
     * @return 注册状态status:[succeed,fail]
     */
    String register(User user);

    /**
     * 绑定小程序id
     * @return
     */
    String binding(User user);


    /**根据userName返回userId
     *
     * @param userName
     * @return
     */
    int get(String userName);


}
