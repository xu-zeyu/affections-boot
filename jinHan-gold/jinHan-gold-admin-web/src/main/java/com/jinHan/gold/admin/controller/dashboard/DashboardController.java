package com.jinHan.gold.admin.controller.dashboard;

import com.affectionsboot.common.model.Result;
import com.jinHan.gold.core.dashboard.domain.handler.DashboardQueryHandler;
import com.jinHan.gold.core.dashboard.domain.vo.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类名: DashboardController
 * 描述: 欢迎页统计接口
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private DashboardQueryHandler dashboardQueryHandler;

    /**
     * 今日统计卡片数据
     * 返回：今日订单数/今日订单金额/今日收入/今日新增用户/库存商品统计
     */
    @GetMapping("/summary")
    public Result<DashboardSummaryVO> summary() {
        return Result.success(dashboardQueryHandler.querySummary());
    }

    /**
     * 业务概览
     * 返回：总订单数/总用户数/活跃用户数/一周订单趋势（折线图数据）
     */
    @GetMapping("/business-overview")
    public Result<BusinessOverviewVO> businessOverview() {
        return Result.success(dashboardQueryHandler.queryBusinessOverview());
    }

    /**
     * 经营数据
     * 返回：本月利润/本月支出/库存率/最近4个月收支趋势（折线图数据）
     */
    @GetMapping("/operation-data")
    public Result<OperationDataVO> operationData() {
        return Result.success(dashboardQueryHandler.queryOperationData());
    }

    /**
     * 用户分布报表
     * 返回：按认证状态分布的用户数量（饼图数据）
     * 格式：[{ type: "未认证", value: 100 }, ...]
     */
    @GetMapping("/user-distribution")
    public Result<List<UserDistributionItemVO>> userDistribution() {
        return Result.success(dashboardQueryHandler.queryUserDistribution());
    }

    

}
