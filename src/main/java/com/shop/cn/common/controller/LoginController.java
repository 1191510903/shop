package com.shop.cn.common.controller;

import com.shop.cn.common.pojo.Subscription;
import com.shop.cn.common.pojo.User;
import com.shop.cn.common.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@Api(value = "登录模块", tags = {"用户的登录注册页面"})
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/test")
    @ApiOperation(value = "主页", notes = "用户显示主页")
    public String test() {
        return "test";
    }

    @GetMapping("/home")
    @ApiOperation(value = "主页", notes = "用户显示主页")
    public String lookHome() {
        return "index";
    }

    @GetMapping("/logout")
    @ApiOperation(value = "登出", notes = "用户/管理员登出页面")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("logout","安全退出！");
        return "welcome";
    }

    //接口描述
    @ApiOperation(value = "用户注册", notes = "用于用户的注册")
    @GetMapping("/registerUser")
    @ResponseBody
    public String userRegister(HttpServletRequest request,Model model) {
        System.out.println("==================用户注册=================");
        String userName = request.getParameter("userName");
        String nickname = request.getParameter("nickName");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        System.out.println(userName);
        System.out.println(nickname);
        System.out.println(password);
        if(userName!=null && password !=null){
            User u2 = loginService.findByName(userName);
            if (u2==null){
                User user = new User();
                user.setUserName(userName);
                user.setNickName(nickname);
                user.setPassword(password);
                user.setMail(mail);
                System.out.println("user:"+user);
                loginService.registerUser(user);
                return "注册成功,请尝试登录！";
            }else {
                return "用户名重复，注册失败，请重新注册！";
            }
        }else {
            return "用户名或密码为空！";
        }
   }

    @ApiOperation(value = "用户登录", notes = "用于用户的登录")
    @GetMapping("login")
    public String userLogin() {
        return "welcome";
    }

    @ApiOperation(value = "认证登录",notes = "用于用户的登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="用户名",required=true,paramType="form"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="form")
    })
    @GetMapping("/toLogin")
    public String userToLogin(String userName, String password, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token); //执行登录方法，如果没有异常就说明ok了
            return "index";
        } catch (UnknownAccountException e) {
            return "welcome";
        }catch (IncorrectCredentialsException e){
            return "welcome";
        }

    }

    @ApiOperation(value = "管理员登录", notes = "用于管理员的登录")
    @GetMapping("/loginAdmin")
    @ApiImplicitParam(value = "", paramType = "")
    public String loginAdmin() {
        return "product";
    }

    @ApiOperation(value = "管理员注册", notes = "用户管理员的注册")
    public void registerAdmin() {

    }


    @ApiOperation(value = "新闻订阅",notes = "用户订阅商城新闻")
    @GetMapping("/subscription")
    @ResponseBody
    public String subscription(String email,Model model, HttpSession session) {
        System.out.println(email);
        if (email == null) {
            return "您输入的email为空，如果不为空，请联系管理员，系统错误！";
        }
        Long id = (Long) session.getAttribute("userId");
        System.out.println("id:" + id);
        Subscription subscription = new Subscription();
        subscription.setUserId(id);
        subscription.setEmail(email);
        List<Subscription> data = loginService.findUserIdFromSubscription(id);
        if (data != null || !data.isEmpty()){
            for (Subscription s : data) {
                if (s.getEmail().equals(email)) {
                    return "您已经订阅了！";
                }
            }
        }
        loginService.saveSubscription(subscription);
        return "订阅成功";
    }


}
