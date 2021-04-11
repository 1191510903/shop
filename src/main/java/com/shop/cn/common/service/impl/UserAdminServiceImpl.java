package com.shop.cn.common.service.impl;

import com.shop.cn.common.dao.UserAdminDao;
import com.shop.cn.common.pojo.Admin;
import com.shop.cn.common.pojo.User;
import com.shop.cn.common.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminDao userAdminDao;

    @Override
    public User findUserInSystem(Long id) {
        return userAdminDao.findUserInSystem(id);
    }

    @Override
    public Admin adminLogin(Admin admin) {
        return userAdminDao.adminLogin(admin);
    }
}
