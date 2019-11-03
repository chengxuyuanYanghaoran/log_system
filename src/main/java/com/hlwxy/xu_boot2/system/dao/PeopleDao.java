package com.hlwxy.xu_boot2.system.dao;

import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.dto.PeopleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeopleDao {

	PeopleDO get(Integer id);

	List<PeopleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(PeopleDO people);

	int update(PeopleDO people);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

	int removeByRoleId(Long roleId);

    List<PeopleDTO> listPeopleAll(Map<String, Object> map);

	PeopleDO finduserid(Integer userId);

	PeopleDO getpeoname(String peoName);

	PeopleDO findByUsername(@Param("username") String username, @Param("password") String password);

}
