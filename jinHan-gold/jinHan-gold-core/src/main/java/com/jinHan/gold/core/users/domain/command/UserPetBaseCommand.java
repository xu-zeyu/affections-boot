package com.jinHan.gold.core.users.domain.command;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * 类名: UserPetBaseCommand
 * 描述: 用户爱宠基础命令
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
public class UserPetBaseCommand {

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
}
