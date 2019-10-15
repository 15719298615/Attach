package com.attach.sign_in.mapper;

import com.attach.sign_in.pojo.UserSignIn;
import com.attach.sign_in.pojo.UserSignInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSignInMapper {
    int countByExample(UserSignInExample example);

    int deleteByExample(UserSignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserSignIn record);

    int insertSelective(UserSignIn record);

    List<UserSignIn> selectByExample(UserSignInExample example);

    UserSignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserSignIn record, @Param("example") UserSignInExample example);

    int updateByExample(@Param("record") UserSignIn record, @Param("example") UserSignInExample example);

    int updateByPrimaryKeySelective(UserSignIn record);

    int updateByPrimaryKey(UserSignIn record);
}