package com.shop.cn.common.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述：一级类目实体类
 * 类名称：com.shop.cn.common.pojo.Category1
 * @author Zhangyk
 * @version V1.0
 */
@Setter
@Getter
@ToString
public class Category1 implements Serializable {

	private Long category1Id;

    private String category1Name;

    private String category1Record;
    
    private Date gmtCreate;

    private Date gmtModified;

    private Integer showFlag;

    private Integer delFlag;


}