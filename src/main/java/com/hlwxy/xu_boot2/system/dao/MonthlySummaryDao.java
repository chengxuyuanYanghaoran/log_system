package com.hlwxy.xu_boot2.system.dao;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.system.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-21-22:59
 */
@Mapper
public interface MonthlySummaryDao {
     //查询人员总条数
     Integer coun();
     //查询所有总结
     List<MonthlySummaryExtendDO> findSummarySummary(Page page);
     int count();
     //根据id查新月总结
     MonthlySummaryExtendDO getMonthlySummaryById(MonthlySummaryDO monthlySummaryDO);
     //根据计划时间查新月总结
     List<MonthlySummaryExtendDO> getMonthlySummaryByTime(MonthlySummaryDO monthlySummaryDO);
     //根据公司id查询 本月份 公司内所有人的月计划
     List<MonthlySummaryExtendDO> findMonthlySummaryByCompany(CompanyDO companyDO);
     //根据部门id查询 本月份 部门内所有人的月计划
     List<MonthlySummaryExtendDO> findMonthlySummaryByDepartment(DepartmentDO departmentDO);
     //组合查询总条数
     Integer queryCount(ConditionDO conditionDO);
     //多条件组合查询
     List<MonthlySummaryExtendDO> compositeQueryMonthlySummary(ConditionDO conditionDO);
     //添加月总结
     void addMonthlySummary(MonthlySummaryDO monthlySummaryDO);
     //根据总结编号查询总结
     MonthlySummaryExtendDO getMonthlySummaryByCode(String code);
     //修改人员月总结
     void updatePeopleByCode(PeopleDO peopleDO);
     //修改月总结内容
     void updateMonthlySummaryContentByPeople(MonthlySummaryExtendDO monthlySummaryExtendDO);
     //修改计划状态
     void updateMonthlySummaryStateById(MonthlySummaryDO monthlySummaryDO);
     //批量修改总结状态
     void updateMonthlySummaryStateListById(BatchAuditUtil batchAuditUtil);

}
