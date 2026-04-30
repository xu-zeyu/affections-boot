package com.jinHan.gold.core.users.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: UserPetUpdateCommand
 * 描述: 更新用户爱宠命令
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPetUpdateCommand extends UserPetBaseCommand {

    /**
     * 爱宠ID
     */
    private Long id;

    /**
     * 当前操作用户ID
     */
    private Long userId;
}
