package com.hlwxy.xu_boot2.system.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author 路林威
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class CompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//公司id
	private Integer id;
	//公司编码
	private String gsCode;
	//公司名称

	private String gsName;

	/**
	 * 设置：公司id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：公司id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：公司编码
	 */
	public void setGsCode(String gsCode) {
		this.gsCode = gsCode;
	}
	/**
	 * 获取：公司编码
	 */
	public String getGsCode() {
		return gsCode;
	}
	/**
	 * 设置：公司名称
	 */
	public void setGsName(String gsName) {
		this.gsName = gsName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getGsName() {
		return gsName;
	}
}
