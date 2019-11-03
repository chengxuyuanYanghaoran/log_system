package com.hlwxy.xu_boot2.common.shiro;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.service.PeopleService;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AuthRealm extends AuthorizingRealm {

    /**
     * @Lazy 延迟注入，不然redis注解会因为注入顺序问题失效
     */
    @Autowired
    private PeopleService peopleService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        PeopleDO user = (PeopleDO) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<RoleDo> roleSet = user.getRoles();
        if (roleSet.size()>0) {
            for(RoleDo role : roleSet) {
                roleNameList.add(role.getUsername());
                Set<MenuDO> permissionSet = role.getMenus();
                if (permissionSet.size()>0) {
                    for (MenuDO permission : permissionSet) {
                        permissionList.add(permission.getPerms());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    /**
     * 认证登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        PeopleDO peopleDO = peopleService.findByUsername(username,password);
        return new SimpleAuthenticationInfo(peopleDO, peopleDO.getPassword(), this.getClass().getName());
    }
}