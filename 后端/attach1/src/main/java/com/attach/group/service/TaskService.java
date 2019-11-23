package com.attach.group.service;

import com.attach.group.pojo.Groups;
import com.attach.group.pojo.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TaskService {

    /**
     *
     * @param group：
     * 用户id(user_id)
     * 任务组名称(group_name)
     * 任务组开始时间(start_time)
     * 任务组结束时间(end_time)
     * @return返回值：
     * 成功与否状态status:[success, fail]
     * 成功再返回任务组号和密码（group_number,group_passowrd）任务组号随机8位数字。
     */
    String create_group(Groups group);



    String is_his(Integer userId,Integer groupId);

    /**
     * 添加任务组任务
     * 请求参数：
     * 小组id(group_id)
     * 任务名称(task_name)
     * @return返回值：
     * 成功与否状态status:[success, fail]
     */
    String create_task(Task task);
    /**
     * 加入任务组
     * @param userId
     * @param groupNumber
     * @param groupPassowrd
     * 请求参数：
     * 用户id(user_id)
     * 任务组号(group_number)
     * 任务组密码(group_passowrd)
     * @return回值：
     * 成功与否状态status:[success, fail]
     */
    String join_group(Integer userId, String groupNumber, String groupPassowrd);

    /**
     * 请求参数：
     * 用户id(user_id)
     * 返回值：
     * 任务组列表，每个元素是一个字典，字典包括:
     * 任务组id（group_id）
     * 任务组名称（group_name）
     * @return
     */
    String get_my_group(Integer userId);

    /**
     *
     * 请求参数：
     * 用户id(user_id)
     * 任务组里的任务id(task_id)
     * 返回值：
     * 成功与否状态status:[success, fail]
     * @return
     */
    String finish_task(Integer userId, Integer taskId);

    /**
     * 取消完成任务(unfinish_task)
     *
     * url：/task/unfinish-task
     * 请求方式：POST
     * 请求参数：
     * 用户id(user_id)
     * 任务组里的任务id(task_id)
     * 返回值：
     * 成功与否状态status:[success, fail]
     * @return
     */
    String unfinish_task(Integer userId, Integer taskId);

    /**
     * 查看单人打卡情况(member_detail):
     *
     * url：/task/member-detail
     * 请求方式：GET
     * 请求参数：
     * 人员id(user_id)
     * 任务组id（group_id)
     * 返回值：
     * 总打卡率(total_complete)
     * 各任务每天的打卡情况列表(task_detail_list)，这是一个二维数组，任务数为横坐标，
     * 天数为纵坐标，arr[1][2]=1表示第2天的第一个任务完成。
     * @return
     */
    String member_detail(Integer userId, Integer groupId, HttpServletRequest req, HttpServletResponse resp);

    /**
     * 分析群打卡，获取各任务打卡率(get_task_detail):
     *
     * url：/task/get-task-detail
     * 请求方式：GET
     * 请求参数：
     * 小组id(group_id)
     * 返回值：
     * 各任务对应的打卡人数和打卡率(task_detail_list)，每个元素是一个字典，包含：{任务id(task_id)，组内共打卡次数(task_count)}
     * @return
     */
    String get_task_detail(Integer groupId);

    /**
     * 查看组员(get_member):
     *
     * url：/task/get-member
     * 请求方式：GET
     * 请求参数：
     * 小组id（group_id)
     * 返回值：
     * 按打卡率排序排序返回组员信息(members)列表，列表每个元素是一个字典，
     * 包含{组员名称(member_name)，组员id(member_id),组员总打卡率(member_task_rate)}
     * @return
     */
    String get_member(Integer groupId);

}
