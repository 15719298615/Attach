package com.attach.learn.commons.pojo;

import java.util.Arrays;

/**
 * 本周每天对应的学习时间列表(day_learned_time_list)（应该是7天，未到7天按小值）
 * 本周学习总时间(week_total_learn_time)
 * 平均学习时间(average_learn_time)（不满7天，按实际天算）
 * 打败人数百分比(beat_people_percent)
 */

public class LearnResult {
    private int[] day_learned_time_list;
    private int total_learned;
    private double average_learned_time;
    private double beat_people_percent;

    public int[] getDay_learned_time_list() {
        return day_learned_time_list;
    }

    public void setDay_learned_time_list(int[] day_learned_time_list) {
        this.day_learned_time_list = day_learned_time_list;
    }

    public int getTotal_learned() {
        return total_learned;
    }

    public void setTotal_learned(int total_learned) {
        this.total_learned = total_learned;
    }

    public double getAverage_learned_time() {
        return average_learned_time;
    }

    public void setAverage_learned_time(double average_learned_time) {
        this.average_learned_time = average_learned_time;
    }

    public double getBeat_people_percent() {
        return beat_people_percent;
    }

    public void setBeat_people_percent(double beat_people_percent) {
        this.beat_people_percent = beat_people_percent;
    }
}
