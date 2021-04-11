package com.shop.cn.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述注解
 * <p>
 * 用于对类、方法、字段描述
 * @author zhangyk
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    /** 描述 */
    String[] value();
}