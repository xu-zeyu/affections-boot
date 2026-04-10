package com.jinHan.gold.core.variety.domain.handler;

import com.affectionsboot.auth.domain.model.Admin;
import com.affectionsboot.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.variety.domain.command.VarietyPageCommand;
import com.jinHan.gold.core.variety.domain.mapper.VarietyMapper;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: VarietyQueryHandler
 * 描述: 品种查询处理器类
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@Component
public class VarietyQueryHandler {
    @Resource
    private VarietyMapper varietyMapper;

    /**
     * 根据ID查询品种
     *
     * @param id 品种ID
     * @return 品种实体
     */
    public Variety findById(Long id) {
        if (id == null) {
            throw new BusinessException("品种ID不能为空");
        }

        Variety variety = varietyMapper.selectById(id);
        if (variety == null) {
            throw new BusinessException("品种不存在");
        }

        return variety;
    }


    /**
     * 根据名称模糊查询品种
     *
     * @param name 名称
     * @return 品种实体
     */
    public List<Variety> findByName(String name) {
        // 构建查询条件
        LambdaQueryWrapper<Variety> queryWrapper = new LambdaQueryWrapper<>();

        // 商品名称模糊查询
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Variety::getName, name.trim());
        }

        return varietyMapper.selectList(queryWrapper);
    }

    /**
     * 查询所有品种
     *
     * @return 品种实体
     */
    public List<Variety> findAll() {

        return varietyMapper.selectList(null);
    }

    /**
     * 分页查询品种列表
     *
     * @param command 分页查询命令
     * @return 分页结果
     */
    public IPage<Variety> page(VarietyPageCommand command) {
        if (command == null) {
            throw new BusinessException("分页参数不能为空");
        }

        // 构建查询条件
        LambdaQueryWrapper<Variety> queryWrapper = new LambdaQueryWrapper<>();

        // 品种名称模糊查询
        if (command.getName() != null && !command.getName().trim().isEmpty()) {
            queryWrapper.like(Variety::getName, command.getName().trim());
        }

        // 状态筛选
        if (command.getStatus() != null) {
            queryWrapper.eq(Variety::getStatus, command.getStatus());
        }

        return varietyMapper.selectPage(new Page<>(command.getPage(),command.getSize()), queryWrapper);
    }
}
