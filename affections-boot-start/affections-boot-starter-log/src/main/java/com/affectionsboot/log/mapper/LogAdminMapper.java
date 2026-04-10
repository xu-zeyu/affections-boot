package com.affectionsboot.log.mapper;

import com.affectionsboot.auth.domain.model.Admin;
import com.affectionsboot.log.entity.LogEntity;
import com.affectionsboot.starter.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名: AdminMapper
 * 描述:
 * 作者: xuzeyu
 * 创建时间: 2026/1/2
 */
@Mapper
public interface LogAdminMapper extends BaseMapperX<Admin> {
}