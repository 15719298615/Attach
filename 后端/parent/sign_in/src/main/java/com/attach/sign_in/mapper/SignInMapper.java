package com.attach.sign_in.mapper;

import com.attach.sign_in.pojo.SignIn;
import com.attach.sign_in.pojo.SignInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignInMapper {
    int countByExample(SignInExample example);

    int deleteByExample(SignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignIn record);

    int insertSelective(SignIn record);

    List<SignIn> selectByExample(SignInExample example);

    SignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByExample(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByPrimaryKeySelective(SignIn record);

    int updateByPrimaryKey(SignIn record);
}