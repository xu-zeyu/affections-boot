package com.jinHan.gold.admin.controller.captcha;

import cn.dev33.satoken.annotation.SaIgnore;
import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.admin.domain.mapper.AdminMapper;
import com.jinHan.gold.core.admin.domain.model.Admin;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Resource
    private AdminMapper adminMapper;

    @SaIgnore // 忽略 Sa-Token 鉴权，包括 token 前缀检查
    @Log(value = "生成登录验证码", operationType = "CAPTCHA_CREATE")
    @PostMapping("/login/{name}")
    public Result<String> create(@PathVariable @NotBlank String name) {
        LambdaQueryWrapper<Admin> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(Admin::getUsername, name);
        Admin admin = adminMapper.selectOne(lambdaQuery);
        if (admin == null) {
            throw new BusinessException("未查询到此管理员");
        }
        String phone = admin.getPhone();
        String code = smsCodeCache.createCache(phone);
        smsCodeCache.setCache(phone,code);
        return Result.success(code);
    }
}
