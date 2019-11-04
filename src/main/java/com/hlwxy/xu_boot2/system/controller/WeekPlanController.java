package com.hlwxy.xu_boot2.system.controller;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
import com.hlwxy.xu_boot2.system.service.WeekPlanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


/**
 *
 * @date 2019-09-26 22:35:53
 */

@Controller
@RequestMapping("/system/weekPlan")
public class WeekPlanController {
	@Resource
	private MonthlyPlanService monthlyPlanService;
	@Resource
	private WeekPlanService weekPlanService;
	@Resource
	private MonthlyController monthlyController;

	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list(Page page) {
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		//计算开始检索位置
		page.setPc(page.getPageSize()*(page.getPage()-1));
		Integer pp=weekPlanService.coun();
		List<WeekPlanExtendDO> weekPlanExtendDOS = weekPlanService.findWeekPlan(page);
		int total = weekPlanService.count();
		map.put("pp",pp);
	    map.put("weekPlanExtendDOS",weekPlanExtendDOS);
	    map.put("total",total);
		return map;
	}

	//查询个人月计划
	@ResponseBody
	@RequestMapping("/getWeekPlan")
	public Map<String,Object> getWeekPlan() {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			WeekPlanDO weekPlanDO = new WeekPlanDO();
			weekPlanDO.setId(people.getWeekPlanId());
			//查询数据
			WeekPlanExtendDO weekPlanExtendDO = weekPlanService.getWeekPlanById2(weekPlanDO);
			if(!weekPlanExtendDO.getWeek_plan_time().equals(dateTool.WeekAndWeek())){
				map.put("code",0);
				map.put("msg","您还没有编写本周的计划");
			}else {
				map.put("weekPlanExtendDO",weekPlanExtendDO);
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
	@RequestMapping("/getWeekPlanById")
	//查看
	@RequiresPermissions("system:weekPlan:edit")
	public Map<String,Object> getWeekPlanById(@RequestBody WeekPlanDO weekPlanDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询周计划数据
			WeekPlanExtendDO weekPlanExtendDO = weekPlanService.getWeekPlanById(weekPlanDO);
			if (weekPlanExtendDO==null){
				map.put("code",-1);
				map.put("msg","他（她）未编写月计划");
			}else {
				//封装到map
				map.put("weekPlanExtendDO",weekPlanExtendDO);
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
		//字符转换
		String sss=conditionDO.getTime().replace('-','年');
		conditionDO.setTime(sss);
		//计算开始检索位置
		conditionDO.setPc(conditionDO.getPageSize()*(conditionDO.getPage()-1));
		try{
			//组合查询
			List<WeekPlanExtendDO> weekPlanExtendDOS = weekPlanService.compositeQuery(conditionDO);
			Integer pp=weekPlanService.queryCount(conditionDO);
			map.put("weekPlanExtendDOS",weekPlanExtendDOS);
			map.put("pp",pp);
			map.put("code",0);
		}catch (Exception e){
			System.out.println(e.getMessage());
			map.put("code",-1);
			map.put("msg","请输入正确的查询条件");
		}
		return map;
	}

	//添加周计划
	@ResponseBody
	@RequestMapping("/addWeekPlan")
	@RequiresPermissions("system:weekPlan:add")
	public Map<String,Object> addWeekPlan(@RequestBody WeekPlanDO weekPlanDO) {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			//判断是否编写过月计划
			if (people.getMonthlyPlanId()!=null&&!people.getMonthlyPlanId().equals("")){  //编写过
				MonthlyPlan monthlyPlan=new MonthlyPlan();
				monthlyPlan.setId(people.getMonthlyPlanId());
				MonthlyPlanExtendDo monthlyPlanExtendDo=monthlyPlanService.getMonthlyPlanById(monthlyPlan);
				if (monthlyPlanExtendDo.getMonthly_plan_time().equals(dateTool.years())){ //是本月的计划

					if (people.getWeekPlanId()!=null&&!people.getWeekPlanId().equals("")){//判断该人员是否编写过周计划
						weekPlanDO.setId(people.getWeekPlanId());//设置周计划实体的id
						//获取人员的最新周计划
						WeekPlanExtendDO weekPlanExtendDO1=weekPlanService.getWeekPlanById2(weekPlanDO);
						//判断该周计划是否是本周的周计划
						if (weekPlanExtendDO1.getWeek_plan_time().equals(dateTool.WeekAndWeek())){//是本周的周计划，修改
							WeekPlanExtendDO weekPlanExtendDO=new WeekPlanExtendDO();
							weekPlanExtendDO.setWeekPlanId(people.getWeekPlanId());
							weekPlanExtendDO.setWeek_plan_content(weekPlanDO.getWeek_plan_content());
							weekPlanExtendDO.setWeek_plan_state(weekPlanDO.getWeek_plan_state());
							weekPlanExtendDO.setWeek_plan_entry_time(dateTool.adyAndDay());
							weekPlanService.updateWeekPlanContentByPeople(weekPlanExtendDO);
							map.put("msg","保存成功");
							map.put("code",0);
						}else {  //不是本周的周计划
							weekPlanDO.setWeek_plan_code(String.valueOf(UUID.randomUUID()));
							weekPlanDO.setWeek_plan_entry_time(dateTool.adyAndDay());
							weekPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());
							weekPlanDO.setWeek_plan_reply("否");//是否查看，默认为否
							weekPlanDO.setWeek_plan_see("否");//是否回复，默认为否
							weekPlanDO.setPeo_id(people.getId());
							weekPlanDO.setMonthly_plan_time(dateTool.years());//改周计划属于那个月
							weekPlanService.addWeekPlan(weekPlanDO);
							WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanByCode(weekPlanDO.getWeek_plan_code());
							people.setWeekPlanId(weekPlanExtendDO.getId());
							//修改人员周计划编码
							weekPlanService.updatePeopleByCode(people);
							map.put("code",0);
						}
					}else {  //未编写过周计划
						weekPlanDO.setWeek_plan_code(String.valueOf(UUID.randomUUID()));
						weekPlanDO.setWeek_plan_entry_time(dateTool.adyAndDay());
						weekPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());
						weekPlanDO.setWeek_plan_reply("否");//是否查看，默认为否
						weekPlanDO.setWeek_plan_see("否");//是否回复，默认为否
						weekPlanDO.setPeo_id(people.getId());
						weekPlanDO.setMonthly_plan_time(dateTool.years());//改周计划属于那个月
						weekPlanService.addWeekPlan(weekPlanDO);
						WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanByCode(weekPlanDO.getWeek_plan_code());
						people.setWeekPlanId(weekPlanExtendDO.getId());
						//修改人员周计划编码
						weekPlanService.updatePeopleByCode(people);
						map.put("code",0);
					}
				}else {
					if (weekPlanDO.getWeek_plan_state()==1){ //保存按钮
						weekPlanDO.setWeek_plan_code(String.valueOf(UUID.randomUUID()));
						weekPlanDO.setWeek_plan_entry_time(dateTool.adyAndDay());
						weekPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());
						weekPlanDO.setWeek_plan_reply("否");//是否查看，默认为否
						weekPlanDO.setWeek_plan_see("否");//是否回复，默认为否
						weekPlanDO.setPeo_id(people.getId());
						weekPlanDO.setMonthly_plan_time(dateTool.years());//改周计划属于那个月
						weekPlanService.addWeekPlan(weekPlanDO);
						WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanByCode(weekPlanDO.getWeek_plan_code());
						people.setWeekPlanId(weekPlanExtendDO.getId());
						//修改人员周计划编码
						weekPlanService.updatePeopleByCode(people);
						map.put("code",0);
						map.put("msg","保存成功！");
					}else {
						map.put("code",-1);
						map.put("msg","请先编写月计划！");
					}

				}
			}else {
				if (weekPlanDO.getWeek_plan_state()==1){ //保存按钮
					weekPlanDO.setWeek_plan_code(String.valueOf(UUID.randomUUID()));
					weekPlanDO.setWeek_plan_entry_time(dateTool.adyAndDay());
					weekPlanDO.setWeek_plan_time(dateTool.WeekAndWeek());
					weekPlanDO.setWeek_plan_reply("否");//是否查看，默认为否
					weekPlanDO.setWeek_plan_see("否");//是否回复，默认为否
					weekPlanDO.setPeo_id(people.getId());
					weekPlanDO.setMonthly_plan_time(dateTool.years());//改周计划属于那个月
					weekPlanService.addWeekPlan(weekPlanDO);
					WeekPlanExtendDO weekPlanExtendDO=weekPlanService.getWeekPlanByCode(weekPlanDO.getWeek_plan_code());
					people.setWeekPlanId(weekPlanExtendDO.getId());
					//修改人员周计划编码
					weekPlanService.updatePeopleByCode(people);
					map.put("code",0);
					map.put("msg","保存成功！");
				}else {
					map.put("code",-1);
					map.put("msg","请先编写月计划！");
				}

			}

		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
			System.out.println(e.getMessage());
		}

		return map;
	}

	//审核周计划
	@ResponseBody
	@RequestMapping("/updateWeekPlanStateById")
	@RequiresPermissions("system:weekPlan:sh")
	public Map<String,Object> updateWeekPlanStateById(@RequestBody WeekPlanDO weekPlanDO) {
		Map<String,Object> map=new HashMap<>();
		try{
			weekPlanService.updateWeekPlanStateById(weekPlanDO);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}
	//批量审核周计划
	@ResponseBody
	@RequestMapping("/updateWeekPlanStateListById")
	@RequiresPermissions("system:weekPlan:sh")
	public Map<String,Object> updateWeekPlanStateListById(String[] ids,String state) {
		Map<String,Object> map=new HashMap<>();
		BatchAuditUtil batchAuditUtil=new BatchAuditUtil();
		batchAuditUtil.setIds(Arrays.asList(ids));
		batchAuditUtil.setState(Integer.valueOf(state));
		try{
			weekPlanService.updateWeekPlanStateListById(batchAuditUtil);
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
