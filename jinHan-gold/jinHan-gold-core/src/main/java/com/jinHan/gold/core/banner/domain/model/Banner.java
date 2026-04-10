package com.jinHan.gold.core.banner.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 类名: Banner
 * 描述: Banner 实体类
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "banner")
public class Banner extends BaseEntity {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
     * 排序字段，数字越小越靠前
     */
    private Integer sort;

    /**
     * 状态
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
}
