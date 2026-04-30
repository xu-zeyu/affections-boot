package com.jinHan.gold.core.users.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.utils.StringUtils;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import com.jinHan.gold.core.users.domain.command.UserPetCreateCommand;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.mapper.UserPetMapper;
import com.jinHan.gold.core.users.domain.model.UserPet;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: UserPetCreateHandler
 * 描述: 用户爱宠创建处理器
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Component
public class UserPetCreateHandler {

    @Resource
    private UserPetMapper userPetMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private VarietyMapper varietyMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long create(UserPetCreateCommand command) {
        if (command == null) {
            throw new BusinessException("创建爱宠参数不能为空");
        }
        if (command.getUserId() == null) {
            throw new BusinessException("用户ID不能为空");
        }
        if (StringUtils.isBlank(command.getPetName())) {
            throw new BusinessException("宠物名称不能为空");
        }
        if (StringUtils.isBlank(command.getPetType())) {
            throw new BusinessException("宠物类型不能为空");
        }
        if (userMapper.selectById(command.getUserId()) == null) {
            throw new BusinessException("用户不存在");
        }
        validateVariety(command.getVarietyId());

        UserPet userPet = new UserPet();
        userPet.setUserId(command.getUserId());
        userPet.setPetName(command.getPetName().trim());
        userPet.setPetType(parsePetType(command.getPetType()));
        userPet.setVarietyId(command.getVarietyId());
        userPet.setGender(command.getGender());
        userPet.setBirthday(command.getBirthday());
        userPet.setAvatar(command.getAvatar());
        userPet.setRemark(command.getRemark());

        if (userPetMapper.insert(userPet) <= 0) {
            throw new BusinessException("创建爱宠失败");
        }
        return userPet.getId();
    }

    private PetTypeEnum parsePetType(String petType) {
        try {
            return PetTypeEnum.fromCode(petType.trim());
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("无效的宠物类型");
        }
    }

    private void validateVariety(Long varietyId) {
        if (varietyId != null && varietyMapper.selectById(varietyId) == null) {
            throw new BusinessException("宠物品种不存在");
        }
    }
}
