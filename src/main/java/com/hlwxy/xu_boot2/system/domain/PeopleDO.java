package com.hlwxy.xu_boot2.system.domain;

import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 *
 *
 * @author 路林威
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class PeopleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//用户id
	private Integer id;
	//用户名
	private String username;

	private String password;
	//邮箱
	private String email;

	//人员编号
	private String peopleCode;
	//工号
	private String jobNumber;
	//人员名称
	private String peoName;
	//上午状态
	private Integer morningState;
	//下午状态
	private Integer afternoonState;
	//账号id
	private Integer userId;

	//公司id
	private String gsId;
	//部门id
	private String bmId;
	//职位id
	private String positionId;
	//日计划id
	private Integer dayPlanId;
	//日总结id
	private Integer daySummaryId;
	//周计划id
	private Integer weekPlanId;
	//周总结
	private Integer weekSummaryId;
	//月计划
	private Integer monthlyPlanId;
	//月总结
	private Integer monthlySummaryId;


	public PeopleDO() {
	}

	public PeopleDO(Integer id, String username, String password, String email, String peopleCode, String jobNumber, String peoName, Integer morningState, Integer afternoonState, Integer userId, String gsId, String bmId, String positionId, Integer dayPlanId, Integer daySummaryId, Integer weekPlanId, Integer weekSummaryId, Integer monthlyPlanId, Integer monthlySummaryId, Set<RoleDo> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.peopleCode = peopleCode;
		this.jobNumber = jobNumber;
		this.peoName = peoName;
		this.morningState = morningState;
		this.afternoonState = afternoonState;
		this.userId = userId;
		this.gsId = gsId;
		this.bmId = bmId;
		this.positionId = positionId;
		this.dayPlanId = dayPlanId;
		this.daySummaryId = daySummaryId;
		this.weekPlanId = weekPlanId;
		this.weekSummaryId = weekSummaryId;
		this.monthlyPlanId = monthlyPlanId;
		this.monthlySummaryId = monthlySummaryId;
		this.roles = roles;
	}

	/**
	 //	 * 权限
	 //	 */
	private Set<RoleDo> roles = new HashSet<>();


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDo> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPeopleCode() {
		return peopleCode;
	}

	public void setPeopleCode(String peopleCode) {
		this.peopleCode = peopleCode;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getPeoName() {
		return peoName;
	}

	public void setPeoName(String peoName) {
		this.peoName = peoName;
	}

	public Integer getMorningState() {
		return morningState;
	}

	public void setMorningState(Integer morningState) {
		this.morningState = morningState;
	}

	public Integer getAfternoonState() {
		return afternoonState;
	}

	public void setAfternoonState(Integer afternoonState) {
		this.afternoonState = afternoonState;
	}

	public String getGsId() {
		return gsId;
	}

	public void setGsId(String gsId) {
		this.gsId = gsId;
	}

	public String getBmId() {
		return bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public Integer getDayPlanId() {
		return dayPlanId;
	}

	public void setDayPlanId(Integer dayPlanId) {
		this.dayPlanId = dayPlanId;
	}

	public Integer getDaySummaryId() {
		return daySummaryId;
	}

	public void setDaySummaryId(Integer daySummaryId) {
		this.daySummaryId = daySummaryId;
	}

	public Integer getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Integer weekPlanId) {
		this.weekPlanId = weekPlanId;
	}

	public Integer getWeekSummaryId() {
		return weekSummaryId;
	}

	public void setWeekSummaryId(Integer weekSummaryId) {
		this.weekSummaryId = weekSummaryId;
	}

	public Integer getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Integer monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public Integer getMonthlySummaryId() {
		return monthlySummaryId;
	}

	public void setMonthlySummaryId(Integer monthlySummaryId) {
		this.monthlySummaryId = monthlySummaryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}