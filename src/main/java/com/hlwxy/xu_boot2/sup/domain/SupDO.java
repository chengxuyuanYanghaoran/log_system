package com.hlwxy.xu_boot2.sup.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lu
 * @email 1992lcg@163.com
 * @date 2019-11-04 11:33:15
 */
public class SupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;

	//类型 1：日计划 2：日总结 3：周计划 4：周总结 5：月计划 6：月总结
	private String type;

	//创建时间：1：日计划时间 2：日总结时间 3：周计划时间 4：周总结时间 5：月计划时间 6：月总结时间
	private String newdata;

	//内容 1：日计划内容 2：日总结内容 3：周计划内容 4：周总结内容 5：月计划内容 6：月总结内容
	private String content;



	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：类型 1：日计划 2：日总结 3：周计划 4：周总结 5：月计划 6：月总结
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型 1：日计划 2：日总结 3：周计划 4：周总结 5：月计划 6：月总结
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：创建时间：1：日计划时间 2：日总结时间 3：周计划时间 4：周总结时间 5：月计划时间 6：月总结时间
	 */
	public void setNewdata(String newdata) {
		this.newdata = newdata;
	}
	/**
	 * 获取：创建时间：1：日计划时间 2：日总结时间 3：周计划时间 4：周总结时间 5：月计划时间 6：月总结时间
	 */
	public String getNewdata() {
		return newdata;
	}
	/**
	 * 设置：内容 1：日计划内容 2：日总结内容 3：周计划内容 4：周总结内容 5：月计划内容 6：月总结内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容 1：日计划内容 2：日总结内容 3：周计划内容 4：周总结内容 5：月计划内容 6：月总结内容
	 */
	public String getContent() {
		return content;
	}
}
