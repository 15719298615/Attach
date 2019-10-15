package com.attach.sign_in.service;

import com.attach.sign_in.pojo.SignIn;

public interface SignInService {

    /**
     * 创建签到任务
     * @return
     */
    String create_sign_in(SignIn signIn);

    /**
     * 加入签到任务
     * @return 成功与否的状态（status:[success,fail])
     * 失败返回
     * 错误信息(message)
     */
    String join_sign_in(Integer userId,Integer signInId,String signInPassword);


    /**
     * 获取我发布的有效期内的签到
     * @param userId
     * @return返回值：
     * 用户发布的的有效签到列表(myeffective_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     */
    String get_myeffective_sign_in(Integer userId);


    /**
     * 获取我参与的有效期内的签到(get_effective_sign_in):
     * 返回值：
     * 用户所加入的有效签到列表(effective_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     * @param userId
     * @return
     */
    String get_effective_sign_in(Integer userId);

    /**
     * 获取我发布的无效签到(get_myuneffective_sign_in):
     *
     * @param userId
     * @return
     * 返回值：
     * 用户发布的无效签到列表(myuneffective_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     */
    String get_myuneffective_sign_in(Integer userId);

    /**
     * 获取我参与的无效的签到
     * @param userId
     * @return返回值：
     * 用户所加入的无效签到列表(uneffective_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     */
    String get_uneffective_sign_in(Integer userId);

    /**
     * 获取用户所加入的所有签到任务
     * @param userId
     * @return返回值：
     * 用户所加入的所有任务列表(all_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     */
    String get_all_sign_in(Integer userId);


    /**
     * 获取用户发布的所有签到任务
     * @param userId
     * @return返回值：
     * 用户所加入的所有任务列表(all_my_sign_in_list)
     * 列表里每个元素是字典，字典里包括：
     * sign_in_id
     * sign_in_name
     * sign_in_start_time
     * sign_in_end_time
     */
    String get_all_my_sign_in(Integer userId);
}
