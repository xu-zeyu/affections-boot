package com.jinHan.gold.core.users.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: UserPetCreateCommand
 * 描述: 创建用户爱宠命令
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPetCreateCommand extends UserPetBaseCommand {

    /**
     * 用户ID
     */
    private Long userId;
}
