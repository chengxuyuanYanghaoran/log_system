package com.hlwxy.xu_boot2.system.service.impl;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.dao.WeekPlanDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.WeekPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class WeekPlanServiceImpl implements WeekPlanService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;
	@Resource
	private WeekPlanDao weekPlanDao;


	@Override
	public Integer coun() {
		return weekPlanDao.coun();
	}

	@Override
	public List<WeekPlanExtendDO> findWeekPlan(Page page) {
		return weekPlanDao.findWeekPlan(page);
	}

	@Override
	public int count() {
		return weekPlanDao.count();
	}

	@Override
	public WeekPlanExtendDO getWeekPlanById(WeekPlanDO weekPlanDO) {
		return weekPlanDao.getWeekPlanById(weekPlanDO);
	}

	@Override
	public List<WeekPlanExtendDO> findWeekPlanByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<WeekPlanExtendDO> weekPlanExtendDOS=weekPlanDao.findWeekPlanByCompany(companyDO1);
		return weekPlanExtendDOS;
	}

	@Override
	public List<WeekPlanExtendDO> findWeekPlanByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<WeekPlanExtendDO> weekPlanExtendDOS=weekPlanDao.findWeekPlanByDepartment(departmentDO1);
		return weekPlanExtendDOS;
	}

	@Override
	public Integer queryCount(ConditionDO conditionDO) {
		if (conditionDO.getGsName()!=null){
			if (!conditionDO.getGsName().equals("")){
				//查询公司
				CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
				//设置公司id
				conditionDO.setGsId(companyDO1.getId());
			}
		}
		if (conditionDO.getBmName()!=null){
			if (!conditionDO.getBmName().equals("")){
				//查询部门
				DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
				//设置部门id
				conditionDO.setBmId(departmentDO1.getId());
			}
		}
		return weekPlanDao.queryCount(conditionDO);
	}

	@Override
	public List<WeekPlanExtendDO> compositeQuery(ConditionDO conditionDO) {
		if (conditionDO.getGsName()!=null){
			if (!conditionDO.getGsName().equals("")){
				//查询公司
				CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
				//设置公司id
				conditionDO.setGsId(companyDO1.getId());
			}
		}
		if (conditionDO.getBmName()!=null){
			if (!conditionDO.getBmName().equals("")){
				//查询部门
				DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
				//设置部门id
				conditionDO.setBmId(departmentDO1.getId());
			}
		}
		//组合查询
		List<WeekPlanExtendDO> weekPlanExtendDOS=weekPlanDao.compositeQuery(conditionDO);
		return weekPlanExtendDOS;
	}

	@Override
	public void addWeekPlan(WeekPlanDO weekPlanDO) {
		weekPlanDao.addWeekPlan(weekPlanDO);
	}

	@Override
	public WeekPlanExtendDO getWeekPlanByCode(String code) {
		return weekPlanDao.getWeekPlanByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO) {
		weekPlanDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateWeekPlanContentByPeople(WeekPlanExtendDO weekPlanExtendDO) {
		weekPlanDao.updateWeekPlanContentByPeople(weekPlanExtendDO);
	}

	@Override
	public void updateWeekPlanStateById(WeekPlanDO weekPlanDO) {
		weekPlanDao.updateWeekPlanStateById(weekPlanDO);
	}

	@Override
	public void updateWeekPlanStateListById(BatchAuditUtil batchAuditUtil) {
		weekPlanDao.updateWeekPlanStateListById(batchAuditUtil);
	}
}
