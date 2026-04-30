package com.jinHan.gold.core.users.domain.handler;

import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.utils.StringUtils;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import com.jinHan.gold.core.users.domain.command.UserPetUpdateCommand;
import com.jinHan.gold.core.users.domain.mapper.UserPetMapper;
import com.jinHan.gold.core.users.domain.model.UserPet;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名: UserPetUpdateHandler
 * 描述: 用户爱宠更新处理器
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Component
public class UserPetUpdateHandler {

    @Resource
    private UserPetMapper userPetMapper;

    @Resource
    private VarietyMapper varietyMapper;

    @Transactional(rollbackFor = Exception.class)
    public UserPet update(UserPetUpdateCommand command) {
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
        validateVariety(command.getVarietyId());

        if (command.getPetName() != null) {
            if (StringUtils.isBlank(command.getPetName())) {
                throw new BusinessException("宠物名称不能为空");
            }
            userPet.setPetName(command.getPetName().trim());
        }
        if (command.getPetType() != null) {
            userPet.setPetType(parsePetType(command.getPetType()));
        }
        if (command.getVarietyId() != null) {
            userPet.setVarietyId(command.getVarietyId());
        }
        if (command.getGender() != null) {
            userPet.setGender(command.getGender());
        }
        if (command.getBirthday() != null) {
            userPet.setBirthday(command.getBirthday());
        }
        if (command.getAvatar() != null) {
            userPet.setAvatar(command.getAvatar());
        }
        if (command.getRemark() != null) {
            userPet.setRemark(command.getRemark());
        }

        if (userPetMapper.updateById(userPet) <= 0) {
            throw new BusinessException("更新爱宠失败");
        }
        return userPetMapper.selectById(command.getId());
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
