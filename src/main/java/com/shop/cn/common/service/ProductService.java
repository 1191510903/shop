package com.shop.cn.common.service;

import com.shop.cn.common.pojo.*;
import com.shop.cn.common.vo.Page;
import com.shop.cn.common.vo.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ProductService {
    /**
     * 联系我们-保存到信箱表
     */
    void saveToLetter(Letter letter);

    /**
     * 查找所有一级目录分类
     * @return
     */
    List<Category1> findCategory1();

    /**
     * 根据一级菜单id查询二级菜单
     * @param id
     * @return
     */
    List<Category2> findCategory2(Long id);

    /**
     * 根据二级菜单id查询商品
     * @param product
     * @param page
     * @return
     */
    PageInfo<Product> findProduct(Product product, Page page);

    /**
     * 根据商品id查询单个商品详情
     * @param id
     * @return
     */
    Product findProductDetail(Long id);

    /**
     * 根据商品主键id查询单个商品详情
     * @param id
     * @return
     */
    Product findProductDetailWithKey(Long id);

    /**
     * 收藏商品
     * @param id
     * @param userId
     */
    void saveToCollection(Long id, Long userId);

    /**
     * 找到收藏夹中商品id
     * @param userId
     * @return
     */
    List<Collection> findAllFromCollection(Long userId);


    /**
     * 先从数据库中找是否存在用户收藏的商品
     * @param id
     * @param userId
     * @return
     */
    Collection findCollectionWithProductIdAndUserId(Long id, Long userId);

    /**
     *删除所选的收藏夹商品
     * @param productId
     * @param userId
     */
    void deleteFromCollectionWithId(Long productId, Long userId);

    /**
     * 清空收藏夹
     * @param userId
     */
    void deleteAllFromCollection(Long userId);


    //TODO
    /**
     * 更新点击商品事件
     * @param id
     * @param hits
     */
    void updateClickProduct(Long id, Long hits);

    /**
     * 查询所有商品根据点击量进行排序
     * @return
     */
    List<Product> findAllProductOrderByHits();
}
