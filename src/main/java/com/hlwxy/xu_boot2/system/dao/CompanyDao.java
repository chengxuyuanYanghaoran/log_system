package com.hlwxy.xu_boot2.system.dao;
import com.hlwxy.xu_boot2.system.domain.CompanyDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyDao {

	CompanyDO get(Integer id);
	
	List<CompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
