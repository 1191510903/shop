package com.shop.cn.common.dao;

import com.shop.cn.common.pojo.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CheckoutDao {

    void addCheckout(Order order);

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
     *根据订单号查询所有订单表中的订单信息（模糊查询）
     * @param uuid
     * @return
     */
    List<Order> findCheckoutWithUUIDForLike(String uuid);

    /**
     * 逻辑删除订单号（管理员依旧可以查看订单）
     * @param uuid
     */
    void logicDeleteCheckoutWithUUID(String uuid);

    /**
     * 逻辑删除订单号对应的商品（管理员依旧可以查看订单）
     * @param uuid
     */
    void logicDeleteFromOrderProductWithUUID(String uuid);
}
