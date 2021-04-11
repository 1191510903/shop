package com.shop.cn.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户
 * @table user
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户实体")
public class User {

    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    private String photo;
    @ApiModelProperty("用户邮箱")
    private String mail;
    @ApiModelProperty("用户电话")
    private String phone;


    public User() {
    }

    public User(Long userId, String userName, String password, String nickName, String photo, String mail) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.photo = photo;
        this.mail = mail;
    }

}
