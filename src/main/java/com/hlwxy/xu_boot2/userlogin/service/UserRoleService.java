package com.hlwxy.xu_boot2.userlogin.service;


import com.hlwxy.xu_boot2.userlogin.domain.UserRoleDO;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2019-10-14-16:23
 */
public interface UserRoleService {
    UserRoleDO get(Integer id);

    List<UserRoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserRoleDO userRole);

    int update(UserRoleDO userRole);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    int removeByUserId(Integer userId);


}
