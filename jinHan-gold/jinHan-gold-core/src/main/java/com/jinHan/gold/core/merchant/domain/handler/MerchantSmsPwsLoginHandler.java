package com.jinHan.gold.core.merchant.domain.handler;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.affectionsboot.auth.domain.mapper.AuthUserMapper;
import com.affectionsboot.auth.domain.model.Users;
import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.auth.stp.StpMerchantUtil;
import com.affectionsboot.auth.utils.PasswordService;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.merchant.domain.command.MerchantSmsPwsLoginCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import com.jinHan.gold.core.merchant.domain.model.MerchantStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: MerchantSmsPwsLoginHandler
 * 描述: 商家端短信验证码密码登录处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/11
 */
@Component
public class MerchantSmsPwsLoginHandler {

    @Resource
    private AuthUserMapper authUserMapper;

    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private PasswordService passwordService;

    @Resource
    private SmsCodeCache smsCodeCache;

    /**
     * 商家端登录
     *
     * @param command 登录命令
     * @return 登录 token
     */
    @Transactional(rollbackFor = Exception.class)
    public String handler(MerchantSmsPwsLoginCommand command) {
        try {
            Users users = getUsers(command.getPhone());
            validatePassword(command.getPassword(), users.getPassword());
            validateSmsCode(users.getPhone(), command.getSmsCode());

            Merchant merchant = getMerchant(users.getUserId());
            validateMerchantStatus(merchant);

            StpMerchantUtil.login(merchant.getMerchantId());
            SaTokenInfo tokenInfo = StpMerchantUtil.getTokenInfo();
            updateLastLoginTime(users.getUserId());
            return tokenInfo.getTokenValue();
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("登录失败：" + e.getMessage());
        }
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

    private Merchant getMerchant(Long userId) {
        LambdaQueryWrapper<Merchant> merchantQueryWrapper = new LambdaQueryWrapper<>();
        merchantQueryWrapper.eq(Merchant::getUserId, userId);
        Merchant merchant = merchantMapper.selectOne(merchantQueryWrapper);
        if (merchant == null) {
            throw new BusinessException("当前用户未开通商家账号");
        }
        return merchant;
    }

    private void validateMerchantStatus(Merchant merchant) {
        if (merchant.getStatus() != MerchantStatusEnum.ACTIVE) {
            throw new BusinessException("当前商家账号不可登录");
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        Boolean passwordValid = passwordService.passwordValid(rawPassword, encodedPassword);
        if (!passwordValid) {
            throw new BusinessException("账户或密码错误");
        }
    }

    private void validateSmsCode(String phone, String smsCode) {
        String expected = smsCodeCache.getMerchantCache(phone).orElse(null);
        if (expected == null || !StringUtils.equals(expected, smsCode)) {
            throw new BusinessException("验证码错误或已过期");
        }
        smsCodeCache.removeMerchantCache(phone);
    }

    private void updateLastLoginTime(Long userId) {
        Users updateUser = new Users();
        updateUser.setUserId(userId);
        updateUser.setLastLoginAt(LocalDateTime.now());
        authUserMapper.updateById(updateUser);
    }
}
