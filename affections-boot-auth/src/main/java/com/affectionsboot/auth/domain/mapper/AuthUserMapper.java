package com.affectionsboot.auth.domain.mapper;

import com.affectionsboot.auth.domain.model.Admin;
import com.affectionsboot.auth.domain.model.Users;
import com.affectionsboot.starter.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名: AuthUserMapper
 * 描述:
 * 作者: xuzeyu
 * 创建时间: 2026/1/2
 */
@Mapper
public interface AuthUserMapper extends BaseMapperX<Users> {
}
