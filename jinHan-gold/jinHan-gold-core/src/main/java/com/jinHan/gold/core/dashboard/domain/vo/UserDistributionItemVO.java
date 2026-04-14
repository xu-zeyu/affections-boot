package com.jinHan.gold.core.dashboard.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名: UserDistributionItemVO
 * 描述: 用户分布饼图数据项
 * 适配 @ant-design/charts Pie: [{ type, value }]
 * 作者: xuzeyu
 * 创建时间: 2026/4/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDistributionItemVO {

    /**
     * 用户状态标签，如"未认证"/"审核中"/"已认证"/"已拒绝"
     */
    private String type;

    /**
     * 该状态的用户数量
     */
    private Long value;
}
