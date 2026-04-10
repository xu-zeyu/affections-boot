package com.affectionsboot.log.config;

import com.affectionsboot.log.aspect.LogAspect;
import com.affectionsboot.log.service.LogService;
import com.affectionsboot.log.service.impl.LogServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志自动配置
 */
@Configuration
@MapperScan(basePackages = "com.affectionsboot.log.mapper")
public class LogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogService logService() {
        return new LogServiceImpl();
    }
}
