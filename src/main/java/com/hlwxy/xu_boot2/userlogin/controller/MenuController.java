package com.hlwxy.xu_boot2.userlogin.controller;

import java.util.List;
import java.util.Map;

import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.common.utils.Tree;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import com.hlwxy.xu_boot2.userlogin.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



 
@Controller
@RequestMapping("/system/menu")
public class MenuController {
	@Autowired
	private MenuService permissionService;

	@Autowired
	private MenuService menuService;
	
	@GetMapping()
	@RequiresPermissions("system:menu:menu")
	String Permission(){
	    return "system/menu/menu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:menu:menu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MenuDO> permissionList = permissionService.list(query);
		int total = permissionService.count(query);
		PageUtils pageUtils = new PageUtils(permissionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:menu:add")
	String add(){
	    return "system/menu/add";
	}

	@GetMapping("/edit/{pid}")
	@RequiresPermissions("system:menu:edit")
	String edit(@PathVariable("pid") String pid,Model model){
		MenuDO permission = permissionService.get(pid);
		model.addAttribute("permission", permission);
	    return "system/menu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:menu:add")
	public R save(MenuDO permission){
		if(permissionService.save(permission)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:menu:edit")
	public R update( MenuDO permission){
		permissionService.update(permission);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:menu:remove")
	public R remove( String pid){
		if(permissionService.remove(pid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:menu:batchRemove")
	public R remove(@RequestParam("ids[]") String[] pids){
		permissionService.batchRemove(pids);
		return R.ok();
	}
	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		Tree<MenuDO>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		return menuService.getTree(roleId);
	}
	
}
