package com.jinHan.gold.api.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import com.affectionsboot.auth.stp.StpUserUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类名: SaTokenConfigure [Sa-Token 权限认证] 配置类
 * 描述: 注册全局过滤器，使用独立的 User StpLogic
 * 作者: xuzeyu
 * 创建时间: 2026/1/1
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 登录校验 -- 拦截所有路由，并排除/login/sms , /public/** 用于开放登录
            SaRouter
                    .match("/**")    // 拦截的 path 列表，可以写多个 */
                    .notMatch("/login/sms","/public/**","/banner/**","/variety/**","/product/page","/login/*")
                    .check(r -> StpUserUtil.checkLogin());        // 使用独立的 User StpLogic

        })).addPathPatterns("/**");
    }
}