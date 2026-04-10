package com.jinHan.gold.core.users.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.users.domain.command.UserPageCommand;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.model.Users;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类名: UserPageCommandHandler
 * 描述: 用户分页查询命令处理类
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Component
public class UserPageCommandHandler {
    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询用户
     *
     * @param command 分页查询用户的命令对象
     * @return 用户分页结果
     */
    public Page<Users> page(UserPageCommand command) {
        // 构建查询条件
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();

        // 名称模糊查询
        if (StringUtils.isNotBlank(command.getUsername())) {
            queryWrapper.apply("EXISTS (SELECT 1 FROM id_card_info a WHERE a.user_id = user_id AND a.real_name LIKE CONCAT('%', {0}, '%'))", command.getUsername());
        }

        if (StringUtils.isNotBlank(command.getPhone()) && command.getPhone() != null) {
            queryWrapper.eq(Users::getPhone, command.getPhone());
        }

        if (command.getGender() != null) {
            queryWrapper.apply("EXISTS (SELECT 1 FROM id_card_info a WHERE a.user_id = user_id AND a.gender = CONCAT({0}))", command.getGender());
        }

        if (command.getStatus() != null) {
            queryWrapper.eq(Users::getStatus, command.getStatus());
        }
        // 分页查询
        return userMapper.selectWithPage(new Page<>(command.getPage(), command.getSize()), queryWrapper);
    }
}
