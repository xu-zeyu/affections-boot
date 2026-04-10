package com.jinHan.gold.core.banner.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.banner.domain.command.BannerFindCommand;
import com.jinHan.gold.core.banner.domain.mapper.BannerMapper;
import com.jinHan.gold.core.banner.domain.model.Banner;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: BannerFindCommandHandler
 * 描述:
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Component
public class BannerFindCommandHandler {
    @Resource
    private BannerMapper bannerMapper;

    public Banner findBanner(BannerFindCommand command) {
        try {
            Banner banner = bannerMapper.selectById(command.getId());
            if (banner == null) {
                throw new BusinessException("查无此banner");
            }
            return banner ;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
