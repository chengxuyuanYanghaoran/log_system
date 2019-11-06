package com.hlwxy.xu_boot2.system.controller;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
import com.hlwxy.xu_boot2.system.service.WeekPlanService;
import com.hlwxy.xu_boot2.system.service.WeekSummaryService;
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
@RequestMapping("/system/weekSummary")
public class WeekSummaryController {
	@Resource
	private MonthlyPlanService monthlyPlanService;
	@Resource
	private WeekSummaryService weekSummaryService;
	@Resource
	private WeekPlanService weekPlanService;

	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list(Page page) {
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		//计算开始检索位置
		page.setPc(page.getPageSize()*(page.getPage()-1));
		Integer pp=weekSummaryService.coun();
		List<WeekSummaryExtendDO> weekSummaryExtendDOS = weekSummaryService.findWeekSummary(page);
		weekSummaryExtendDOS=findMonPeoByPid(weekSummaryExtendDOS);
		int total = weekSummaryService.count();
	    map.put("weekSummaryExtendDOS",weekSummaryExtendDOS);
		map.put("pp",pp);
	    map.put("total",total);
		return map;
	}

	//查询个人月总结
	@ResponseBody
	@RequestMapping("/getWeekSummary")
	public Map<String,Object> getWeekSummary() {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			WeekSummaryDO weekSummaryDO = new WeekSummaryDO();
			weekSummaryDO.setId(people.getWeekSummaryId());
			//查询数据
			WeekSummaryExtendDO weekSummaryExtendDO = weekSummaryService.getWeekSummaryById2(weekSummaryDO);
			if(!weekSummaryExtendDO.getWeek_summary_time().equals(dateTool.WeekAndWeek())){
				map.put("code",0);
				map.put("msg","您还没有编写本周的总结");
			}else {
				map.put("weekSummaryExtendDO",weekSummaryExtendDO);
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
	@RequestMapping("/getWeekSummaryById")
	//chax
	@RequiresPermissions("system:weekSummary:edit")
	public Map<String,Object> getWeekSummaryById(@RequestBody WeekSummaryDO weekSummaryDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询周总结数据
			WeekSummaryExtendDO weekSummaryExtendDO = weekSummaryService.getWeekSummaryById(weekSummaryDO);
			if (weekSummaryExtendDO==null){
				map.put("code",-1);
				map.put("msg","他（她）未编写月计划或周计划");
			}else {
				//封装到map
				map.put("weekSummaryExtendDO",weekSummaryExtendDO);
				map.put("code",0);
			}

		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//多条件组合查询
	@ResponseBody
	@RequestMapping("/compositeQueryWeekSummary")
	public Map<String,Object> compositeQueryWeekSummary(ConditionDO conditionDO) {
		Map<String,Object> map=new HashMap<>();
		String sss=conditionDO.getTime().replace('-','年');
		conditionDO.setTime(sss);
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));
		try{
			//组合查询
			List<WeekSummaryExtendDO> weekSummaryExtendDOS = weekSummaryService.compositeQueryWeekSummary(conditionDO);
			weekSummaryExtendDOS=findMonPeoByPid(weekSummaryExtendDOS);
			Integer pp=weekSummaryService.queryCount(conditionDO);
			map.put("weekSummaryExtendDOS",weekSummaryExtendDOS);
			map.put("pp",pp);
			map.put("code",0);
		}catch (Exception e){
			System.out.println(e.getMessage());
			map.put("code",-1);
			map.put("msg","请输入正确的查询条件");
		}
		return map;
	}

	//保存和提交
	@ResponseBody
	@RequestMapping("/addWeekSummary")
	//添加
	@RequiresPermissions("system:weekSummary:add")
	public Map<String,Object> addWeekSummary(@RequestBody WeekSummaryDO weekSummaryDO) {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			//判断是否编写过周计划
			if (people.getWeekPlanId()!=null&&!people.getWeekPlanId().equals("")){// 编写过
				WeekPlanDO weekPlanDO=new WeekPlanDO();
				weekPlanDO.setId(people.getWeekPlanId());
				WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanById2(weekPlanDO);
				if (weekPlanExtendDO.getWeek_plan_time().equals(dateTool.WeekAndWeek())){ //是本周的计划
					//判断并添加周总结
					map=add(weekSummaryDO,dateTool);
				}else { //不是本周的总结
					if (weekSummaryDO.getWeek_summary_state()==1){ //保存按钮
						map=add(weekSummaryDO,dateTool);
					}else {
						map.put("code",-1);
						map.put("msg","请先编写周计划！");
					}
				}
			}else { //未编写周计划
				if (weekSummaryDO.getWeek_summary_state()==1){ //保存按钮
					map=add(weekSummaryDO,dateTool);
				}else {
					map.put("code",-1);
					map.put("msg","请先编写周计划！");
				}
			}

		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
			System.out.println(e.getMessage());
		}
		return map;
	}

	//审核月总结
	@ResponseBody
	@RequestMapping("/updateWeekSummaryStateById")
	@RequiresPermissions("system:weekSummary:sh")
	public Map<String,Object> updateWeekSummaryStateById(@RequestBody WeekSummaryDO weekSummaryDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			weekSummaryService.updateWeekSummaryStateById(weekSummaryDO);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}
	//批量审核周计划
	@ResponseBody
	@RequestMapping("/updateWeekSummaryStateListById")
	@RequiresPermissions("system:weekSummary:sh")
	public Map<String,Object> updateWeekSummaryStateListById(String[] ids,String state) {
		Map<String,Object> map=new HashMap<>();
		BatchAuditUtil batchAuditUtil=new BatchAuditUtil();
		batchAuditUtil.setIds(Arrays.asList(ids));
		batchAuditUtil.setState(Integer.valueOf(state));
		try{
			weekSummaryService.updateWeekSummaryStateListById(batchAuditUtil);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据工号查询该人所有月计划
	@ResponseBody
	@RequestMapping("/findWeelSummPeoByPid")
	@RequiresPermissions("system:weekSummary:edit")
	public Map<String,Object> findWeelSummPeoByPid(ConditionDO conditionDO,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<>();
		String pageStr=request.getParameter("page");
		String limitStr=request.getParameter("limit");
		conditionDO.setPage(Integer.valueOf(pageStr));
		conditionDO.setPageSize(Integer.valueOf(limitStr));
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));
		try{
			Integer pp=weekSummaryService.countSumPeoByPid(conditionDO);
			List<WeekSummaryExtendDO> weekPlanExtendDOS = weekSummaryService.findSumPeoByPid(conditionDO);
			map.put("count",pp);
			map.put("data",weekPlanExtendDOS);
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

	//获取每个人的周总结总数
	private List<WeekSummaryExtendDO> findMonPeoByPid(List<WeekSummaryExtendDO> weekPlanExtendDOS){
		Integer count=0;
		ConditionDO conditionDO = new ConditionDO();
		for (WeekSummaryExtendDO monthlyPlan:weekPlanExtendDOS) {
			conditionDO.setJobNumber(monthlyPlan.getJobNumber());
			Integer integer=weekSummaryService.countSumPeoByPid(conditionDO);
			if (weekSummaryService.countSumPeoByPid(conditionDO)==null){
				count=0;
			}else {
				count=weekSummaryService.countSumPeoByPid(conditionDO);
			}
			monthlyPlan.setCountSumm(count);
		}
		return weekPlanExtendDOS;
	}

	//添加或保存周总结方法
	private Map<String,Object> add(WeekSummaryDO weekSummaryDO,DateTool dateTool){
		Map<String,Object> map=new HashMap<>();
		PeopleDO people=getUser();

		if (people.getWeekSummaryId()!=null&&!people.getWeekSummaryId().equals("")){ //判断该用户是否编写过周总结
			weekSummaryDO.setId(people.getWeekSummaryId());
			WeekSummaryExtendDO weekSummaryExtendDO1=weekSummaryService.getWeekSummaryById2(weekSummaryDO);
			//判断该周总结是否为本周的周总结
			if (weekSummaryExtendDO1.getWeek_summary_time().equals(dateTool.WeekAndWeek())){  //是本周，修改
				WeekSummaryExtendDO weekSummaryExtendDO=new WeekSummaryExtendDO();
				weekSummaryExtendDO.setWeekSummaryId(people.getWeekSummaryId());
				weekSummaryExtendDO.setWeek_summary_content(weekSummaryDO.getWeek_summary_content());
				weekSummaryExtendDO.setWeek_summary_state(weekSummaryDO.getWeek_summary_state());
				weekSummaryExtendDO.setWeek_summary_entry_time(dateTool.adyAndDay());
				weekSummaryService.updateWeekSummaryContentByPeople(weekSummaryExtendDO);
				map.put("msg","保存成功");
				map.put("code",0);
			}else {  //不是本周
				weekSummaryDO.setWeek_summary_code(String.valueOf(UUID.randomUUID()));
				weekSummaryDO.setWeek_summary_entry_time(dateTool.adyAndDay());
				weekSummaryDO.setWeek_summary_time(dateTool.WeekAndWeek());
				weekSummaryDO.setWeek_summary_reply("否");//是否查看，默认为否
				weekSummaryDO.setWeek_summary_see("否");//是否回复，默认为否
				weekSummaryDO.setPeo_id(people.getId());
				weekSummaryService.addWeekSummary(weekSummaryDO);
				WeekSummaryExtendDO weekSummaryExtendDO=weekSummaryService.getWeekSummaryByCode(weekSummaryDO.getWeek_summary_code());
				people.setWeekSummaryId(weekSummaryExtendDO.getId());
				//修改人员月计划编码
				weekSummaryService.updatePeopleByCode(people);
				map.put("msg","保存成功");
				map.put("code",0);
			}
		}else {  //未编写过周总结
			weekSummaryDO.setWeek_summary_code(String.valueOf(UUID.randomUUID()));
			weekSummaryDO.setWeek_summary_entry_time(dateTool.adyAndDay());
			weekSummaryDO.setWeek_summary_time(dateTool.WeekAndWeek());
			weekSummaryDO.setWeek_summary_reply("否");//是否查看，默认为否
			weekSummaryDO.setWeek_summary_see("否");//是否回复，默认为否
			weekSummaryDO.setPeo_id(people.getId());
			weekSummaryService.addWeekSummary(weekSummaryDO);
			WeekSummaryExtendDO weekSummaryExtendDO=weekSummaryService.getWeekSummaryByCode(weekSummaryDO.getWeek_summary_code());
			people.setWeekSummaryId(weekSummaryExtendDO.getId());
			//修改人员月计划编码
			weekSummaryService.updatePeopleByCode(people);
			map.put("msg","保存成功");
			map.put("code",0);
		}

		return map;
	}

}
