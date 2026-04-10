package com.jinHan.gold.core.auth.domain.command;

import com.jinHan.gold.core.auth.domain.model.AuditStatusEnum;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserAuthCommand
 * 描述: 用户身份认证命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@Data
public class UserAuthCommand {
    private String userId;
    private String idNumber;
    private String realName;
    private GenderEnum gender;
    private LocalDate validStartDate;
    private LocalDate validEndDate;
    private AuditStatusEnum auditStatus;
}
