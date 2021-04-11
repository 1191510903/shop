package com.shop.cn.common.controller;

import com.shop.cn.common.pojo.Admin;
import com.shop.cn.common.pojo.User;
import com.shop.cn.common.service.UserAdminService;
import com.shop.cn.common.vo.GenericResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Api(value = "用户信息页面", tags = {"对用户信息进行操作"})
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("/userMessage")
    @ApiOperation(value = "用户信息页面")
    public String userMessage(){
        return "/admin/userMessage";
    }


    @GetMapping("admin/adminLogin")
    @ApiOperation(value = "管理员登录页面")
    public String adminLogin(){
        return "/admin/login";
    }

    @GetMapping("admin/index")
    @ApiOperation(value = "管理员登录页面")
    public String adminIndex(){
        return "/admin/index";
    }


    @ApiOperation("管理员实现登录验证")
    @PostMapping("admin/toLogin")
    public String adminToLogin(Admin admin, Model model,HttpSession session){
        Integer integer = Integer.valueOf(admin.getSuperAdminStr());
        admin.setSuperAdmin(integer);
        Admin a = null;
        try {
            a = userAdminService.adminLogin(admin);
            if(a!=null){
                if(a.getSuperAdmin()==1){
                    model.addAttribute("message","超级管理员");
                }else{
                    model.addAttribute("message","普通管理员");
                }
                session.setAttribute("adminName",a.getAdminName());
                session.setAttribute("adminId",a.getAdminId());
                session.setAttribute("superAdmin",a.getSuperAdmin());
                return "/admin/index";
            }
            return "/admin/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "/admin/error-404";
        }
    }


    @ApiOperation("管理员实现登录验证")
    @PostMapping("admin/register")
    public String adminRegister(Admin admin, Model model,HttpSession session){
        Integer integer = Integer.valueOf(admin.getSuperAdminStr());
        admin.setSuperAdmin(integer);
        return "/admin/index";
    }

    @ApiOperation(value = "查询当前登录的用户")
    @GetMapping("findUserInSystem")
    @ResponseBody
    public GenericResult<User> findUserInSystem(HttpSession session){
        GenericResult<User> result = new GenericResult<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            User user = userAdminService.findUserInSystem(id);
            result.setData(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @GetMapping("/layout")
    @ApiOperation(value = "页面UI")
    public String layout(){
        return "top-menu";
    }

    @GetMapping("/two-menu-1")
    @ApiOperation(value = "页面UI")
    public String twoMenu(){
        return "/two-menu-1";
    }

    @GetMapping("/two-menu-2")
    @ApiOperation(value = "页面UI")
    public String twoMenu2(){
        return "/two-menu-2";
    }

}
