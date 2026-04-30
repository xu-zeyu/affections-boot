package com.jinHan.gold.core.users.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import com.jinHan.gold.core.users.domain.command.UserPetPageCommand;
import com.jinHan.gold.core.users.domain.mapper.UserPetMapper;
import com.jinHan.gold.core.users.domain.model.UserPet;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类名: UserPetQueryHandler
 * 描述: 用户爱宠查询处理器
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Component
public class UserPetQueryHandler {

    @Resource
    private UserPetMapper userPetMapper;

    /**
     * 分页查询爱宠
     */
    public IPage<UserPet> page(UserPetPageCommand command) {
        if (command == null) {
            throw new BusinessException("分页参数不能为空");
        }

        LambdaQueryWrapper<UserPet> queryWrapper = new LambdaQueryWrapper<UserPet>()
                .eq(command.getUserId() != null, UserPet::getUserId, command.getUserId())
                .like(StringUtils.isNotBlank(command.getPetName()), UserPet::getPetName, command.getPetName())
                .eq(command.getVarietyId() != null, UserPet::getVarietyId, command.getVarietyId())
                .eq(command.getGender() != null, UserPet::getGender, command.getGender())
                .orderByDesc(UserPet::getCreatedTime);

        if (StringUtils.isNotBlank(command.getPetType())) {
            queryWrapper.eq(UserPet::getPetType, parsePetType(command.getPetType()));
        }

        return userPetMapper.selectPage(new Page<>(command.getPage(), command.getSize()), queryWrapper);
    }

    /**
     * 根据ID查询爱宠
     */
    public UserPet findById(Long id) {
        if (id == null) {
            throw new BusinessException("爱宠ID不能为空");
        }
        UserPet userPet = userPetMapper.selectById(id);
        if (userPet == null) {
            throw new BusinessException("爱宠不存在");
        }
        return userPet;
    }

    /**
     * 查询当前用户指定爱宠
     */
    public UserPet findById(Long id, Long userId) {
        UserPet userPet = findById(id);
        if (userId != null && !userId.equals(userPet.getUserId())) {
            throw new BusinessException("无权查看该爱宠");
        }
        return userPet;
    }

    /**
     * 查询单个用户的爱宠列表
     */
    public List<UserPet> findByUserId(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        return userPetMapper.selectList(new LambdaQueryWrapper<UserPet>()
                .eq(UserPet::getUserId, userId)
                .orderByDesc(UserPet::getCreatedTime));
    }

    /**
     * 批量查询用户爱宠列表
     */
    public Map<Long, List<UserPet>> findMapByUserIds(Collection<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return userPetMapper.selectList(new LambdaQueryWrapper<UserPet>()
                        .in(UserPet::getUserId, userIds)
                        .orderByDesc(UserPet::getCreatedTime))
                .stream()
                .collect(Collectors.groupingBy(UserPet::getUserId, Collectors.toList()));
    }

    private PetTypeEnum parsePetType(String petType) {
        try {
            return PetTypeEnum.fromCode(petType.trim());
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("无效的宠物类型");
        }
    }
}
