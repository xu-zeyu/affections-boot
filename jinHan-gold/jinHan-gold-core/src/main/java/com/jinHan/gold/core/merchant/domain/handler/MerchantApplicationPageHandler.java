package com.jinHan.gold.core.merchant.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.merchant.domain.command.MerchantApplicationPageCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantApplicationMapper;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 类名: MerchantApplicationPageHandler
 * 描述: 商家申请分页查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Component
@RequiredArgsConstructor
public class MerchantApplicationPageHandler {
    private final MerchantApplicationMapper merchantApplicationMapper;

    public Page<MerchantApplication> handle(MerchantApplicationPageCommand command) {
        LambdaQueryWrapper<MerchantApplication> wrapper = new LambdaQueryWrapper<>();

        if (command.getUserId() != null) {
            wrapper.eq(MerchantApplication::getUserId, command.getUserId());
        }

        if (command.getStatus() != null) {
            wrapper.eq(MerchantApplication::getStatus, command.getStatus());
        }

        wrapper.orderByDesc(MerchantApplication::getCreatedTime);

        return merchantApplicationMapper.selectPage(command.getPage(), wrapper);
    }
}
