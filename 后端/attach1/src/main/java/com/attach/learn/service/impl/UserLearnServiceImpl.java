package com.attach.learn.service.impl;

import com.attach.learn.commons.pojo.LearnResult;
import com.attach.learn.commons.utils.JsonUtils;
import com.attach.learn.mapper.DayLearningTimeMapper;
import com.attach.learn.mapper.UserLearnMapper;
import com.attach.learn.pojo.DayLearningTime;
import com.attach.learn.pojo.DayLearningTimeExample;
import com.attach.learn.pojo.UserLearn;
import com.attach.learn.pojo.UserLearnExample;

import com.attach.learn.service.UserLearnService;
import com.attach.sign_in.commons.utils.GetId;
import com.attach.sign_in.mapper.UserMapper;
import com.attach.sign_in.pojo.User;
import com.attach.sign_in.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserLearnServiceImpl implements UserLearnService {
    @Resource
    private UserLearnMapper userLearnMapper;
    @Resource
    private DayLearningTimeMapper dayLearningTimeMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public int get_today_learn_time(Integer userId) {
        if (userId == null) {
            return 0;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() <= 0) {
            return 0;
        }
        Integer all = 0;
//            //得到今天的日期，再在每日学习里面遍历这个userid的学习时间
//            GregorianCalendar calendar=new GregorianCalendar();
//            int year=calendar.get(calendar.YEAR);
//            // 月的数值加1，使之变成习惯的月份大小（1～12月）
//            int month=calendar.get(calendar.MONTH)+1;
//            int today=calendar.get(calendar.DAY_OF_MONTH);
//            String date=year+"-"+month+"-"+today;
//            //得到今天6点的时间
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, 6);
//            c.set(Calendar.MINUTE, 0);
//            c.set(Calendar.SECOND, 0);
////        System.out.println(c.getTime());
//            int year1=c.get(calendar.YEAR);
//            int month1=calendar.get(calendar.MONTH)+1;
//            int today1=calendar.get(calendar.DAY_OF_MONTH);
//            String date1=year1+"-"+month1+"-"+today1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String date = sdf.format(new Date());
        DayLearningTimeExample dayLearningTimeExample = new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId).andTodayEqualTo(date);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        if (dayLearningTimes != null && dayLearningTimes.size() > 0) {
            for (DayLearningTime d : dayLearningTimes) {
                all += d.getTime();
            }
        }
        return all;
    }

    @Override
    public String start_time(Integer userId, Long currentTime) {
        if (userId == null || currentTime == null) {
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);

        if (users == null || users.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
        UserLearn userLearn = new UserLearn();
        //id是自增的不用放
        userLearn.setUserId(userId);
        userLearn.setStartTime(currentTime);
        int result = userLearnMapper.insert(userLearn);
        if (result > 0) {
            return JsonUtils.objectToJson("success");
        } else {
            return JsonUtils.objectToJson("fail");
        }
    }

    @Override
    public String end_time(Integer userId, Long currentTime) {
        if (userId == null || currentTime == null) {
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
        DayLearningTime dayLearningTime = new DayLearningTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String date = sdf.format(new Date());

        DayLearningTimeExample dayLearningTimeExample = new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId).andTodayEqualTo(date);   //只会返回今天的一个数据
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        Integer id = null;
        if (dayLearningTimes.size() > 0 && dayLearningTimes != null) {
            id = dayLearningTimes.get(0).getId();
        }


        UserLearnExample userLearnExample = new UserLearnExample();
        UserLearnExample.Criteria criteria = userLearnExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        if (userLearns != null && userLearns.size() > 0) {
            //遍历找到那个结束时间是null的行，并且更新，如果前面还有肯定就是开始不为空，结束也不为null
            for (UserLearn u : userLearns) {
                //开始时间不为空   结束时间为空    开始时间要小于当前时间    需要往结束时间放入当前时间
                if (u.getEndTime() == null && u.getStartTime() != null && u.getStartTime() < currentTime) {
                    UserLearn userLearn = new UserLearn();
                    //更新要更新哪一行，得知道id
                    userLearn.setId(u.getId());
                    userLearn.setUserId(userId);
                    userLearn.setStartTime(u.getStartTime());
                    userLearn.setEndTime(currentTime);
//                      int i = userLearnMapper.updateByExample(userLearn, userLearnExample);
                    int i = userLearnMapper.updateByPrimaryKey(userLearn);
                    if (i > 0) {//更新成功,开始更新今天的学习时间
                        if (dayLearningTimes.size() > 0 && dayLearningTimes != null) {       //不是第一次，就得到时间加上原来的时间并且更新。
                            Integer time = dayLearningTimes.get(0).getTime();
                            String today = dayLearningTimes.get(0).getToday();
                            time += (int) (currentTime - u.getStartTime()) / 1000;
                            dayLearningTime.setId(id);
                            dayLearningTime.setUserId(userId);
                            dayLearningTime.setTime(time);
                            dayLearningTime.setToday(today);
                            int update = dayLearningTimeMapper.updateByExample(dayLearningTime, dayLearningTimeExample);
                            if (update > 0) {
                                return JsonUtils.objectToJson("success");   //更新成功
                            }
                            break;
                        } else {                //今天第一次插入,不用插入id
                            dayLearningTime.setUserId(userId);
                            dayLearningTime.setToday(date);
                            dayLearningTime.setTime((int) (currentTime - u.getStartTime()) / 1000);
                            int insert = dayLearningTimeMapper.insert(dayLearningTime);
                            if (insert > 0) {
                                return JsonUtils.objectToJson("success");   //更新成功
                            }
                            break;
                        }
                    } else {
                        return JsonUtils.objectToJson("fail");     //更新失败
                    }
                }
            }
            return JsonUtils.objectToJson("fail");     //没找到符合条件的
        }
        return JsonUtils.objectToJson("fail");     //userLearn为空
    }

    @Override
    public String current_status(Integer userId) {
        //有问题，小程序端可能在学习的时候直接关闭程序，然后应该时learning，postman返回的时learning，而小程序端得到的时resting。
        if (userId == null) {
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
        //得到今天6点的时间。
        Date current = new Date();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        c.set(year + 1900, month, day, 6, 0);
        long all = 0;
        int comp = (int) current.getTime() / 1000;
        //看用户学习表，是否学习。
        UserLearnExample userLearnExample = new UserLearnExample();
        userLearnExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        if (userLearns != null && userLearns.size() > 0) {
            for (UserLearn u : userLearns) {
                Long startTime = u.getStartTime() / 1000;
                if (u.getEndTime() == null && u.getStartTime() > c.getTimeInMillis()) {    //今天6点开始后开始的学习。
                    all = (c.getTimeInMillis() - u.getStartTime()) / 1000;
                    StringBuilder stringBuilder = new StringBuilder();
                    long hours = all / 3600;
                    long minites = (all - 3600 * hours) / 60;
                    long seconds = (all - 3600 * hours - 60 * minites) % 60;
                    stringBuilder.append("learning,学习时长").append(":").append(hours).append(".").append(minites).append(".").append(seconds);
                    return JsonUtils.objectToJson(stringBuilder);
                }else{
                    continue;
                }
            }
            return JsonUtils.objectToJson("resting");
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_week_record(Integer userId) throws ParseException {
        //判断空和有没有这个人
        if (userId == null) {
            return JsonUtils.objectToJson("fail");
        }
        int value = 0;
        UserExample userExample = new UserExample();             //看有没有这个人
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> user = userMapper.selectByExample(userExample);
        if (user == null || user.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample1 = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0 && users!=null){
            value=users.size();     //user用户的个数,多少个用户
        }
        //得到这周之前一周的日期。

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);    //1是周日
        // 今天是一周中的第几天
        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        // 计算一周前开始的日期
//            c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);    //得到这周刚开始的日期。
        c.add(Calendar.DAY_OF_MONTH, -7);
//        for (int i = 1; i < dayOfWeek; i++) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//        }
        //初始化
        LearnResult learnResult = new LearnResult();
        int[] day_learned_time_list = null;
        int week_total_learn_time = 0;
        double average_learned_time = 0.0;
        double beat_people_percent = 0.0;
        int count = 0;
        int index = 0;
        int all = 0;
        long time1 = 0;
        DayLearningTimeExample dayLearningTimeExample = new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        if (dayLearningTimes != null && dayLearningTimes.size() > 0) {
//            time1 = dayLearningTimes.get(dayLearningTimes.size()).getTime()/1000;    //最后一次的时间
            Date date = sdf.parse(dayLearningTimes.get(dayLearningTimes.size()).getToday());
            time1 = date.getTime() / 1000;      //最后一次每天学习时间这一天的时间
        }
        long r = 0;     //最后一次之前一周的,初始化,一周是604800s
        String start = dayLearningTimes.get(0).getToday();
        //得到第一天的时间，进行比较，是否在一周前注册。
        if (sdf.parse(start).getTime() / 1000 < c.getTimeInMillis() / 1000 ) {
            //calendar本身就是一周前的，如果第一次的时间比一周前的还小，说明自己在一周前已经开始了学习，只要判断这一周内的即可。
            r = time1 - 604800;
            day_learned_time_list = new int[7];
        } else {
            //如果比一周前的大，说明在这周才开始进行学习的，直接从第一天开始判断即可。
            r = sdf.parse(start).getTime() / 1000;
            day_learned_time_list = new int[dayLearningTimes.size()];
        }
        if (dayLearningTimes != null && dayLearningTimes.size() > 0) {
            for (DayLearningTime d : dayLearningTimes) {
                String today = d.getToday();
                long l = sdf.parse(today).getTime() / 1000;
                if (l < r) {   //如果比一周前或者刚开始日期还要小就继续寻找
                    continue;
                }
                if (l >= r && l <= time1) {       //这一周内的
                    day_learned_time_list[count++] = d.getTime();     //存放时间
                    week_total_learn_time += d.getTime();
                }
            }
            average_learned_time = ((double) week_total_learn_time / day_learned_time_list.length);
        }
        //求打败人数百分比
        int number = 0;
        boolean flag;
        Integer[] UserId = new Integer[value];
        DayLearningTimeExample dayLearningTimeExample2 = new DayLearningTimeExample();
        List<DayLearningTime> dayLearningTimes1 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample2);
        if (dayLearningTimes1 != null && dayLearningTimes1.size() > 0) {
            //得到所有的userid
            for (DayLearningTime d : dayLearningTimes1) {
                flag = true;        //每次将标志位重置.
                if (number < value) {
                    Integer userId1 = d.getUserId();     //得到每一个userid，和数组UserId进行比较，是否有重复的id
                    for (int i = 0;i <=number;i++){         //只遍历有的，可以减少次数。
                        if (i == userId1) {     //如果重复，将标志位置为false；
                            flag = false;
                        }
                    }
//                    for (Integer i : UserId) {        //要遍历所有，次数比较多.
//                    }
                    if (flag) {     //判断标志位，看是否重复。
                        UserId[number] = userId1;
                        number++;
                    }
                }
            }
        }
        //求所有人的平均时间
        int n = 0;
        Double[] allIdRate = new Double[number];
        for (Integer i : UserId) {
            if (i != null) {
                DayLearningTimeExample dayLearningTimeExample3 = new DayLearningTimeExample();
                dayLearningTimeExample3.createCriteria().andUserIdEqualTo(i);
                List<DayLearningTime> dayLearningTimes2 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample3);
                int total = 0;
                for (DayLearningTime d : dayLearningTimes) {
                    String today = d.getToday();
                    Integer time = d.getTime();
//                    total += time;
                    long l = sdf.parse(today).getTime() / 1000;
                    if (l < r) {
                        continue;
                    }
                    if (l > r && l < time1) {       //这一周内的
                        total += d.getTime();
                    }
                }
                allIdRate[n++] = ((double) total / (double) day_learned_time_list.length);    //每个人的平均学习时间是总共的除以这一周的天数
            }
        }
        //求打败百分比
        int many = 0;
        if (allIdRate != null && allIdRate.length > 0) {        //有其他人
            for (Double d : allIdRate) {
                if (average_learned_time == 0.0) {
                    beat_people_percent = 0.0;
                } else {
                    if (average_learned_time > d) {
                        many++;
                    }
                }
            }
            beat_people_percent = ((double) many / (double) (allIdRate.length))*100;
        }else{      //没有其他人，只有一个人。
            beat_people_percent = 1.0;
        }
        //往结果里面存放数据。
        learnResult.setAverage_learned_time(average_learned_time);
        learnResult.setBeat_people_percent(beat_people_percent);
        learnResult.setDay_learned_time_list(day_learned_time_list);
        learnResult.setTotal_learned(week_total_learn_time);
        return JsonUtils.objectToJson(learnResult);
    }

    @Override
    public String get_all_record(Integer userId) {
        //判断空和有没有这个人
        if (userId == null) {
            return JsonUtils.objectToJson("fail");
        }
        int value = 0;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> user = userMapper.selectByExample(userExample);
        HashMap<Integer, Integer> map = new HashMap();
        if (user == null || user.size() <= 0) {             //看有没有这个人
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample1 = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0 && users != null) {
            value = users.size();     //user用户的个数,多少个用户
        }
        //初始化
        LearnResult learnResult = new LearnResult();
        int[] day_learned_time_list = new int[365];
        int total_learned = 0;
        double average_learned_time = 0.0;
        double beat_people_percent = 0.0;
        int count = 0;
        Calendar c = Calendar.getInstance();
        DayLearningTimeExample dayLearningTimeExample = new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        count = dayLearningTimes.size();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

        int index = c.get(Calendar.DAY_OF_YEAR) - count + 1;
        if (dayLearningTimes != null && dayLearningTimes.size() > 0) {
//            Date date = sdf.parse(dayLearningTimes.get(0).getToday());      //得到第一天的日期。
//            Date date1 = GetId.addDate(date, dayLearningTimes.size());
//            day_learned_time_list=new int[dayLearningTimes.size()];
            for (DayLearningTime d : dayLearningTimes) {
                Integer time = d.getTime();
                day_learned_time_list[index] = time;
                index++;
                total_learned += time;
//            String today = d.getToday();
            }
        }
        average_learned_time = ((double) total_learned / count);
        //打败百分比
        int number = 0;
        boolean flag;
        Integer[] UserId = null;
        UserLearnExample userLearnExample = new UserLearnExample();
        userLearnExample.createCriteria();
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        if (userLearns != null && userLearns.size() > 0) {
            UserId = new Integer[userLearns.size()];
        }
        DayLearningTimeExample dayLearningTimeExample2 = new DayLearningTimeExample();
        List<DayLearningTime> dayLearningTimes1 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample2);
        if (dayLearningTimes1 != null && dayLearningTimes1.size() > 0) {
            //得到所有的userid
            for (DayLearningTime d : dayLearningTimes1) {
                flag = true;
                if (number < value) {
                    Integer userId1 = d.getUserId();
                    for (Integer i : UserId) {
                        if (i == userId1) {
                            flag = false;
                        }
                    }
                    if (flag) {        //没在UserId中找到UserId1
                        UserId[number] = userId1;
                        number++;
                    }
                }
            }
        }
        //求所有人的平均时间
        Double[] allId = new Double[number];
        for (Integer i : UserId) {
            DayLearningTimeExample dayLearningTimeExample3 = new DayLearningTimeExample();
            if (i != null) {       //userid不为空,就遍历他的学习时间。
                dayLearningTimeExample3.createCriteria().andUserIdEqualTo(i);
                List<DayLearningTime> dayLearningTimes2 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample3);
                int n = 0;
                int nowCount = dayLearningTimes2.size();
                int nowIndex = c.get(Calendar.DAY_OF_YEAR) - nowCount + 1;
                int total = 0;
                for (DayLearningTime d : dayLearningTimes2) {
                    Integer time = d.getTime();
                    total += time;
                }
                allId[n] = (total / (double) nowCount);
            } else {
                continue;
            }
        }
        //求打败百分比
        int many = 0;
//        if (average_learned_time == 0.0) {
//            learnResult.setAverage_learned_time(0.0);
//            learnResult.setBeat_people_percent(0.0);
//        } else {
//            if (allId != null && allId.length > 0) {
//                for (Double d : allId) {
//                    if (d != null) {
//                        if (average_learned_time > d) {
//                            many++;
//                        } else {
//                            continue;
//                        }
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        }
        if (allId != null && allId.length > 0) {        //有其他人
            for (Double d : allId) {
                if (average_learned_time == 0.0) {
                    beat_people_percent = 0.0;
                    average_learned_time = 0.0;
                } else {
                    if (average_learned_time > d) {
                        many++;
                    }
                }
            }
            beat_people_percent = ((double) many / (double) (allId.length)) * 100;
        } else {      //没有其他人，只有一个人。
            beat_people_percent = 1.0;
        }
        //遍历每天找出学习的放入map，在使用另外的数组存放新的数据。
        for (int i = 0; i < 365; i++) {
            if (day_learned_time_list[i] != 0) {
                map.put(i, day_learned_time_list[i]);
            }
        }
        int[] day_learned_time_list2 = new int[map.size()];
        int x = 0;
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            day_learned_time_list2[x++] = i.getValue();
        }
//            map.entrySet();

        learnResult.setAverage_learned_time(average_learned_time);
        learnResult.setBeat_people_percent(beat_people_percent);
        learnResult.setDay_learned_time_list(day_learned_time_list2);
        learnResult.setTotal_learned(total_learned);

        return JsonUtils.objectToJson(learnResult);
    }

    @Override
    public String get_time_slot(Integer userId) {
        if (userId == null) { //是否传入了userId
            return JsonUtils.objectToJson("fail");
        }
        //看有没有这个人，没人直接返回fail，存在这个人吗，在user表中。
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() <= 0) {
            return JsonUtils.objectToJson("fail");
        }
//            Map<Integer, Integer> result=new HashMap<Integer, Integer>();
//            Map<Integer, Float> result2=new HashMap<Integer, Float>();
        double[] day = new double[1440];
        int[] temp = new int[1440];
        int all = 0;
        Date now = new Date();
        int count = 0;
        Calendar c = Calendar.getInstance();
        //遍历这个人的所有每日学习时间表，得到总共时间all。
        DayLearningTimeExample dayLearningTimeExample = new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        if (dayLearningTimes != null && dayLearningTimes.size() > 0) {
            //得到到目前为止所学习时间
//            for (DayLearningTime d : dayLearningTimes) {
//                Integer time = d.getTime() / 60;   //精确到分钟
//                all += time;          //总共有多少时间    用于计算概率
//            }
            count = dayLearningTimes.size();  ///学习了多少天。
        }
        int index = c.get(Calendar.DAY_OF_YEAR) - count + 1;
        //学习时段分布百分比，精确到一天的每分
        UserLearnExample userLearnExample = new UserLearnExample();
        userLearnExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        int value = 0;
        Date date = new Date();
        date.setTime(userLearns.get(0).getStartTime());
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(userLearns.get(0).getStartTime()));     //第一天
        if (value <= count) {//遍历所有天的学习情况。
            cal1.setTime(GetId.addDate(cal1.getTime(), value));//每次增加value天，因为每次都是第一天，开始，所以第一次也是增加0天
            Calendar cal2 = Calendar.getInstance();
            if (userLearns != null && userLearns.size() > 0) {
                //遍历到目前为止的所有学习时长    找到里面的学习时间放入map   每次让value+1
                for (UserLearn u : userLearns) {
                    cal2.setTime(new Date(u.getStartTime()));
                    if (cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6)) {   //同一天
                        Long startTime = u.getStartTime() / 1000;
                        Long endTime = u.getEndTime() / 1000;
                        long l = startTime % 86400 / 60;           //一小时时86400秒
                        long l1 = endTime % 86400 / 60;
                        now.setTime(startTime * 1000);
                        //开始遍历从开始到结束，把那会的时间赋值为多少,
                        while (l <= l1) {
//                            day[(int)l]+=1;
                            temp[(int) l] += 1;     //将这会学习的放在学习时间里面。
                            l++;
                        }
                    }
                    //不是同一天,就直接退出，继续找下一天。
                    if (cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) != cal2.get(6)) {
                        break;
                    }
                }
            }
            //记得下一天，不然value一直没变。一直都是第一天。
            value++;
        }
        //得到所有时间。
        for(int i = 0;i<temp.length;i++){
            all+=temp[i];
        }

        //学习时段分布百分比,遍历day的表，查找不是0的
        if (all != 0) {
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == 0) {
                    continue;
                } else {
                    day[j] = (temp[j] / (double) all);
                }
            }
        } else {
            return JsonUtils.objectToJson("时长0");
        }
        return JsonUtils.objectToJson(day);
    }
}
