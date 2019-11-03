package com.hlwxy.xu_boot2.system.service.impl;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.DayPlanDao;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.DayPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DayPlanServiceImpl implements DayPlanService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;
	@Resource
	private DayPlanDao dayPlanDao;


	@Override
	public Integer coun() {
		return dayPlanDao.coun();
	}

	@Override
	public List<DayPlanExtendDO> findDayPlan(Page page) {
		return dayPlanDao.findDayPlan(page);
	}

	@Override
	public int count() {
		return dayPlanDao.count();
	}

	@Override
	public DayPlanExtendDO getDayPlanById(DayPlanDO dayPlanDO) {
		return dayPlanDao.getDayPlanById(dayPlanDO);
	}

	@Override
	public List<DayPlanExtendDO> findDayPlanByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<DayPlanExtendDO> dayPlanByCompany=dayPlanDao.findDayPlanByCompany(companyDO1);
		return dayPlanByCompany;
	}

	@Override
	public List<DayPlanExtendDO> findDayPlanByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<DayPlanExtendDO> dayPlanExtendDOS=dayPlanDao.findDayPlanByDepartment(departmentDO1);
		return dayPlanExtendDOS;
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
		return dayPlanDao.queryCount(conditionDO);
	}

	@Override
	public List<DayPlanExtendDO> compositeQuery(ConditionDO conditionDO) {
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
		List<DayPlanExtendDO> dayPlanExtendDOS=dayPlanDao.compositeQuery(conditionDO);
		return dayPlanExtendDOS;
	}

	@Override
	public void addDayPlan(DayPlanDO dayPlanDO) {
		dayPlanDao.addDayPlan(dayPlanDO);
	}

	@Override
	public DayPlanExtendDO getDayPlanByCode(String code) {
		return dayPlanDao.getDayPlanByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO) {
		dayPlanDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateDayPlanContentByPeople(DayPlanExtendDO dayPlanExtendDO) {
		dayPlanDao.updateDayPlanContentByPeople(dayPlanExtendDO);
	}

	@Override
	public void updateDayPlanStateById(DayPlanDO dayPlanDO) {
		dayPlanDao.updateDayPlanStateById(dayPlanDO);
	}

	@Override
	public void updateDayPlanStateListById(BatchAuditUtil batchAuditUtil) {
		dayPlanDao.updateDayPlanStateListById(batchAuditUtil);
	}
}
