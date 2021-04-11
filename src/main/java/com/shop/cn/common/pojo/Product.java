package com.shop.cn.common.pojo;

import com.shop.cn.common.vo.FBSubject;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 类描述：商品类
 * 类名称：com.shop.cn.common.pojo.Product
 *
 * @author zhangyk
 * @version V1.0
 */
@Getter
@Setter
@ToString
@ApiModel(value = "商品实体")
public class Product extends FBSubject {


    private Long productId;

    private String productName;

    private Long category1Id;

    private Long category2Id;

    private Double purchasePrice;

    private Double salePrice;

    private Integer inventory;

    // 商品的点击量
    private Long hits;

    /**
     * 商品整体图片
     */
    private String imgSrc;
    private String imgSrc2;
    private String imgSrc3;
    private String imgSrc4;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer showFlag;

    private Integer delFlag;

    private String outline;


}
