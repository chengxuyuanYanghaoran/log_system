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
public interface MonthlyPlanDao {
     //查询人员总条数
     Integer coun();
     //查询所有计划
     List<MonthlyPlanExtendDo> findMonthlyPlan(Page page);

     int count();

     //根据人员编号查询人员
     com.hlwxy.xu_boot2.system.domain.PeopleDO getPeopleByCode(com.hlwxy.xu_boot2.system.domain.PeopleDO peopleDO);

     //根据id查新月计划
     MonthlyPlanExtendDo getMonthlyPlanById(MonthlyPlan monthlyPlan);

     //根据计划时间查新月计划
     List<MonthlyPlanExtendDo> getMonthlyPlanByTime(MonthlyPlan monthlyPlan);

     //根据公司名称查询公司
     CompanyDO getCompanyByName(ConditionDO conditionDO);
     //根据公司id查询 本月份 公司内所有人的月计划
     List<MonthlyPlanExtendDo> findMonthlyPlanByCompany(CompanyDO companyDO);

     //根据部门名称查询部门
     DepartmentDO getDepartmentByName(ConditionDO conditionDO);
     //根据部门id查询 本月份 部门内所有人的月计划
     List<MonthlyPlanExtendDo> findMonthlyPlanByDepartment(DepartmentDO departmentDO);

     //组合查询总条数
     Integer queryConut(ConditionDO conditionDO);
     //多条件组合查询
     List<MonthlyPlanExtendDo> compositeQuery(ConditionDO conditionDO);

     //添加月计划
     void addMonthlyPlan(MonthlyPlan monthlyPlan);

     //根据账号查询人员
     com.hlwxy.xu_boot2.system.domain.PeopleDO getPeople(PeopleDO user);

     //根据计划编号查询计划
     MonthlyPlanExtendDo getMonthlyplanByCode(String code);
     //修改人员月计划
     void updatePeopleByCode(PeopleDO peopleDO);

     //修改月计划内容
     void updateMonthlyPlanContentByPeople(MonthlyPlanExtendDo monthlyPlanExtendDo);

     //修改计划状态
     void updateMonthlyPlanStateById(MonthlyPlan monthlyPlan);

     //批量修改状态
     void updateMonthlyPlanStateListById(BatchAuditUtil batchAuditUtil);

     //根据人员id查询人员
     PeopleDO getPeopleById(PeopleDO peopleDO);

}
