package com.jinHan.gold.core.variety.domain.handler;

import com.jinHan.gold.core.variety.domain.command.VarietyCreateCommand;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: VarietyCreateCommandHandler
 * 描述: 品种创建命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Component
public class VarietyCreateCommandHandler {
    @Resource
    private VarietyMapper varietyMapper;

    /**
     * 创建宠物品种
     *
     * @param command 创建品种的命令对象
     * @return 创建后的品种实体
     */
    @Transactional(rollbackFor = Exception.class)
    public Variety create(VarietyCreateCommand command) {
        // 参数校验
        if (command == null) {
            throw new RuntimeException("创建品种的参数不能为空");
        }

        // 设置默认值
        if (command.getStatus() == null) {
            command.setStatus(1); // 默认启用
        }
        if (command.getSortOrder() == null) {
            command.setSortOrder(0); // 默认排序为0
        }

        // 转换为实体并插入
        Variety variety = command.convertToEntity(command);
        int result = varietyMapper.insert(variety);
        if (result <= 0) {
            throw new RuntimeException("创建品种失败");
        }

        return variety;
    }
}
