package com.jinHan.gold.merchant.controller.captcha;

import com.affectionsboot.auth.domain.mapper.AuthUserMapper;
import com.affectionsboot.auth.domain.model.Users;
import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: CaptchaController
 * 描述: 商家端验证码接口
 * 作者: xuzeyu
 * 创建时间: 2026/05/11
 */
@RestController
@RequestMapping("/public/captcha")
public class CaptchaController {

    @Resource
    private SmsCodeCache smsCodeCache;

    @Resource
    private AuthUserMapper authUserMapper;

    @Resource
    private MerchantMapper merchantMapper;

    @PostMapping("/login/{phone}")
    public Result<String> create(@PathVariable @NotBlank String phone) {
        Users users = getUsers(phone);
        validateMerchant(users.getUserId());
        String code = smsCodeCache.createCache(phone);
        smsCodeCache.setMerchantCache(phone, code);
        return Result.success(code);
    }

    private Users getUsers(String phone) {
        LambdaQueryWrapper<Users> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(Users::getPhone, phone);
        Users users = authUserMapper.selectOne(userQueryWrapper);
        if (users == null) {
            throw new BusinessException("未查询到此商家账号");
        }
        return users;
    }

    private void validateMerchant(Long userId) {
        LambdaQueryWrapper<Merchant> merchantQueryWrapper = new LambdaQueryWrapper<>();
        merchantQueryWrapper.eq(Merchant::getUserId, userId);
        Merchant merchant = merchantMapper.selectOne(merchantQueryWrapper);
        if (merchant == null) {
            throw new BusinessException("当前用户未开通商家账号");
        }
    }
}
