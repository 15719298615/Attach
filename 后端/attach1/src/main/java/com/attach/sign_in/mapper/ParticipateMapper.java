package com.attach.sign_in.mapper;

import com.attach.sign_in.pojo.Participate;
import com.attach.sign_in.pojo.ParticipateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParticipateMapper {
    int countByExample(ParticipateExample example);

    int deleteByExample(ParticipateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Participate record);

    int insertSelective(Participate record);

    List<Participate> selectByExample(ParticipateExample example);

    Participate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Participate record, @Param("example") ParticipateExample example);

    int updateByExample(@Param("record") Participate record, @Param("example") ParticipateExample example);

    int updateByPrimaryKeySelective(Participate record);

    int updateByPrimaryKey(Participate record);
}