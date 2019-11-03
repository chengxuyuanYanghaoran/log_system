package com.hlwxy.xu_boot2.common.utils;

import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static PeopleDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (PeopleDO)object;
    }
    public static Long getUserId() {
        return Long.valueOf(getUser().getId());
    }
    public static void logout() {
        getSubjct().logout();
    }

}
