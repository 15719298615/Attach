package com.attach.learn.mapper;

import com.attach.learn.pojo.DayLearningTime;
import com.attach.learn.pojo.DayLearningTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DayLearningTimeMapper {
    int countByExample(DayLearningTimeExample example);

    int deleteByExample(DayLearningTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DayLearningTime record);

    int insertSelective(DayLearningTime record);

    List<DayLearningTime> selectByExample(DayLearningTimeExample example);

    DayLearningTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DayLearningTime record, @Param("example") DayLearningTimeExample example);

    int updateByExample(@Param("record") DayLearningTime record, @Param("example") DayLearningTimeExample example);

    int updateByPrimaryKeySelective(DayLearningTime record);

    int updateByPrimaryKey(DayLearningTime record);
}