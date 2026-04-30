package com.jinHan.gold.core.users.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: UserPetPageCommand
 * 描述: 用户爱宠分页查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPetPageCommand extends PageParam {

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
}
