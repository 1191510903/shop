package com.shop.cn.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //分页
        PaginationInterceptor interceptor = new PaginationInterceptor();
        //设置请求的页面大于最后页面后操作，true调回到首页，false继续请求，默认false
        interceptor.setOverflow(false);
        //设置最大单页限制数量，默认500条，-1 不受限制
        interceptor.setLimit(-1);
        return interceptor;
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
