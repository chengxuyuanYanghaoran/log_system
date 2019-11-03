package com.hlwxy.xu_boot2.system.controller;
import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
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

 * @date 2019-09-26 22:35:53
 */

@Controller
@RequestMapping("/system/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;


	@GetMapping
	@RequiresPermissions("system:department:department")
	public  String DepartmentController(){
		return "system/department/department";
	}

	@GetMapping(value = "/add")
	@RequiresPermissions("system:department:add")
	public  String add(){
		return "system/department/add";
	}


	@GetMapping("/list")
	@ResponseBody
	@RequiresPermissions("system:department:department")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        PageUtils pageUtils;
        if (params!=null &&params.size()>0){
            Query query = new Query(params);
            List<DepartmentDO> departmentList = departmentService.list(query);
            int total = departmentService.count(query);
            pageUtils = new PageUtils(departmentList, total);
        }else {
            List<DepartmentDO> departmentList = departmentService.list(null);
            int total = departmentService.count(null);
            pageUtils = new PageUtils(departmentList, total);
        }


        return pageUtils;
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:department:edit")
	String edit(@PathVariable("id") Integer id, Model model){
		model.addAttribute("department", departmentService.get(id));
	    return "system/department/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:department:add")
	public R save(DepartmentDO department){
		if(departmentService.save(department)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:department:edit")
	public R update( DepartmentDO department){
		departmentService.update(department);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:department:remove")
	public R remove( Integer id){
		if(departmentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:department:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		departmentService.batchRemove(ids);
		return R.ok();
	}

}
