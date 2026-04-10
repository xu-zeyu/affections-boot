package com.jinHan.gold.api.controller.banner;

import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.banner.domain.command.BannerPageCommand;
import com.jinHan.gold.core.banner.domain.handler.BannerPageCommandHandler;
import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: BannerController
 * 描述: 用户端 Banner 接口
 * 作者: xuzeyu
 * 创建时间: 2025/1/27
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Resource
    private BannerPageCommandHandler bannerPageCommandHandler;

    /**
     * 查询启用的 Banner 列表（小程序端）
     */
    @GetMapping("/list")
    public Result<Page<Banner>> getBannerList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        BannerPageCommand command = new BannerPageCommand();
        command.setPage(page);
        command.setSize(size);
        command.setStatus(StatusEnum.ENABLE); // 只查询启用的
        command.setPlatform(PlatformEnum.ALL);
        
        Page<Banner> bannerPage = bannerPageCommandHandler.page(command);
        return Result.success(bannerPage);
    }
}
