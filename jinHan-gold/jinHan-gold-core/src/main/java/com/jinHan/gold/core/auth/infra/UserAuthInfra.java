package com.jinHan.gold.core.auth.infra;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.auth.domain.mapper.IdCardInfoMapper;
import com.jinHan.gold.core.auth.domain.model.AuditStatusEnum;
import com.jinHan.gold.core.auth.domain.model.IdCardInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类名: UserAutnInfra
 * 描述: 用户认证公共方法类
 * 作者: xuzeyu
 * 创建时间: 2026/1/16
 */
@Component
public class UserAuthInfra {

    @Resource
    private IdCardInfoMapper idCardInfoMapper;

    public String getUserRealName(Long userId) {
        // 根据 userId 查询身份证认证信息，获取真实姓名作为用户名
        LambdaQueryWrapper<IdCardInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IdCardInfo::getUserId,userId);
        queryWrapper.eq(IdCardInfo::getAuditStatus,AuditStatusEnum.APPROVED);
        queryWrapper.select(IdCardInfo::getRealName);
        queryWrapper.last("LIMIT 1");

        IdCardInfo idCardInfo = idCardInfoMapper.selectOne(queryWrapper);

        if (idCardInfo != null && idCardInfo.getRealName() != null) {
            return idCardInfo.getRealName();
        }
        return null;
    }
}
