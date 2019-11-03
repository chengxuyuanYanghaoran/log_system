package com.hlwxy.xu_boot2.userlogin.service.serviceimpl;


import com.hlwxy.xu_boot2.userlogin.dao.UserRoleDao;
import com.hlwxy.xu_boot2.userlogin.domain.UserRoleDO;
import com.hlwxy.xu_boot2.userlogin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2019-10-14-16:23
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRoleDO get(Integer id) {
        return userRoleDao.get(id);
    }

    @Override
    public List<UserRoleDO> list(Map<String, Object> map) {
        return userRoleDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userRoleDao.count(map);
    }

    @Override
    public int save(UserRoleDO userRole) {
        return userRoleDao.save(userRole);
    }

    @Override
    public int update(UserRoleDO userRole) {
        return userRoleDao.update(userRole);
    }

    @Override
    public int remove(Integer id) {
        return userRoleDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return userRoleDao.batchRemove(ids);
    }

    @Override
    public int removeByUserId(Integer userId) {
        return userRoleDao.removeByUserId(userId);
    }


}
