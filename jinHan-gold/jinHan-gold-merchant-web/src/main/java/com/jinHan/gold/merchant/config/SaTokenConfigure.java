package com.jinHan.gold.merchant.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import com.affectionsboot.auth.stp.StpMerchantUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类名: SaTokenConfigure
 * 描述: 商家端 Sa-Token 权限认证配置
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> SaRouter
                .match("/**")
                .notMatch("/login/sms", "/public/**", "/error")
                .check(r -> StpMerchantUtil.checkLogin()))).addPathPatterns("/**");
    }
}
