package com.jinHan.gold.admin.controller.user.request;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.command.UserPetUpdateCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserPetUpdateRequest
 * 描述: 管理端更新用户爱宠请求
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
public class UserPetUpdateRequest {

    /**
     * 爱宠ID
     */
    @NotNull(message = "爱宠ID不能为空")
    private Long id;

    /**
     * 宠物名称
     */
    private String petName;

    /**
     * 宠物类型
     */
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

    public UserPetUpdateCommand toCommand() {
        UserPetUpdateCommand command = new UserPetUpdateCommand();
        command.setId(this.id);
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
