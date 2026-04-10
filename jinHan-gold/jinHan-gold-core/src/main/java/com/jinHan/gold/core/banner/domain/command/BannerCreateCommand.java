package com.jinHan.gold.core.banner.domain.command;

import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * 类名: BannerCreateCommand
 * 描述: 创建 banner 命令
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerCreateCommand extends BaseCommand {
    /**
     * 将命令对象转换为实体对象
     */
    public Banner convertToEntity(BannerCreateCommand command) {
        Banner banner = new Banner();

        // 设置基本字段
        banner.setUrl(command.getUrl());
        banner.setLink(command.getLink());
        banner.setTitle(command.getTitle());
        banner.setDescription(command.getDescription());
        banner.setSort(Optional.ofNullable(command.getSort()).orElse(0)); // 默认排序为0
        banner.setStatus(command.getStatus());
        banner.setStartTime(command.getStartTime());
        banner.setEndTime(command.getEndTime());
        banner.setPlatform(command.getPlatform());

        return banner;
    }
}

