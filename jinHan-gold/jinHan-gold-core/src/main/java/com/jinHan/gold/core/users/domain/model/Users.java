package com.jinHan.gold.core.users.domain.model;

import com.affectionsboot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 类名: Users
 * 描述: 用户实体类
 * 作者: xuzeyu
 * 创建时间: 2026/1/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "users")
public class Users extends BaseEntity {
    /**
     * 用户ID，主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 手机号，唯一
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 认证状态
     */
    private UsersStatusEnum status;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginAt;

    /**
     * 用户名（从身份证认证信息表获取）
     * 该字段不在 users 表中，通过关联查询填充
     */
    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String idNumber;

    @TableField(exist = false)
    private GenderEnum gender;
}
