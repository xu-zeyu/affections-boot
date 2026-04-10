package com.jinHan.gold.core.banner.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.banner.domain.command.BannerFindCommand;
import com.jinHan.gold.core.banner.domain.mapper.BannerMapper;
import com.jinHan.gold.core.banner.domain.model.Banner;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: BannerDeleteCommandHandler
 * 描述: 删除  banner 的命令对象
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Component
public class BannerDeleteCommandHandler {
    @Resource
    private BannerMapper bannerMapper;

    public Boolean deleteBanner(BannerFindCommand command) {
        try {
            int result = bannerMapper.deleteById(command.getId());
            if (result <= 0) {
                throw new BusinessException("删除失败");
            }
            return true;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
