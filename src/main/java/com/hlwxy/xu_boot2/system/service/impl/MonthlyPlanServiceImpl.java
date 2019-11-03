package com.hlwxy.xu_boot2.system.service.impl;

import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MonthlyPlanServiceImpl implements MonthlyPlanService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;

	@Override
	public Integer coun() {
		return monthlyPlanDao.coun();
	}

	@Override
	public List<MonthlyPlanExtendDo> findMonthlyPlan(Page page) {
		return monthlyPlanDao.findMonthlyPlan(page);
	}

	@Override
	public int count() {
		return monthlyPlanDao.count();
	}

	@Override
	public com.hlwxy.xu_boot2.system.domain.PeopleDO getPeopleByCode(com.hlwxy.xu_boot2.system.domain.PeopleDO peopleDO) {
		return monthlyPlanDao.getPeopleByCode(peopleDO);
	}

	@Override
	public MonthlyPlanExtendDo getMonthlyPlanById(MonthlyPlan monthlyPlan) {
		return monthlyPlanDao.getMonthlyPlanById(monthlyPlan);
	}

	@Override
	public List<MonthlyPlanExtendDo> findMonthlyPlanByTime(MonthlyPlan monthlyPlan) {
		return monthlyPlanDao.getMonthlyPlanByTime(monthlyPlan);
	}

	@Override
	public List<MonthlyPlanExtendDo> findMonthlyPlanByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<MonthlyPlanExtendDo> monthlyPlanExtendDos=monthlyPlanDao.findMonthlyPlanByCompany(companyDO1);
		return monthlyPlanExtendDos;
	}

	@Override
	public List<MonthlyPlanExtendDo> findMonthlyPlanByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<MonthlyPlanExtendDo> monthlyPlanExtendDos=monthlyPlanDao.findMonthlyPlanByDepartment(departmentDO1);
		return monthlyPlanExtendDos;
	}

	@Override
	public Integer queryConut(ConditionDO conditionDO) {
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
		return monthlyPlanDao.queryConut(conditionDO);
	}

	@Override
	public List<MonthlyPlanExtendDo> compositeQuery(ConditionDO conditionDO) {
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
		List<MonthlyPlanExtendDo> monthlyPlanExtendDos=monthlyPlanDao.compositeQuery(conditionDO);
		return monthlyPlanExtendDos;
	}

	@Override
	public void addMonthlyPlan(MonthlyPlan monthlyPlan) {
		monthlyPlanDao.addMonthlyPlan(monthlyPlan);
	}

	@Override
	public com.hlwxy.xu_boot2.system.domain.PeopleDO getPeople(PeopleDO user) {
		return monthlyPlanDao.getPeople(user);
	}

	@Override
	public MonthlyPlanExtendDo getMonthlyplanByCode(String code) {
		return monthlyPlanDao.getMonthlyplanByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO) {
		monthlyPlanDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateMonthlyPlanContentByPeople(MonthlyPlanExtendDo monthlyPlanExtendDo) {
		monthlyPlanDao.updateMonthlyPlanContentByPeople(monthlyPlanExtendDo);
	}

	@Override
	public void updateMonthlyPlanStateById(MonthlyPlan monthlyPlan) {
		monthlyPlanDao.updateMonthlyPlanStateById(monthlyPlan);
	}

	@Override
	public void updateMonthlyPlanStateListById(BatchAuditUtil batchAuditUtil) {
		monthlyPlanDao.updateMonthlyPlanStateListById(batchAuditUtil);
	}

	@Override
	public PeopleDO getPeopleById(PeopleDO peopleDO) {
		return monthlyPlanDao.getPeopleById(peopleDO);
	}


}
