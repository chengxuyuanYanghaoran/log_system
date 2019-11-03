package com.hlwxy.xu_boot2.system.dao;
import com.hlwxy.xu_boot2.system.domain.DepartmentDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentDao {

	DepartmentDO get(Integer id);
	
	List<DepartmentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DepartmentDO department);
	
	int update(DepartmentDO department);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
