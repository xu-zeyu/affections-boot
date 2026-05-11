package com.jinHan.gold.core.merchant.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.merchant.domain.command.MerchantPageCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 类名: MerchantPageHandler
 * 描述: 商家分页查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Component
@RequiredArgsConstructor
public class MerchantPageHandler {
    private final MerchantMapper merchantMapper;

    public Page<Merchant> handle(MerchantPageCommand command) {
        LambdaQueryWrapper<Merchant> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(command.getMerchantName())) {
            wrapper.like(Merchant::getMerchantName, command.getMerchantName());
        }

        if (command.getStatus() != null) {
            wrapper.eq(Merchant::getStatus, command.getStatus());
        }

        wrapper.orderByDesc(Merchant::getCreatedTime);

        return merchantMapper.selectPage(command.getPage(), wrapper);
    }
}
