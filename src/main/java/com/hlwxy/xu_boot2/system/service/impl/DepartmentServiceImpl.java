package com.hlwxy.xu_boot2.system.service.impl;
import com.hlwxy.xu_boot2.system.dao.DepartmentDao;
import com.hlwxy.xu_boot2.system.domain.DepartmentDO;
import com.hlwxy.xu_boot2.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public DepartmentDO get(Integer id){
		return departmentDao.get(id);
	}
	
	@Override
	public List<DepartmentDO> list(Map<String, Object> map){
		return departmentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return departmentDao.count(map);
	}
	
	@Override
	public int save(DepartmentDO department){
		return departmentDao.save(department);
	}
	
	@Override
	public int update(DepartmentDO department){
		return departmentDao.update(department);
	}
	
	@Override
	public int remove(Integer id){
		return departmentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return departmentDao.batchRemove(ids);
	}
	
}
