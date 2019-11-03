package com.hlwxy.xu_boot2.system.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author 路林威
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class PositionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//职位id
	private Integer id;
	//职位名称
	private String jobTitle;

	/**
	 * 设置：职位id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：职位id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：职位名称
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * 获取：职位名称
	 */
	public String getJobTitle() {
		return jobTitle;
	}
}
