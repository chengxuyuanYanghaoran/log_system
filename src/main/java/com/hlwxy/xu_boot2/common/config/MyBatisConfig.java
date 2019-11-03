package com.hlwxy.xu_boot2.common.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 使用Lambda 表达式 使用驼峰命名法
 */
public class MyBatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return  (s) -> s.setMapUnderscoreToCamelCase(true);
    }
}
