package com.hlwxy.xu_boot2.system.domain;

import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;

import java.util.Set;

/**
 * 日总结扩展类
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class DaySummaryExtendDO extends PeopleDO {
    private Integer id;
    private String day_summary_code;//日计划编码
    private String day_summary_time;
    private String day_summary_content;
    private String day_summary_entry_time;
    private String day_summary_reply;
    private String day_summary_see;
    private Integer day_summary_state;//总结状态
    private Integer peo_id;
    private MonthlyPlan monthlyPlan;
    private WeekPlanDO weekPlanDO;
    private DayPlanDO dayPlanDO;
    private Integer countSumm;

    public DaySummaryExtendDO() {
    }

    public DaySummaryExtendDO(Integer id, String day_summary_code, String day_summary_time, String day_summary_content, String day_summary_entry_time, String day_summary_reply, String day_summary_see, Integer day_summary_state, Integer peo_id, MonthlyPlan monthlyPlan, WeekPlanDO weekPlanDO, DayPlanDO dayPlanDO, Integer countSumm) {
        this.id = id;
        this.day_summary_code = day_summary_code;
        this.day_summary_time = day_summary_time;
        this.day_summary_content = day_summary_content;
        this.day_summary_entry_time = day_summary_entry_time;
        this.day_summary_reply = day_summary_reply;
        this.day_summary_see = day_summary_see;
        this.day_summary_state = day_summary_state;
        this.peo_id = peo_id;
        this.monthlyPlan = monthlyPlan;
        this.weekPlanDO = weekPlanDO;
        this.dayPlanDO = dayPlanDO;
        this.countSumm = countSumm;
    }

    public DaySummaryExtendDO(Integer id, String username, String password, String email, String peopleCode, String jobNumber, String peoName, Integer morningState, Integer afternoonState, Integer userId, String gsId, String bmId, String positionId, Integer dayPlanId, Integer daySummaryId, Integer weekPlanId, Integer weekSummaryId, Integer monthlyPlanId, Integer monthlySummaryId, Set<RoleDo> roles, Integer id1, String day_summary_code, String day_summary_time, String day_summary_content, String day_summary_entry_time, String day_summary_reply, String day_summary_see, Integer day_summary_state, Integer peo_id, MonthlyPlan monthlyPlan, WeekPlanDO weekPlanDO, DayPlanDO dayPlanDO, Integer countSumm) {
        super(id, username, password, email, peopleCode, jobNumber, peoName, morningState, afternoonState, userId, gsId, bmId, positionId, dayPlanId, daySummaryId, weekPlanId, weekSummaryId, monthlyPlanId, monthlySummaryId, roles);
        this.id = id1;
        this.day_summary_code = day_summary_code;
        this.day_summary_time = day_summary_time;
        this.day_summary_content = day_summary_content;
        this.day_summary_entry_time = day_summary_entry_time;
        this.day_summary_reply = day_summary_reply;
        this.day_summary_see = day_summary_see;
        this.day_summary_state = day_summary_state;
        this.peo_id = peo_id;
        this.monthlyPlan = monthlyPlan;
        this.weekPlanDO = weekPlanDO;
        this.dayPlanDO = dayPlanDO;
        this.countSumm = countSumm;
    }

    public Integer getCountSumm() {
        return countSumm;
    }

    public void setCountSumm(Integer countSumm) {
        this.countSumm = countSumm;
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

    public DayPlanDO getDayPlanDO() {
        return dayPlanDO;
    }

    public void setDayPlanDO(DayPlanDO dayPlanDO) {
        this.dayPlanDO = dayPlanDO;
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

    public String getDay_summary_code() {
        return day_summary_code;
    }

    public void setDay_summary_code(String day_summary_code) {
        this.day_summary_code = day_summary_code;
    }

    public String getDay_summary_time() {
        return day_summary_time;
    }

    public void setDay_summary_time(String day_summary_time) {
        this.day_summary_time = day_summary_time;
    }

    public String getDay_summary_content() {
        return day_summary_content;
    }

    public void setDay_summary_content(String day_summary_content) {
        this.day_summary_content = day_summary_content;
    }

    public String getDay_summary_entry_time() {
        return day_summary_entry_time;
    }

    public void setDay_summary_entry_time(String day_summary_entry_time) {
        this.day_summary_entry_time = day_summary_entry_time;
    }

    public String getDay_summary_reply() {
        return day_summary_reply;
    }

    public void setDay_summary_reply(String day_summary_reply) {
        this.day_summary_reply = day_summary_reply;
    }

    public String getDay_summary_see() {
        return day_summary_see;
    }

    public void setDay_summary_see(String day_summary_see) {
        this.day_summary_see = day_summary_see;
    }

    public Integer getDay_summary_state() {
        return day_summary_state;
    }

    public void setDay_summary_state(Integer day_summary_state) {
        this.day_summary_state = day_summary_state;
    }
}
