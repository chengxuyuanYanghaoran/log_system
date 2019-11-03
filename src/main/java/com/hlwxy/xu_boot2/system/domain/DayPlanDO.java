package com.hlwxy.xu_boot2.system.domain;

/**
 * 日计划实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class DayPlanDO {
    private Integer id;
    private String day_plan_code;//日计划编码
    private String day_plan_time; //页面自动生成------下周
    private String day_plan_entry_end_time;//自动生成----下周第一天
    private String day_plan_content;//页面编写
    private String day_plan_entry_time;//当前时间
    private String day_plan_reply;//编写--------是否回复
    private String day_plan_see;//点击----------是否查看
    private Integer day_plan_state;//计划状态
    private Integer peo_id;
    private String week_plan_time;

    public DayPlanDO() {
    }

    public DayPlanDO(Integer id, String day_plan_code, String day_plan_time, String day_plan_entry_end_time, String day_plan_content, String day_plan_entry_time, String day_plan_reply, String day_plan_see, Integer day_plan_state, Integer peo_id, String week_plan_time) {
        this.id = id;
        this.day_plan_code = day_plan_code;
        this.day_plan_time = day_plan_time;
        this.day_plan_entry_end_time = day_plan_entry_end_time;
        this.day_plan_content = day_plan_content;
        this.day_plan_entry_time = day_plan_entry_time;
        this.day_plan_reply = day_plan_reply;
        this.day_plan_see = day_plan_see;
        this.day_plan_state = day_plan_state;
        this.peo_id = peo_id;
        this.week_plan_time = week_plan_time;
    }

    public String getWeek_plan_time() {
        return week_plan_time;
    }

    public void setWeek_plan_time(String week_plan_time) {
        this.week_plan_time = week_plan_time;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay_plan_code() {
        return day_plan_code;
    }

    public void setDay_plan_code(String day_plan_code) {
        this.day_plan_code = day_plan_code;
    }

    public String getDay_plan_time() {
        return day_plan_time;
    }

    public void setDay_plan_time(String day_plan_time) {
        this.day_plan_time = day_plan_time;
    }

    public String getDay_plan_entry_end_time() {
        return day_plan_entry_end_time;
    }

    public void setDay_plan_entry_end_time(String day_plan_entry_end_time) {
        this.day_plan_entry_end_time = day_plan_entry_end_time;
    }

    public String getDay_plan_content() {
        return day_plan_content;
    }

    public void setDay_plan_content(String day_plan_content) {
        this.day_plan_content = day_plan_content;
    }

    public String getDay_plan_entry_time() {
        return day_plan_entry_time;
    }

    public void setDay_plan_entry_time(String day_plan_entry_time) {
        this.day_plan_entry_time = day_plan_entry_time;
    }

    public String getDay_plan_reply() {
        return day_plan_reply;
    }

    public void setDay_plan_reply(String day_plan_reply) {
        this.day_plan_reply = day_plan_reply;
    }

    public String getDay_plan_see() {
        return day_plan_see;
    }

    public void setDay_plan_see(String day_plan_see) {
        this.day_plan_see = day_plan_see;
    }

    public Integer getDay_plan_state() {
        return day_plan_state;
    }

    public void setDay_plan_state(Integer day_plan_state) {
        this.day_plan_state = day_plan_state;
    }
}
