package com.jinHan.gold.core.variety.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jinHan.gold.core.variety.domain.command.VarietyUpdateCommand;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: VarietyUpdateCommandHandler
 * 描述: 品种更新命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Component
public class VarietyUpdateCommandHandler {
    @Resource
    private VarietyMapper varietyMapper;

    /**
     * 更新宠物品种
     *
     * @param command 更新品种的命令对象
     * @return 更新后的品种实体
     */
    @Transactional(rollbackFor = Exception.class)
    public Variety update(VarietyUpdateCommand command) {
        // 参数校验
        if (command == null) {
            throw new BusinessException("更新品种的参数不能为空");
        }

        if (command.getId() == null) {
            throw new BusinessException("品种ID不能为空");
        }

        // 校验品种是否存在
        Variety existVariety = varietyMapper.selectById(command.getId());
        if (existVariety == null) {
            throw new BusinessException("品种不存在");
        }

        try {
            // 构建更新条件
            LambdaUpdateWrapper<Variety> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Variety::getId, command.getId())
                    .set(command.getName() != null, Variety::getName, command.getName())
                    .set(command.getIconUrl() != null, Variety::getIconUrl, command.getIconUrl())
                    .set(command.getMinPrice() != null, Variety::getMinPrice, command.getMinPrice())
                    .set(command.getSortOrder() != null, Variety::getSortOrder, command.getSortOrder())
                    .set(command.getStatus() != null, Variety::getStatus, command.getStatus());

            // 执行更新
            int result = varietyMapper.update(null, updateWrapper);
            if (result <= 0) {
                throw new BusinessException("更新品种失败");
            }

            return varietyMapper.selectById(command.getId());
        } catch (Exception e) {
            throw new BusinessException("更新品种失败：" + e.getMessage());
        }
    }
}
