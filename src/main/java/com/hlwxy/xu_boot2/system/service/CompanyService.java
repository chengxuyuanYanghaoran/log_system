package com.hlwxy.xu_boot2.system.service;
import com.hlwxy.xu_boot2.system.domain.CompanyDO;
import java.util.List;
import java.util.Map;

public interface CompanyService {
	
	CompanyDO get(Integer id);
	
	List<CompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
