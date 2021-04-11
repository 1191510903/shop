package com.shop.cn.common.pojo;

import com.shop.cn.common.vo.FBSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述：收货地址实体
 * 类名称：com.shop.cn.common.pojo.Address
 * @author zhangyk
 * @version V1.0
 */
@Getter
@Setter
@ToString
@ApiModel(value = "收货地址实体")
public class Address  extends FBSubject {
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

    @ApiModelProperty("收货人Id")
    private Long userId;
}
