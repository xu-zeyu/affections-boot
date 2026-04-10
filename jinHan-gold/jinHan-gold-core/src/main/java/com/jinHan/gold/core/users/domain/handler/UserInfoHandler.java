package com.jinHan.gold.core.users.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.users.domain.command.UserInfoCommand;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.model.Users;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: UserInfoHandler
 * 描述: 客户端获取用户信息指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/15
 */
@Component
public class UserInfoHandler {
    @Resource
    private UserMapper userMapper;

    public Users userInfo(UserInfoCommand command) {
        // 查询用户信息
        Users users = userMapper.selectInfoById(command.getUserId());
        if (users == null) {
            throw new BusinessException("未查询到此 id 用户");
        }
        return users;
    }
}
