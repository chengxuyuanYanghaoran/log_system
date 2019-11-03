package com.hlwxy.xu_boot2.system.domain;

import java.io.Serializable;


/**
 * @author 查询条件
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class ConditionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//时间
	private String time;
	//公司名称
	private String gsName;
	//部门名称
	private String bmName;
	//人员姓名
	private String peoName;
	//公司id
	private Integer gsId;
	//部门id
	private Integer bmId;
	private Integer pageSize;//每页显示多少条
	private Integer page;//当前页数
	private Integer pp;//总记录数
	private Integer pc;//开始检索位置

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPp() {
		return pp;
	}

	public void setPp(Integer pp) {
		this.pp = pp;
	}

	public Integer getPc() {
		return pc;
	}

	public void setPc(Integer pc) {
		this.pc = pc;
	}

	public ConditionDO() {
	}

	public ConditionDO(String time, String gsName, String bmName, String peoName, Integer gsId, Integer bmId, Integer pageSize, Integer page, Integer pp, Integer pc) {
		this.time = time;
		this.gsName = gsName;
		this.bmName = bmName;
		this.peoName = peoName;
		this.gsId = gsId;
		this.bmId = bmId;
		this.pageSize = pageSize;
		this.page = page;
		this.pp = pp;
		this.pc = pc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGsName() {
		return gsName;
	}

	public void setGsName(String gsName) {
		this.gsName = gsName;
	}

	public String getBmName() {
		return bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	public String getPeoName() {
		return peoName;
	}

	public void setPeoName(String peoName) {
		this.peoName = peoName;
	}

	public Integer getGsId() {
		return gsId;
	}

	public void setGsId(Integer gsId) {
		this.gsId = gsId;
	}

	public Integer getBmId() {
		return bmId;
	}

	public void setBmId(Integer bmId) {
		this.bmId = bmId;
	}
}
