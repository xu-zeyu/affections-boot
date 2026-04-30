package com.jinHan.gold.core.users.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名: UserPetDeleteCommand
 * 描述: 删除用户爱宠命令
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPetDeleteCommand {

    /**
     * 爱宠ID
     */
    private Long id;

    /**
     * 当前操作用户ID
     */
    private Long userId;
}
