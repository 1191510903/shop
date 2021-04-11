package com.shop.cn.shiroConfig;

import com.shop.cn.common.pojo.User;
import com.shop.cn.common.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");
        //连接真实的数据库
        UsernamePasswordToken userToken =(UsernamePasswordToken) token;
        User user = loginService.findByName(userToken.getUsername());
        System.out.println("user:"+user);
        if(user==null){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("userName",user.getUserName());
        session.setAttribute("userId",user.getUserId());
        //密码认证，shiro做
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
