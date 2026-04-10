package com.jinHan.gold.core.variety.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.variety.domain.command.VarietyDeleteCommand;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: VarietyDeleteCommandHandler
 * 描述: 品种删除命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Component
public class VarietyDeleteCommandHandler {
    @Resource
    private VarietyMapper varietyMapper;

    /**
     * 删除宠物品种
     *
     * @param command 删除品种的命令对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(VarietyDeleteCommand command) {
        // 参数校验
        if (command == null || command.getId() == null) {
            throw new BusinessException("品种ID不能为空");
        }

        // 校验品种是否存在
        if (varietyMapper.selectById(command.getId()) == null) {
            throw new BusinessException("品种不存在");
        }

        // 执行删除
        int result = varietyMapper.deleteById(command.getId());
        if (result <= 0) {
            throw new BusinessException("删除品种失败");
        }
    }
}
