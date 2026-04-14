package com.jinHan.gold.core.dashboard.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 类名: BusinessOverviewVO
 * 描述: 业务概览（总订单/总用户/活跃用户/订单趋势）
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Data
public class BusinessOverviewVO {

    /**
     * 总订单数
     */
    private Long totalOrders;

    /**
     * 总用户数
     */
    private Long totalUsers;

    /**
     * 活跃用户数（30天内有登录记录）
     */
    private Long activeUsers;

    /**
     * 待处理订单数（待审核-待发货）
     */
    private Long pendingOrders;

    /**
     * 订单趋势（最近30天，每天订单数量）
     * 适配 @ant-design/charts Line: [{ date, value }]
     */
    private List<TrendPoint> orderTrend;
}
