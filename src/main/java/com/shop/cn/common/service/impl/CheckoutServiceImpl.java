package com.shop.cn.common.service.impl;

import com.shop.cn.common.dao.CheckoutDao;
import com.shop.cn.common.pojo.Order;
import com.shop.cn.common.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CheckoutDao checkoutDao;

    @Override
    public void logicDeleteCheckoutWithUUID(String uuid) {
        checkoutDao.logicDeleteCheckoutWithUUID(uuid);
    }

    @Override
    public void logicDeleteFromOrderProductWithUUID(String uuid) {
        checkoutDao.logicDeleteFromOrderProductWithUUID(uuid);
    }


    @Override
    public void addCheckout(Order order) {
        checkoutDao.addCheckout(order);
    }

    @Override
    public Order findCheckoutWithUUID(String uuid) {
        return checkoutDao.findCheckoutWithUUID(uuid);
    }

    @Override
    public List<Order> findCheckoutWithUUIDForLike(String uuid) {
        return checkoutDao.findCheckoutWithUUIDForLike(uuid);
    }

    @Override
    public List<Order> findAllFromOrderProductWithUUID(String orderUUID) {
        return checkoutDao.findAllFromOrderProductWithUUID(orderUUID);
    }

    @Override
    public List<Order> findAllFromOrder(Long id) {
        return checkoutDao.findAllFromOrder(id);
    }

    @Override
    public void addCheckoutUUID(Order order) {
        checkoutDao.addCheckoutUUID(order);
    }
}

