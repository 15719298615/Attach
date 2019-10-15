package com.attach.sign_in.service;

import com.attach.sign_in.pojo.User;

public interface UserService {

    /**
     * 登录
     * @return 登陆状态status:[succeed,fail]
     */
    String login(User user);


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
}
