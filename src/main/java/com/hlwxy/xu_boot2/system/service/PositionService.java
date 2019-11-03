package com.hlwxy.xu_boot2.system.service;
import com.hlwxy.xu_boot2.system.domain.PositionDO;
import java.util.List;
import java.util.Map;

public interface PositionService {
	
	PositionDO get(Integer id);
	
	List<PositionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PositionDO position);
	
	int update(PositionDO position);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
