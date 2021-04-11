package com.shop.cn.common.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述：二级类目实体类
 * 类名称：com.shop.cn.common.pojo.Category2
 * @author zhangyk
 * @version V1.0
 */
@Getter
@ToString
@Setter
public class Category2 implements Serializable {

	private Long category2Id;

    private String category2Name;

    private String category2Record;
    
    private Long category1Id;
    
    private Date gmtCreate;

    private Date gmtModified;

    private Integer showFlag;

    private Integer delFlag;

}