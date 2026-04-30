package com.jinHan.gold.core.users.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 类名: UserPet
 * 描述: 用户爱宠信息
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user_pet")
public class UserPet extends BaseEntity {

    /**
     * 爱宠ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private PetTypeEnum petType;

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
