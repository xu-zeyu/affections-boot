package com.jinHan.gold.api.controller.user.vo;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.users.domain.model.Users;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * 类名: UserInfoVo
 * 描述: 响应数据- 用户信息
 * 作者: xuzeyu
 * 创建时间: 2026/1/15
 */
@Data
public class UserInfoVo {
    private Long userId;
    private String username;
    private String phone;
    private UsersStatusEnum status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime lastLoginAt;
    private String idNumber;
    private GenderEnum gender;
    private List<UserPetVo> petList;


    public UserInfoVo(Users users) {
        userId = users.getUserId();
        username = users.getUsername();
        phone = users.getPhone();
        status = users.getStatus();
        createdTime = users.getCreatedTime();
        updatedTime = users.getUpdatedTime();
        lastLoginAt = users.getLastLoginAt();
        idNumber = users.getIdNumber();
        gender = users.getGender();
        petList = Collections.emptyList();
    }
}
