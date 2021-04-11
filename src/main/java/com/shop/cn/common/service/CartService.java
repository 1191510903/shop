package com.shop.cn.common.service;

import com.shop.cn.common.pojo.Address;
import com.shop.cn.common.pojo.Cart;
import com.shop.cn.common.pojo.Product;

import java.util.List;

public interface CartService {

    /**
     * 商品加入购物车
     * @param cart
     * @return
     */
    Product addProductToCart(Cart cart);

    /**
     * 从购物车中查询商品
     * @param id
     * @return
     */
    List<Cart> findProductFromCart(Long id);

    /**
     * 从购物车中查询商品数量
     * @param id
     * @return
     */
    int findProductFromCartCount(Long id);

    /**
     * 删除购物车中所有商品
     * @param id
     * @return
     */
    void deleteProductFromCart(Long id);

    /**
     * 删除购物车中选定的商品
     * @param cart
     * @return
     */
    void deleteProductFromCartWithProductId(Cart cart);

    /**
     * 添加收货地址
     * @param address
     */
    void addAddressToTable(Address address);

    /**
     * 查询所有收货地址
     * @param id
     * @return
     */
    List<Address> findAllAddress(Long id);

    /**
     * 删除所选收货地址信息
     * @param userId
     * @param addressId
     */
    void deleteAddressWithId(Long userId, Long addressId);

    /**
     * 删除所有收货地址信息
     * @param userId
     */
    void deleteAllAddress(Long userId);

    /**
     * 根据addressId查询单独的收货地址信息
     * @param addressId
     * @return
     */
    Address findAddressWithId(Long addressId);
}
