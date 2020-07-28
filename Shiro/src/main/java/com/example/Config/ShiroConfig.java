package com.example.Config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//Shiro配置类
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
        /**
         * anno:无需认证就可以访问
         * authc：必须认证了才可以访问
         * user:必须拥有记住我功能后才能访问
         * perms:拥有对某个资源权限才能访问
         * role:拥有某个角色权限才能访问
         */
        //拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //没有授权回跳到未授权页面
        //filterChainDefinitionMap.put("/user/*","authc");
        filterChainDefinitionMap.put("/user/add","perms[add]");
        filterChainDefinitionMap.put("/user/update","perms[update]");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //拦截后的请求（设置登陆请求）
        bean.setLoginUrl("/tologin");


        //未授权页面
        bean.setUnauthorizedUrl("/user/Unauthorized");




        return bean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建Realm对象，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


    //thymeleaf整合shiro
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
