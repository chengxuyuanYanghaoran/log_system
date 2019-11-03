package com.hlwxy.xu_boot2.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xzbd 1992lcg@163.com
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;//{total:1,rows:[1,"测试"],total:2,rows:[1,"测试"]}
	private List<?> rows;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public PageUtils() {
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
