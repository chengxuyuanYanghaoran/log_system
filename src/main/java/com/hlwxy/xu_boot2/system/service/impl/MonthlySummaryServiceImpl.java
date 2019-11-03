package com.hlwxy.xu_boot2.system.service.impl;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.dao.MonthlySummaryDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.MonthlySummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MonthlySummaryServiceImpl implements MonthlySummaryService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;
	@Resource
	private MonthlySummaryDao monthlySummaryDao;


	@Override
	public Integer coun() {
		return monthlySummaryDao.coun();
	}

	@Override
	public List<MonthlySummaryExtendDO> findSummarySummary(Page page) {
		return monthlySummaryDao.findSummarySummary(page);
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public MonthlySummaryExtendDO getMonthlySummaryById(MonthlySummaryDO monthlySummaryDO) {
		return monthlySummaryDao.getMonthlySummaryById(monthlySummaryDO);
	}

	@Override
	public List<MonthlySummaryExtendDO> getMonthlySummaryByTime(MonthlySummaryDO monthlySummaryDO) {
		return monthlySummaryDao.getMonthlySummaryByTime(monthlySummaryDO);
	}

	@Override
	public List<MonthlySummaryExtendDO> findMonthlySummaryByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<MonthlySummaryExtendDO> monthlyPlanExtendDos=monthlySummaryDao.findMonthlySummaryByCompany(companyDO1);
		return monthlyPlanExtendDos;
	}

	@Override
	public List<MonthlySummaryExtendDO> findMonthlySummaryByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<MonthlySummaryExtendDO> monthlyPlanExtendDos=monthlySummaryDao.findMonthlySummaryByDepartment(departmentDO1);
		return monthlyPlanExtendDos;
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
		return monthlySummaryDao.queryCount(conditionDO);
	}


	@Override
	public List<MonthlySummaryExtendDO> compositeQueryMonthlySummary(ConditionDO conditionDO) {
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
		List<MonthlySummaryExtendDO> monthlyPlanExtendDos=monthlySummaryDao.compositeQueryMonthlySummary(conditionDO);
		return monthlyPlanExtendDos;
	}

	@Override
	public void addMonthlySummary(MonthlySummaryDO monthlySummaryDO) {
		monthlySummaryDao.addMonthlySummary(monthlySummaryDO);
	}

	@Override
	public MonthlySummaryExtendDO getMonthlySummaryByCode(String code) {
		return monthlySummaryDao.getMonthlySummaryByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO
	) {
		monthlySummaryDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateMonthlySummaryContentByPeople(MonthlySummaryExtendDO monthlySummaryExtendDO) {
		monthlySummaryDao.updateMonthlySummaryContentByPeople(monthlySummaryExtendDO);
	}

	@Override
	public void updateMonthlySummaryStateById(MonthlySummaryDO monthlySummaryDO) {
		monthlySummaryDao.updateMonthlySummaryStateById(monthlySummaryDO);
	}

	@Override
	public void updateMonthlySummaryStateListById(BatchAuditUtil batchAuditUtil) {
		monthlySummaryDao.updateMonthlySummaryStateListById(batchAuditUtil);
	}
}
