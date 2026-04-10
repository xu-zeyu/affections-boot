package com.jinHan.gold.core.banner.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerPageCommand extends PageParam {
    private String title;
    private StatusEnum status;
    private PlatformEnum platform;
}