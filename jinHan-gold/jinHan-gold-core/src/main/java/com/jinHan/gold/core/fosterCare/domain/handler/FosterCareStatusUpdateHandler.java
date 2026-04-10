package com.jinHan.gold.core.fosterCare.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareStatusUpdateCommand;
import com.jinHan.gold.core.fosterCare.domain.mapper.FosterCareRecordMapper;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareRecord;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 类名: FosterCareStatusUpdateHandler
 * 描述: 更新寄养状态处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Component
public class FosterCareStatusUpdateHandler {

    @Resource
    private FosterCareRecordMapper fosterCareRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(FosterCareStatusUpdateCommand command) {
        // 查询寄养记录
        FosterCareRecord record = fosterCareRecordMapper.selectById(command.getId());
        if (record == null) {
            throw new BusinessException("寄养记录不存在");
        }

        // 验证状态
        FosterCareStatusEnum newStatus;
        try {
            newStatus = FosterCareStatusEnum.fromCode(command.getStatus());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("无效的寄养状态");
        }

        FosterCareStatusEnum currentStatus = record.getStatus();

        // 状态流转校验
        if (currentStatus == FosterCareStatusEnum.COMPLETED || 
            currentStatus == FosterCareStatusEnum.CANCELLED) {
            throw new BusinessException("当前状态不允许修改");
        }

        // 待寄养 -> 寄养中 -> 已完成
        if (currentStatus == FosterCareStatusEnum.PENDING && 
            newStatus != FosterCareStatusEnum.FOSTERING && 
            newStatus != FosterCareStatusEnum.CANCELLED) {
            throw new BusinessException("待寄养状态只能更新为寄养中或已取消");
        }

        if (currentStatus == FosterCareStatusEnum.FOSTERING && 
            newStatus != FosterCareStatusEnum.COMPLETED) {
            throw new BusinessException("寄养中状态只能更新为已完成");
        }

        // 更新状态
        record.setStatus(newStatus);
        record.setUpdatedTime(LocalDateTime.now());

        int updated = fosterCareRecordMapper.updateById(record);
        if (updated <= 0) {
            throw new BusinessException("更新寄养状态失败");
        }
    }
}
