package com.attach.sign_in.service.impl;

import com.attach.sign_in.commons.pojo.SignDetailResult;
import com.attach.sign_in.commons.pojo.effectiveSignIn;
import com.attach.sign_in.commons.pojo.signInResult;
import com.attach.sign_in.commons.pojo.signinDetailResult;
import com.attach.sign_in.commons.utils.GetId;
import com.attach.sign_in.commons.utils.JsonUtils;
import com.attach.sign_in.mapper.*;
import com.attach.sign_in.pojo.*;
import com.attach.sign_in.service.SignInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SiginInServiceImpl implements SignInService {
    @Resource
    private SignInMapper signInMapper;
    @Resource
    private UserSignInMapper userSignInMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ParticipateMapper participateMapper;
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public String create_sign_in(Integer userId,String signInName,Double siteLo,Double siteLa,Long startTime,Long endTime,Integer maxNumber) {
        signInResult result = new signInResult();

        if (userId != null && signInName.length() != 0 && siteLa != null && siteLo != null
                && startTime != null && endTime!= null && maxNumber!= null) {

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if (users != null && users.size() > 0) {
                SignIn signIn = new SignIn();
                int SignInId = GetId.getSigInId();
                String SignInPwd = GetId.getSignInPassword(4);
                signIn.setSignInId(SignInId);
                signIn.setSignInPassword(SignInPwd);
                signIn.setEffective((byte) 1);
                Date date = new Date(startTime);
                signIn.setStartTime(date);
                date = new Date(endTime);
                signIn.setEndTime(date);
                signIn.setUserId(userId);
                signIn.setSignInName(signInName);
                signIn.setSiteLa(siteLa);
                signIn.setSiteLo(siteLo);
                signIn.setMaxNumber(maxNumber);
                int index = signInMapper.insert(signIn);
                if (index > 0) {
                    result.setSignInId(SignInId);
                    result.setSignInPassword(SignInPwd);
                    UserSignIn userSignIn = new UserSignIn();
                    userSignIn.setUserId(signIn.getUserId());
                    userSignIn.setSignInId(signIn.getSignInId());
                    userSignIn.setEffective((byte) 1);
                    int insert = userSignInMapper.insert(userSignIn);
                    if (insert > 0) {
                        result.setStatus("success");
                    } else {
                        return "fail4";
                    }
                    return JsonUtils.objectToJson(result);
                } else {
                    result.setStatus("fail3");
                    return JsonUtils.objectToJson(result);
                }
            }
            result.setStatus("fail2");
            return JsonUtils.objectToJson(result);

        }
        result.setStatus("fail1");
        return JsonUtils.objectToJson(result);
    }

    @Override
    public String join_sign_in(Integer userId, Integer signInId, String signInPassword) {
        if (userId == null || signInId == null || signInPassword.length() != 4) {
            return JsonUtils.objectToJson("fail");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }


        SignInExample example = new SignInExample();
        example.createCriteria().andSignInIdEqualTo(signInId).andSignInPasswordEqualTo(signInPassword);
        List<SignIn> signIns = signInMapper.selectByExample(example);
        if (signIns != null && signIns.size() > 0) {
            if (signIns.get(0).getSignInId().equals(signInId)) {

                UserSignInExample userSignInExample = new UserSignInExample();
                userSignInExample.createCriteria().andSignInIdEqualTo(signInId).andUserIdEqualTo(userId);
                List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
                if(userSignIns!=null&&userSignIns.size()>0){
                    return JsonUtils.objectToJson("fail");
                }


                UserSignIn userSignIn = new UserSignIn();
                userSignIn.setUserId(userId);
                userSignIn.setSignInId(signInId);
                userSignIn.setEffective((byte) 1);
                int index = userSignInMapper.insert(userSignIn);
                if (index > 0) {
                    return JsonUtils.objectToJson("success");
                } else {
                    return JsonUtils.objectToJson("fail");
                }
            } else {
                return JsonUtils.objectToJson("fail");
            }
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String out_sign_in(Integer userId, Integer signInId, String signInPassword) {
        if (userId == null || signInId == null || signInPassword.length() != 4) {
            return JsonUtils.objectToJson("fail");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }


        SignInExample example = new SignInExample();
        example.createCriteria().andSignInIdEqualTo(signInId).andSignInPasswordEqualTo(signInPassword);
        List<SignIn> signIns = signInMapper.selectByExample(example);
        if (signIns != null && signIns.size() > 0) {
            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId);
            int index = userSignInMapper.deleteByExample(userSignInExample);
            if (index > 0) {
                return JsonUtils.objectToJson("success");
            }
            return JsonUtils.objectToJson("fail");
        }
        return JsonUtils.objectToJson("fail");
    }


    @Override
    public String get_myeffective_sign_in(Integer userId) {

        if (userId != null) {

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }


            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte) 1);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for (SignIn ins : signIns) {
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                effectiveSignIn.setSignInId(ins.getSignInId());
                effectiveSignIn.setSignInName(ins.getSignInName());
                effectiveSignIn.setSignInStartTime(ins.getStartTime());
                effectiveSignIn.setSignInEndTime(ins.getEndTime());
                effectiveSignIns.add(effectiveSignIn);
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_effective_sign_in(Integer userId) {
        if (userId != null) {


            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }


            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte) 1);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for (UserSignIn userSignIn : userSignIns) {
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                if (signIns.size() > 0 && signIns != null) {
                    effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                    effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                    effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                    effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                    effectiveSignIns.add(effectiveSignIn);
                }
                continue;
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");

    }

    @Override
    public String get_myuneffective_sign_in(Integer userId) {
        if (userId != null) {


            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }



            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte) 0);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for (SignIn ins : signIns) {
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                effectiveSignIn.setSignInId(ins.getSignInId());
                effectiveSignIn.setSignInName(ins.getSignInName());
                effectiveSignIn.setSignInStartTime(ins.getStartTime());
                effectiveSignIn.setSignInEndTime(ins.getEndTime());
                effectiveSignIns.add(effectiveSignIn);
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_uneffective_sign_in(Integer userId) {
        if (userId != null) {

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }



            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte) 0);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for (UserSignIn userSignIn : userSignIns) {
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                if (signIns != null && signIns.size() > 0) {
                    effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                    effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                    effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                    effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                    effectiveSignIns.add(effectiveSignIn);
                }
                continue;
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_all_sign_in(Integer userId) {
        if (userId != null) {


            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }


            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for (UserSignIn userSignIn : userSignIns) {
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                if (signIns != null && signIns.size() > 0) {
                    effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                    effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                    effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                    effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                    effectiveSignIns.add(effectiveSignIn);
                }
                continue;
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }

        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_all_my_sign_in(Integer userId) {
        if (userId != null) {

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userId);
            List<User> users = userMapper.selectByExample(userExample);
            if(users==null||users.size()<=0){
                return JsonUtils.objectToJson("fail");
            }


            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            if (signIns.size() > 0 && signIns != null) {
                for (SignIn ins : signIns) {
                    effectiveSignIn effectiveSignIn = new effectiveSignIn();
                    effectiveSignIn.setSignInId(ins.getSignInId());
                    effectiveSignIn.setSignInName(ins.getSignInName());
                    effectiveSignIn.setSignInStartTime(ins.getStartTime());
                    effectiveSignIn.setSignInEndTime(ins.getEndTime());
                    effectiveSignIns.add(effectiveSignIn);
                }
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");
    }

    /**
     * 高赟三个
     */


    @Override
    public String sign_in(Integer userId, Integer signInId, Integer accuracy, Double siteLo, Double siteLa) {
        if (userId == null || signInId == null || accuracy == null || siteLa == null || siteLo == null) {
            return JsonUtils.objectToJson("fail");
        }
        //判断有没有这个人
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0 && users!=null){   //有这个人
            //看有没有这个签到任务
            SignInExample signInExample = new SignInExample();
            signInExample.createCriteria().andSignInIdEqualTo(signInId);
            List<SignIn> signIns = signInMapper.selectByExample(signInExample);
            if (signIns != null && signIns.size() > 0) {
                //这个人是否参与了这个打卡任务
                UserSignInExample userSignInExample = new UserSignInExample();
                userSignInExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId).andEffectiveEqualTo((byte) 1);
                List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
                if (userSignIns != null && userSignIns.size() > 0) {
                    if (userId.equals(userSignIns.get(0).getUserId()) && signInId.equals(userSignIns.get(0).getSignInId())) {
                        //如果这个人已经签到就直接不插入了
                        ParticipateExample participateExample=new ParticipateExample();
                        participateExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId); //得到这个人的签到
                        List<Participate> participates = participateMapper.selectByExample(participateExample);
                        if(participates!=null && participates.size()>0){    //今天已经签到了,直接出错,判断是不是今天
                            int day = participates.get(participates.size()-1).getSignInTime().getDay();
                            int month = participates.get(participates.size()-1).getSignInTime().getMonth();
                            int year = participates.get(participates.size()-1).getSignInTime().getYear();
                            int date = participates.get(participates.size() - 1).getSignInTime().getDate();
                            if(day==GetId.getNowTime().getDay() && month==GetId.getNowTime().getMonth() && year==GetId.getNowTime().getYear() && date==GetId.getNowTime().getDate()){
                                return JsonUtils.objectToJson("fail");
                            }else {      //今天没有签到,可以签到
                                Double siteLo1 = signIns.get(0).getSiteLo();
                                Double siteLa1 = signIns.get(0).getSiteLa();
                                if (Math.abs(siteLa1 - siteLa) * 111000 < accuracy && Math.abs(siteLo1 - siteLo) * 111000 < accuracy) {
                                    Participate participate = new Participate();
                                    participate.setUserId(userId);
                                    participate.setSignInId(signInId);
                                    participate.setSignInTime(GetId.getNowTime());
                                    int index = participateMapper.insert(participate);
                                    if (index > 0) {
                                        //往这个任务的总签到人数里面加入即可。
                                        StatisticsExample statisticsExample =new StatisticsExample();
                                        statisticsExample.createCriteria().andSignInIdEqualTo(signInId).andIdEqualTo(userId);
                                        List<Statistics> statistics = statisticsMapper.selectByExample(statisticsExample);
                                        if(statistics.size()>0 && statistics!=null){
                                            Statistics statistics1=new Statistics();
                                            statistics1.setId(statistics.get(0).getId());
                                            statistics1.setCount(statistics.get(0).getCount()+1);
                                            statistics1.setSignInId(signInId);
                                            int i = statisticsMapper.updateByExample(statistics1, statisticsExample);
                                            if(i>0){
                                                return JsonUtils.objectToJson("success");
                                            }else{
                                                return JsonUtils.objectToJson("fail");      //没有更新，在任务每日情况表中
                                            }
                                        }else{
                                            Statistics statistics1=new Statistics();
                                            statistics1.setCount(1);
                                            statistics1.setSignInId(signInId);
                                            int insert = statisticsMapper.insert(statistics1);
                                            if(insert>0){
                                                return JsonUtils.objectToJson("success");
                                            }else{
                                                return JsonUtils.objectToJson("fail");      //没有插入进去，在任务每日情况表中
                                            }
                                        }
                                    } else {
                                        return JsonUtils.objectToJson("fail");    //插入失败
                                    }
                                } else {
                                    return JsonUtils.objectToJson("fail");    //精确度错误
                                }
                            }
                        }else{    //第一次签到，直接签到
                                Double siteLo1 = signIns.get(0).getSiteLo();
                                Double siteLa1 = signIns.get(0).getSiteLa();
                                if (Math.abs(siteLa1 - siteLa) * 111000 < accuracy && Math.abs(siteLo1 - siteLo) * 111000 < accuracy) {
                                    Participate participate = new Participate();
                                    participate.setUserId(userId);
                                    participate.setSignInId(signInId);
                                    participate.setSignInTime(GetId.getNowTime());
                                    int index = participateMapper.insert(participate);
                                    if (index > 0) {
                                        return JsonUtils.objectToJson("success");
                                    } else {
                                        return JsonUtils.objectToJson("fail");    //插入失败
                                    }
                                } else {
                                    return JsonUtils.objectToJson("fail");    //精确度错误
                                }
                            }
                    }else{
                        return JsonUtils.objectToJson("fail");   //不是同一个userid，不是同一个人
                    }
                }else{
                    return JsonUtils.objectToJson("fail");   //这个人没有参与这个签到任务
                }
            }else{
                return JsonUtils.objectToJson("fail");   //没有这个签到任务
            }
        }else{
            return JsonUtils.objectToJson("fail");   //不存在这人
        }
    }


    @Override
    public String my_sign_in_detail(Integer userId, Integer signInId) {
        SignDetailResult result = new SignDetailResult();
        if (userId == null || signInId == null) {
            return JsonUtils.objectToJson("fail");
        }
        //看有没有这个人
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
        //初始化
        int count = 0;
        int index = 0;
        int sign_in_num = 0;
        int total_num = 0;
        Date[] effective_date_list = null;
        Date[] total_date_list = null;

        //寻找这个用户签到的次数--sign_in_num
        SignInExample signInExample = new SignInExample();
        signInExample.createCriteria().andSignInIdEqualTo(signInId);
        List<SignIn> signIns = signInMapper.selectByExample(signInExample);

        //查看这给任务的情况。
        if (signIns != null && signIns.size() > 0) {
            //这个任务的签到范围，就是总共的签到日期。
            total_num = GetId.getTimeDistance(signIns.get(0).getStartTime(), signIns.get(0).getEndTime()) + 1;
            total_date_list = new Date[GetId.getTimeDistance(signIns.get(0).getStartTime(), signIns.get(0).getEndTime()) + 1];
            for (int i = 0; i < total_date_list.length; i++) {
//                    Date date = GetId.addDate(signIns.get(0).getStartTime(), i);
                total_date_list[i] = GetId.addDate(signIns.get(0).getStartTime(), i);
            }
            //判断这个人是否参与这个签到表
            UserSignInExample userSignInExample=new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            if(userSignIns.size()>0 && userSignIns!=null){
                //看有没有人参与签到。
                ParticipateExample participateExample = new ParticipateExample();
                participateExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId);
                List<Participate> participates = participateMapper.selectByExample(participateExample);
                if (participates != null && participates.size() > 0) {
                    sign_in_num = participates.size();
                    effective_date_list = new Date[sign_in_num];
                    for (Participate p : participates) {
                        //签到日期在范围内。
                        if (p.getSignInTime().getTime() >= signIns.get(0).getStartTime().getTime() && p.getSignInTime().getTime() <= signIns.get(0).getEndTime().getTime()) {
                            effective_date_list[count] = p.getSignInTime();
                            count++;
//                    sign_in_num++;
                        }
                    }
                }
            }
            result.setSignInNum(sign_in_num);
            result.setTotalNum(total_num);
            result.setEffectiveDateList(effective_date_list);
            result.setTotalDateList(total_date_list);
        }
        return JsonUtils.objectToJson(result);


//
//        //判断这个人是否参与这个签到表
//        UserSignInExample userSignInExample=new UserSignInExample();
//        userSignInExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId);
//        List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
//        if(userSignIns.size()>0 && userSignIns!=null){
//            //寻找这个用户签到的次数--sign_in_num
//                SignInExample signInExample = new SignInExample();
//                signInExample.createCriteria().andSignInIdEqualTo(signInId);
//                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
//                if (signIns != null && signIns.size() > 0) {
//                    //这个任务的签到范围，就是总共的签到日期。
//                    total_num = GetId.getTimeDistance(signIns.get(0).getStartTime(), signIns.get(0).getEndTime()) + 1;
//                    total_date_list = new Date[GetId.getTimeDistance(signIns.get(0).getStartTime(), signIns.get(0).getEndTime()) + 1];
//                    for (int i = 0; i < total_date_list.length; i++) {
////                    Date date = GetId.addDate(signIns.get(0).getStartTime(), i);
//                        total_date_list[i] = GetId.addDate(signIns.get(0).getStartTime(), i);
//                    }
//                    //看有没有人参与签到。
//                    ParticipateExample participateExample = new ParticipateExample();
//                    participateExample.createCriteria().andUserIdEqualTo(userId).andSignInIdEqualTo(signInId);
//                    List<Participate> participates = participateMapper.selectByExample(participateExample);
//                    if (participates != null && participates.size() > 0) {
//                        sign_in_num = participates.size();
//                        effective_date_list = new Date[sign_in_num];
//                        for (Participate p : participates) {
//                            if (p.getSignInTime().getTime() >= signIns.get(0).getStartTime().getTime() && p.getSignInTime().getTime() <= signIns.get(0).getEndTime().getTime()) {
//                                effective_date_list[count] = p.getSignInTime();
//                                count++;
////                    sign_in_num++;
//                            }
//                        }
//                    }
//                    result.setSignInNum(sign_in_num);
//                    result.setTotalNum(total_num);
//                    result.setEffectiveDateList(effective_date_list);
//                    result.setTotalDateList(total_date_list);
//                    }
//            return JsonUtils.objectToJson(result);
//        }else{
//            //根本没有参与这个签到任务
//            return JsonUtils.objectToJson("fail");
//        }
    }


    @Override
    public String get_sign_in_detail(Integer signInId) {
        if (signInId == null) {
            return JsonUtils.objectToJson("fail");
        }
        signinDetailResult Result = new signinDetailResult();
        int all = 0;
        int index = 0;
        int count = 0;
        int All = 0;
        double rate=0.0;
        List userIdList = new ArrayList();
        List AlluserIdList = new ArrayList();
        List<String> userNameList = new ArrayList();
        List<String> UnuserNameList = new ArrayList();
        List<String> AlluserNameList = new ArrayList();

        int[] everyday_number = null;
        SignInExample signInExample = new SignInExample();
        signInExample.createCriteria().andSignInIdEqualTo(signInId);
        List<SignIn> signIns = signInMapper.selectByExample(signInExample);
        int length = 0;
        int k = 1;
        int body = 0;
        Date start = null;
        Date end = null;
        if (signIns != null && signIns.size() > 0) {  //多少天
            length = GetId.getTimeDistance(signIns.get(0).getStartTime(), signIns.get(0).getEndTime()) + 1;
            start = signIns.get(0).getStartTime();
            end = signIns.get(0).getEndTime();
        }
        Map<String,Map<String,List<String>>> sign_in_detail_list=new HashMap<>();
//        String[] sign_in_detail_list = new String[length];
        UserSignInExample userSignInExample1 = new UserSignInExample();
        userSignInExample1.createCriteria().andSignInIdEqualTo(signInId);
        List<UserSignIn> userSignIn = userSignInMapper.selectByExample(userSignInExample1);
        if (userSignIn != null && userSignIn.size() > 0) {   //参与这个活动的人
            body = userSignIn.size();
            All = length * body;             //每天需要签到的和多少天相乘即可。
        }
        System.out.println(body);
        StatisticsExample statisticsExample=new StatisticsExample();
        statisticsExample.createCriteria().andSignInIdEqualTo(signInId);     //参与任务每日统计，多少条就是多少天。
        List<Statistics> result = statisticsMapper.selectByExample(statisticsExample);
        everyday_number=new int[length];
        if(result!=null && result.size()>0){     //得到总共的签到次数。
            for (Statistics s:result){
                int x=s.getCount();
                everyday_number[index]=x;
                index++;
                all+=x;
            }
        }
        Result.setEveryday_number(everyday_number);
        //得到所有人的id和名字,只要一次就行，放在循环里面由好多此。
        UserSignInExample userSignInExample=new UserSignInExample();
        userSignInExample.createCriteria().andSignInIdEqualTo(signInId);        //得到这个任务的参与人。
        List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
        if(userSignIns!=null && userSignIns.size()>0){
            for(UserSignIn u:userSignIns){
                Integer userSignId = u.getUserId();
                AlluserIdList.add(userSignId);
                UserExample userExample1=new UserExample();
                userExample1.createCriteria().andUserIdEqualTo(userSignId);
                List<User> users1 = userMapper.selectByExample(userExample1);
                if(users1.size()>0 && users1!=null){
                    for (User us:users1){
                        AlluserNameList.add(us.getUserName());//得到，名字
                    }
                }
            }
        }
        Date now=start;         //每次加一天。
        if(result!=null && result.size()>0){     //签到并且是这天的
            for (Statistics s:result){
                if(now.getTime()<end.getTime()){
                    ParticipateExample participateExample =new ParticipateExample();
                    participateExample.createCriteria().andSignInIdEqualTo(signInId).andSignInTimeEqualTo(now);   //参与了这个活动的
                    List<Participate> participates = participateMapper.selectByExample(participateExample);
                    //遍历参与者。
                    if(participates!=null && participates.size()>0){
                        for(Participate p:participates){
                            Integer userPaId = p.getUserId();          //得到id
                            UserExample userExample=new UserExample();
                            userExample.createCriteria().andUserIdEqualTo(userPaId);
                            List<User> users = userMapper.selectByExample(userExample);
                            //放入这个userid的名字。（参与了这个活动的人的名字）
                            if(users!=null && users.size()>0){
                                for (User u:users){
                                    String userName = u.getUserName();
                                    userNameList.add(userName);
//                                    AlluserNameList.add(userName);
                                }
                                userIdList.add(userPaId);
                            }
//                            //得到所有人的id和名字
//                            UserSignInExample userSignInExample=new UserSignInExample();
//                            userSignInExample.createCriteria().andSignInIdEqualTo(signInId);        //得到这个任务的参与人。
//                            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
//                            if(userSignIns!=null && userSignIns.size()>0){
//                                for(UserSignIn u:userSignIns){
//                                    Integer userSignId = u.getUserId();
//                                    AlluserIdList.add(userSignId);
//                                    UserExample userExample1=new UserExample();
//                                    userExample1.createCriteria().andUserIdEqualTo(userSignId);
//                                    List<User> users1 = userMapper.selectByExample(userExample1);
//                                    if(users1.size()>0 && users1!=null){
//                                        for (User us:users1){
//                                            AlluserNameList.add(us.getUserName());//得到，名字
//                                        }
//                                    }
//                                }
//                            }
                        }
                    }
                    now = GetId.addDate(now, 1);
                }
                //每次复制。/这样所有人的就不用变了，变得时复制品。
                for(String u:AlluserNameList){
                    UnuserNameList.add(u);
                }
                //移出所有签到的就是没有签到的
                for(String u:userNameList){
                    UnuserNameList.remove(u);
                }
                Map<String,List<String>> map=new HashMap();
                List list=new ArrayList();
                list.add(userNameList.toString());
                List list2=new ArrayList();
                list2.add(UnuserNameList.toString());
//                sign_in_detail_list[k++]=userNameList.toString()+UnuserNameList.toString();
                map.put("unsignInList",list2);
                map.put("signInList",list);
                sign_in_detail_list.put("day"+k++,map);
                userNameList.clear();
                UnuserNameList.clear();
            }
        }else{          //没有人签到这个任务。就直接放空就可以。
            for(int i=0;i<length;i++){
                Map<String,List<String>> map=new HashMap();
                List list=new ArrayList();
                List list2=new ArrayList();
                list.add("[]");
                list2.add("[]");
                map.put("signInList",list);
                map.put("unsignInList",list2);
                sign_in_detail_list.put("day"+k++,map);
            }
        }


        rate=((double) all/ All)*100;
        System.out.println(rate);
        AlluserIdList.removeAll(userIdList);       //从总的id中去掉签到的就是没签到的。
        for(Object id:AlluserIdList){           //遍历user得到他的名字，放入未签到表中。
            UserExample userExample=new UserExample();
            userExample.createCriteria().andUserIdEqualTo((Integer) id);
            List<User> users=userMapper.selectByExample(userExample);
            if(users!=null && users.size()>0){
                UnuserNameList.add(users.get(0).getUserName());
            }
        }
        Result.setSign_in_rate(rate);
        Result.setSign_in_detail_list(sign_in_detail_list);
        return JsonUtils.objectToJson(Result);
    }
}
