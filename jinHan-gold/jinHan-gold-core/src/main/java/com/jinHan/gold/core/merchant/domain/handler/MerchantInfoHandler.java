package com.jinHan.gold.core.merchant.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.merchant.domain.command.MerchantInfoCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 类名: MerchantInfoHandler
 * 描述: 商家信息查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Component
@RequiredArgsConstructor
public class MerchantInfoHandler {
    private final MerchantMapper merchantMapper;

    public Merchant handle(MerchantInfoCommand command) {
        LambdaQueryWrapper<Merchant> wrapper = new LambdaQueryWrapper<>();

        if (command.getMerchantId() != null) {
            wrapper.eq(Merchant::getMerchantId, command.getMerchantId());
        } else if (command.getUserId() != null) {
            wrapper.eq(Merchant::getUserId, command.getUserId());
        } else {
            throw new BusinessException("商家ID和用户ID不能同时为空");
        }

        Merchant merchant = merchantMapper.selectOne(wrapper);
        if (merchant == null) {
            throw new BusinessException("商家信息不存在");
        }

        return merchant;
    }
}
