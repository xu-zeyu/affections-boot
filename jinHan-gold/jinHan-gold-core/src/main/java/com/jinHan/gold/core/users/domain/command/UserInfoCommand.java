package com.jinHan.gold.core.users.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名: UserInfoCommand
 * 描述: 客户端获取用户信息命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoCommand {
    private String userId;
}
