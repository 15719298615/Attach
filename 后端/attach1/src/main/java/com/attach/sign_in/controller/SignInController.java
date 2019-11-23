package com.attach.sign_in.controller;

import com.attach.sign_in.pojo.SignIn;
import com.attach.sign_in.service.SignInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {
    @Resource
    private SignInService signInServiceImpl;


    @RequestMapping(value="create-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String create_sign_in(SignIn signIn, HttpServletRequest req, HttpServletResponse resp){
        //设置响应头
        try{
            return signInServiceImpl.create_sign_in(signIn,req,resp);
        }catch (Exception e){
            return "status:[fail]";
        }
    }


    @RequestMapping(value="join-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String join_sign_in(Integer userId,Integer signInId,String signInPassword, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.join_sign_in(userId,signInId,signInPassword);
    }


    @RequestMapping(value="out-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String out_sign_in(Integer userId,Integer signInId,String signInPassword){
        return signInServiceImpl.out_sign_in(userId,signInId,signInPassword);
    }



    @RequestMapping(value = "get-myeffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_myeffective_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_myeffective_sign_in(userId);
    }


    @RequestMapping(value = "get-effective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_effective_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_effective_sign_in(userId);
    }

    @RequestMapping(value = "get-myuneffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_myuneffective_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_myuneffective_sign_in(userId);
    }

    @RequestMapping(value = "get-uneffective-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_uneffective_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_uneffective_sign_in(userId);
    }

    @RequestMapping(value = "get-all-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_all_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_all_sign_in(userId);
    }

    @RequestMapping(value = "get-all-my-sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_all_my_sign_in(Integer userId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_all_my_sign_in(userId);
    }

    @RequestMapping(value = "sign-in",produces="application/json;charset=utf-8")
    @ResponseBody
    public String sign_in(Integer userId,Integer signInId,Integer accuracy,Double siteLo,Double siteLa, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.sign_in(userId,signInId,accuracy,siteLo,siteLa);
    }

    @RequestMapping(value = "my-sign-in-detail",produces="application/json;charset=utf-8")
    @ResponseBody
    public String my_sign_in_detail(Integer userId,Integer signInId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.my_sign_in_detail(userId,signInId);
    }

    @RequestMapping(value = "sign-in-detail",produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_sign_in_detail(Integer signInId, HttpServletResponse response){
        //设置响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        return signInServiceImpl.get_sign_in_detail(signInId);
    }
}
