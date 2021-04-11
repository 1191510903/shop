package com.shop.cn.common.dao;

import com.shop.cn.common.pojo.Admin;
import com.shop.cn.common.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserAdminDao {
    User findUserInSystem(Long id);

    Admin adminLogin(Admin admin);
}
