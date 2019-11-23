package com.attach.group.mapper;

import com.attach.group.pojo.UserDayTask;
import com.attach.group.pojo.UserDayTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDayTaskMapper {
    int countByExample(UserDayTaskExample example);

    int deleteByExample(UserDayTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDayTask record);

    int insertSelective(UserDayTask record);

    List<UserDayTask> selectByExample(UserDayTaskExample example);

    UserDayTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDayTask record, @Param("example") UserDayTaskExample example);

    int updateByExample(@Param("record") UserDayTask record, @Param("example") UserDayTaskExample example);

    int updateByPrimaryKeySelective(UserDayTask record);

    int updateByPrimaryKey(UserDayTask record);
}