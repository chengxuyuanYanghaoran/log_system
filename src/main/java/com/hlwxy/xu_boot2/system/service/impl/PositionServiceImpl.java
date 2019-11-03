package com.hlwxy.xu_boot2.system.service.impl;
import com.hlwxy.xu_boot2.system.dao.PositionDao;
import com.hlwxy.xu_boot2.system.domain.PositionDO;
import com.hlwxy.xu_boot2.system.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public PositionDO get(Integer id){
		return positionDao.get(id);
	}
	
	@Override
	public List<PositionDO> list(Map<String, Object> map){
		return positionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return positionDao.count(map);
	}
	
	@Override
	public int save(PositionDO position){
		return positionDao.save(position);
	}
	
	@Override
	public int update(PositionDO position){
		return positionDao.update(position);
	}
	
	@Override
	public int remove(Integer id){
		return positionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return positionDao.batchRemove(ids);
	}
	
}
