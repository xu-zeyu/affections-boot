package com.jinHan.gold.core.banner.domain.command;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 类名: baseCommand
 * 描述: banner 基础命令
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Data
public class BaseCommand {
    /**
     * 开始展示时间
     */
    private LocalDateTime startTime;

    /**
     * 结束展示时间
     */
    private LocalDateTime endTime;

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
     * 状态：0-禁用，1-启用
     */
    private StatusEnum status;

    /**
     * 目标平台：ALL-全部，PC-PC端，MOBILE-移动端
     */
    private PlatformEnum platform;

    /**
     * 校验时间逻辑
     */
    public void validateTimeLogic() {
        if (this.getStartTime() == null || this.getEndTime() == null) {
            throw new BusinessException("开始时间和结束时间不能为空");
        }

        if (this.getEndTime().isBefore(this.getStartTime())) {
            throw new BusinessException("结束时间不能早于开始时间");
        }

        if (this.getEndTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("结束时间不能早于当前时间");
        }
    }
}
