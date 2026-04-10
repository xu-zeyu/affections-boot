package com.jinHan.gold.core.auth.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jinHan.gold.core.auth.domain.command.UserAuthCommand;
import com.jinHan.gold.core.auth.domain.mapper.IdCardInfoMapper;
import com.jinHan.gold.core.auth.domain.model.IdCardInfo;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.model.Users;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: UserAuthHandler
 * 描述: 用户身份认证指令
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@Component
public class UserAuthHandler {
    @Resource
    private IdCardInfoMapper idCardInfoMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Log(value = "用户身份认证", operationType = "AUTH")
    public Long startAuth(UserAuthCommand command) {
        try {
            IdCardInfo idCardInfo = new IdCardInfo();
            idCardInfo.setUserId(Long.parseLong(command.getUserId()));
            idCardInfo.setIdNumber(command.getIdNumber());
            idCardInfo.setRealName(command.getRealName());
            idCardInfo.setGender(command.getGender());
            idCardInfo.setAuditStatus(command.getAuditStatus());
            idCardInfo.setValidStartDate(command.getValidStartDate());
            idCardInfo.setValidEndDate(command.getValidEndDate());
            // 根据用户 id 存入其认证信息
            int result = idCardInfoMapper.insert(idCardInfo);
            if (result <= 0) {
                throw new BusinessException("创建认证信息失败");
            }
            // 存入成功后  用户状态变为待审核
            UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", Long.parseLong(command.getUserId()))
                         .set("status", UsersStatusEnum.UNDER_REVIEW);
            userMapper.update(null, updateWrapper);

            return idCardInfo.getId();
        } catch (Exception e) {
            throw new BusinessException("创建认证信息失败");
        }
    }
}
