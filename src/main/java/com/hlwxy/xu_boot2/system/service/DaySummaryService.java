package com.hlwxy.xu_boot2.system.service;



import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.domain.*;

import java.util.List;

public interface DaySummaryService {
	//查询人员总条数
	Integer coun();
	//查询所有总结
	List<DaySummaryExtendDO> findDaySummary(Page page);
	int count();
	//根据id查新月总结
	DaySummaryExtendDO getDaySummaryById(DaySummaryDo daySummaryDo);
	//根据公司id查询 本月份 公司内所有人的月计划
	List<DaySummaryExtendDO> findDaySummaryByCompany(ConditionDO conditionDO);
	//根据部门id查询 本月份 部门内所有人的月计划
	List<DaySummaryExtendDO> findDaySummaryByDepartment(ConditionDO conditionDO);
	//组合查询总条数
	Integer queryCount(ConditionDO conditionDO);
	//多条件组合查询
	List<DaySummaryExtendDO> compositeQueryDaySummary(ConditionDO conditionDO);
	//添加月总结
	void addDaySummary(DaySummaryDo daySummaryDo);
	//根据总结编号查询总结
	DaySummaryExtendDO getDaySummaryByCode(String code);
	//修改人员周总结
	void updatePeopleByCode(PeopleDO peopleDO);
	//修改月总结内容
	void updateDaySummaryContentByPeople(DaySummaryExtendDO daySummaryExtendDO);
	//修改计划状态
	void updateDaySummaryStateById(DaySummaryDo daySummaryDo);
	//批量修改日总结状态
	void updateDaySummaryStateListById(BatchAuditUtil batchAuditUtil);
}
