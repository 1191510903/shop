package com.shop.cn.common.service.impl;

import com.shop.cn.common.dao.CartDao;
import com.shop.cn.common.pojo.Address;
import com.shop.cn.common.pojo.Cart;
import com.shop.cn.common.pojo.Product;
import com.shop.cn.common.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Address findAddressWithId(Long addressId) {
        return cartDao.findAddressWithId(addressId);
    }

    @Override
    public void deleteAllAddress(Long userId) {
        cartDao.deleteAllAddress(userId);
    }

    @Override
    public void deleteAddressWithId(Long userId, Long addressId) {
        cartDao.deleteAddressWithId(userId,addressId);
    }

    @Override
    public List<Address> findAllAddress(Long id) {
        return cartDao.findAllAddress(id);
    }

    @Override
    public void addAddressToTable(Address address) {
        cartDao.addAddressToTable(address);
    }

    @Override
    public void deleteProductFromCartWithProductId(Cart cart) {
        cartDao.deleteProductFromCartWithProductId(cart);
    }

    @Override
    public void deleteProductFromCart(Long id) {
        cartDao.deleteProductFromCart(id);
    }

    @Override
    public int findProductFromCartCount(Long id) {
        return cartDao.findProductFromCartCount(id);
    }

    @Override
    public List<Cart> findProductFromCart(Long id) {

        return cartDao.findProductFromCart(id);
    }

    @Override
    public Product addProductToCart(Cart cart) {
        cartDao.addProductToCart(cart);
        return null;
    }
}
