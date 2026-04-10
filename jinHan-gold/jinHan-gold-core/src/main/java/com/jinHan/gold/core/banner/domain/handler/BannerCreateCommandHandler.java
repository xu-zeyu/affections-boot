package com.jinHan.gold.core.banner.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.mapper.BannerMapper;
import com.jinHan.gold.core.banner.domain.model.Banner;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 类名: BannerCreateCommandHandler
 * 描述: banner 命令处理类
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */
@Component
public class BannerCreateCommandHandler {
    @Resource
    private BannerMapper bannerMapper;

    /**
     * 创建 banner
     * @param command 创建 banner 的命令对象
     * @return 创建的 banner ID
     */
    @Transactional(rollbackFor =  Exception.class)
    public Long create(BannerCreateCommand command) {
        // 参数校验
        if (command == null) {
            throw new BusinessException("创建 banner 的参数不能为空");
        }

        // 校验时间逻辑
        command.validateTimeLogic();

        // 转换为 Banner 实体
        Banner banner = command.convertToEntity(command);

        // 插入数据库
        int result = bannerMapper.insert(banner);
        if (result <= 0) {
            throw new BusinessException("创建 banner 失败");
        }

        return banner.getId();
    }
}
