package com.shop.cn.common.dao;

import com.shop.cn.common.pojo.Subscription;
import com.shop.cn.common.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LoginDao {

    /**
     * 通过id查找用户
     * @return
     */
    User findById();


    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 通过账号密码查找用户
     * @return
     */
    User findByName(String userName);

    /**
     * 保存新闻订阅
     * @param subscription
     */
    void saveSubscription(Subscription subscription);

    /**
     * 查找新闻订阅
     * @param id
     * @return
     */
    List<Subscription> findUserIdFromSubscription(Long id);
}
