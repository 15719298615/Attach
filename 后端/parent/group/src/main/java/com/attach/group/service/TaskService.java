package com.attach.group.service;

import com.attach.group.pojo.Groups;

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
    String join_group(Integer userId,String groupNumber,String groupPassowrd);

}
