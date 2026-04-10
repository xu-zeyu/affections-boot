package com.jinHan.gold.core.fosterCare.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareCreateCommand;
import com.jinHan.gold.core.fosterCare.domain.mapper.FosterCareRecordMapper;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareRecord;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareStatusEnum;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 类名: FosterCareCreateHandler
 * 描述: 创建寄养记录处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Component
public class FosterCareCreateHandler {

    @Resource
    private FosterCareRecordMapper fosterCareRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long create(FosterCareCreateCommand command) {
        // 验证宠物类型
        PetTypeEnum petTypeEnum;
        try {
            petTypeEnum = PetTypeEnum.fromCode(command.getPetType());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("无效的宠物类型");
        }

        // 验证日期
        if (command.getFosterStartDate().isAfter(command.getFosterEndDate())) {
            throw new BusinessException("寄养开始日期不能晚于结束日期");
        }

        // 计算寄养天数
        long days = ChronoUnit.DAYS.between(command.getFosterStartDate().toLocalDate(), 
                                          command.getFosterEndDate().toLocalDate()) + 1;
        
        if (days <= 0) {
            throw new BusinessException("寄养天数必须大于0");
        }

        // 计算总金额
        BigDecimal totalAmount = command.getFosterPrice().multiply(BigDecimal.valueOf(days));

        // 创建寄养记录
        FosterCareRecord record = new FosterCareRecord();
        record.setUserId(command.getUserId());
        record.setPetName(command.getPetName());
        record.setPetType(petTypeEnum);
        record.setOwnerName(command.getOwnerName());
        record.setOwnerPhone(command.getOwnerPhone());
        record.setFosterStartDate(command.getFosterStartDate());
        record.setFosterEndDate(command.getFosterEndDate());
        record.setFosterDays((int) days);
        record.setFosterPrice(command.getFosterPrice());
        record.setTotalAmount(totalAmount);
        record.setFosterAddress(command.getFosterAddress());
        record.setSpecialRequirements(command.getSpecialRequirements());
        record.setStatus(FosterCareStatusEnum.PENDING);
        record.setRemark(command.getRemark());
        record.setCreatedTime(LocalDateTime.now());
        record.setUpdatedTime(LocalDateTime.now());

        int inserted = fosterCareRecordMapper.insert(record);
        if (inserted <= 0) {
            throw new BusinessException("创建寄养记录失败");
        }
        
        return record.getId();
    }
}
