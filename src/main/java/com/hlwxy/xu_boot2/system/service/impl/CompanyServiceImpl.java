package com.hlwxy.xu_boot2.system.service.impl;
import com.hlwxy.xu_boot2.system.dao.CompanyDao;
import com.hlwxy.xu_boot2.system.domain.CompanyDO;
import com.hlwxy.xu_boot2.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public CompanyDO get(Integer id){
		return companyDao.get(id);
	}
	
	@Override
	public List<CompanyDO> list(Map<String, Object> map){
		return companyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyDao.count(map);
	}
	
	@Override
	public int save(CompanyDO company){
		return companyDao.save(company);
	}
	
	@Override
	public int update(CompanyDO company){
		return companyDao.update(company);
	}
	
	@Override
	public int remove(Integer id){
		return companyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return companyDao.batchRemove(ids);
	}
	
}
