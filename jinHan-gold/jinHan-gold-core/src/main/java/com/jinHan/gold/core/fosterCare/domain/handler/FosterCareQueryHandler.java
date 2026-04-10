package com.jinHan.gold.core.fosterCare.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCarePageQueryCommand;
import com.jinHan.gold.core.fosterCare.domain.mapper.FosterCareRecordMapper;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: FosterCareQueryHandler
 * 描述: 寄养记录查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Component
public class FosterCareQueryHandler {

    @Resource
    private FosterCareRecordMapper fosterCareRecordMapper;

    /**
     * 分页查询寄养记录
     */
    public IPage<FosterCareRecord> queryPage(FosterCarePageQueryCommand command) {
        Page<FosterCareRecord> page = new Page<>(command.getPage(), command.getSize());
        
        LambdaQueryWrapper<FosterCareRecord> wrapper = new LambdaQueryWrapper<FosterCareRecord>()
                .like(StringUtils.isNotBlank(command.getPetName()), FosterCareRecord::getPetName, command.getPetName())
                .eq(StringUtils.isNotBlank(command.getPetType()), FosterCareRecord::getPetType, command.getPetType())
                .like(StringUtils.isNotBlank(command.getOwnerName()), FosterCareRecord::getOwnerName, command.getOwnerName())
                .like(StringUtils.isNotBlank(command.getOwnerPhone()), FosterCareRecord::getOwnerPhone, command.getOwnerPhone())
                .eq(StringUtils.isNotBlank(command.getStatus()), FosterCareRecord::getStatus, command.getStatus())
                .ge(StringUtils.isNotBlank(command.getFosterStartDateStart()), FosterCareRecord::getFosterStartDate, command.getFosterStartDateStart())
                .le(StringUtils.isNotBlank(command.getFosterStartDateEnd()), FosterCareRecord::getFosterStartDate, command.getFosterStartDateEnd())
                .orderByDesc(FosterCareRecord::getCreatedTime);
        
        return fosterCareRecordMapper.selectPage(page, wrapper);
    }

    /**
     * 根据ID查询寄养记录详情
     */
    public FosterCareRecord queryDetail(Long id) {
        return fosterCareRecordMapper.selectById(id);
    }
}
