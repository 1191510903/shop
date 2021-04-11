package com.shop.cn.common.pojo;

import com.shop.cn.common.vo.FBEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @table letter
 *
 */
@Setter
@Getter
@ToString
@ApiModel(value = "联系我们信箱实体")
public class Letter extends FBEntity{
    /**
     * 信箱Id
     */
    @ApiModelProperty("信箱主键id")

    Long letterId;

    /**
     * 信箱主题
     */
    @ApiModelProperty("信箱主题")
    String contactCompany;
    /**
     * 信箱内容
     */
    @ApiModelProperty("信箱内容")
    String contact;


    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    String contactName;

    /**
     * 登录id
     */
    @ApiModelProperty("登录id")
    Long userId;

}
