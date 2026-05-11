package com.jinHan.gold.core.merchant.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.merchant.domain.command.MerchantApplicationCreateCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantApplicationMapper;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplication;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplicationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 类名: MerchantApplicationCreateHandler
 * 描述: 创建商家申请处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Component
@RequiredArgsConstructor
public class MerchantApplicationCreateHandler {
    private final MerchantApplicationMapper merchantApplicationMapper;
    private final MerchantMapper merchantMapper;

    public Long handle(MerchantApplicationCreateCommand command) {
        Long userId = command.getUserId();

        // 检查用户是否已经是商家
        Long existingMerchantCount = merchantMapper.selectCount(
                new LambdaQueryWrapper<com.jinHan.gold.core.merchant.domain.model.Merchant>()
                        .eq(com.jinHan.gold.core.merchant.domain.model.Merchant::getUserId, userId)
        );
        if (existingMerchantCount > 0) {
            throw new BusinessException("该用户已经是商家，无需重复申请");
        }

        // 检查是否有待审核的申请
        Long pendingCount = merchantApplicationMapper.selectCount(
                new LambdaQueryWrapper<MerchantApplication>()
                        .eq(MerchantApplication::getUserId, userId)
                        .eq(MerchantApplication::getStatus, MerchantApplicationStatusEnum.PENDING)
        );
        if (pendingCount > 0) {
            throw new BusinessException("您已有待审核的申请，请勿重复提交");
        }

        MerchantApplication application = new MerchantApplication();
        application.setUserId(userId);
        application.setMerchantName(command.getMerchantName());
        application.setBusinessLicense(command.getBusinessLicense());
        application.setContactPerson(command.getContactPerson());
        application.setContactPhone(command.getContactPhone());
        application.setAddress(command.getAddress());
        application.setDescription(command.getDescription());
        application.setLogo(command.getLogo());
        application.setStatus(MerchantApplicationStatusEnum.PENDING);

        merchantApplicationMapper.insert(application);
        return application.getApplicationId();
    }
}
