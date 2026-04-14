package com.jinHan.gold.core.dashboard.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 类名: OperationDataVO
 * 描述: 经营数据（本月利润/本月支出/库存率/收支趋势）
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Data
public class OperationDataVO {

    /**
     * 本月利润（本月已确认收入总额）
     */
    private BigDecimal monthlyProfit;

    /**
     * 本月支出（暂无支出表，默认为0）
     */
    private BigDecimal monthlyExpense;

    /**
     * 库存率 = 在售商品数 / 总商品数，保留4位小数
     */
    private BigDecimal inventoryRate;

    /**
     * 收支趋势（最近12个月）
     * 适配 @ant-design/charts Line 多系列: [{ date, value, type }]
     * type 取值："收入" / "支出"
     */
    private List<TrendPoint> incomeTrend;
}
