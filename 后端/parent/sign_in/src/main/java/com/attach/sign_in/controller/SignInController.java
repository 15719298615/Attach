package com.attach.sign_in.controller;

import com.attach.sign_in.pojo.SignIn;
import com.attach.sign_in.service.SignInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SignInController {
    @Resource
    private SignInService signInServiceImpl;
    private Integer userId;


    @RequestMapping("create-sign-in")
    @ResponseBody
    public String create_sign_in(SignIn signIn){
        return signInServiceImpl.create_sign_in(signIn);
    }


    @RequestMapping("join-sign-in")
    @ResponseBody
    public String join_sign_in(Integer userId,Integer signInId,String signInPassword){
        return signInServiceImpl.join_sign_in(userId,signInId,signInPassword);
    }

    @RequestMapping(value = "get-myeffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_myeffective_sign_in(Integer userId){
        return signInServiceImpl.get_myeffective_sign_in(userId);
    }


    @RequestMapping(value = "get-effective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_effective_sign_in(Integer userId){
        return signInServiceImpl.get_effective_sign_in(userId);
    }

    @RequestMapping(value = "get-myuneffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_myuneffective_sign_in(Integer userId){
        return signInServiceImpl.get_myuneffective_sign_in(userId);
    }

    @RequestMapping(value = "get-uneffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_uneffective_sign_in(Integer userId){
        return signInServiceImpl.get_uneffective_sign_in(userId);
    }

    @RequestMapping(value = "get-all-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_all_sign_in(Integer userId){
        return signInServiceImpl.get_all_sign_in(userId);
    }

    @RequestMapping(value = "get-all-my-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_all_my_sign_in(Integer userId){
        return signInServiceImpl.get_all_my_sign_in(userId);
    }
}
