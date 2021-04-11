package com.shop.cn.common.pojo;

import com.shop.cn.common.vo.FBSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 类描述：收藏夹实体
 * 类名称：com.shop.cn.common.pojo.Collection
 * @author Zhangyk
 * @version V1.0
 */
@Setter
@Getter
@ToString
@ApiModel(value = "购物车实体")
public class Collection extends FBSubject{
    @ApiModelProperty("购物车id")
    private Long collectionId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("商品ID")
    private Long productId;
}
