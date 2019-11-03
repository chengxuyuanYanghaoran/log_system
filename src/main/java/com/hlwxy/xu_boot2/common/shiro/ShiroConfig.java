package com.hlwxy.xu_boot2.common.shiro;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //登录接口
        bean.setLoginUrl("/login");
        //登录成功跳转页面
        bean.setSuccessUrl("/toPageMain");
        //没有权限跳转的页面
        bean.setUnauthorizedUrl("/error/403");

        /**
         *
         *admins/**=anon               # 表示该 uri 可以匿名访问
         *admins/**=auth               # 表示该 uri 需要认证才能访问
         *admins/**=authcBasic         # 表示该 uri 需要 httpBasic 认证
         *admins/**=perms[user:add:*]  # 表示该 uri 需要认证用户拥有 user:add:* 权限才能访问
         *admins/**=port[8081]         # 表示该 uri 需要使用 8081 端口
         *admins/**=rest[user]         # 相当于 /admins/**=perms[user:method]，其中，method 表示  get、post、delete 等
         *admins/**=roles[admin]       # 表示该 uri 需要认证用户拥有 admin 角色才能访问
         *admins/**=ssl                # 表示该 uri 需要使用 https 协议
         *admins/**=user               # 表示该 uri 需要认证或通过记住我认证才能访问
         *logout=logout                # 表示注销,可以当作固定配置
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/user/index", "authc");
        // 配置不会被拦截的链接 顺序判断
        map.put("/static/**", "anon");
        map.put("/login", "anon");
        map.put("/main", "anon");
        map.put("/reg", "anon");
        map.put("/public/**", "anon");
        map.put("/user/register", "anon");
//        map.put("/system/role", "roles[vip]");
        map.put("/user/edit", "perms[edit]");
        map.put("/druid/**", "anon");
        map.put("/**", "user");
        map.put("/logout", "logout");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }


    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }


}