package com.shop.cn.common.dao;

import com.shop.cn.common.pojo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductDao {

    void saveToLetter(Letter letter);

    /**
     *查找所有一级目录分类
     * @return
     */
    List<Category1> findCategory1();

    /**
     *查找所有一级目录分类
     * @param id 一级菜单id
     * @return
     */
    List<Category2> findCategory2(Long id);

    /**
     * /**
     * 根据二级菜单id查询商品
     * @param product
     * @return
     */
    List<Product> findProduct(Product product);

    /**
     *根据商品id查询单个商品详情
     * @param id
     * @return
     */
    Product findProductDetail(Long id);

    Product findProductDetailWithKey(Long id);

    /**
     * 商品收藏
     * @param productId
     * @param userId
     */
    void saveToCollection(Long productId, Long userId);

    /**
     * 找到收藏夹中商品id
     * @param userId
     * @return
     */
    List<Collection> findAllFromCollection(Long userId);

    /**
     *先从数据库中找是否存在用户收藏的商品
     * @param id
     * @param userId
     * @return
     */
    Collection findCollectionWithProductIdAndUserId(Long id, Long userId);

    /**
     * 删除所选的收藏夹商品
     * @param productId
     * @param userId
     */
    void deleteFromCollectionWithId(Long productId, Long userId);

    /**
     *  清空收藏夹
     * @param userId
     */
    void deleteAllFromCollection(Long userId);
    /**
     * 更新点击商品事件
     * @param id
     * @param hits
     */
    void updateClickProduct(Long id, Long hits);

    List<Product> findAllProductOrderByHits();
}
