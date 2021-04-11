package com.shop.cn.common.pojo;


import com.shop.cn.common.vo.FBSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 类描述：购物车
 * 类名称：com.shop.cn.common.pojo.cart
 * @author zhangyk
 * @version V1.0
 */
@Getter
@Setter
@ToString
@ApiModel(value = "购物车实体")
public class Cart extends FBSubject {
    /**
     *加入购物车的用户id
     */
    @ApiModelProperty("加入购物车的用户id")
    private Long userId;
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long productId;
    /**
     * 加入购物车商品数量
     */
    @ApiModelProperty("加入购物车商品数量")
    private String quantity;
    /**
     * 购物车商品总价
     */
    @ApiModelProperty("购物车商品总价")
    private Double price;
    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("购物车商品创建时间")
    private Date createDate;

    @ApiModelProperty("购物车商品创建时间(String类型)")
    private String createDateStr;

    @ApiModelProperty("购物车商品修改时间")
    private Date modifiedDate;

    @ApiModelProperty("商品尺寸")
    private String size;

    @ApiModelProperty("逻辑上是否删除 （1未删除/0已删除）")
    private int isDelete;

    private Product product;


}
