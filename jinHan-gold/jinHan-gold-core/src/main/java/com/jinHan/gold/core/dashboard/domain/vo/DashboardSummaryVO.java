package com.jinHan.gold.core.dashboard.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: DashboardSummaryVO
 * 描述: 欢迎页卡片统计数据（今日订单/收入/新增用户/库存商品）
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Data
public class DashboardSummaryVO {

    /**
     * 今日订单数
     */
    private Long todayOrderCount;

    /**
     * 今日订单总金额
     */
    private BigDecimal todayOrderAmount;

    /**
     * 今日收入（已确认的income_records汇总）
     */
    private BigDecimal todayIncome;

    /**
     * 今日新增用户数
     */
    private Long todayNewUsers;

    /**
     * 库存商品总数
     */
    private Long totalProducts;

    /**
     * 在售商品数
     */
    private Long onSaleProducts;

    /**
     * 已下架商品数
     */
    private Long offSaleProducts;
}
