package com.hlwxy.xu_boot2.system.service.impl;


import com.hlwxy.xu_boot2.system.dao.DataDao;
import com.hlwxy.xu_boot2.system.domain.DataDO;
import com.hlwxy.xu_boot2.system.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class DataServiceImpl implements DataService {
	@Resource
	private DataDao dataDao;

	@Override
	public void del() {
		List<DataDO> del=dataDao.findDel();
		dataDao.del(del);
	}
}
