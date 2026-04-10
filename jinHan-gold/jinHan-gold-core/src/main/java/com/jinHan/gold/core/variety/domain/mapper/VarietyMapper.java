package com.jinHan.gold.core.variety.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinHan.gold.core.variety.domain.model.Variety;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名: VarietyMapper
 * 描述: 宠物品种数据访问层
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Mapper
public interface VarietyMapper extends BaseMapper<Variety> {
}
