package com.shop.cn.common.service;

import com.shop.cn.common.pojo.Order;

import java.util.List;

public interface CheckoutService {

    /**
     * 将商品添加到订单表
     * @param order
     */
    void addCheckout(Order order);

    /**
     * 将订单uuid添加到order_address_express表
     * @param order
     */
    void addCheckoutUUID(Order order);

    /**
     * 从订单表中查询当前用户所有订单
     * @param id
     * @return
     */
    List<Order> findAllFromOrder(Long id);

    /**
     * 根据订单号查询所有订单商品
     * @param orderUUID
     * @return
     */
    List<Order> findAllFromOrderProductWithUUID(String orderUUID);

    /**
     * 根据订单号查询所有订单表中的订单信息
     * @param uuid
     * @return
     */
    Order findCheckoutWithUUID(String uuid);

    /**
     * 根据订单号查询所有订单表中的订单信息（模糊查询）
     * @param uuid
     * @return
     */
    List<Order> findCheckoutWithUUIDForLike(String uuid);

    /**
     *逻辑删除订单号从订单表中
     * @param uuid
     */
    void logicDeleteCheckoutWithUUID(String uuid);

    /**
     *逻辑删除订单号从订单表中
     * @param uuid
     */
    void logicDeleteFromOrderProductWithUUID(String uuid);

}
