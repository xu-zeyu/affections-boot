package com.jinHan.gold.admin.controller.banner.response;

import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类名: MngBannerFindResponse
 * 描述: 查找的 banner 详情
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Data
public class BannerFindResponse {
    private Long id;

    /**
     * banner图片URL
     */
    private String url;

    /**
     * 跳转链接
     */
    private String link;

    /**
     * banner标题
     */
    private String title;

    /**
     * banner描述
     */
    private String description;

    /**
     * 状态：0-禁用，1-启用
     */
    private StatusEnum status;

    /**
     * 开始展示时间
     */
    private LocalDateTime startTime;

    /**
     * 结束展示时间
     */
    private LocalDateTime endTime;

    /**
     * 目标平台：ALL-全部，PC-PC端，MOBILE-移动端
     */
    private PlatformEnum platform;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    public BannerFindResponse(Banner banner) {
        this.setId(banner.getId());
        this.setUrl(banner.getUrl());
        this.setLink(banner.getLink());
        this.setTitle(banner.getTitle());
        this.setDescription(banner.getDescription());
        this.setStatus(banner.getStatus());
        this.setStartTime(banner.getStartTime());
        this.setEndTime(banner.getEndTime());
        this.setPlatform(banner.getPlatform());
        this.setCreatedTime(banner.getCreatedTime());
        this.setUpdatedTime(banner.getUpdatedTime());
    }
}
