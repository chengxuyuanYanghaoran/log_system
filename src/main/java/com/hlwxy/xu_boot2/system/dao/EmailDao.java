package com.hlwxy.xu_boot2.system.dao;


import com.hlwxy.xu_boot2.system.domain.DayPlanDO;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailDao {
	//查询所有人员
	List<com.hlwxy.xu_boot2.system.domain.PeopleDO> findPeopleDO();
	//根据人员查询该人员最新的日计划
	DayPlanDO gerDdyPlan(com.hlwxy.xu_boot2.system.domain.PeopleDO peopleDO);
	//根据人员查询人员的账号和邮箱
	PeopleDO getUser(com.hlwxy.xu_boot2.system.domain.PeopleDO peopleDO);
}
