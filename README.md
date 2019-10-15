# Attach

# 介绍
Attach是集合定点打卡，学习计时，团队每日任务，个人任务清单，项目汇报管理为一体的程序。

# 软件架构

## 目录
- 用户系统（小程序端）
  - 介绍
  - 功能
  - 页面
- 用户系统（web端）
  - 介绍
  - 功能
  - 页面
- 管理员系统设计
  - 介绍
  - 功能
  - 页面
- 数据库设计
  - 数据关系模型
  - 数据表
- 接口文档
  - 用户系统接口
  - 管理员系统接口
- 特别说明


---

## 用户系统（小程序端）

### 介绍
小程序，方便用户使用，主要功能包括定点签到，学习时间记录，团队每日任务三大模块（45模块暂不开发）

### 功能模块
- 登录注册

  > 通过平台注册账号使用，小程序端需要用openid绑定平台账号。


- 绑定账号 

  > 将微信小程序账号与Web端账号绑定，可以在两端查看同样数据。

- 定点签到  
  
  > 第一个功能模块，根据定位创建签到任务，每个签到任务只能一个地点，可以拉n个人，组长可见组内打卡情况。
  
  - 创建签到任务
    - 描述：创建人①选择签到地点，②选择签到周期，③设置人数上限，进行创建。创建后，服务器返回一个签到任务号和对应密码。
  - 加入签到任务
    - 描述：点击加入签到任务后，输入任务号和密码可以加入签到任务。
  - 查看所有签到任务
    - 点击我的签到后，能看到所有签到任务
  - 退出签到任务
    - 描述：加入错误的签到任务后，点击退出可以退出该任务。
  - 进行签到
    - 描述：点击开始签到后，选择签到任务，然后获取位置信息进行签到，如果签到位置不符合要有对应的提示，签到成功后进入相应签到后的页面（7号页面）
  - 我的打卡信息
    - 生成打卡次数
    - 打卡率（饼状图）
    - 每日打卡情况（特殊柱状图or日历打钩？）



- 学习计时
  > 第二个功能，主要以记录学习时间，分析学时间为主。分析包括一周内学习时间折线图，总学习曲线图，日学习时间统计的圆盘散点分布图，平均时间，超过多少用户，学习时间段分析，是否能坚持学习，作息是否稳定。
  - 开始计时
    - 点击开始后服务器开始计时，状态改为learning。
  - 结束计时
    - 点击后结束计时，并统计时间进入总时间，状态改为resting。 
  - 查看今日总学习时间
  - 查看本次学习时间
    - 为了在前端显示计时
  - 查看学习分析
    - 本周学习时间折线图
    - 总学习时长
    - 平均每日学习时长
    - 超过多少人
    - 加入我们以来总学习时间折线图（曲线表示，区域填充）
    - 分析学习时段(以环状图散点分布表示，学习时间集中在某一区域的话颜色就深，颜色需要设计好，类比热力图)



- 团队每日任务
  > 团队每日任务打卡，创建团队后，组长可以在里面发布每日任务，成员需要每日打卡。
  - 创建团队
    - 创建者输入团队名字和周期就可以创建。创建成功后会返回一个团队号和密码。
  - 加入团队每日任务
    - 输入团队号和密码加入打卡。
  - 团队里创建打卡每日任务（组长）
    - 点击加号添加打卡任务
  - 查看我加入的团队（改）
    - 点击后，到新页面把n个队伍显示出来。 
  - 查看我当日任务（改）
    - 显示在主页，可以一直往下滑，分别根据加入的团队来显示出来。
  - 完成当日任务
    - 点一下任务前的○就能勾选完成。
  - 打卡分析（组长）
    - 查看各任务完成情况柱状图
    - 查看各组员（按组员打卡率顺序列出，背景填充淡色表示打卡率）
    - 查看组员详细打卡信息
      - 二维矩阵，纵坐标是天，横坐标是任务号，填充颜色表示出某日某任务是否打卡。
      - 显示总打卡率（总打卡任务/总任务数）


- 任务清单（暂不开发）
- 项目管理（暂不开发）



### 页面
见图片

---

## 用户系统（web端）
### 介绍
web端的本项目，通过注册的账号进行登录，和小程序端功能相同，不同的是小程序将使用openid绑定平台账号。
### 功能
- 如上小程序端


### 页面
暂无

---

## 管理员系统设计
### 介绍
管理员能对所有的数据进行增删改查，同时也能看到所有用户的信息。
### 功能
- 用户的增删改查
    * 新增用户：openid与用户id绑定
    * 查找用户：可以根据id与姓名查找

- 定点签到功能：
    * 查看所有签到任务
    * 查看所有用户
    * 查看每个用户的所有签到
    * 查看每个用户每个签到的数据分析（打卡次数和频率的分析，连续柱状图）
    * 查看每个签到任务的分析（组长的那个）
    * 签到任务的增删改查（签到地点、签到任务总起止时间、签到任务名称、最大组员数）
    * 签到任务号和密码的改查
    * 查看签到任务成员列表
    * 查看签到任务成员的签到情况

- 学习计时功能：
    * 查看用户列表
    * 查看用户学习情况（周学习图、总学习图、深浅学习时段图）

- 团队每日：
    * 团队增删改查
    * 某团队任务的增删改查
    * 每个团队分析（各项任务打卡率分析、组员打卡率排名、每个组员各任务打卡分析）

- 日志管理功能
    * 获取所有日志
        - 提取错误信息
        - 提取对数据库操作信息
        - 提取访问信息

### 页面
暂无

---

## 数据库
### 关系模型
暂无
### 数据库表
### 用户表(user)

字段|类型|含义|备注
---|---|---|---
user_id|int|用户标识|主键，自增
user_name|varchar(16)|用户名
user_openid|varchar(32)|微信小程序绑定id
password|varchar(16)|密码

## 定点签到功能
### 签到表（sign_in)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
sign_in_id|int|随机生成8位房间号|唯一
sign_in_password|varchar(4)|房间密码|后端生成
sign_in_name|varchar(16)|签到名|
user_id|int|组长id|user表主键
start_time|date|开始时间|
end_time|date|结束时间|
site_lo|double|经度|
site_la|double|纬度|
max_number|int|最大参与人数|
effective|boolean|签到任务是否被删除|false：已删除，true：有效

### 用户参与签到表(user_sign_in)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
user_id|int|用户id|关联自user
sign_in_id|int|签到任务id|关联自sign_in
effective|boolean|是否有效|false：已删除，true：有效

### 用户签到(participate)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
user_id|int|用户id|关联自user
sign_in_id|int|签到任务id|关联自sign_in
sign_in_time|date|用户签到时间|


### 签到任务每日统计（statistics）
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
sign_in_id|int|签到任务id|关联自sign_in
count|int|当前任务当前打卡人数|

## 学习计时功能
### 用户-学习表(user_learn)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
user_id|int|用户id|关联自user
start_time|long|结束时间戳|
end_time|long|结束时间戳|

### 用户每日学习时长表(day_learning_time)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
user_id|int|用户id|关联自user
today|varchar(16)|当前日期|按每天6点到次日6点为一天
time|int|一天学习的总时长|按秒存储

## 小组每日任务功能
### 任务组(group)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
user_id|int|用户id|关联自user
start_time|date|开始时间|
end_time|date|结束时间|
group_name|varchar(32)|任务组名称
group_password|varchar(16)|密码|
effective|Boolean|true代表任务有效

### 任务组任务(task)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
group_id|int|任务组id|关联自group
task_name|varchar(32)|任务名称

### 用户-任务组（user_group)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
group_id|int|任务组id|关联自group
user_id|int|用户id|关联自user

### 用户每日任务表(user_day_task)
字段|类型|含义|备注
---|---|---|---
id|int|主键|自增
task_id|int|任务id|关联自task
user_id|int|用户id|关联自user
time|date|时间|每天都会新建一条任务
complete|Boolean|0或1代表当天任务是否完成
---

## 接口文档
### 用户系统接口

- 登录(login):
  - url：/login
  - 请求方式：POST
  - 参数信息：
    - 用户名(user_name)
    - 密码(user_password)
  - 返回值：
    - 成功与否的状态（status:[succeed,fail]）

- 注册(register):
  - url：/register
  - 请求方式：POST
  - 参数信息：
    - 用户名(user_name)
    - 密码(user_password)
  - 返回值：
    - 成功与否的状态（status:[success,fail])

- 绑定小程序id（binding）
  - url：/binding
  - 请求方式：POST
  - 参数信息：
    - 小程序id（user_openid）
    - 用户名(user_name)
    - 密码(user_password)
  - 返回值：
    - 成功与否的状态（status:[success,fail])

#### 定点签到（sign_in）

- 创建签到任务(create_sign_in):
    * url：/sign-in/create-sign-in
    * 请求方式：POST
    * 参数信息：
        - 发起人id(user_id)
        - 本次签到任务名称(sign_in_name)
        - 打卡地点（site）（如果是经纬度分开的，则分经度site_lo和纬度site_la）
        - 签到周期开始时间(start_time）
        - 结束时间（end_time)
        - 上限人数(max_number)
    * 返回值信息：
        - 成功与否的状态（status:[success,fail])
        - 成功返回：
            - 房间号（sign_in_id)
            - 密码(sign_in_password)
        - 失败返回：
            - 错误信息（message）

- 加入签到任务(join_sign_in)
    * url：/sign-in/join-sign-in
    * 请求方式：POST
    * 参数信息：
        - 用户id(user_id)
        - 房间号(sign_in_id)
        - 密码(sign_in_password)
    * 返回值信息：
        - 成功与否的状态（status:[success,fail])
        - 失败返回
            * 错误信息(message)

- 获取我发布的有效期内的签到(get_myeffective_sign_in):
    * url：/sign-in/get-myeffective-sign-in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户发布的的有效签到列表(myeffective_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time
    * 注意：
        - 验证登录
        - 验证账号

- 获取我参与的有效期内的签到(get_effective_sign_in):
    * url：/sign-in/get-effective-sign-in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户所加入的有效签到列表(effective_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time
    * 注意：
        - 验证登录
        - 验证账号

- 获取我发布的无效签到(get_myuneffective_sign_in):
    * url：/sign-in/get_myuneffective_sign_in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户发布的无效签到列表(myuneffective_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time
    * 注意：
        - 验证登录
        - 验证账号

- 获取我参与的无效的签到(get_uneffective_sign_in):
    * url：/sign-in/get_uneffective_sign_in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户所加入的无效签到列表(uneffective_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time
    * 注意：
        - 验证登录
        - 验证账号

- 获取用户所加入的所有签到任务(get_all_sign_in):
    * url：/sign-in/get_all_sign_in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户所加入的所有任务列表(all_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time

- 获取用户发布的所有签到任务(get_all_my_sign_in):
    * url：/sign-in/get_all_my_sign_in
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 用户所加入的所有任务列表(all_my_sign_in_list)
            - 列表里每个元素是字典，字典里包括：
                - sign_in_id
                - sign_in_name
                - sign_in_start_time
                - sign_in_end_time

- 进行签到(sign_in):
    * url：/sign-in/sign_in
    * 请求方式：POST
    * 请求参数：
        - 用户id:(user_id)
        - 签到id:(sign_id)
        - 用户位置:(site或者：经度site_lo，纬度site_la)
    * 返回值：
        - 成功与否的状态（status:[success,fail])

- 获取我的某个签到任务详情(my_sign_in_detail):
    * url：/sign-in/my_sign_in_detail
    * 请求方式：GET
    * 请求参数：
        - 用户id（user_id)
        - 签到任务id(sign_in_id)
    * 返回值：
        - 本签到任务有效签到次数(sign_in_num)
        - 本签到任务应签到次数(total_num)
        - 有效签到日期列表(effective_date_list)
        - 应签到日期列表(total_date_list)

- 获取某签到任务的详情（get_sign_in_detail）
    * url：/sign-in/sign_in_detail
    * 请求方式：GET
    * 请求参数：
        - 签到任务id(sign_in_id)
    * 返回值：
        - 每日签到情况列表(sign_in_detail_list)
            - 列表每个元素是一个字典包括：
                - 签到人列表sign_in_list
                - 未签到人列表unsign_in_list
            - [day1 = {'sign_in_list':['tam','sam'] ,'unsign_in_list': ['tom','bom']}, day2, day3, ...]
        - 周期内每天签到次数列表(everyday_number)
        - 总签到率(sign_in_rate)
    * 注意：
        - 发布人才可以获取这个
        - 权限问题

#### 学习计时（timing）
- 获取今日已学习时间(get_today_learn_time):
    * url：/timing/get-stoday-learn-time
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 今日学习时长(today_learn_time)

- 开始计时(start_time):
    * url：/timing/start-time
    * 请求方式：POST
    * 请求参数：
        - 用户id(user_id)
        - 当前时间(current_time)
    * 返回值：
        - 成功与否状态status:[success, fail]

- 结束计时(end_time):
    * url：/timing/end-time
    * 请求方式：POST
    * 请求参数：
        - 用户id(user_id)
        - 当前时间(current_time)
    * 返回值：
        - 成功与否状态status:[success, fail]

- 获取当前状态(current_status):
    * url：/timing/current-status
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 状态status:[learning,resting]
        - 学习中返回:
            - 返回学习时长(this_hour,this_min,this_sec)


- 获取单人一周学习分析(get_week_record)：
    * url：/timing/get-week-record
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 本周每天对应的学习时间列表(day_learned_time_list)（应该是7天，未到7天按小值）
        - 本周学习总时间(week_total_learn_time)
        - 平均学习时间(average_learn_time)（不满7天，按实际天算）
        - 打败人数百分比(beat_people_percent)

- 获取加入我们以来学习记录(get_all_record):
    * url：/timing/get-all-record
    * 请求方式：GET
    * 请求参数：
        - 用户id（user_id)
    * 返回值：
        - 自加入以来每日对应的学习时长列表(day_learned_time_list)
        - 加入以来总学习时间(total_learned)
        - 加入以来平均学习时间(average_learned_time)
        - 击败人数百分比(beat_people_percent)

- 获取学习时间段分析(get_time_slot):
    * url：/timing/get-time-slot
    * 请求方式：GET
    * 请求参数：
        - 用户id(user_id)
    * 返回值：
        - 学习时段分布百分比，精确到一天的每分。(time_slot_percent_list)
            - 列表每个数据是字典，包含两个key：{min, rate}
        - eg:[{1:0.0013},{2:0.0011}...{1440:0.0021}]
        

 

#### 小组每日任务（task）

- 创建任务组（create_group):
    * url：/task/create-group
    * 请求方式：POST
    * 请求参数：
        - 用户id(user_id)
        - 任务组名称(group_name)
        - 任务组开始时间(start_time)
        - 任务组结束时间(end_time)
    * 返回值：
        - 成功与否状态status:[success, fail]
        - 成功再返回任务组号和密码（group_number,group_passowrd）任务组号随机8位数字。

- 添加任务组任务(create_task):
    * url：/task/create-task
    * 请求方式：POST
    * 请求参数：
        - 小组id(group_id)
        - 任务名称(task_name)
    * 返回值：
        - 成功与否状态status:[success, fail]

- 加入任务组（join_group）:
  - url：/task/join-group
  - 请求方式：POST
  - 请求参数：
    - 用户id(user_id)
    - 任务组号(group_number)
    - 任务组密码(group_passowrd)
  - 返回值：
    - 成功与否状态status:[success, fail]

- 查看我的任务组（get_my_group）
  - url：/task/get-my-group
  - 请求方式：GET
  - 请求参数：
    - 用户id(user_id)
  - 返回值：
    - 任务组列表，每个元素是一个字典，字典包括:
      - 任务组id（group_id）
      - 任务组名称（group_name）

- 完成任务(finish_task)
  - url：/task/finish-task
  - 请求方式：POST
  - 请求参数：
    - 用户id(user_id)
    - 任务组id(group_id)
    - 任务组里的任务id(task_id)
  - 返回值：
    - 成功与否状态status:[success, fail]


- 取消完成任务(unfinish_task)
  - url：/task/unfinish-task
  - 请求方式：POST
  - 请求参数：
    - 用户id(user_id)
    - 任务组id(group_id)
    - 任务组里的任务id(task_id)
  - 返回值：
    - 成功与否状态status:[success, fail]

- 查看单人打卡情况(member_detail):
    * url：/task/member-detail
    * 请求方式：GET
    * 请求参数：
        - 人员id(user_id)
        - 任务组id（group_id)
    * 返回值：
        - 总打卡率(total_complete)
        - 各任务每天的打卡情况列表(task_detail_list)，这是一个二维数组，任务数为横坐标，天数为纵坐标，arr[1][2]=1表示第2天的第一个任务完成。

- 分析群打卡，获取各任务打卡率(get_task_detail):
    * url：/task/get-task-detail
    * 请求方式：GET
    * 请求参数：
        - 小组id(group_id)
    * 返回值：
        - 各任务对应的打卡人数和打卡率(task_detail_list)，每个元素是一个字典，包含：{任务id(task_id)，组内共打卡次数(task_count)}

- 查看组员(get_member):
    * url：/task/get-member
    * 请求方式：GET
    * 请求参数：
        - 小组id（group_id)
    * 返回值：
        - 按打卡率排序排序返回组员信息(members)列表，列表每个元素是一个字典，包含{组员名称(member_name)，组员id(member_id),组员总打卡率(member_task_rate)}



### 管理员系统接口
暂无

---

## 特别说明
暂无



# 安装教程

1. xxxx
2. xxxx
3. xxxx

# 使用说明

1. xxxx
2. xxxx
3. xxxx

# 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


# 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)