package com.jinHan.gold.core.users.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinHan.gold.core.users.domain.model.UserPet;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名: UserPetMapper
 * 描述: 用户爱宠数据访问层
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Mapper
public interface UserPetMapper extends BaseMapper<UserPet> {
}
