package com.hlwxy.xu_boot2.userlogin.dao;



import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;
import com.hlwxy.xu_boot2.userlogin.dto.RoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-10-05 21:22:10
 */
@Mapper
public interface RoleDao {


	List<RoleDo>  listByName(String name);

	RoleDo get(String id);
	
	List<RoleDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleDo role);
	
	int update(RoleDo role);
	
	int remove(String id);
	
	int batchRemove(String[] ids);


	List<RoleDTO> listall(Map<String, Object> map);
}
