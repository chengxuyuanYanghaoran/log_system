package com.hlwxy.xu_boot2.system.domain;

/**
 * 周计划实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class WeekPlanDO {
    private Integer id;
    private String week_plan_code;//周计划编码
    private String week_plan_time; //页面自动生成------下周
    private String week_plan_entry_end_time;//自动生成----下周第一天
    private String week_plan_content;//页面编写
    private String week_plan_entry_time;//当前时间
    private String week_plan_reply;//编写--------是否回复
    private String week_plan_see;//点击----------是否查看
    private Integer week_plan_state;//计划状态
    private Integer peo_id;
    private String monthly_plan_time;

    public WeekPlanDO() {
    }

    public WeekPlanDO(Integer id, String week_plan_code, String week_plan_time, String week_plan_entry_end_time, String week_plan_content, String week_plan_entry_time, String week_plan_reply, String week_plan_see, Integer week_plan_state, Integer peo_id, String monthly_plan_time) {
        this.id = id;
        this.week_plan_code = week_plan_code;
        this.week_plan_time = week_plan_time;
        this.week_plan_entry_end_time = week_plan_entry_end_time;
        this.week_plan_content = week_plan_content;
        this.week_plan_entry_time = week_plan_entry_time;
        this.week_plan_reply = week_plan_reply;
        this.week_plan_see = week_plan_see;
        this.week_plan_state = week_plan_state;
        this.peo_id = peo_id;
        this.monthly_plan_time = monthly_plan_time;
    }

    public String getMonthly_plan_time() {
        return monthly_plan_time;
    }

    public void setMonthly_plan_time(String monthly_plan_time) {
        this.monthly_plan_time = monthly_plan_time;
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

    public String getWeek_plan_code() {
        return week_plan_code;
    }

    public void setWeek_plan_code(String week_plan_code) {
        this.week_plan_code = week_plan_code;
    }

    public String getWeek_plan_time() {
        return week_plan_time;
    }

    public void setWeek_plan_time(String week_plan_time) {
        this.week_plan_time = week_plan_time;
    }

    public String getWeek_plan_entry_end_time() {
        return week_plan_entry_end_time;
    }

    public void setWeek_plan_entry_end_time(String week_plan_entry_end_time) {
        this.week_plan_entry_end_time = week_plan_entry_end_time;
    }

    public String getWeek_plan_content() {
        return week_plan_content;
    }

    public void setWeek_plan_content(String week_plan_content) {
        this.week_plan_content = week_plan_content;
    }

    public String getWeek_plan_entry_time() {
        return week_plan_entry_time;
    }

    public void setWeek_plan_entry_time(String week_plan_entry_time) {
        this.week_plan_entry_time = week_plan_entry_time;
    }

    public String getWeek_plan_reply() {
        return week_plan_reply;
    }

    public void setWeek_plan_reply(String week_plan_reply) {
        this.week_plan_reply = week_plan_reply;
    }

    public String getWeek_plan_see() {
        return week_plan_see;
    }

    public void setWeek_plan_see(String week_plan_see) {
        this.week_plan_see = week_plan_see;
    }

    public Integer getWeek_plan_state() {
        return week_plan_state;
    }

    public void setWeek_plan_state(Integer week_plan_state) {
        this.week_plan_state = week_plan_state;
    }
}
