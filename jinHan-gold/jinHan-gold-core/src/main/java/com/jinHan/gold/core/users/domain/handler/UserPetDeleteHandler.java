package com.jinHan.gold.core.users.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.jinHan.gold.core.users.domain.command.UserPetDeleteCommand;
import com.jinHan.gold.core.users.domain.mapper.UserPetMapper;
import com.jinHan.gold.core.users.domain.model.UserPet;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: UserPetDeleteHandler
 * 描述: 用户爱宠删除处理器
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Component
public class UserPetDeleteHandler {

    @Resource
    private UserPetMapper userPetMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(UserPetDeleteCommand command) {
        if (command == null || command.getId() == null) {
            throw new BusinessException("爱宠ID不能为空");
        }

        UserPet userPet = userPetMapper.selectById(command.getId());
        if (userPet == null) {
            throw new BusinessException("爱宠不存在");
        }
        if (command.getUserId() != null && !command.getUserId().equals(userPet.getUserId())) {
            throw new BusinessException("无权操作该爱宠");
        }

        return userPetMapper.deleteById(command.getId()) > 0;
    }
}
