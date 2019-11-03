package com.hlwxy.xu_boot2.system.dao;
import com.hlwxy.xu_boot2.system.domain.PositionDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface PositionDao {

	PositionDO get(Integer id);
	
	List<PositionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PositionDO position);
	
	int update(PositionDO position);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
