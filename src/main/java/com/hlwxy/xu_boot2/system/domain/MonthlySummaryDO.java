package com.hlwxy.xu_boot2.system.domain;

/**
 * 月总结实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class MonthlySummaryDO {
    private Integer id;
    private String monthly_summary_code;//月总结编码
    private String monthly_summary_time;
    private String monthly_summary_content;
    private String monthly_summary_entry_time;
    private String monthly_summary_reply;
    private String monthly_summary_see;
    private Integer monthly_summary_state;//总结状态
    private Integer peo_id;

    public MonthlySummaryDO() {
    }

    public MonthlySummaryDO(Integer id, String monthly_summary_code, String monthly_summary_time, String monthly_summary_content, String monthly_summary_entry_time, String monthly_summary_reply, String monthly_summary_see, Integer monthly_summary_state, Integer peo_id) {
        this.id = id;
        this.monthly_summary_code = monthly_summary_code;
        this.monthly_summary_time = monthly_summary_time;
        this.monthly_summary_content = monthly_summary_content;
        this.monthly_summary_entry_time = monthly_summary_entry_time;
        this.monthly_summary_reply = monthly_summary_reply;
        this.monthly_summary_see = monthly_summary_see;
        this.monthly_summary_state = monthly_summary_state;
        this.peo_id = peo_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonthly_summary_code() {
        return monthly_summary_code;
    }

    public void setMonthly_summary_code(String monthly_summary_code) {
        this.monthly_summary_code = monthly_summary_code;
    }

    public String getMonthly_summary_time() {
        return monthly_summary_time;
    }

    public void setMonthly_summary_time(String monthly_summary_time) {
        this.monthly_summary_time = monthly_summary_time;
    }

    public String getMonthly_summary_content() {
        return monthly_summary_content;
    }

    public void setMonthly_summary_content(String monthly_summary_content) {
        this.monthly_summary_content = monthly_summary_content;
    }

    public String getMonthly_summary_entry_time() {
        return monthly_summary_entry_time;
    }

    public void setMonthly_summary_entry_time(String monthly_summary_entry_time) {
        this.monthly_summary_entry_time = monthly_summary_entry_time;
    }

    public String getMonthly_summary_reply() {
        return monthly_summary_reply;
    }

    public void setMonthly_summary_reply(String monthly_summary_reply) {
        this.monthly_summary_reply = monthly_summary_reply;
    }

    public String getMonthly_summary_see() {
        return monthly_summary_see;
    }

    public void setMonthly_summary_see(String monthly_summary_see) {
        this.monthly_summary_see = monthly_summary_see;
    }

    public Integer getMonthly_summary_state() {
        return monthly_summary_state;
    }

    public void setMonthly_summary_state(Integer monthly_summary_state) {
        this.monthly_summary_state = monthly_summary_state;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }
}
