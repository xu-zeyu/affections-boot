package com.jinHan.gold.core.banner.domain.command;

import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 类名: MngBannerUpdateCommand
 * 描述: 更新 banner 命令
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerUpdateCommand extends BaseCommand {
    private Long id;

}
