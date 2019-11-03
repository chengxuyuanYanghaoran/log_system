package com.hlwxy.xu_boot2.system.domain;

/**
 * 周总结实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class WeekSummaryDO {
    private Integer id;
    private String week_summary_code;//周总结编码
    private String week_summary_time;
    private String week_summary_content;
    private String week_summary_entry_time;
    private String week_summary_reply;
    private String week_summary_see;
    private Integer week_summary_state;//总结状态
    private Integer peo_id;

    public WeekSummaryDO() {
    }

    public WeekSummaryDO(Integer id, String week_summary_code, String week_summary_time, String week_summary_content, String week_summary_entry_time, String week_summary_reply, String week_summary_see, Integer week_summary_state, Integer peo_id) {
        this.id = id;
        this.week_summary_code = week_summary_code;
        this.week_summary_time = week_summary_time;
        this.week_summary_content = week_summary_content;
        this.week_summary_entry_time = week_summary_entry_time;
        this.week_summary_reply = week_summary_reply;
        this.week_summary_see = week_summary_see;
        this.week_summary_state = week_summary_state;
        this.peo_id = peo_id;
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

    public String getWeek_summary_code() {
        return week_summary_code;
    }

    public void setWeek_summary_code(String week_summary_code) {
        this.week_summary_code = week_summary_code;
    }

    public String getWeek_summary_time() {
        return week_summary_time;
    }

    public void setWeek_summary_time(String week_summary_time) {
        this.week_summary_time = week_summary_time;
    }

    public String getWeek_summary_content() {
        return week_summary_content;
    }

    public void setWeek_summary_content(String week_summary_content) {
        this.week_summary_content = week_summary_content;
    }

    public String getWeek_summary_entry_time() {
        return week_summary_entry_time;
    }

    public void setWeek_summary_entry_time(String week_summary_entry_time) {
        this.week_summary_entry_time = week_summary_entry_time;
    }

    public String getWeek_summary_reply() {
        return week_summary_reply;
    }

    public void setWeek_summary_reply(String week_summary_reply) {
        this.week_summary_reply = week_summary_reply;
    }

    public String getWeek_summary_see() {
        return week_summary_see;
    }

    public void setWeek_summary_see(String week_summary_see) {
        this.week_summary_see = week_summary_see;
    }

    public Integer getWeek_summary_state() {
        return week_summary_state;
    }

    public void setWeek_summary_state(Integer week_summary_state) {
        this.week_summary_state = week_summary_state;
    }
}
