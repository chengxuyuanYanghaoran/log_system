package com.hlwxy.xu_boot2.userlogin.dao;



import com.hlwxy.xu_boot2.userlogin.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleDao {

	UserRoleDO get(Integer id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

	int removeByUserId(Integer userId);

}
