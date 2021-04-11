package com.shop.cn.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 类描述：订单
 * 类名称：com.shop.cn.common.pojo.Order
 * @author zhangyk
 * @version V1.0
 */
@Getter
@Setter
@ToString
@ApiModel(value = "订单实体")
public class Order{

    @ApiModelProperty("uuid订单号")
    private String orderUUID;

    @ApiModelProperty("用户Id")
    private Long userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("快递方式")
    private String expressWay;

    @ApiModelProperty("快递费用")
    private Double expressFee;

    @ApiModelProperty("购物车商品id")
    private Long orderProductId;

    @ApiModelProperty("购物车商品姓名")
    private String orderProductName;

    @ApiModelProperty("订单单个商品的数量")
    private String productCount;

    @ApiModelProperty("订单单个商品的总价")
    private Double productSubtotal;

    @ApiModelProperty("订单单个商品的总价")
    private String subtotalStr;

    @ApiModelProperty("订单所有商品的总价（包含运费）")
    private Double productTotal;

    @ApiModelProperty("订单号所有商品总价（包含运费）")
    private String totalStr;

    @ApiModelProperty("订单中商品创建时间")
    private Date gmtCreate;

    @ApiModelProperty("订单创建时间")
    private Date uuidCreate;

    @ApiModelProperty("订单创建时间")
    private String uuidCreateStr;

    @ApiModelProperty("用户将所选的商品删除（用户只是单纯的将订单删除，但是管理员依旧可以查看订单的具体情况）")
    private int isDeleted;

    @ApiModelProperty("付款方式")
    private String paymentWay;

    @ApiModelProperty("收货地址主键")
    private Long addressId;

    @ApiModelProperty("收货人姓名")
    private String addressName;

    @ApiModelProperty("收货地址")
    private String address;

    @ApiModelProperty("收货人电话")
    private String phone;

    @ApiModelProperty("收货人邮编")
    private String code;

    @ApiModelProperty("收货人性别")
    private String sex;

    @ApiModelProperty("收货人所属机构")
    private String company;

    @ApiModelProperty("收货人电子邮件")
    private String email;

    @ApiModelProperty("商品信息")
    private Product product;
}
