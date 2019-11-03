package com.hlwxy.xu_boot2.system.service;
import com.hlwxy.xu_boot2.system.domain.DepartmentDO;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
	
	DepartmentDO get(Integer id);
	
	List<DepartmentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DepartmentDO department);
	
	int update(DepartmentDO department);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
