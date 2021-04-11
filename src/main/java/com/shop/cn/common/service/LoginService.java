package com.shop.cn.common.service;

import com.shop.cn.common.pojo.Subscription;
import com.shop.cn.common.pojo.User;

import java.util.List;


public interface LoginService {

    /**
     * 用户注册-插入用户信息
     * @param user
     */

    void registerUser(User user);
    /**
     * 用户登录-查找信息
     */

    User findByName(String userName);

    /**
     * 查找新闻订阅
     * @param id
     * @return
     */
    List<Subscription> findUserIdFromSubscription(Long id);

    /**
     * 保存新闻订阅
     * @param subscription
     */
    void saveSubscription(Subscription subscription);
}
