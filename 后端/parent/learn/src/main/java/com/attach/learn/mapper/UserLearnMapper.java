package com.attach.learn.mapper;

import com.attach.learn.pojo.UserLearn;
import com.attach.learn.pojo.UserLearnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLearnMapper {
    int countByExample(UserLearnExample example);

    int deleteByExample(UserLearnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLearn record);

    int insertSelective(UserLearn record);

    List<UserLearn> selectByExample(UserLearnExample example);

    UserLearn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLearn record, @Param("example") UserLearnExample example);

    int updateByExample(@Param("record") UserLearn record, @Param("example") UserLearnExample example);

    int updateByPrimaryKeySelective(UserLearn record);

    int updateByPrimaryKey(UserLearn record);
}