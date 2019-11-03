package com.hlwxy.xu_boot2.userlogin.service.serviceimpl;


import com.hlwxy.xu_boot2.userlogin.dao.RoleDao;
import com.hlwxy.xu_boot2.userlogin.dao.RoleMenuDao;
import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;
import com.hlwxy.xu_boot2.userlogin.domain.RoleMenuDO;
import com.hlwxy.xu_boot2.userlogin.dto.RoleDTO;
import com.hlwxy.xu_boot2.userlogin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleMenuDao roleMenuDao;


	@Override
	public RoleDo get(String id){
		return roleDao.get(id);
	}

	@Override
	public List<RoleDo> list(Map<String, Object> map){
		return roleDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return roleDao.count(map);
	}

	@Override
	public int save(RoleDo role) {
         //保存role
		int count = roleDao.save(role);
//		获取菜单id
		List<Long> menuIds = role.getMenuIds();
//		获取角色id
		String roleId = role.getId();
//		遍历role_menu
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setPid(menuId.toString());
			rms.add(rmDo);
		}
//		如果有就删除
		roleMenuDao.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuDao.batchSave(rms);
		}
		return count;
	}


	@Override
	public int update(RoleDo role){
//		同上
		int r = roleDao.update(role);
		List<Long> menuIds = role.getMenuIds();
		//角色id
		String roleId = role.getId();
		roleMenuDao.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setPid(menuId.toString());
			rms.add(rmDo);
		}
		if (rms.size() > 0) {
			roleMenuDao.batchSave(rms);
		}
		return r;
	}

	@Override
	public int remove(String id){
		return roleDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return roleDao.batchRemove(ids);
	}

	@Override
	public List<RoleDTO> listall(Map<String, Object> map) {
		return roleDao.listall(map);
	}


}
