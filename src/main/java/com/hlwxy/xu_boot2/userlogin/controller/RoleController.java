package com.hlwxy.xu_boot2.userlogin.controller;


import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;
import com.hlwxy.xu_boot2.userlogin.domain.UserRoleDO;
import com.hlwxy.xu_boot2.userlogin.dto.RoleDTO;
import com.hlwxy.xu_boot2.userlogin.service.RoleService;
import com.hlwxy.xu_boot2.system.domain.DepartmentDO;
import com.hlwxy.xu_boot2.system.service.DepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-10-05 21:22:10
 */
 
@Controller
@RequestMapping("/system/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;


	@GetMapping
	@RequiresPermissions("system:role:role")
	public  String RoleController(){
		return "system/role/role";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:role:role")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoleDTO> listAll = roleService.listall(query);
		int total = roleService.count(query);
		return new PageUtils(listAll, total);
	}


	
	@GetMapping("/add")
	@RequiresPermissions("system:role:add")
	String add(Model model){
		List<DepartmentDO> departmentList = departmentService.list(null);
		model.addAttribute("departmentList", departmentList);
	    return "system/role/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:role:edit")
	String edit(@PathVariable("id") String id, Model model){
		List<DepartmentDO> departmentList = departmentService.list(null);
		model.addAttribute("departmentList",departmentList);

		model.addAttribute("department",departmentService.get(Integer.valueOf(id)));
		model.addAttribute("role",roleService.get(id));

	    return "system/role/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:role:add")
	public R save(RoleDo role){
		if(roleService.save(role)>0){
			return  R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:role:edit")
	public R update( RoleDo role){
		roleService.update(role);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:role:remove")
	public R remove( String id){
		if(roleService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:role:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		roleService.batchRemove(ids);
		return R.ok();
	}
	
}
