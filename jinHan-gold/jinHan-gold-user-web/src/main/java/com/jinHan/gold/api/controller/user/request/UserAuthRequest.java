package com.jinHan.gold.api.controller.user.request;

import com.jinHan.gold.core.auth.domain.command.UserAuthCommand;
import com.jinHan.gold.core.auth.domain.model.AuditStatusEnum;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.command.UserPageCommand;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserAuthRequest
 * 描述: 用户身份认证请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class UserAuthRequest {
    @NotBlank(message = "身份证不能为空")
    private String idNumber;
    @NotBlank(message = "身份证姓名不能为空")
    private String realName;
    @NotNull(message = "身份证性别不能为空")
    private GenderEnum gender;
    @NotNull(message = "身份证有效期不能为空")
    private LocalDate validStartDate;
    @NotNull(message = "身份证有效期不能为空")
    private LocalDate validEndDate;

    public UserAuthCommand toCommand(String userId) {
        UserAuthCommand command = new UserAuthCommand();
        command.setUserId(userId);
        command.setIdNumber(this.idNumber);
        command.setRealName(this.realName);
        command.setGender(this.gender);
        command.setValidStartDate(this.validStartDate);
        command.setValidEndDate(this.validEndDate);
        command.setAuditStatus(AuditStatusEnum.PENDING);
        return command;
    }
}
