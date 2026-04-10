package com.jinHan.gold.admin.controller.banner.request;

import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类名: MngBannerUpdateRequest
 * 描述: 后台更新 banner 实体类
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */

@Data
public class BannerCreateRequest {
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

    public BannerCreateCommand toCommand() {
        BannerCreateCommand bannerCreateCommand = new BannerCreateCommand();
        bannerCreateCommand.setUrl(this.url);
        bannerCreateCommand.setLink(this.link);
        bannerCreateCommand.setTitle(this.title);
        bannerCreateCommand.setDescription(this.description);
        bannerCreateCommand.setStatus(this.status);
        bannerCreateCommand.setStartTime(this.startTime);
        bannerCreateCommand.setEndTime(this.endTime);
        bannerCreateCommand.setPlatform(this.platform);
        return bannerCreateCommand;
    }
}


