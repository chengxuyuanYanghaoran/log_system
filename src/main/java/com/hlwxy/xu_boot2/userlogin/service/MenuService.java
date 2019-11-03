package com.hlwxy.xu_boot2.userlogin.service;



import com.hlwxy.xu_boot2.common.utils.Tree;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;

import java.util.List;
import java.util.Map;


public interface MenuService {

	MenuDO get(String pid);

	List<MenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MenuDO menuDO);

	int update(MenuDO menuDO);

	int remove(String pid);

	int batchRemove(String[] pids);


    List<Tree<MenuDO>> listMenuTree(Long userId);

	Tree<MenuDO> getTree();

	Tree<MenuDO> getTree(Long id);
}
