package com.jinHan.gold.core.dashboard.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名: TrendPoint
 * 描述: 趋势图数据点（适配 @ant-design/charts）
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Data
public class TrendPoint {
    /**
     * 日期，格式 yyyy-MM-dd 或 yyyy-MM
     */
    private String date;

    /**
     * 数值（订单数量/金额）
     */
    private BigDecimal value;

    /**
     * 系列类型，多条折线时使用，如"收入"/"支出"
     */
    private String type;
}
