package com.shop.cn.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.shop.cn.common.dao.ProductDao;
import com.shop.cn.common.pojo.*;
import com.shop.cn.common.service.ProductService;
import com.shop.cn.common.vo.Page;
import com.shop.cn.common.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public Product findProductDetailWithKey(Long id) {
        return  productDao.findProductDetailWithKey(id);
    }

    @Override
    public Product findProductDetail(Long id) {
        return  productDao.findProductDetail(id);
    }

    @Override
    public PageInfo<Product> findProduct(Product product, Page page) {
        //分页实现
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<Product> products = productDao.findProduct(product);
        PageInfo<Product> info = new PageInfo<>(products);
        return info;
    }

    @Override
    public void saveToLetter(Letter letter) {
        productDao.saveToLetter(letter);

    }

    @Override
    public List<Category2> findCategory2(Long id) {
        return productDao.findCategory2(id);
    }

    @Override
    public List<Category1> findCategory1() {
        return productDao.findCategory1();
    }

    @Override
    public void saveToCollection(Long id, Long userId) {
        productDao.saveToCollection(id,userId);
    }

    @Override
    public List<Collection> findAllFromCollection(Long userId) {
        return  productDao.findAllFromCollection(userId);
    }

    @Override
    public Collection findCollectionWithProductIdAndUserId(Long id, Long userId) {
        return productDao.findCollectionWithProductIdAndUserId(id,userId);
    }

    @Override
    public void deleteFromCollectionWithId(Long productId, Long userId) {
        productDao.deleteFromCollectionWithId(productId,userId);
    }

    @Override
    public void deleteAllFromCollection(Long userId) {
        productDao.deleteAllFromCollection(userId);
    }

    @Override
    public void updateClickProduct(Long id, Long hits) {
        productDao.updateClickProduct(id,hits);
    }

    @Override
    public List<Product> findAllProductOrderByHits() {
        return productDao.findAllProductOrderByHits();
    }
}
