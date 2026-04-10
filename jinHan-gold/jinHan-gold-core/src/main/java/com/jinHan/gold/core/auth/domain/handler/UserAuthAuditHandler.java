package com.jinHan.gold.core.auth.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jinHan.gold.core.auth.domain.command.UserAuthAuditCommand;
import com.jinHan.gold.core.auth.domain.mapper.IdCardInfoMapper;
import com.jinHan.gold.core.auth.domain.model.AuditStatusEnum;
import com.jinHan.gold.core.auth.domain.model.IdCardInfo;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.model.Users;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: UserAuthAuditHandler
 * 描述: 身份认证审核指定
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@Component
public class UserAuthAuditHandler {

    @Resource
    private UserMapper userMapper;

    @Resource
    private IdCardInfoMapper idCardInfoMapper;

    /*
    * 审核通过
    * */
    @Transactional(rollbackFor =  Exception.class)
    public Boolean approved(UserAuthAuditCommand command) {
        try {
            // 更新IdCardInfo表
            LambdaUpdateWrapper<IdCardInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(IdCardInfo::getUserId, command.getUserId());
            updateWrapper.set(IdCardInfo::getAuditStatus, AuditStatusEnum.APPROVED);
            int result = idCardInfoMapper.update(null, updateWrapper);
            if (result <= 0) {
                throw new BusinessException("更新失败");
            }

            // 更新Users表
            LambdaUpdateWrapper<Users> updateUserWrapper = new LambdaUpdateWrapper<>();
            updateUserWrapper.eq(Users::getUserId, command.getUserId());
            updateUserWrapper.set(Users::getStatus, UsersStatusEnum.COMPLETED);
            int userResult = userMapper.update(null, updateUserWrapper);
            if (userResult <= 0) {
                throw new BusinessException("更新失败");
            }

            return true;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }
    public Boolean rejected(UserAuthAuditCommand command) {
        try {
            // 更新IdCardInfo表
            LambdaUpdateWrapper<IdCardInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(IdCardInfo::getUserId, command.getUserId());
            updateWrapper.set(IdCardInfo::getAuditStatus, AuditStatusEnum.REJECTED);
            int result = idCardInfoMapper.update(null, updateWrapper);
            if (result <= 0) {
                throw new BusinessException("更新失败");
            }

            // 更新Users表
            LambdaUpdateWrapper<Users> updateUserWrapper = new LambdaUpdateWrapper<>();
            updateUserWrapper.eq(Users::getUserId, command.getUserId());
            updateUserWrapper.set(Users::getStatus, UsersStatusEnum.REJECTED);
            int userResult = userMapper.update(null, updateUserWrapper);
            if (userResult <= 0) {
                throw new BusinessException("更新失败");
            }

            return true;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
