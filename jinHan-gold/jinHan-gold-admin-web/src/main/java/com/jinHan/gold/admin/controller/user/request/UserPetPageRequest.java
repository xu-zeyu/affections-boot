package com.jinHan.gold.admin.controller.user.request;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.command.UserPetPageCommand;
import lombok.Data;

/**
 * 类名: UserPetPageRequest
 * 描述: 管理端用户爱宠分页查询请求
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
public class UserPetPageRequest {

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

    /**
     * 用户ID
     */
    private Long userId;

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

    public UserPetPageCommand toCommand() {
        UserPetPageCommand command = new UserPetPageCommand();
        command.setPage(this.page);
        command.setSize(this.size);
        command.setUserId(this.userId);
        command.setPetName(this.petName);
        command.setPetType(this.petType);
        command.setVarietyId(this.varietyId);
        command.setGender(this.gender);
        return command;
    }
}
