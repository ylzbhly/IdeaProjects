package com.cubegalaxy.cubestage.mybatisdemo.config;
/**
 * Copyright (c) 2019-2119,CubeGalaxy.com All rights reserved.
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis-plus分页插件配置
 * @author lyh
 * @version 1.0
 */
@Configuration
@MapperScan("com.cubegalaxy.cubestage.mybatisdemo.mapper.*")
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }


}
