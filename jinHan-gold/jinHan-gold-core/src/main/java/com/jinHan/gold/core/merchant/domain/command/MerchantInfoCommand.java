package com.jinHan.gold.core.merchant.domain.command;

import lombok.Data;

/**
 * 类名: MerchantInfoCommand
 * 描述: 商家信息查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
public class MerchantInfoCommand {
    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 用户ID
     */
    private Long userId;
}
