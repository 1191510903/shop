package com.shop.cn.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 管理员
 * @table user
 */
@Getter
@Setter
@ToString
@ApiModel(value = "管理员实体")
public class Admin {
    @ApiModelProperty("管理员Id")
    private Long adminId;
    @ApiModelProperty("管理员姓名")
    private String adminName;
    @ApiModelProperty("管理员密码")
    private String password;
    @ApiModelProperty("管理员头像")
    private String photo;
    @ApiModelProperty("管理员邮箱")
    private String mail;
    @ApiModelProperty("管理员电话")
    private String phone;
    @ApiModelProperty("是否是超级管理员")
    private int superAdmin=0;
    @ApiModelProperty("是否是超级管理员")
    private String superAdminStr="0";
}
