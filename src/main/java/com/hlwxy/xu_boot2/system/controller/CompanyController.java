package com.hlwxy.xu_boot2.system.controller;


import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.system.domain.CompanyDO;
import com.hlwxy.xu_boot2.system.service.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 *
 * @date 2019-09-26 22:35:53
 */

@Controller
@RequestMapping("/system/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@GetMapping
	@RequiresPermissions("system:company:company")
	public  String CompanyController(){
		return "system/company/company";
	}

	@GetMapping(value = "/add")
	@RequiresPermissions("system:company:add")
	public  String add(){
		return "system/company/add";
	}



	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:company:company")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<CompanyDO> companyList = companyService.list(query);
		int total = companyService.count(query);
	    PageUtils	pageUtils = new PageUtils(companyList, total);
		return pageUtils;
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:company:edit")
	String edit(@PathVariable("id") Integer id, Model model){
		model.addAttribute("company",companyService.get(id));
	    return "system/company/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:company:add")
	public R save(CompanyDO company){
		if(companyService.save(company)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:company:edit")
	public R update( CompanyDO company){
		companyService.update(company);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping( "/remove")
	@RequiresPermissions("system:company:remove")
	public R remove( Integer id){
		if(companyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping( "/batchRemove")
	@RequiresPermissions("system:company:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		companyService.batchRemove(ids);
		return R.ok();
	}

}
