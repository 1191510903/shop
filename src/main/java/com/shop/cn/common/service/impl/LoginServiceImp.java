package com.shop.cn.common.service.impl;

import com.shop.cn.common.dao.LoginDao;
import com.shop.cn.common.pojo.Subscription;
import com.shop.cn.common.pojo.User;
import com.shop.cn.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

@Transactional
public class LoginServiceImp implements LoginService {


    @Autowired
    LoginDao loginDao;

    @Override
    public void registerUser(User user) {
        loginDao.save(user);
    }

    @Override
    public void saveSubscription(Subscription subscription) {
        loginDao.saveSubscription(subscription);
    }

    @Override
    public List<Subscription>  findUserIdFromSubscription(Long id) {
        return loginDao.findUserIdFromSubscription(id);
    }

    @Override
    public User findByName(String userName) {
        User u = loginDao.findByName(userName);
        return u;
    }
}
