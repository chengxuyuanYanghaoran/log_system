package com.hlwxy.xu_boot2.system.service;


import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.domain.UserVO;
import com.hlwxy.xu_boot2.system.dto.PeopleDTO;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface PeopleService {

	PeopleDO get(Integer id);

	List<PeopleDO> list(Map<String, Object> map);


	List<PeopleDTO> listPeopleAll(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(PeopleDO people);

	int update(PeopleDO people);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

	/**
	 //	 * 根据用户名查询用户
	 //	 * @param username
	 //	 * @return
	 //	 */
	PeopleDO findByUsername(@Param("username") String username, @Param("password") String password);

	int modifyPassword(String password, String newpassword, String username);

}
