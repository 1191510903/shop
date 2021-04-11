package com.shop.cn.common.service;

import com.shop.cn.common.pojo.Admin;
import com.shop.cn.common.pojo.User;

public interface UserAdminService {

    User findUserInSystem(Long id);

    Admin adminLogin(Admin admin);
}
