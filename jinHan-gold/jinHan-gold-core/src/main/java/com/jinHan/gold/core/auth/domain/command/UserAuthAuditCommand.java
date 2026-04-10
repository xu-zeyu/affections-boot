package com.jinHan.gold.core.auth.domain.command;

import lombok.Data;

/**
 * 类名: UserAuthAuditCommand
 * 描述: 身份认证审核命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@Data
public class UserAuthAuditCommand {
    private Long userId;
}
