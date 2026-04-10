package com.jinHan.gold.core.banner.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.banner.domain.command.BannerPageCommand;
import com.jinHan.gold.core.banner.domain.mapper.BannerMapper;
import com.jinHan.gold.core.banner.domain.model.Banner;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: BannerPageCommandHandler
 * 描述: 分页查询处理器
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */

@Component
public class BannerPageCommandHandler {
    @Resource
    private BannerMapper bannerMapper;

    public Page<Banner> page(BannerPageCommand command) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(command.getTitle() != null, Banner::getTitle, command.getTitle())
                .eq(command.getStatus() != null, Banner::getStatus, command.getStatus())
                .eq(command.getPlatform() != null, Banner::getPlatform, command.getPlatform())
                .orderByAsc(Banner::getSort); // 按排序字段升序

        return bannerMapper.selectPage(
                new Page<>(command.getPage(), command.getSize()),
                queryWrapper
        );
    }
}
