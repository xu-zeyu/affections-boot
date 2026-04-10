package com.jinHan.gold.core.fosterCare.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareUpdateCommand;
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
 * 类名: FosterCareUpdateHandler
 * 描述: 更新寄养记录处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Component
public class FosterCareUpdateHandler {

    @Resource
    private FosterCareRecordMapper fosterCareRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public void update(FosterCareUpdateCommand command) {
        // 查询寄养记录
        FosterCareRecord record = fosterCareRecordMapper.selectById(command.getId());
        if (record == null) {
            throw new BusinessException("寄养记录不存在");
        }

        // 如果已完成或已取消，不允许修改
        if (record.getStatus() == FosterCareStatusEnum.COMPLETED || 
            record.getStatus() == FosterCareStatusEnum.CANCELLED) {
            throw new BusinessException("当前状态不允许修改");
        }

        // 更新字段
        if (command.getPetName() != null) {
            record.setPetName(command.getPetName());
        }
        if (command.getPetType() != null) {
            try {
                record.setPetType(PetTypeEnum.fromCode(command.getPetType()));
            } catch (IllegalArgumentException e) {
                throw new BusinessException("无效的宠物类型");
            }
        }
        if (command.getOwnerName() != null) {
            record.setOwnerName(command.getOwnerName());
        }
        if (command.getOwnerPhone() != null) {
            record.setOwnerPhone(command.getOwnerPhone());
        }
        if (command.getFosterAddress() != null) {
            record.setFosterAddress(command.getFosterAddress());
        }
        if (command.getSpecialRequirements() != null) {
            record.setSpecialRequirements(command.getSpecialRequirements());
        }
        if (command.getRemark() != null) {
            record.setRemark(command.getRemark());
        }

        // 如果修改了日期或价格，重新计算天数和总金额
        if (command.getFosterStartDate() != null || command.getFosterEndDate() != null || 
            command.getFosterPrice() != null) {
            
            LocalDateTime startDate = command.getFosterStartDate() != null ? 
                    command.getFosterStartDate() : record.getFosterStartDate();
            LocalDateTime endDate = command.getFosterEndDate() != null ? 
                    command.getFosterEndDate() : record.getFosterEndDate();
            BigDecimal price = command.getFosterPrice() != null ? 
                    command.getFosterPrice() : record.getFosterPrice();

            if (startDate.isAfter(endDate)) {
                throw new BusinessException("寄养开始日期不能晚于结束日期");
            }

            long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
            if (days <= 0) {
                throw new BusinessException("寄养天数必须大于0");
            }

            record.setFosterStartDate(startDate);
            record.setFosterEndDate(endDate);
            record.setFosterDays((int) days);
            record.setFosterPrice(price);
            record.setTotalAmount(price.multiply(BigDecimal.valueOf(days)));
        }

        record.setUpdatedTime(LocalDateTime.now());

        int updated = fosterCareRecordMapper.updateById(record);
        if (updated <= 0) {
            throw new BusinessException("更新寄养记录失败");
        }
    }
}
