package com.jinHan.gold.core.users.domain.mapper;

import com.affectionsboot.starter.mybatis.mapper.BaseMapperX;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.users.domain.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类名: AuthUserMapper
 * 描述:
 * 作者: xuzeyu
 * 创建时间: 2026/1/2
 */
@Mapper
public interface UserMapper extends BaseMapperX<Users> {
    Page<Users> selectWithPage(@Param("page") Page<Users> page, @Param(Constants.WRAPPER) Wrapper<Users> queryWrapper);
    Users selectInfoById(String userId);
}
