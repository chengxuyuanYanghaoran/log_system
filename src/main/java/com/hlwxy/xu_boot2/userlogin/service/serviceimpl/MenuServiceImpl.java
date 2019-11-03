package com.hlwxy.xu_boot2.userlogin.service.serviceimpl;

import com.hlwxy.xu_boot2.common.utils.BuildTree;
import com.hlwxy.xu_boot2.common.utils.Tree;
import com.hlwxy.xu_boot2.userlogin.dao.MenuDao;
import com.hlwxy.xu_boot2.userlogin.dao.RoleMenuDao;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import com.hlwxy.xu_boot2.userlogin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public MenuDO get(String pid) {
		return menuDao.get(pid);
	}

	@Override
	public List<MenuDO> list(Map<String, Object> map) {
		return menuDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return menuDao.count(map);
	}

	@Override
	public int save(MenuDO menuDO) {
		return menuDao.save(menuDO);
	}

	@Override
	public int update(MenuDO menuDO) {
		return menuDao.update(menuDO);
	}

	@Override
	public int remove(String pid) {
		return menuDao.remove(pid);
	}

	@Override
	public int batchRemove(String[] pids) {
		return menuDao.batchRemove(pids);
	}

	@Override
	public List<Tree<MenuDO>> listMenuTree(Long userId) {
		List<Tree<MenuDO>> trees = new ArrayList<>();
		List<MenuDO> menuDOs = menuDao.listMenuByUserId(userId);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		return BuildTree.buildList(trees, "0");
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<MenuDO> getTree(Long id) {
		// 根据roleId查询权限
//		查询所有的菜单
		List<MenuDO> menus = menuDao.list(new HashMap<>(16));
//		查询当前角色拥有的菜单/权限
		List<Long> menuIds = roleMenuDao.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (MenuDO menu : menus) {
//			判断拥有的菜单权限是否包括当前的菜单的父id
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<MenuDO>> trees = new ArrayList<>();
		//		查询所有的菜单
		List<MenuDO> menuDOs = menuDao.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		return BuildTree.build(trees);
	}
}
