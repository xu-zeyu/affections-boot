package com.jinHan.gold.core.merchant.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.merchant.domain.command.MerchantApplicationReviewCommand;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantApplicationMapper;
import com.jinHan.gold.core.merchant.domain.mapper.MerchantMapper;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplication;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplicationStatusEnum;
import com.jinHan.gold.core.merchant.domain.model.MerchantStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: MerchantApplicationReviewHandler
 * 描述: 审核商家申请处理器
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Component
@RequiredArgsConstructor
public class MerchantApplicationReviewHandler {
    private final MerchantApplicationMapper merchantApplicationMapper;
    private final MerchantMapper merchantMapper;

    @Transactional(rollbackFor = Exception.class)
    public void handle(MerchantApplicationReviewCommand command) {
        Long applicationId = command.getApplicationId();
        MerchantApplication application = merchantApplicationMapper.selectById(applicationId);
        if (application == null) {
            throw new BusinessException("申请记录不存在");
        }

        if (!MerchantApplicationStatusEnum.PENDING.equals(application.getStatus())) {
            throw new BusinessException("该申请已被审核，无法重复审核");
        }

        Boolean approved = command.getApproved();
        application.setReviewedBy(command.getReviewedBy());
        application.setReviewedTime(LocalDateTime.now());

        if (Boolean.TRUE.equals(approved)) {
            // 通过申请，创建商家记录
            application.setStatus(MerchantApplicationStatusEnum.APPROVED);

            // 检查用户是否已经是商家
            Long existingMerchantCount = merchantMapper.selectCount(
                    new LambdaQueryWrapper<Merchant>()
                            .eq(Merchant::getUserId, application.getUserId())
            );
            if (existingMerchantCount > 0) {
                throw new BusinessException("该用户已经是商家");
            }

            Merchant merchant = new Merchant();
            merchant.setUserId(application.getUserId());
            merchant.setMerchantName(application.getMerchantName());
            merchant.setBusinessLicense(application.getBusinessLicense());
            merchant.setContactPerson(application.getContactPerson());
            merchant.setContactPhone(application.getContactPhone());
            merchant.setAddress(application.getAddress());
            merchant.setDescription(application.getDescription());
            merchant.setLogo(application.getLogo());
            merchant.setStatus(MerchantStatusEnum.ACTIVE);
            merchantMapper.insert(merchant);
        } else {
            // 拒绝申请
            application.setStatus(MerchantApplicationStatusEnum.REJECTED);
            application.setRejectReason(command.getRejectReason());
        }

        merchantApplicationMapper.updateById(application);
    }
}
