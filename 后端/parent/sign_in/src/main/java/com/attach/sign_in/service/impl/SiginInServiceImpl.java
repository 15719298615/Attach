package com.attach.sign_in.service.impl;

import com.attach.sign_in.commons.pojo.effectiveSignIn;
import com.attach.sign_in.commons.pojo.signInResult;
import com.attach.sign_in.commons.utils.GetId;
import com.attach.sign_in.commons.utils.JsonUtils;
import com.attach.sign_in.mapper.SignInMapper;
import com.attach.sign_in.mapper.UserSignInMapper;
import com.attach.sign_in.pojo.SignIn;
import com.attach.sign_in.pojo.SignInExample;
import com.attach.sign_in.pojo.UserSignIn;
import com.attach.sign_in.pojo.UserSignInExample;
import com.attach.sign_in.service.SignInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SiginInServiceImpl implements SignInService {
    @Resource
    private SignInMapper signInMapper;
    @Resource
    private UserSignInMapper userSignInMapper;
    @Override
    public String create_sign_in(SignIn signIn) {
        signInResult result = new signInResult();

        if (signIn.getUserId() != null && signIn.getSignInName() != null && signIn.getSiteLa() != null && signIn.getSiteLo() != null
                && signIn.getStartTime() != null && signIn.getEndTime() != null && signIn.getMaxNumber() != null) {
            int SignInId = GetId.getSigInId();
            String SignInPwd = GetId.getSignInPassword(4);
            signIn.setSignInId(SignInId);
            signIn.setSignInPassword(SignInPwd);
            signIn.setEffective((byte)1);
            int index = signInMapper.insert(signIn);
            if (index > 0) {
                result.setSignInId(SignInId);
                result.setSignInPassword(SignInPwd);
                result.setStatus("success");
                return JsonUtils.objectToJson(result);
            } else {
                result.setStatus("fail");
                result.setMessage("网络异常请重试！");
                return JsonUtils.objectToJson(result);
            }
        }
        result.setStatus("fail");
        result.setMessage("请填写所有内容");
        return JsonUtils.objectToJson(result);
    }

    @Override
    public String join_sign_in(Integer userId, Integer signInId, String signInPassword) {
        if (userId==null||signInId==null||signInPassword==null){
            return JsonUtils.objectToJson("填写fail");
        }
        SignInExample example = new SignInExample();
        example.createCriteria().andSignInIdEqualTo(signInId).andSignInPasswordEqualTo(signInPassword);
        List<SignIn> signIns = signInMapper.selectByExample(example);
        if(signIns!=null&&signIns.size()>0){
                if(signIns.get(0).getSignInId().equals(signInId)){
                    UserSignIn userSignIn = new UserSignIn();
                    userSignIn.setUserId(userId);
                    userSignIn.setSignInId(signInId);
                    userSignIn.setEffective((byte)1);
                    int index = userSignInMapper.insert(userSignIn);
                    if(index>0){
                        return JsonUtils.objectToJson("success");
                    }else{
                        return JsonUtils.objectToJson("3fail");
                    }
                }else{
                    return JsonUtils.objectToJson("2fail");
                }
            }
        return JsonUtils.objectToJson("1fail");
    }

    @Override
    public String get_myeffective_sign_in(Integer userId) {

        if(userId!=null){
            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte)1);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(SignIn ins:signIns){
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
        if(userId!=null){
            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte)1);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(UserSignIn userSignIn:userSignIns){
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                effectiveSignIns.add(effectiveSignIn);
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");

    }

    @Override
    public String get_myuneffective_sign_in(Integer userId) {
        if(userId!=null){
            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte)0);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(SignIn ins:signIns){
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
        if(userId!=null){
            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId).andEffectiveEqualTo((byte)0);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(UserSignIn userSignIn:userSignIns){
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                effectiveSignIns.add(effectiveSignIn);
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_all_sign_in(Integer userId) {
        if(userId!=null){
            UserSignInExample userSignInExample = new UserSignInExample();
            userSignInExample.createCriteria().andUserIdEqualTo(userId);
            List<UserSignIn> userSignIns = userSignInMapper.selectByExample(userSignInExample);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(UserSignIn userSignIn:userSignIns){
                effectiveSignIn effectiveSignIn = new effectiveSignIn();
                SignInExample signInExample = new SignInExample();
                signInExample.createCriteria().andSignInIdEqualTo(userSignIn.getSignInId());
                List<SignIn> signIns = signInMapper.selectByExample(signInExample);
                effectiveSignIn.setSignInId(signIns.get(0).getSignInId());
                effectiveSignIn.setSignInName(signIns.get(0).getSignInName());
                effectiveSignIn.setSignInStartTime(signIns.get(0).getStartTime());
                effectiveSignIn.setSignInEndTime(signIns.get(0).getEndTime());
                effectiveSignIns.add(effectiveSignIn);
            }
            return JsonUtils.objectToJson(effectiveSignIns);
        }

        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_all_my_sign_in(Integer userId) {
        if(userId!=null){
            SignInExample example = new SignInExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<SignIn> signIns = signInMapper.selectByExample(example);
            List<effectiveSignIn> effectiveSignIns = new ArrayList<>();
            for(SignIn ins:signIns){
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


}
