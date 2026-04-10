package com.jinHan.gold.admin.controller.banner.request;

import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.command.BannerUpdateCommand;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类名: MngBannerUpdateRequest
 * 描述: 后台更新 banner请求 实体类
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */

@Data
public class BannerUpdateRequest {
    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull(message = "uri不能为空")
    private String url;

    private String link;

    @NotNull(message = "标题不能为空")
    private String title;

    private String description;

    @NotNull(message = "状态不能为空")
    private StatusEnum status;

    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @NotNull(message = "平台不能为空")
    private PlatformEnum platform;

    public BannerUpdateCommand toCommand() {
        BannerUpdateCommand bannerUpdateCommand = new BannerUpdateCommand();
        bannerUpdateCommand.setId(this.id);
        bannerUpdateCommand.setUrl(this.url);
        bannerUpdateCommand.setLink(this.link);
        bannerUpdateCommand.setTitle(this.title);
        bannerUpdateCommand.setDescription(this.description);
        bannerUpdateCommand.setStatus(this.status);
        bannerUpdateCommand.setStartTime(this.startTime);
        bannerUpdateCommand.setEndTime(this.endTime);
        bannerUpdateCommand.setPlatform(this.platform);
        return bannerUpdateCommand;
    }
}
