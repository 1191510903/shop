package com.shop.cn.common.pojo;

import com.shop.cn.common.vo.FBSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述：订阅
 * 类名称：com.shop.cn.common.pojo.cart
 * @author zhangyk
 * @version V1.0
 */
@Getter
@Setter
@ToString
@ApiModel(value = "订阅实体")
public class Subscription extends FBSubject{
    @ApiModelProperty("订阅用户")
    private Long userId;

    @ApiModelProperty("订阅的信息")
    private String email;
}
