package com.hlwxy.xu_boot2.system.service.impl;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.dao.WeekSummaryDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.WeekSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class WeekSummaryServiceImpl implements WeekSummaryService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;
	@Resource
	private WeekSummaryDao weekSummaryDao;


	@Override
	public Integer coun() {
		return weekSummaryDao.coun();
	}

	@Override
	public List<WeekSummaryExtendDO> findWeekSummary(Page page) {
		return weekSummaryDao.findWeekSummary(page);
	}

	@Override
	public int count() {
		return weekSummaryDao.count();
	}

	@Override
	public WeekSummaryExtendDO getWeekSummaryById(WeekSummaryDO weekSummaryDO) {
		return weekSummaryDao.getWeekSummaryById(weekSummaryDO);
	}

	@Override
	public List<WeekSummaryExtendDO> findWeekSummaryByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<WeekSummaryExtendDO> weekSummaryByCompany=weekSummaryDao.findWeekSummaryByCompany(companyDO1);
		return weekSummaryByCompany;
	}

	@Override
	public List<WeekSummaryExtendDO> findWeekSummaryByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<WeekSummaryExtendDO> weekSummaryByDepartment=weekSummaryDao.findWeekSummaryByDepartment(departmentDO1);
		return weekSummaryByDepartment;
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
		return weekSummaryDao.queryCount(conditionDO);
	}

	@Override
	public List<WeekSummaryExtendDO> compositeQueryWeekSummary(ConditionDO conditionDO) {
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
		List<WeekSummaryExtendDO> weekSummaryExtendDOS=weekSummaryDao.compositeQueryWeekSummary(conditionDO);
		return weekSummaryExtendDOS;
	}

	@Override
	public void addWeekSummary(WeekSummaryDO weekSummaryDO) {
		weekSummaryDao.addWeekSummary(weekSummaryDO);
	}

	@Override
	public WeekSummaryExtendDO getWeekSummaryByCode(String code) {
		return weekSummaryDao.getWeekSummaryByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO) {
		weekSummaryDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateWeekSummaryContentByPeople(WeekSummaryExtendDO weekSummaryExtendDO) {
		weekSummaryDao.updateWeekSummaryContentByPeople(weekSummaryExtendDO);
	}

	@Override
	public void updateWeekSummaryStateById(WeekSummaryDO weekSummaryDO) {
		weekSummaryDao.updateWeekSummaryStateById(weekSummaryDO);
	}

	@Override
	public void updateWeekSummaryStateListById(BatchAuditUtil batchAuditUtil) {
		weekSummaryDao.updateWeekSummaryStateListById(batchAuditUtil);
	}
}
