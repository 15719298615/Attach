package com.attach.learn.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public interface UserLearnService {
    /**
     * 获取今日已学习时间
     * @param userId      用户id
     * @return   今日学习时长  int
     */
    int get_today_learn_time(Integer userId);
    /**
     *开始计时
     * @param userId    用户id
     * @param currentTime    当前时间
     * @return     成功与否状态status:[success, fail]
     */
    String start_time(Integer userId, Long currentTime);

    /**
     *结束计时
     * @param userId    用户id
     * @param currentTime    当前时间
     * @return     成功与否状态status:[success, fail]
     */
    String end_time(Integer userId, Long currentTime);

    /**
     * 获取当前状态
     * @param userId     用户id
     */
    String current_status(Integer userId);

    /**
     * 获取单人一周学习分析
     * @param userId    用户id
     * @return   LearnResult.toString()
     */
    String get_week_record(Integer userId) throws ParseException;
    /**
     * 获取加入我们以来学习记录
     * @param userId    用户id
     * @return   LearnResult.toString()
     */
    String get_all_record(Integer userId);

    /**
     * 获取学习时间段分析
     * @param userId    用户id
     * @return    学习时段分布百分比，精确到一天的每分。
     */
    String get_time_slot(Integer userId);
}
