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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return 0;
        }
            if(userId==null) {
                return 0;
            }
            Integer all=0;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
        String date=sdf.format(new Date());
        DayLearningTimeExample dayLearningTimeExample=new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId).andTodayEqualTo(date);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        if(dayLearningTimes!=null && dayLearningTimes.size()>0){
            for(DayLearningTime d:dayLearningTimes){
                all+= d.getTime();
            }
        }
        return all;
    }

    @Override
    public String start_time(Integer userId, Long currentTime) {
        if(userId==null || currentTime==null){
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);

        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }
        UserLearn userLearn=new UserLearn();
        //id是自增的不用放
        userLearn.setUserId(userId);
        userLearn.setStartTime(currentTime);
        int result = userLearnMapper.insert(userLearn);
        if(result>0){
            return JsonUtils.objectToJson("success");
        }else{
            return JsonUtils.objectToJson("fail");
        }
    }

    @Override
    public String end_time(Integer userId, Long currentTime) {
        if(userId==null || currentTime==null){
            return JsonUtils.objectToJson("fail");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }
        DayLearningTime dayLearningTime=new DayLearningTime();

        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
        String date=sdf.format(new Date());

        DayLearningTimeExample dayLearningTimeExample=new DayLearningTimeExample();
        dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId).andTodayEqualTo(date);
        List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
        Integer id = dayLearningTimes.get(0).getId();


        UserLearnExample userLearnExample=new UserLearnExample();
        UserLearnExample.Criteria criteria = userLearnExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        if(userLearns!=null && userLearns.size()>0){
            //遍历找到那个结束时间是null的行，并且更新，如果前面还有肯定就是开始不为空，结束也不为null
            for(UserLearn u:userLearns){
                //开始时间不为空   结束时间为空    开始时间要小于当前时间    需要往结束时间放入当前时间
                if(u.getEndTime()==null && u.getStartTime()!=null && u.getStartTime()<currentTime){
//                    UserLearnExample userLearnExample1=new UserLearnExample();
//                    UserLearnExample.Criteria criteria1= userLearnExample.createCriteria().andUserIdEqualTo(userId);
//                    if(userLearnExample1!=null){
                        UserLearn userLearn=new UserLearn();
                        //更新要更新哪一行，得知道id
                        userLearn.setId(u.getId());
                        userLearn.setUserId(userId);
                        userLearn.setStartTime(u.getStartTime());
                        userLearn.setEndTime(currentTime);
                        int i = userLearnMapper.updateByExample(userLearn, userLearnExample);
                        if(i>0 ) {
                            if(dayLearningTimes.size()>0 && dayLearningTimes!=null){       //不是第一次，就得到时间加上原来的时间并且更新。

                                        Integer time = dayLearningTimes.get(0).getTime();
                                        time+=(int)( currentTime-u.getStartTime())/1000;
                                        dayLearningTime.setId(id);
                                        dayLearningTime.setUserId(userId);
                                        dayLearningTime.setTime(time);
                                        dayLearningTime.setToday(date);
                                        int update = dayLearningTimeMapper.updateByExample(dayLearningTime, dayLearningTimeExample);
                                        if(update>0){
                                            return JsonUtils.objectToJson("success");   //更新成功
                                        }
                            }else{                //今天第一次插入
                                dayLearningTime.setUserId(userId);
                                dayLearningTime.setToday(date);
                                dayLearningTime.setTime((int)( currentTime-u.getStartTime())/1000);
                                int insert = dayLearningTimeMapper.insert(dayLearningTime);
                                if(insert>0){
                                    return JsonUtils.objectToJson("success");   //更新成功
                                }
                           }
                        }else {
                            return JsonUtils.objectToJson("fail");     //更新失败
                        }
//                    }else {
//                        return JsonUtils.objectToJson("status:[fail]");     //没有得到
//                    }
                }
            }
            return JsonUtils.objectToJson("fail");     //没找到符合条件的
        }
        return JsonUtils.objectToJson("fail");     //userLearn为空
    }

    @Override
    public String current_status(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }

        if(userId==null){
            return JsonUtils.objectToJson("fail");
        }

        UserLearnExample userLearnExample=new UserLearnExample();
        userLearnExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
        Date current=new Date();
        Date date=new Date();
        Calendar c = Calendar.getInstance();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        c.set(year+1900, month, day, 6, 0);
        long all=0;
        int comp=(int)current.getTime()/1000;
        if(userLearns!=null && userLearns.size()>0){
            for(UserLearn u:userLearns){
                Long startTime = u.getStartTime()/1000;
                if(u.getEndTime()==null&& u.getStartTime()<c.getTimeInMillis()){
                    all=c.getTimeInMillis()-u.getStartTime();
                    StringBuilder stringBuilder=new StringBuilder();
                    long hours=all/3600;
                    long minites=(all-3600*hours)/60;
                    long seconds=(all-3600*hours-60*minites)%60;
                    stringBuilder.append("learning,学习时长:").append(":").append(hours).append(".").append(minites).append(".").append(seconds);
                    return JsonUtils.objectToJson(stringBuilder);
//                if(u.getEndTime()!=null){
//                    Long endTime = u.getEndTime()/1000;
//                    if(u.getStartTime()>c.getTimeInMillis()){     //开始时间大于当前的6点   说明今天的学习早就开始了
//                        if(comp>startTime && endTime==null){   // 遍历今天的   当前时间大于开始时间    没有结束时间    学习状态
//                            all+=(int)(comp-startTime);
//                        }else if(comp>endTime){       //当前时间大于这次的结束时间    将这些时间加到今天时间内。
//                            all+= (int) (endTime-startTime);
//                        } else{
//                            return JsonUtils.objectToJson("status:[resting]");;     //都不满足就说明今天还没开始学习
//                        }
//                    }
                }
//                else {
//                    return JsonUtils.objectToJson("status:[fail]");
//                }
            }
            return JsonUtils.objectToJson("resting");
        }
        return JsonUtils.objectToJson("fail");
    }

    @Override
    public String get_week_record(Integer userId) throws ParseException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
            if(userId==null){
                return JsonUtils.objectToJson("fail");
            }
            LearnResult learnResult=new LearnResult();
            int[] day_learned_time_list=null;
            int week_total_learn_time=0;
            double average_learned_time=0.0;
            double beat_people_percent=0.0;
            Date now=new Date();
            Calendar c=Calendar.getInstance();
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK );    //1是周日
            // 今天是一周中的第几天
            if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            // 计算一周开始的日期
            c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
            for (int i=1;i<dayOfWeek;i++) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }


            int count=0;
            int index=0;
            int all=0;
            long time1 = 0;
            DayLearningTimeExample dayLearningTimeExample=new DayLearningTimeExample();
            dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
            List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
            if(dayLearningTimes!=null && dayLearningTimes.size()>0){
//            time1 = dayLearningTimes.get(dayLearningTimes.size()).getTime()/1000;    //最后一次的时间
                Date date = sdf.parse(dayLearningTimes.get(0).getToday());
                time1=date.getTime()/1000;      //最后一天的时间
            }
//        if(time1==null) {
//            return JsonUtils.objectToJson("status:[fail]");
//        }
            long r=time1-604800;     //最后一次之前一周的
            String start=dayLearningTimes.get(0).getToday();          //得到第一天的时间，进行比较，是否在一周前注册。
            if(sdf.parse(start).getTime()/1000<c.getTimeInMillis()/1000-604800) {
                r = time1 - 604800;
            }else{
//                r=Long.parseLong(dayLearningTimes.get(0).getToday())/1000;
                r=sdf.parse(start).getTime()/1000;
            }
            if(dayLearningTimes!=null && dayLearningTimes.size()>0){
                day_learned_time_list=new int[dayLearningTimes.size()];
                for(DayLearningTime d:dayLearningTimes){
                    String today = d.getToday();
//                    long l = Long.parseLong(today)/1000;
                    long l = sdf.parse(today).getTime()/1000;
                    if(l<r){
                        continue;
                    }
                    if(l>r && l<time1){       //这一周内的
                        day_learned_time_list[count]=d.getTime();     //存放时间
                        all+=d.getTime();
                    }
                }
            }
            average_learned_time=((double)all/day_learned_time_list.length);

            //求打败人数百分比
            int number=0;
            boolean flag;
            Integer[] UserId=new Integer[100];
            DayLearningTimeExample dayLearningTimeExample2=new DayLearningTimeExample();
            List<DayLearningTime> dayLearningTimes1 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample2);
            if(dayLearningTimes1!=null && dayLearningTimes1.size()>0){
                //得到所有的userid
                for(DayLearningTime d:dayLearningTimes1){
                    flag=true;
                    if(number<100) {
                        Integer userId1 = d.getUserId();
                        for (Integer i : UserId) {
                            if (i == userId1) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            UserId[number] = userId1;
                            number++;
                        }
                    }
                }
            }
            //求所有人的平均时间
            Double[] allIdRate=new Double[number];
            DayLearningTimeExample dayLearningTimeExample3=new DayLearningTimeExample();
            for (Integer i : UserId) {
                if(i!=null) {
                    dayLearningTimeExample3.createCriteria().andUserIdEqualTo(i);
                    List<DayLearningTime> dayLearningTimes2 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample3);
                    int n = 0;
                    int total = 0;
                    for (DayLearningTime d : dayLearningTimes) {
                        String today = d.getToday();
                        Integer time = d.getTime();
                        total += time;
//                    long l = Long.parseLong(today)/1000;
                        long l = sdf.parse(today).getTime() / 1000;
                        if (l < r) {
                            continue;
                        }
                        if (l > r && l < time1) {       //这一周内的
                            total += d.getTime();
                        }
                    }
                    allIdRate[n++] = (total / (double) allIdRate.length);
                }
            }
            //求打败百分比
            int many=0;
            for(Double d:allIdRate){
                if(average_learned_time>d){
                    many++;
                }
            }
            beat_people_percent=(many/(double)(allIdRate.length));

            learnResult.setAverage_learned_time(average_learned_time);
            learnResult.setBeat_people_percent(beat_people_percent);
            learnResult.setDay_learned_time_list(day_learned_time_list);
            learnResult.setTotal_learned(week_total_learn_time);
            return JsonUtils.objectToJson(learnResult);
    }

    @Override
    public String get_all_record(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        HashMap<Integer,Integer> map=new HashMap();
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }

            if(userId==null){
                return JsonUtils.objectToJson("fail");
            }
            LearnResult learnResult=new LearnResult();
            int[] day_learned_time_list=new int[365];
            int total_learned = 0;
            double average_learned_time=0.0;
            double beat_people_percent=0.0;
            int count=0;
//        Date now=new Date();
            Calendar c=Calendar.getInstance();
            DayLearningTimeExample dayLearningTimeExample=new DayLearningTimeExample();
            dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
            List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
            count=dayLearningTimes.size();

            int index=c.get(Calendar.DAY_OF_YEAR)-count+1;
            if(dayLearningTimes!=null && dayLearningTimes.size()>0){
//                day_learned_time_list=new int[dayLearningTimes.size()];
                for(DayLearningTime d:dayLearningTimes){
                    Integer time = d.getTime();
                    day_learned_time_list[index]=time;
                    index++;
                    total_learned+=time;
//            String today = d.getToday();
                }
            }
            average_learned_time=((double)total_learned/count);
            //打败百分比
            int number=0;
            boolean flag;
            Integer[] UserId=null;
            UserLearnExample userLearnExample=new UserLearnExample();
            userLearnExample.createCriteria();
            List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
            if(userLearns!=null && userLearns.size()>0){
                UserId=new Integer[userLearns.size()];
            }
            DayLearningTimeExample dayLearningTimeExample2=new DayLearningTimeExample();
            List<DayLearningTime> dayLearningTimes1 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample2);
            if(dayLearningTimes1!=null && dayLearningTimes1.size()>0){
                //得到所有的userid
                for(DayLearningTime d:dayLearningTimes1){
                    flag=true;
                    if(number<100) {
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
            Double[] allId=new Double[number];

            for (Integer i : UserId) {
                DayLearningTimeExample dayLearningTimeExample3=new DayLearningTimeExample();
                if(i!=null){       //userid不为空
                    dayLearningTimeExample3.createCriteria().andUserIdEqualTo(i);
                    List<DayLearningTime> dayLearningTimes2 = dayLearningTimeMapper.selectByExample(dayLearningTimeExample3);
                    int n=0;
                    int nowCount=dayLearningTimes2.size();
                    int nowIndex=c.get(Calendar.DAY_OF_YEAR)-nowCount+1;
                    int total=0;
                    for(DayLearningTime d:dayLearningTimes2){
                        Integer time = d.getTime();
                        total+=time;
                    }
                    allId[n]=(total/(double)nowCount);
                }else{
                    return JsonUtils.objectToJson("fail");
                }
            }

            //求打败百分比
            int many=0;
            for(Double d:allId){
                if(average_learned_time>d){
                    many++;
                }
            }
            beat_people_percent=(many/(double)allId.length)*100;

            //遍历每天找出学习的放入map，在使用另外的数组存放新的数据。
            for (int i=0;i<365;i++){
                if(day_learned_time_list[i]!=0){
                    map.put(i,day_learned_time_list[i]);
                }
            }
            int[] day_learned_time_list2=new int[map.size()];
            int x=0;
            for(Map.Entry<Integer,Integer> i:map.entrySet()){
                day_learned_time_list2[x++]=i.getValue();
            }
            map.entrySet();

            learnResult.setAverage_learned_time(average_learned_time);
            learnResult.setBeat_people_percent(beat_people_percent);
            learnResult.setDay_learned_time_list(day_learned_time_list2);
            learnResult.setTotal_learned(total_learned);


            return JsonUtils.objectToJson(learnResult);
    }

    @Override
    public String get_time_slot(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            return JsonUtils.objectToJson("fail");
        }

            if(userId==null){
                return JsonUtils.objectToJson("fail");
            }
            Map<Integer, Integer> result=new HashMap<Integer, Integer>();
            Map<Integer, Float> result2=new HashMap<Integer, Float>();
            double[] year=new double[1440];
            int[] temp=new int[1440];
            int all=0;
            Date now=new Date();
            int count=0;
            Calendar c=Calendar.getInstance();
            DayLearningTimeExample dayLearningTimeExample=new DayLearningTimeExample();
            dayLearningTimeExample.createCriteria().andUserIdEqualTo(userId);
            List<DayLearningTime> dayLearningTimes = dayLearningTimeMapper.selectByExample(dayLearningTimeExample);
            if(dayLearningTimes!=null && dayLearningTimes.size()>0){
                //得到到目前为止所学习时间
                for(DayLearningTime d:dayLearningTimes){
                    Integer time = d.getTime()/60;   //精确到分钟
                    all+=time;          //总共有多少时间    用于计算概率
                }
                count=dayLearningTimes.size();
                int index=c.get(Calendar.DAY_OF_YEAR)-count+1;
            }

            //学习时段分布百分比，精确到一天的每分
            UserLearnExample userLearnExample=new UserLearnExample();
            userLearnExample.createCriteria().andUserIdEqualTo(userId);
            List<UserLearn> userLearns = userLearnMapper.selectByExample(userLearnExample);
            int value=1;
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(new Date(userLearns.get(0).getStartTime()));     //第一天
            if(userLearns!=null && userLearns.size()>0){
                //遍历到目前为止的所有学习时长    找到里面的学习时间放入map   每次让value+1
                Calendar cal2 = Calendar.getInstance();
                for(UserLearn u:userLearns){
                    cal2.setTime(new Date(u.getStartTime()));
                    if(cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6)){   //同一天
                        Long startTime = u.getStartTime()/1000;
                        Long endTime = u.getEndTime()/1000;
                        long l = startTime % 86400/60;
                        long l1 = endTime % 86400/60;
//                    long t=endTime-startTime;
//                all+=t;
                        now.setTime(startTime);
                        //开始遍历从开始到结束，把那会的时间赋值为多少,
                        while (l<=l1) {
                            result.put((int) l,value);
                            temp[(int)l]=value;
                            l++;
                        }
                    }
                    //如果不一致就value++
                    if(cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) != cal2.get(6)) {   //不是同一天
                        value++;
                    }
                }
            }
            //学习时段分布百分比
            for(int i=0;i<temp.length;i++){
                if(temp[i]==0){
                    continue;
                }
                if(all!=0){
                    result2.put(i, (float) (temp[i]/(float)all)*100);
                }else{
                    return JsonUtils.objectToJson("时长0");
                }
            }
            return JsonUtils.objectToJson(result2);
    }
}
