package com.example.Config;

import com.example.Service.ServiceImpl.UserServerImpl;
import com.example.pojo.userInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了==>doGetAuthorizationInfo授权");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //拿到当前登陆的对象
        Subject subject=SecurityUtils.getSubject();
        userInfo userInfo= (userInfo) subject.getPrincipal();//拿到user对象
        System.out.printf(userInfo+"");
        //设置当前用户权限
        info.addStringPermission(userInfo.getPower());

        return info;
    }


    @Autowired
    UserServerImpl userserver;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了==>doGetAuthorizationInfo认证");
//        //用户名，密码，数据库中取
//        String user="admin";
//        String pwd="123456";
        UsernamePasswordToken usertoken=(UsernamePasswordToken) authenticationToken;
        userInfo user=userserver.userPwd(((UsernamePasswordToken) authenticationToken).getUsername());
        if(null==user){//没有这个人的话
            return null;//抛出异常UsernamePasswordToken
        }

        //存放session
        Subject currentSubject= SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginuser",user);
        //密码认证shiro做，正常MD5加密，MD5盐值加密
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
