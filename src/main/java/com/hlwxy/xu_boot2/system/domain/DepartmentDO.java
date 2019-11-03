package com.hlwxy.xu_boot2.system.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author 路林威
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class DepartmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//部门主键
	private Integer id;
	//部门编号
	private String bmCode;
	//部门名称
	private String bmName;
	//公司id
	private Integer gsId;

	/**
	 * 设置：部门主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：部门主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：部门编号
	 */
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}
	/**
	 * 获取：部门编号
	 */
	public String getBmCode() {
		return bmCode;
	}
	/**
	 * 设置：部门名称
	 */
	public void setBmName(String bmName) {
		this.bmName = bmName;
	}
	/**
	 * 获取：部门名称
	 */
	public String getBmName() {
		return bmName;
	}
	/**
	 * 设置：公司id
	 */
	public void setGsId(Integer gsId) {
		this.gsId = gsId;
	}
	/**
	 * 获取：公司id
	 */
	public Integer getGsId() {
		return gsId;
	}

	public DepartmentDO(Integer id, String bmName) {
		this.id = id;
		this.bmName = bmName;
	}
}
