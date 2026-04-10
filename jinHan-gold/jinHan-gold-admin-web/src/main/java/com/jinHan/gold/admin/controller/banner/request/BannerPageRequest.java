package com.jinHan.gold.admin.controller.banner.request;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.command.BannerPageCommand;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: BannerPageRequest
 * 描述: 分页查询接口
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerPageRequest extends PageParam  {
    private String title;      // 按标题模糊查询
    private StatusEnum status; // 按状态筛选
    private PlatformEnum platform; // 按平台筛选

    public BannerPageCommand toCommand() {
        BannerPageCommand bannerPageCommand = new BannerPageCommand();
        bannerPageCommand.setTitle(this.title);
        bannerPageCommand.setStatus(this.status);
        bannerPageCommand.setPlatform(this.platform);
        bannerPageCommand.setPage(super.getPage());
        bannerPageCommand.setSize(super.getSize());
        return bannerPageCommand;
    }
}
