package com.hlwxy.xu_boot2.system.domain;

import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;

import java.util.Set;

/**
 * 月计划实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class DayPlanExtendDO extends PeopleDO{
    private Integer id;
    private String day_plan_code;//日计划编码
    private String day_plan_time; //页面自动生成------下月
    private String day_plan_entry_end_time;//自动生成----下月第一天
    private String day_plan_content;//页面编写
    private String day_plan_entry_time;//当前时间
    private String day_plan_reply;//生成--------是否查看
    private String day_plan_see;//编写----------是否回复
    private Integer day_plan_state;//计划状态
    private Integer peo_id;
    private MonthlyPlan monthlyPlan;
    private WeekPlanDO weekPlanDO;
    private Integer countPlan;

    public DayPlanExtendDO() {
    }

    public DayPlanExtendDO(Integer id, String day_plan_code, String day_plan_time, String day_plan_entry_end_time, String day_plan_content, String day_plan_entry_time, String day_plan_reply, String day_plan_see, Integer day_plan_state, Integer peo_id, MonthlyPlan monthlyPlan, WeekPlanDO weekPlanDO, Integer countPlan) {
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
        this.monthlyPlan = monthlyPlan;
        this.weekPlanDO = weekPlanDO;
        this.countPlan = countPlan;
    }

    public DayPlanExtendDO(Integer id, String username, String password, String email, String peopleCode, String jobNumber, String peoName, Integer morningState, Integer afternoonState, Integer userId, String gsId, String bmId, String positionId, Integer dayPlanId, Integer daySummaryId, Integer weekPlanId, Integer weekSummaryId, Integer monthlyPlanId, Integer monthlySummaryId, Set<RoleDo> roles, Integer id1, String day_plan_code, String day_plan_time, String day_plan_entry_end_time, String day_plan_content, String day_plan_entry_time, String day_plan_reply, String day_plan_see, Integer day_plan_state, Integer peo_id, MonthlyPlan monthlyPlan, WeekPlanDO weekPlanDO, Integer countPlan) {
        super(id, username, password, email, peopleCode, jobNumber, peoName, morningState, afternoonState, userId, gsId, bmId, positionId, dayPlanId, daySummaryId, weekPlanId, weekSummaryId, monthlyPlanId, monthlySummaryId, roles);
        this.id = id1;
        this.day_plan_code = day_plan_code;
        this.day_plan_time = day_plan_time;
        this.day_plan_entry_end_time = day_plan_entry_end_time;
        this.day_plan_content = day_plan_content;
        this.day_plan_entry_time = day_plan_entry_time;
        this.day_plan_reply = day_plan_reply;
        this.day_plan_see = day_plan_see;
        this.day_plan_state = day_plan_state;
        this.peo_id = peo_id;
        this.monthlyPlan = monthlyPlan;
        this.weekPlanDO = weekPlanDO;
        this.countPlan = countPlan;
    }

    public Integer getCountPlan() {
        return countPlan;
    }

    public void setCountPlan(Integer countPlan) {
        this.countPlan = countPlan;
    }

    public MonthlyPlan getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(MonthlyPlan monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public WeekPlanDO getWeekPlanDO() {
        return weekPlanDO;
    }

    public void setWeekPlanDO(WeekPlanDO weekPlanDO) {
        this.weekPlanDO = weekPlanDO;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
