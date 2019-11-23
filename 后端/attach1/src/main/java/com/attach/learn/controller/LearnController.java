package com.attach.learn.controller;

import com.attach.learn.service.UserLearnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
public class LearnController {
    @Resource
    private UserLearnService userLearnServiceImpl;

    @RequestMapping(value ="get-stoday-learn-time" , produces="application/json;charset=utf-8")
    @ResponseBody
    public int get_today_learn_time(Integer userId){
        return userLearnServiceImpl.get_today_learn_time(userId);
    }

    @RequestMapping(value ="start-time" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String start_time(Integer userId, Long currentTime){
        return userLearnServiceImpl.start_time(userId,currentTime);
    }

    @RequestMapping(value ="end-time" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String end_time(Integer userId, Long currentTime){
        return userLearnServiceImpl.end_time(userId,currentTime);
    }

    @RequestMapping(value ="current-status" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String current_status(Integer userId){
        return userLearnServiceImpl.current_status(userId);
    }

    @RequestMapping(value ="get-week-record" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_week_record(Integer userId){
        try {
            return userLearnServiceImpl.get_week_record(userId);
        } catch (ParseException e) {
            return "fail";
        }

    }

    @RequestMapping(value ="get-all-record" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_all_record(Integer userId){
        return userLearnServiceImpl.get_all_record(userId);
    }

    @RequestMapping(value ="get-time-slot" , produces="application/json;charset=utf-8")
    @ResponseBody
    public String get_time_slot(Integer userId){
        return userLearnServiceImpl.get_time_slot(userId);
    }

}
