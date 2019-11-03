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
public interface DaySummaryDao {
     //查询人员总条数
     Integer coun();
     //查询所有总结
     List<DaySummaryExtendDO> findDaySummary(Page page);
     int count();
     //根据id查新月总结
     DaySummaryExtendDO getDaySummaryById(DaySummaryDo daySummaryDo);
     //根据公司id查询 本月份 公司内所有人的月计划
     List<DaySummaryExtendDO> findDaySummaryByCompany(CompanyDO companyDO);
     //根据部门id查询 本月份 部门内所有人的月计划
     List<DaySummaryExtendDO> findDaySummaryByDepartment(DepartmentDO departmentDO);
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
     //修改日总结状态
     void updateDaySummaryStateById(DaySummaryDo daySummaryDo);
     //批量修改日总结状态
     void updateDaySummaryStateListById(BatchAuditUtil batchAuditUtil);
}
