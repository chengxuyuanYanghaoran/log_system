package com.hlwxy.xu_boot2.system.controller;
import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.system.domain.PositionDO;
import com.hlwxy.xu_boot2.system.service.PositionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/position")
public class PositionController {
	@Autowired
	private PositionService positionService;


	@GetMapping
	@RequiresPermissions("system:position:position")
	public  String PositionController(){
		return "system/position/position";
	}


	@RequiresPermissions("system:position:add")
	@GetMapping(value = "/add")
	public  String add(){
		return "system/position/add";
	}

	@GetMapping("/list")
	@ResponseBody
	@RequiresPermissions("system:position:position")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		 PageUtils pageUtils;
		if(params !=null && params.size()>0){
			Query query = new Query(params);
			List<PositionDO> positionList = positionService.list(query);
			int total = positionService.count(query);
			pageUtils = new PageUtils(positionList, total);
		}else{
			List<PositionDO> positionList = positionService.list(null);
			int total = positionService.count(null);
			pageUtils = new PageUtils(positionList, total);
		}
		return pageUtils;
	}

	@RequiresPermissions("system:position:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id, Model model){
		model.addAttribute("position",positionService.get(id));
	    return "system/position/edit";
	}

	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("system:position:add")
	public R save(PositionDO position){
		if(positionService.save(position)>0){
			return R.ok();
		}
		return R.error();
	}

	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("system:position:edit")
	public R update( PositionDO position){
		positionService.update(position);
		return R.ok();
	}
	

	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:position:remove")
	public R remove( Integer id){
		if(positionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:position:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		positionService.batchRemove(ids);
		return R.ok();
	}
	
}
