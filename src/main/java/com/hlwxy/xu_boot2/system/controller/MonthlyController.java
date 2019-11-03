package com.hlwxy.xu_boot2.system.controller;

import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 *
 * @date 2019-09-26 22:35:53
 */

@Controller
@RequestMapping("/system/monthly")
public class MonthlyController {
	@Resource
	private MonthlyPlanService monthlyPlanService;

	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list(Page page) {
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		//计算开始检索位置
		page.setPc(page.getPageSize()*(page.getPage()-1));
		Integer pp=monthlyPlanService.coun();
		List<MonthlyPlanExtendDo> monthlyPlanExtends = monthlyPlanService.findMonthlyPlan(page);
		int total = monthlyPlanService.count();
		map.put("monthlyPlanExtends",monthlyPlanExtends);
	    map.put("pp",pp);
	    map.put("total",total);
		return map;
	}

	//查询个人月计划
	@ResponseBody
	@RequestMapping("/getMonthlyPlan")
	public Map<String,Object> getMonthlyPlan() {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
//			PeopleDO people=monthlyPlanService.getPeople(user);
			MonthlyPlan monthlyPlan = new MonthlyPlan();
			monthlyPlan.setId(people.getMonthlyPlanId());
			//查询数据
			MonthlyPlanExtendDo monthlyPlan1 = monthlyPlanService.getMonthlyPlanById(monthlyPlan);
			if(!monthlyPlan1.getMonthly_plan_time().equals(dateTool.years())){
				map.put("code",0);
				map.put("msg","您还没有编写本月的计划");
			}else {
				map.put("monthlyPlan1",monthlyPlan1);
				map.put("code",0);
			}

		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据计划id查询
	@ResponseBody
	@RequestMapping("/getMonthlyPlanById")
	//cahxun
	@RequiresPermissions("system:monthly:edit")
	public Map<String,Object> getMonthlyPlanById(@RequestBody MonthlyPlan monthlyPlan) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询数据
			MonthlyPlanExtendDo monthlyPlan1 = monthlyPlanService.getMonthlyPlanById(monthlyPlan);
			map.put("monthlyPlan1",monthlyPlan1);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据月计划时间查询
	@ResponseBody
	@RequestMapping("/getMonthlyPlanByTime")
	public Map<String,Object> getMonthlyPlanByTime(MonthlyPlan monthlyPlan) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询数据
			List<MonthlyPlanExtendDo> monthlyPlan1 = monthlyPlanService.findMonthlyPlanByTime(monthlyPlan);
			map.put("monthlyPlan1",monthlyPlan1);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据公司查询 本月份 公司月计划
	@ResponseBody
	@RequestMapping("/findMonthlyPlanByCompany")
	public Map<String,Object> findMonthlyPlanByCompany(ConditionDO conditionDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询数据
			List<MonthlyPlanExtendDo> monthlyPlan1 = monthlyPlanService.findMonthlyPlanByCompany(conditionDO);
			map.put("monthlyPlan1",monthlyPlan1);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据部门查询 本月份 部门月计划
	@ResponseBody
	@RequestMapping("/findMonthlyPlanByDepartment")
	public Map<String,Object> findMonthlyPlanByDepartment(ConditionDO conditionDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询数据
			List<MonthlyPlanExtendDo> monthlyPlan1 = monthlyPlanService.findMonthlyPlanByDepartment(conditionDO);
			map.put("monthlyPlan1",monthlyPlan1);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//多条件组合查询
	@ResponseBody
	@RequestMapping("/compositeQuery")
	public Map<String,Object> compositeQuery(ConditionDO conditionDO) {
		Map<String,Object> map=new HashMap<>();
		String sss=conditionDO.getTime().replace('-','年');
		conditionDO.setTime(sss);
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));
		try{
			//组合查询
			List<MonthlyPlanExtendDo> monthlyPlan1 = monthlyPlanService.compositeQuery(conditionDO);
			Integer pp=monthlyPlanService.queryConut(conditionDO);
			map.put("monthlyPlan1",monthlyPlan1);
			map.put("pp",pp);
			map.put("code",0);
		}catch (Exception e){
			System.out.println(e.getMessage());
			map.put("code",-1);
			map.put("msg","请输入正确的查询条件");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/addMonthlyPlan")
	//添加
	@RequiresPermissions("system:monthly:add")
	public Map<String,Object> addMonthlyPlan(@RequestBody MonthlyPlan monthlyPlan) {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
//			com.hlwxy.xu_boot2.system.domain.PeopleDO people=monthlyPlanService.getPeople(user);
			//判断该计划是否为本下月的计划
			if (people.getMonthlyPlanId()!=null&&!people.getMonthlyPlanId().equals("")){  //已编写，进行下一步判断
				monthlyPlan.setId(people.getMonthlyPlanId());
				MonthlyPlanExtendDo monthlyPlanExtendDo1=monthlyPlanService.getMonthlyPlanById(monthlyPlan);
				//判断该用户最新的月计划是否为本月的月计划
				if (monthlyPlanExtendDo1.getMonthly_plan_time().equals(dateTool.years())){  //是，修改
					MonthlyPlanExtendDo monthlyPlanExtend=new MonthlyPlanExtendDo();
					monthlyPlanExtend.setMonthlyPlanId(people.getMonthlyPlanId());
					monthlyPlanExtend.setMonthly_plan_content(monthlyPlan.getMonthly_plan_content());
					monthlyPlanExtend.setMonthly_plan_state(monthlyPlan.getMonthly_plan_state());
					monthlyPlanExtend.setMonthly_plan_entry_time(dateTool.adyAndDay());
					monthlyPlanService.updateMonthlyPlanContentByPeople(monthlyPlanExtend);
					map.put("msg","保存成功");
					map.put("code",0);
				}else {  //不是，今天添加
					monthlyPlan.setMonthly_plan_code(String.valueOf(UUID.randomUUID()));
					monthlyPlan.setMonthly_plan_entry_time(dateTool.adyAndDay());
					monthlyPlan.setMonthly_plan_time(dateTool.years());
					monthlyPlan.setMonthly_plan_reply("否");//是否查看，默认为否
					monthlyPlan.setMonthly_plan_see("否");//是否回复，默认为否
					monthlyPlan.setPeo_id(people.getId());
					monthlyPlanService.addMonthlyPlan(monthlyPlan);
					//查询新添加的月计划
					MonthlyPlanExtendDo monthlyPlanExtendDo=monthlyPlanService.getMonthlyplanByCode(monthlyPlan.getMonthly_plan_code());
					people.setMonthlyPlanId(monthlyPlanExtendDo.getId());
					//修改人员月计划编码
					monthlyPlanService.updatePeopleByCode(people);
					map.put("code",0);
				}

			}else {
				monthlyPlan.setMonthly_plan_code(String.valueOf(UUID.randomUUID()));
				monthlyPlan.setMonthly_plan_entry_time(dateTool.adyAndDay());
				monthlyPlan.setMonthly_plan_time(dateTool.years());
				monthlyPlan.setMonthly_plan_reply("否");//是否查看，默认为否
				monthlyPlan.setMonthly_plan_see("否");//是否回复，默认为否
				monthlyPlan.setPeo_id(people.getId());
				monthlyPlanService.addMonthlyPlan(monthlyPlan);
				MonthlyPlanExtendDo monthlyPlanExtendDo=monthlyPlanService.getMonthlyplanByCode(monthlyPlan.getMonthly_plan_code());
				people.setMonthlyPlanId(monthlyPlanExtendDo.getId());
				//修改人员月计划编码
				monthlyPlanService.updatePeopleByCode(people);
				map.put("code",0);
			}
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
			System.out.println(e.getMessage());
		}

		return map;
	}

	//审核月计划
	@ResponseBody
	@RequestMapping("/updateMonthlyPlanStateById")
	@RequiresPermissions("system:monthly:sh")
	public Map<String,Object> updateMonthlyPlanStateById(@RequestBody MonthlyPlan monthlyPlan) {
		Map<String,Object> map=new HashMap<>();
		try{
			monthlyPlanService.updateMonthlyPlanStateById(monthlyPlan);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//批量审核月计划
	@ResponseBody
	@RequestMapping("/updateMonthlyPlanStateListById")
	@RequiresPermissions("system:monthly:sh")
	public Map<String,Object> updateMonthlyPlanStateListById(String[] ids,String state) {
		Map<String,Object> map=new HashMap<>();
		BatchAuditUtil batchAuditUtil=new BatchAuditUtil();
		batchAuditUtil.setIds(Arrays.asList(ids));
		batchAuditUtil.setState(Integer.valueOf(state));
		try{
			monthlyPlanService.updateMonthlyPlanStateListById(batchAuditUtil);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	private PeopleDO getUser(){
		PeopleDO user = (PeopleDO) SecurityUtils.getSubject().getPrincipal();
		PeopleDO peopleDO=monthlyPlanService.getPeopleById(user);
		return peopleDO;
	}



}
