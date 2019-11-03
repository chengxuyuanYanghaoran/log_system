package com.hlwxy.xu_boot2.system.service.impl;

import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.dao.DaySummaryDao;
import com.hlwxy.xu_boot2.system.dao.MonthlyPlanDao;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.DaySummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DaySummaryServiceImpl implements DaySummaryService {
	@Resource
	private MonthlyPlanDao monthlyPlanDao;
	@Resource
	private DaySummaryDao daySummaryDao;

	@Override
	public Integer coun() {
		return daySummaryDao.coun();
	}

	@Override
	public List<DaySummaryExtendDO> findDaySummary(Page page) {
		return daySummaryDao.findDaySummary(page);
	}

	@Override
	public int count() {
		return daySummaryDao.count();
	}

	@Override
	public DaySummaryExtendDO getDaySummaryById(DaySummaryDo daySummaryDo) {
		return daySummaryDao.getDaySummaryById(daySummaryDo);
	}

	@Override
	public List<DaySummaryExtendDO> findDaySummaryByCompany(ConditionDO conditionDO) {
		CompanyDO companyDO1=monthlyPlanDao.getCompanyByName(conditionDO);
		List<DaySummaryExtendDO> daySummaryByCompany=daySummaryDao.findDaySummaryByCompany(companyDO1);
		return daySummaryByCompany;
	}

	@Override
	public List<DaySummaryExtendDO> findDaySummaryByDepartment(ConditionDO conditionDO) {
		DepartmentDO departmentDO1=monthlyPlanDao.getDepartmentByName(conditionDO);
		List<DaySummaryExtendDO> daySummaryByDepartment=daySummaryDao.findDaySummaryByDepartment(departmentDO1);
		return daySummaryByDepartment;
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
		return daySummaryDao.queryCount(conditionDO);
	}

	@Override
	public List<DaySummaryExtendDO> compositeQueryDaySummary(ConditionDO conditionDO) {
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
		List<DaySummaryExtendDO> daySummaryExtendDOS=daySummaryDao.compositeQueryDaySummary(conditionDO);
		return daySummaryExtendDOS;
	}

	@Override
	public void addDaySummary(DaySummaryDo daySummaryDo) {
		daySummaryDao.addDaySummary(daySummaryDo);
	}

	@Override
	public DaySummaryExtendDO getDaySummaryByCode(String code) {
		return daySummaryDao.getDaySummaryByCode(code);
	}

	@Override
	public void updatePeopleByCode(PeopleDO peopleDO) {
		daySummaryDao.updatePeopleByCode(peopleDO);
	}

	@Override
	public void updateDaySummaryContentByPeople(DaySummaryExtendDO daySummaryExtendDO) {
		daySummaryDao.updateDaySummaryContentByPeople(daySummaryExtendDO);
	}

	@Override
	public void updateDaySummaryStateById(DaySummaryDo daySummaryDo) {
		daySummaryDao.updateDaySummaryStateById(daySummaryDo);
	}

	@Override
	public void updateDaySummaryStateListById(BatchAuditUtil batchAuditUtil) {
		daySummaryDao.updateDaySummaryStateListById(batchAuditUtil);
	}
}
