package com.jinHan.gold.admin.controller.user.response;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.fosterCare.domain.model.PetTypeEnum;
import com.jinHan.gold.core.users.domain.model.UserPet;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 类名: UserPetResponse
 * 描述: 响应数据- 用户爱宠信息
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Data
public class UserPetResponse {

    private Long id;

    private Long userId;

    private String petName;

    private PetTypeEnum petType;

    private Long varietyId;

    private GenderEnum gender;

    private LocalDate birthday;

    private String avatar;

    private String remark;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public UserPetResponse(UserPet userPet) {
        this.id = userPet.getId();
        this.userId = userPet.getUserId();
        this.petName = userPet.getPetName();
        this.petType = userPet.getPetType();
        this.varietyId = userPet.getVarietyId();
        this.gender = userPet.getGender();
        this.birthday = userPet.getBirthday();
        this.avatar = userPet.getAvatar();
        this.remark = userPet.getRemark();
        this.createdTime = userPet.getCreatedTime();
        this.updatedTime = userPet.getUpdatedTime();
    }
}
