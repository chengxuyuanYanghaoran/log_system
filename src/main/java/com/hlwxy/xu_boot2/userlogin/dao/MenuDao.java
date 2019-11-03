package com.hlwxy.xu_boot2.userlogin.dao;



import java.util.List;
import java.util.Map;

import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MenuDao {

	MenuDO get(String pid);

	List<MenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MenuDO menuDO);

	int update(MenuDO menuDO);

	int remove(String pid);

	int batchRemove(String[] pids);


    List<MenuDO> listMenuByUserId(Long userId);
}
