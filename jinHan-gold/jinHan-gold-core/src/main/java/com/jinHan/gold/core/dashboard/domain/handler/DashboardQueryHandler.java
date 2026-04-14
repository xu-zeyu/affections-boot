package com.jinHan.gold.core.dashboard.domain.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinHan.gold.core.dashboard.domain.vo.*;
import com.jinHan.gold.core.finance.domain.mapper.IncomeRecordMapper;
import com.jinHan.gold.core.finance.domain.model.IncomeRecord;
import com.jinHan.gold.core.finance.domain.model.IncomeRecordStatusEnum;
import com.jinHan.gold.core.order.domain.mapper.OrderMapper;
import com.jinHan.gold.core.order.domain.model.OrderStatusEnum;
import com.jinHan.gold.core.order.domain.model.Orders;
import com.jinHan.gold.core.product.domain.mapper.ProductMapper;
import com.jinHan.gold.core.product.domain.model.Product;
import com.jinHan.gold.core.users.domain.mapper.UserMapper;
import com.jinHan.gold.core.users.domain.model.Users;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类名: DashboardQueryHandler
 * 描述: 欢迎页统计数据处理器
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Component
public class DashboardQueryHandler {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private IncomeRecordMapper incomeRecordMapper;

    /**
     * 今日统计卡片数据：今日订单/收入/新增用户/库存商品
     */
    public DashboardSummaryVO querySummary() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        // 今日订单
        List<Orders> todayOrders = orderMapper.selectList(
                new LambdaQueryWrapper<Orders>()
                        .between(Orders::getCreatedTime, startOfDay, endOfDay)
                        .select(Orders::getTotalAmount)
        );
        BigDecimal todayOrderAmount = todayOrders.stream()
                .map(o -> o.getTotalAmount() != null ? o.getTotalAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 今日已确认收入
        List<IncomeRecord> todayIncomes = incomeRecordMapper.selectList(
                new LambdaQueryWrapper<IncomeRecord>()
                        .between(IncomeRecord::getCreatedTime, startOfDay, endOfDay)
                        .eq(IncomeRecord::getStatus, IncomeRecordStatusEnum.CONFIRMED)
                        .select(IncomeRecord::getMoney)
        );
        BigDecimal todayIncome = todayIncomes.stream()
                .map(r -> r.getMoney() != null ? r.getMoney() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 今日新增用户
        long todayNewUsers = userMapper.selectCount(
                new LambdaQueryWrapper<Users>()
                        .between(Users::getCreatedTime, startOfDay, endOfDay)
        );

        // 商品库存统计
        long totalProducts = productMapper.selectCount(null);
        long onSaleProducts = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().eq(Product::getStatus, 1)
        );

        DashboardSummaryVO vo = new DashboardSummaryVO();
        vo.setTodayOrderCount((long) todayOrders.size());
        vo.setTodayOrderAmount(todayOrderAmount);
        vo.setTodayIncome(todayIncome);
        vo.setTodayNewUsers(todayNewUsers);
        vo.setTotalProducts(totalProducts);
        vo.setOnSaleProducts(onSaleProducts);
        vo.setOffSaleProducts(totalProducts - onSaleProducts);
        return vo;
    }

    /**
     * 业务概览：总订单/总用户/活跃用户/最近30天订单趋势
     */
    public BusinessOverviewVO queryBusinessOverview() {
        // 总订单数
        long totalOrders = orderMapper.selectCount(null);

        // 总用户数
        long totalUsers = userMapper.selectCount(null);

        // 活跃用户数：30天内有登录
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        long activeUsers = userMapper.selectCount(
                new LambdaQueryWrapper<Users>()
                        .ge(Users::getLastLoginAt, thirtyDaysAgo)
        );

        // 待处理订单数（待审核-待发货：PAID、PENDING_CONFIRM、PROCESSING）
        long pendingOrders = orderMapper.selectCount(
                new LambdaQueryWrapper<Orders>()
                        .in(Orders::getStatus, OrderStatusEnum.PAID, OrderStatusEnum.PENDING_CONFIRM, OrderStatusEnum.PROCESSING)
        );

        // 本周7天订单趋势（周一到周日）
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate startDate = monday;
        LocalDate endDate = monday.plusDays(6);
        
        List<Orders> recentOrders = orderMapper.selectList(
                new LambdaQueryWrapper<Orders>()
                        .ge(Orders::getCreatedTime, startDate.atStartOfDay())
                        .le(Orders::getCreatedTime, endDate.atTime(LocalTime.MAX))
                        .select(Orders::getCreatedTime)
        );
        Map<LocalDate, Long> countByDate = recentOrders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getCreatedTime().toLocalDate(),
                        Collectors.counting()
                ));

        List<TrendPoint> orderTrend = new ArrayList<>();
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        for (int i = 0; i <= 6; i++) {
            LocalDate date = monday.plusDays(i);
            TrendPoint point = new TrendPoint();
            point.setDate(weekDays[i]);
            point.setValue(new BigDecimal(countByDate.getOrDefault(date, 0L)));
            orderTrend.add(point);
        }

        BusinessOverviewVO vo = new BusinessOverviewVO();
        vo.setTotalOrders(totalOrders);
        vo.setTotalUsers(totalUsers);
        vo.setActiveUsers(activeUsers);
        vo.setPendingOrders(pendingOrders);
        vo.setOrderTrend(orderTrend);
        return vo;
    }

    /**
     * 经营数据：本月利润/本月支出/库存率/最近4个月收支趋势
     */
    public OperationDataVO queryOperationData() {
        LocalDate now = LocalDate.now();
        LocalDateTime monthStart = now.withDayOfMonth(1).atStartOfDay();
        LocalDateTime monthEnd = now.atTime(LocalTime.MAX);

        // 本月已确认收入
        List<IncomeRecord> monthlyIncomes = incomeRecordMapper.selectList(
                new LambdaQueryWrapper<IncomeRecord>()
                        .between(IncomeRecord::getCreatedTime, monthStart, monthEnd)
                        .eq(IncomeRecord::getStatus, IncomeRecordStatusEnum.CONFIRMED)
                        .select(IncomeRecord::getMoney)
        );
        BigDecimal monthlyProfit = monthlyIncomes.stream()
                .map(r -> r.getMoney() != null ? r.getMoney() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 库存率 = 在售商品 / 总商品
        long totalProducts = productMapper.selectCount(null);
        long onSaleProducts = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().eq(Product::getStatus, 1)
        );
        BigDecimal inventoryRate = totalProducts > 0
                ? new BigDecimal(onSaleProducts).divide(new BigDecimal(totalProducts), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        // 最近4个月收支趋势
        LocalDate fourMonthsAgo = now.minusMonths(3).withDayOfMonth(1);
        List<IncomeRecord> allIncomes = incomeRecordMapper.selectList(
                new LambdaQueryWrapper<IncomeRecord>()
                        .ge(IncomeRecord::getCreatedTime, fourMonthsAgo.atStartOfDay())
                        .eq(IncomeRecord::getStatus, IncomeRecordStatusEnum.CONFIRMED)
                        .select(IncomeRecord::getMoney, IncomeRecord::getCreatedTime)
        );

        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, BigDecimal> incomeByMonth = new HashMap<>();
        for (IncomeRecord record : allIncomes) {
            String month = record.getCreatedTime().format(monthFormatter);
            incomeByMonth.merge(month,
                    record.getMoney() != null ? record.getMoney() : BigDecimal.ZERO,
                    BigDecimal::add);
        }

        List<TrendPoint> incomeTrend = new ArrayList<>();
        for (int i = 3; i >= 0; i--) {
            String month = now.minusMonths(i).format(monthFormatter);
            BigDecimal income = incomeByMonth.getOrDefault(month, BigDecimal.ZERO);

            TrendPoint incomePoint = new TrendPoint();
            incomePoint.setDate(month);
            incomePoint.setValue(income);
            incomePoint.setType("收入");
            incomeTrend.add(incomePoint);

            // 暂无支出数据，默认为0（待后续添加支出记录表后对接）
            TrendPoint expensePoint = new TrendPoint();
            expensePoint.setDate(month);
            expensePoint.setValue(BigDecimal.ZERO);
            expensePoint.setType("支出");
            incomeTrend.add(expensePoint);
        }

        OperationDataVO vo = new OperationDataVO();
        vo.setMonthlyProfit(monthlyProfit);
        vo.setMonthlyExpense(BigDecimal.ZERO);
        vo.setInventoryRate(inventoryRate);
        vo.setIncomeTrend(incomeTrend);
        return vo;
    }

    /**
     * 用户分布饼图数据（按认证状态分组）
     */
    public List<UserDistributionItemVO> queryUserDistribution() {
        Map<UsersStatusEnum, String> statusLabels = new LinkedHashMap<>();
        statusLabels.put(UsersStatusEnum.UNAUTHENTICATED, "未认证");
        statusLabels.put(UsersStatusEnum.UNDER_REVIEW, "审核中");
        statusLabels.put(UsersStatusEnum.COMPLETED, "已认证");
        statusLabels.put(UsersStatusEnum.REJECTED, "已拒绝");

        List<UserDistributionItemVO> result = new ArrayList<>();
        for (Map.Entry<UsersStatusEnum, String> entry : statusLabels.entrySet()) {
            long count = userMapper.selectCount(
                    new LambdaQueryWrapper<Users>().eq(Users::getStatus, entry.getKey())
            );
            result.add(new UserDistributionItemVO(entry.getValue(), count));
        }
        return result;
    }
}
