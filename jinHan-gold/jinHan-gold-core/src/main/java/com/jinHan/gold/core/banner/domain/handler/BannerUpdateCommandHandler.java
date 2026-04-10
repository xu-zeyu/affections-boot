package com.jinHan.gold.core.banner.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.command.BannerUpdateCommand;
import com.jinHan.gold.core.banner.domain.mapper.BannerMapper;
import com.jinHan.gold.core.banner.domain.model.Banner;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: BannerUpdateCommandHandler
 * 描述: banner更新处理类
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Component
public class BannerUpdateCommandHandler {
    @Resource
    private BannerMapper bannerMapper;

    /**
     * 更新 banner
     * @param command 创建 banner 的命令对象
     * @return 创建的 banner ID
     */
    @Transactional(rollbackFor =  Exception.class)
    public Banner update(BannerUpdateCommand command) {
        // 参数校验
        if (command == null) {
            throw new BusinessException("更新 banner 的参数不能为空");
        }

        if (command.getId() == null) {
            throw new BusinessException("banner ID 不能为空");
        }
        // 校验时间逻辑
        command.validateTimeLogic();
        try {
            // 构建更新条件
            LambdaUpdateWrapper<Banner> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Banner::getId, command.getId())
                    .set(command.getUrl() != null, Banner::getUrl, command.getUrl())
                    .set(command.getLink() != null, Banner::getLink, command.getLink())
                    .set(command.getTitle() != null, Banner::getTitle, command.getTitle())
                    .set(command.getDescription() != null, Banner::getDescription, command.getDescription())
                    .set(command.getSort() != null, Banner::getSort, command.getSort())
                    .set(command.getStatus() != null, Banner::getStatus, command.getStatus())
                    .set(command.getPlatform() != null, Banner::getPlatform, command.getPlatform());

            // 执行更新
            int result = bannerMapper.update(null, updateWrapper);
            if (result <= 0) {
                throw new BusinessException("更新 banner 失败，可能记录不存在");
            }
            return bannerMapper.selectById(command.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
