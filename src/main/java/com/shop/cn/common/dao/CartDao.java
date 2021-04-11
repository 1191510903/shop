package com.shop.cn.common.dao;

import com.shop.cn.common.pojo.Address;
import com.shop.cn.common.pojo.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartDao {
    /**
     * 商品加入购物车
     * @param cart
     * @return
     */
    Integer addProductToCart(Cart cart);

    /**
     * 从购物车中查询商品
     * @param id
     * @return
     */
    List<Cart> findProductFromCart(Long id);

    /**
     * 购物车所有数量
     * @param id
     * @return
     */
    Integer findProductFromCartCount(Long id);

    /**
     *清空购物车
     * @param id
     */
    void deleteProductFromCart(Long id);

    /**
     * 清空购物车中选定的的商品

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
     */
    Address findAddressWithId(Long addressId);
}
