package com.jinHan.gold.api.controller.captcha;

import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.admin.domain.mapper.AdminMapper;
import com.jinHan.gold.core.admin.domain.model.Admin;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: CaptchaController
 * 描述: 验证码接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/4
 */
@RestController
@RequestMapping("/public/captcha")
public class CaptchaController {

    @Resource
    private SmsCodeCache smsCodeCache;

    @PostMapping("/login/{phone}")
    public Result<String> create(@PathVariable @NotBlank String phone) {
        String code = smsCodeCache.createCache(phone);
        smsCodeCache.setUserCache(phone,code);
        return Result.success(code);
    }
}
