package com.hlwxy.xu_boot2.system.domain;

import java.io.Serializable;

/**
 * 月计划实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class MonthlyPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String monthly_plan_code;//月计划编码
    private String monthly_plan_time; //页面自动生成------下月
    private String monthly_plan_entry_end_time;//自动生成----下月第一天
    private String monthly_plan_content;//页面编写
    private String monthly_plan_entry_time;//当前时间
    private String monthly_plan_reply;//编写--------是否回复
    private String monthly_plan_see;//点击----------是否查看
    private Integer monthly_plan_state;//计划状态
    private Integer peo_id;

    public MonthlyPlan() {
    }

    public MonthlyPlan(Integer id, String monthly_plan_code, String monthly_plan_time, String monthly_plan_entry_end_time, String monthly_plan_content, String monthly_plan_entry_time, String monthly_plan_reply, String monthly_plan_see, Integer monthly_plan_state, Integer peo_id) {
        this.id = id;
        this.monthly_plan_code = monthly_plan_code;
        this.monthly_plan_time = monthly_plan_time;
        this.monthly_plan_entry_end_time = monthly_plan_entry_end_time;
        this.monthly_plan_content = monthly_plan_content;
        this.monthly_plan_entry_time = monthly_plan_entry_time;
        this.monthly_plan_reply = monthly_plan_reply;
        this.monthly_plan_see = monthly_plan_see;
        this.monthly_plan_state = monthly_plan_state;
        this.peo_id = peo_id;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer pro_id) {
        this.peo_id = pro_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonthly_plan_code() {
        return monthly_plan_code;
    }

    public void setMonthly_plan_code(String monthly_plan_code) {
        this.monthly_plan_code = monthly_plan_code;
    }

    public String getMonthly_plan_time() {
        return monthly_plan_time;
    }

    public void setMonthly_plan_time(String monthly_plan_time) {
        this.monthly_plan_time = monthly_plan_time;
    }

    public String getMonthly_plan_entry_end_time() {
        return monthly_plan_entry_end_time;
    }

    public void setMonthly_plan_entry_end_time(String monthly_plan_entry_end_time) {
        this.monthly_plan_entry_end_time = monthly_plan_entry_end_time;
    }

    public String getMonthly_plan_content() {
        return monthly_plan_content;
    }

    public void setMonthly_plan_content(String monthly_plan_content) {
        this.monthly_plan_content = monthly_plan_content;
    }

    public String getMonthly_plan_entry_time() {
        return monthly_plan_entry_time;
    }

    public void setMonthly_plan_entry_time(String monthly_plan_entry_time) {
        this.monthly_plan_entry_time = monthly_plan_entry_time;
    }

    public String getMonthly_plan_reply() {
        return monthly_plan_reply;
    }

    public void setMonthly_plan_reply(String monthly_plan_reply) {
        this.monthly_plan_reply = monthly_plan_reply;
    }

    public String getMonthly_plan_see() {
        return monthly_plan_see;
    }

    public void setMonthly_plan_see(String monthly_plan_see) {
        this.monthly_plan_see = monthly_plan_see;
    }

    public Integer getMonthly_plan_state() {
        return monthly_plan_state;
    }

    public void setMonthly_plan_state(Integer monthly_plan_state) {
        this.monthly_plan_state = monthly_plan_state;
    }
}
