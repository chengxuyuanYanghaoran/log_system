package com.hlwxy.xu_boot2.system.controller;

import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.DayPlanService;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
import com.hlwxy.xu_boot2.system.service.WeekPlanService;
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
@RequestMapping("/system/dayPlan")
public class DayPlanController {
	@Resource
	private MonthlyPlanService monthlyPlanService;
	@Resource
	private WeekPlanService weekPlanService;
	@Resource
	private DayPlanService dayPlanService;

	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list(Page page) {
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		//计算开始检索位置
		page.setPc(page.getPageSize()*(page.getPage()-1));
		Integer pp=dayPlanService.coun();
		List<DayPlanExtendDO> dayPlanExtendDOS = dayPlanService.findDayPlan(page);
		dayPlanExtendDOS=findDayMonPeoByPid(dayPlanExtendDOS);
		int total = dayPlanService.count();
	    map.put("dayPlanExtendDOS",dayPlanExtendDOS);
		map.put("pp",pp);
	    map.put("total",total);
		return map;
	}

	//查询个人日计划
	@ResponseBody
	@RequestMapping("/getDayPlan")
	public Map<String,Object> getDayPlan() {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			DayPlanDO weekPlanDO = new DayPlanDO();
			weekPlanDO.setId(people.getDayPlanId());
			DayPlanExtendDO dayPlanExtendDO = dayPlanService.getDayPlanById2(weekPlanDO);
			if(!dayPlanExtendDO.getDay_plan_time().equals(dateTool.adyAndDay())){
				map.put("code",0);
				map.put("msg","您还没有编写今天的计划");
			}else {
				map.put("dayPlanExtendDO",dayPlanExtendDO);
				map.put("code",0);
			}
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","未查询到相应数据！");
		}
		return map;
	}

	//根据计划id查询
	@ResponseBody
	@RequestMapping("/getDayPlanById")
	//查询
	@RequiresPermissions("system:dayPlan:edit")
	public Map<String,Object> getDayPlanById(@RequestBody DayPlanDO dayPlanDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询日计划数据
			DayPlanExtendDO dayPlanExtendDO = dayPlanService.getDayPlanById(dayPlanDO);
			if (dayPlanExtendDO==null){
				map.put("code",-1);
				map.put("msg","他（她）未编写周计划或月计划");
			}else {
				map.put("dayPlanExtendDO",dayPlanExtendDO);
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
	@RequestMapping("/compositeQuery")
	public Map<String,Object> compositeQuery(ConditionDO conditionDO) {
		Map<String,Object> map=new HashMap<>();

		//字符转换，将第一个转换成年，第二个转换成月
		String str = conditionDO.getTime();
		String destStr = "";
		String ss[] = str.split("-");
		for (int i = 0; i < ss.length; i++) {
			if (i % 2 == 1) {
				destStr = destStr + ss[i] + "月" + ss[i+1];
			} else if (i != ss.length - 1) {
				destStr = destStr + ss[i] + "年";
			}
		}
		conditionDO.setTime(destStr);
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));
		try{
			//组合查询
			List<DayPlanExtendDO> dayPlanExtendDOS = dayPlanService.compositeQuery(conditionDO);
			dayPlanExtendDOS=findDayMonPeoByPid(dayPlanExtendDOS);
			Integer pp=dayPlanService.queryCount(conditionDO);
			map.put("dayPlanExtendDOS",dayPlanExtendDOS);
			map.put("pp",pp);
			map.put("code",0);
		}catch (Exception e){
			System.out.println(e.getMessage());
			map.put("code",-1);
			map.put("msg","请输入正确的查询条件");
		}
		return map;
	}

	//添加日计划
	@ResponseBody
	@RequestMapping("/addDayPlan")
	//添加
	@RequiresPermissions("system:dayPlan:add")
	public Map<String,Object> addDayPlan(@RequestBody DayPlanDO dayPlanDO) {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			//获取登陆的账号
			PeopleDO people=getUser();
			//判断是否编写过周计划
			if(people.getWeekPlanId()!=null&&!people.getWeekPlanId().equals("")){  //编写过周计划
				WeekPlanDO weekPlanDO=new WeekPlanDO();
				weekPlanDO.setId(people.getWeekPlanId());
				WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanById2(weekPlanDO);
				if (weekPlanExtendDO.getWeek_plan_time().equals(dateTool.WeekAndWeek())){ //是本周的计划
					//判断日计划并添加
					map=add(dayPlanDO,dateTool);
				}else {//不是本周的周计划
					if (dayPlanDO.getDay_plan_state()==1){ //保存按钮
						//判断日计划并添加
						map=add(dayPlanDO,dateTool);
					}else {
						map.put("code",-1);
						map.put("msg","请先编写周计划！");
					}
				}
			}else {  //未编写周计划
				if (dayPlanDO.getDay_plan_state()==1){ //保存按钮
					//判断日计划并添加
					map=add(dayPlanDO,dateTool);
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

	//审核日计划
	@ResponseBody
	@RequestMapping("/updateDayPlanStateById")
	@RequiresPermissions("system:dayPlan:sh")
	public Map<String,Object> updateDayPlanStateById(@RequestBody DayPlanDO dayPlanDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			dayPlanService.updateDayPlanStateById(dayPlanDO);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//批量审核日计划
	@ResponseBody
	@RequestMapping("/updateDayPlanStateListById")
	@RequiresPermissions("system:dayPlan:sh")
	public Map<String,Object> updateDayPlanStateListById(String[] ids,String state) {
		Map<String,Object> map=new HashMap<>();
		BatchAuditUtil batchAuditUtil=new BatchAuditUtil();
		batchAuditUtil.setIds(Arrays.asList(ids));
		batchAuditUtil.setState(Integer.valueOf(state));
		try{
			dayPlanService.updateDayPlanStateListById(batchAuditUtil);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//根据工号查询该人所有日计划
	@ResponseBody
	@RequestMapping("/findDayPlanPeoByPid")
	@RequiresPermissions("system:dayPlan:edit")
	public Map<String,Object> findDayPlanPeoByPid(ConditionDO conditionDO,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<>();
		String pageStr=request.getParameter("page");
		String limitStr=request.getParameter("limit");
		conditionDO.setPage(Integer.valueOf(pageStr));
		conditionDO.setPageSize(Integer.valueOf(limitStr));
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));

		try{
			Integer pp=dayPlanService.countMonPeoByPid(conditionDO);
			List<DayPlanExtendDO> weekPlanExtendDOS = dayPlanService.findMonPeoByPid(conditionDO);
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

	//获取每个人的月总结总数
	private List<DayPlanExtendDO> findDayMonPeoByPid(List<DayPlanExtendDO> dayPlanExtendDOS){
		Integer count=0;
		ConditionDO conditionDO = new ConditionDO();
		for (DayPlanExtendDO monthlyPlan:dayPlanExtendDOS) {
			conditionDO.setJobNumber(monthlyPlan.getJobNumber());
			Integer integer=dayPlanService.countMonPeoByPid(conditionDO);
			if (dayPlanService.countMonPeoByPid(conditionDO)==null){
				count=0;
			}else {
				count=dayPlanService.countMonPeoByPid(conditionDO);
			}
			monthlyPlan.setCountPlan(count);
		}
		return dayPlanExtendDOS;
	}

	//保存或添加方法
	private Map<String,Object> add(DayPlanDO dayPlanDO,DateTool dateTool){
		Map<String,Object> map=new HashMap<>();
		PeopleDO people=getUser();
		//判断该人员是否编写过日计划
		if (people.getDayPlanId()!=null&&!people.getDayPlanId().equals("")){  //编写过，进行下一步判断
			//设置实体中日计划的id
			dayPlanDO.setId(people.getDayPlanId());
			//获取该人员最新的日计划
			DayPlanExtendDO dayPlanExtendDO1=dayPlanService.getDayPlanById2(dayPlanDO);
			//判断还计划是否是今天的计划
			if (dayPlanExtendDO1.getDay_plan_time().equals(dateTool.adyAndDay())){//是今天的计划,进行更改
				DayPlanExtendDO dayPlanExtendDO=new DayPlanExtendDO();
				dayPlanExtendDO.setDayPlanId(people.getDayPlanId());
				dayPlanExtendDO.setDay_plan_content(dayPlanDO.getDay_plan_content());
				dayPlanExtendDO.setDay_plan_state(dayPlanDO.getDay_plan_state());
				dayPlanExtendDO.setDay_plan_entry_time(dateTool.adyAndDay());
				dayPlanService.updateDayPlanContentByPeople(dayPlanExtendDO);
				map.put("msg","修改成功");
				map.put("code",0);
			}else {  //不是今天的计划，进行添加
				dayPlanDO.setDay_plan_code(String.valueOf(UUID.randomUUID()));
				dayPlanDO.setDay_plan_entry_time(dateTool.adyAndDay());
				dayPlanDO.setDay_plan_time(dateTool.adyAndDay());
				dayPlanDO.setDay_plan_reply("否");//是否查看，默认为否
				dayPlanDO.setDay_plan_see("否");//是否回复，默认为否
				dayPlanDO.setPeo_id(people.getId());
				dayPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());//改日计划属于第几周
				dayPlanService.addDayPlan(dayPlanDO);
				DayPlanExtendDO dayPlanExtendDO=dayPlanService.getDayPlanByCode(dayPlanDO.getDay_plan_code());
				people.setDayPlanId(dayPlanExtendDO.getId());
				//修改人员日计划编码
				dayPlanService.updatePeopleByCode(people);
				map.put("msg","保存成功");
				map.put("code",0);
			}
		}else { //该人员还未编写过日计划，进行添加
			dayPlanDO.setDay_plan_code(String.valueOf(UUID.randomUUID()));
			dayPlanDO.setDay_plan_entry_time(dateTool.adyAndDay());
			dayPlanDO.setDay_plan_time(dateTool.adyAndDay());
			dayPlanDO.setDay_plan_reply("否");//是否查看，默认为否
			dayPlanDO.setDay_plan_see("否");//是否回复，默认为否
			dayPlanDO.setPeo_id(people.getId());//该日计划属于谁
			dayPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());//改日计划属于第几周
			dayPlanService.addDayPlan(dayPlanDO);
			DayPlanExtendDO dayPlanExtendDO=dayPlanService.getDayPlanByCode(dayPlanDO.getDay_plan_code());
			people.setDayPlanId(dayPlanExtendDO.getId());
			//修改人员日计划编码
			dayPlanService.updatePeopleByCode(people);
			map.put("msg","保存成功");
			map.put("code",0);
		}
		return map;
	}

}
