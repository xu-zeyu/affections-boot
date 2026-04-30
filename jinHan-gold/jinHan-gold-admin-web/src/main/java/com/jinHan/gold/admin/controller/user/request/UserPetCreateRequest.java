package com.jinHan.gold.admin.controller.user.request;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.command.UserPetCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserPetCreateRequest
 * 描述: 管理端创建用户爱宠请求
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
public class UserPetCreateRequest {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 宠物名称
     */
    @NotBlank(message = "宠物名称不能为空")
    private String petName;

    /**
     * 宠物类型
     */
    @NotBlank(message = "宠物类型不能为空")
    private String petType;

    /**
     * 宠物品种ID
     */
    private Long varietyId;

    /**
     * 宠物性别
     */
    private GenderEnum gender;

    /**
     * 宠物生日
     */
    private LocalDate birthday;

    /**
     * 宠物头像
     */
    private String avatar;

    /**
     * 备注
     */
    private String remark;

    public UserPetCreateCommand toCommand() {
        UserPetCreateCommand command = new UserPetCreateCommand();
        command.setUserId(this.userId);
        command.setPetName(this.petName);
        command.setPetType(this.petType);
        command.setVarietyId(this.varietyId);
        command.setGender(this.gender);
        command.setBirthday(this.birthday);
        command.setAvatar(this.avatar);
        command.setRemark(this.remark);
        return command;
    }
}
