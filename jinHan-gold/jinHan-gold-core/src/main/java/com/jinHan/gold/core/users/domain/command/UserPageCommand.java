package com.jinHan.gold.core.users.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类名: UserPageCommand
 * 描述: 分页查询用户命令
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageCommand extends PageParam {
    /**
     * 手机号
     */
    private String phone;

    /**
     *  姓名（模糊查询）
     */
    private String username;

    /**
     * 状态
     */
    private UsersStatusEnum status;

    /**
     * 状态
     */
    private GenderEnum gender;
}
