package com.hlwxy.xu_boot2.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hlwxy.xu_boot2.common.utils.MD5Utils;
import com.hlwxy.xu_boot2.common.utils.PageUtils;
import com.hlwxy.xu_boot2.common.utils.Query;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.system.domain.CompanyDO;
import com.hlwxy.xu_boot2.system.domain.DepartmentDO;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.domain.PositionDO;
import com.hlwxy.xu_boot2.system.dto.PeopleDTO;
import com.hlwxy.xu_boot2.system.service.CompanyService;
import com.hlwxy.xu_boot2.system.service.DepartmentService;
import com.hlwxy.xu_boot2.system.service.PeopleService;
import com.hlwxy.xu_boot2.system.service.PositionService;
import com.hlwxy.xu_boot2.userlogin.domain.UserRoleDO;
import com.hlwxy.xu_boot2.userlogin.service.UserRoleService;
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

import javax.servlet.http.HttpSession;


/**
 *
 * @date 2019-09-26 22:35:53
 */
@Controller
@RequestMapping("/system/people")
public class PeopleController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private UserRoleService userRoleService;


	@GetMapping
    @RequiresPermissions("system:people:people")
	public  String PeopleController(){
		return "system/people/people";
	}

	@GetMapping(value = "/add")
    @RequiresPermissions("system:people:add")
	public  String add(Model model){
		List<CompanyDO> companyList = companyService.list(null);
		List<DepartmentDO> departmentList = departmentService.list(null);
		List<PositionDO> positionList = positionService.list(null);
		model.addAttribute("companyList",companyList);
		model.addAttribute("departmentList",departmentList);
		model.addAttribute("positionList",positionList);
		return "system/people/add";
	}



	@ResponseBody
	@GetMapping("/list")
    @RequiresPermissions("system:people:people")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
			Query query = new Query(params);
			List<PeopleDTO> peopleDTOS = peopleService.listPeopleAll(query);
			int total = peopleService.count(query);
		    PageUtils pageUtils = new PageUtils(peopleDTOS, total);
		    return pageUtils;
		}



	@GetMapping("/edit/{id}")
    @RequiresPermissions("system:people:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		List<CompanyDO> companyList = companyService.list(null);
		List<DepartmentDO> departmentList = departmentService.list(null);
		List<PositionDO> positionList = positionService.list(null);
		model.addAttribute("companyList",companyList);
		model.addAttribute("departmentList",departmentList);
		model.addAttribute("positionList",positionList);

		model.addAttribute("people",peopleService.get(id));
		model.addAttribute("company",companyService.get(id));
		model.addAttribute("department",departmentService.get(id));
		model.addAttribute("position",positionService.get(id));
	    return "system/people/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
    @RequiresPermissions("system:people:add")
	public R save(PeopleDO people){
//		UserDo user=new UserDo(people.getPeopleCode(),people.getJobNumber());
//		userService.save(user);
//		userRoleService.save(new UserRoleDO(user.getId(),Long.parseLong(people.getBmName())));

//		people.setPassword(MD5Utils.encrypt(people.getUsername(), people.getPassword()));
        if(peopleService.save(people)>0){
            userRoleService.save(new UserRoleDO(people.getId(), Integer.valueOf(people.getBmId())));
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
    @RequiresPermissions("system:people:edit")
	public R update(PeopleDO people){
		peopleService.update(people);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
    @RequiresPermissions("system:people:remove")
	public R remove( Integer id){
		if(peopleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
    @RequiresPermissions("system:people:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		peopleService.batchRemove(ids);
		return R.ok();
	}



	//修改密码
	@RequestMapping("/adminResetPwd")
	@ResponseBody
	@RequiresPermissions("system:people:adminResetPwd")
	public Map<String, Object> resetPwd(String password, String newpassword, String username){
		Map<String,Object> map=new HashMap<>();
		int i= peopleService.modifyPassword( password, newpassword, username);
		if(i==1){
			map.put("code",0);
			map.put("msg","修改成功！");

		}else {
			map.put("code",1);
			map.put("msg","修改失败");
		}
		return map;
	}



	@GetMapping("/resetPwd/{id}")
	@RequiresPermissions("system:people:adminResetPwd")
	String resetPwd(@PathVariable("id") Integer id, Model model) {
		PeopleDO peopleDO = new PeopleDO();
		peopleDO.setId(id);
		model.addAttribute("people", peopleDO);
		return "system/people/resetPwd";
	}

}