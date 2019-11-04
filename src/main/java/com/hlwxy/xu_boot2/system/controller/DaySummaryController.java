package com.hlwxy.xu_boot2.system.controller;


import com.hlwxy.xu_boot2.common.utils.BatchAuditUtil;
import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.DayPlanService;
import com.hlwxy.xu_boot2.system.service.DaySummaryService;
import com.hlwxy.xu_boot2.system.service.MonthlyPlanService;
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
@RequestMapping("/system/daySummary")
public class DaySummaryController {
	@Resource
	private MonthlyPlanService monthlyPlanService;
	@Resource
	private DaySummaryService daySummaryService;
	@Resource
	private DayPlanService dayPlanService;

	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list(Page page) {
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		//计算开始检索位置
		page.setPc(page.getPageSize()*(page.getPage()-1));
		Integer pp=daySummaryService.coun();
		List<DaySummaryExtendDO> daySummaryExtendDOS = daySummaryService.findDaySummary(page);
		int total = daySummaryService.count();
	    map.put("daySummaryExtendDOS",daySummaryExtendDOS);
		map.put("pp",pp);
	    map.put("total",total);
		return map;
	}

	//查询个人月总结
	@ResponseBody
	@RequestMapping("/getDaySummary")
	public Map<String,Object> getDaySummary() {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			DaySummaryDo daySummaryDo = new DaySummaryDo();
			daySummaryDo.setId(people.getDaySummaryId());
			//查询数据
			DaySummaryExtendDO daySummaryExtendDO = daySummaryService.getDaySummaryById2(daySummaryDo);
			if(!daySummaryExtendDO.getDay_summary_time().equals(dateTool.adyAndDay())){
				map.put("code",0);
				map.put("msg","您还没有编写今天总结");
			}else {
				map.put("daySummaryExtendDO",daySummaryExtendDO);
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
	@RequestMapping("/getDaySummaryById")
	//查看
	@RequiresPermissions("system:daySummary:edit")
	public Map<String,Object> getDaySummaryById(@RequestBody DaySummaryDo daySummaryDo) {
		Map<String,Object> map=new HashMap<>();
		try{
			//查询日总结数据
			DaySummaryExtendDO daySummaryExtendDO = daySummaryService.getDaySummaryById(daySummaryDo);
			if (daySummaryExtendDO==null){
				map.put("code",-1);
				map.put("msg","他（她）未编写周计划或月计划或日计划");
			}else {
				//封装到map
				map.put("daySummaryExtendDO",daySummaryExtendDO);
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
	@RequestMapping("/compositeQueryDaySummary")
	public Map<String,Object> compositeQueryDaySummary(ConditionDO conditionDO) {
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
			List<DaySummaryExtendDO> daySummaryExtendDOS = daySummaryService.compositeQueryDaySummary(conditionDO);
			Integer pp=daySummaryService.queryCount(conditionDO);
			map.put("daySummaryExtendDOS",daySummaryExtendDOS);
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
	@RequestMapping("/addDaySummary")
	//添加总结
	@RequiresPermissions("system:daySummary:add")
	public Map<String,Object> addDaySummary(@RequestBody DaySummaryDo daySummaryDo) {
		Map<String,Object> map=new HashMap<>();
		DateTool dateTool=new DateTool();
		try{
			PeopleDO people=getUser();
			//判断是否编写过日计划
			if (people.getDayPlanId()!=null&&!people.getDayPlanId().equals("")){ //编写过，判断该计划是否为今天的日计划
				//为日计划的实体赋值
				DayPlanDO dayPlanDO = new DayPlanDO();
				dayPlanDO.setId(people.getDayPlanId());
				DayPlanExtendDO dayPlanExtendDO = dayPlanService.getDayPlanById2(dayPlanDO);//获取日计划
				if (dayPlanExtendDO.getDay_plan_time().equals(dateTool.adyAndDay())){//是今天的日计划

					//判断是否编写过日总结
					if (people.getDaySummaryId()!=null&&!people.getDaySummaryId().equals("")){  //编写过，进行下一步判断
						//给日总结的实体类设置id
						daySummaryDo.setId(people.getDaySummaryId());
						//获取该用户最新的日总结
						DaySummaryExtendDO daySummaryExtendDO1=daySummaryService.getDaySummaryById(daySummaryDo);
						//判断最新的日总结是否是今天的日总结
						if (daySummaryExtendDO1.getDay_summary_time().equals(dateTool.adyAndDay())){  //是今天的日总结
							DaySummaryExtendDO daySummaryExtendDO=new DaySummaryExtendDO();
							daySummaryExtendDO.setDaySummaryId(people.getDaySummaryId());
							daySummaryExtendDO.setDay_summary_content(daySummaryDo.getDay_summary_content());
							daySummaryExtendDO.setDay_summary_state(daySummaryDo.getDay_summary_state());
							daySummaryExtendDO.setDay_summary_entry_time(dateTool.adyAndDay());
							daySummaryService.updateDaySummaryContentByPeople(daySummaryExtendDO);
							map.put("msg","保存成功");
							map.put("code",0);
						}else {  //不是今天的日总结
							daySummaryDo.setDay_summary_code(String.valueOf(UUID.randomUUID()));
							daySummaryDo.setDay_summary_entry_time(dateTool.adyAndDay());
							daySummaryDo.setDay_summary_time(dateTool.adyAndDay());
							daySummaryDo.setDay_summary_reply("否");//是否查看，默认为否
							daySummaryDo.setDay_summary_see("否");//是否回复，默认为否
							daySummaryDo.setPeo_id(people.getId());
							daySummaryService.addDaySummary(daySummaryDo);
							DaySummaryExtendDO daySummaryExtendDO=daySummaryService.getDaySummaryByCode(daySummaryDo.getDay_summary_code());
							people.setDaySummaryId(daySummaryExtendDO.getId());
							//修改人员月计划编码
							daySummaryService.updatePeopleByCode(people);
							map.put("code",0);
						}
					}else { //未编写过，进行添加
						daySummaryDo.setDay_summary_code(String.valueOf(UUID.randomUUID()));
						daySummaryDo.setDay_summary_entry_time(dateTool.adyAndDay());
						daySummaryDo.setDay_summary_time(dateTool.adyAndDay());
						daySummaryDo.setDay_summary_reply("否");//是否查看，默认为否
						daySummaryDo.setDay_summary_see("否");//是否回复，默认为否
						daySummaryDo.setPeo_id(people.getId());
						daySummaryService.addDaySummary(daySummaryDo);
						DaySummaryExtendDO daySummaryExtendDO=daySummaryService.getDaySummaryByCode(daySummaryDo.getDay_summary_code());
						people.setDaySummaryId(daySummaryExtendDO.getId());
						//修改人员月计划编码
						daySummaryService.updatePeopleByCode(people);
						map.put("code",0);
					}

				}else {  //不是今天的日计划
					if (daySummaryDo.getDay_summary_state()==1){ //保存按钮
						daySummaryDo.setDay_summary_code(String.valueOf(UUID.randomUUID()));
						daySummaryDo.setDay_summary_entry_time(dateTool.adyAndDay());
						daySummaryDo.setDay_summary_time(dateTool.adyAndDay());
						daySummaryDo.setDay_summary_reply("否");//是否查看，默认为否
						daySummaryDo.setDay_summary_see("否");//是否回复，默认为否
						daySummaryDo.setPeo_id(people.getId());
						daySummaryService.addDaySummary(daySummaryDo);
						DaySummaryExtendDO daySummaryExtendDO=daySummaryService.getDaySummaryByCode(daySummaryDo.getDay_summary_code());
						people.setDaySummaryId(daySummaryExtendDO.getId());
						//修改人员月计划编码
						daySummaryService.updatePeopleByCode(people);
						map.put("code",0);
						map.put("msg","保存成功！");
					}else {
						map.put("code",-1);
						map.put("msg","请先编写日计划！");
					}

				}
			}else {  //未编写日计划
				if (daySummaryDo.getDay_summary_state()==1){ //保存按钮
					daySummaryDo.setDay_summary_code(String.valueOf(UUID.randomUUID()));
					daySummaryDo.setDay_summary_entry_time(dateTool.adyAndDay());
					daySummaryDo.setDay_summary_time(dateTool.adyAndDay());
					daySummaryDo.setDay_summary_reply("否");//是否查看，默认为否
					daySummaryDo.setDay_summary_see("否");//是否回复，默认为否
					daySummaryDo.setPeo_id(people.getId());
					daySummaryService.addDaySummary(daySummaryDo);
					DaySummaryExtendDO daySummaryExtendDO=daySummaryService.getDaySummaryByCode(daySummaryDo.getDay_summary_code());
					people.setDaySummaryId(daySummaryExtendDO.getId());
					//修改人员月计划编码
					daySummaryService.updatePeopleByCode(people);
					map.put("code",0);
					map.put("msg","保存成功！");
				}else {
					map.put("code",-1);
					map.put("msg","请先编写日计划！");
				}

			}

		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
			System.out.println(e.getMessage());
		}
		return map;
	}

	//审核日总结
	@ResponseBody
	@RequestMapping("/updateDaySummaryStateById")
	//审核
	@RequiresPermissions("system:daySummary:sh")
	public Map<String,Object> updateDaySummaryStateById(@RequestBody DaySummaryDo daySummaryDo) {
		Map<String,Object> map=new HashMap<>();
		try{
			daySummaryService.updateDaySummaryStateById(daySummaryDo);
			map.put("code",0);
		}catch (Exception e){
			map.put("code",-1);
			map.put("msg","系统异常");
		}
		return map;
	}

	//批量审核日总结
	@ResponseBody
	@RequestMapping("/updateDaySummaryStateListById")
	@RequiresPermissions("system:daySummary:sh")
	public Map<String,Object> updateDaySummaryStateListById(String[] ids,String state) {
		Map<String,Object> map=new HashMap<>();
		BatchAuditUtil batchAuditUtil=new BatchAuditUtil();
		batchAuditUtil.setIds(Arrays.asList(ids));
		batchAuditUtil.setState(Integer.valueOf(state));
		try{
			daySummaryService.updateDaySummaryStateListById(batchAuditUtil);
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
