package com.jinHan.gold.core.merchant.domain.command;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.merchant.domain.model.Merchant;
import com.jinHan.gold.core.merchant.domain.model.MerchantStatusEnum;
import lombok.Data;

/**
 * 类名: MerchantPageCommand
 * 描述: 商家分页查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
public class MerchantPageCommand {
    /**
     * 分页对象
     */
    private Page<Merchant> page;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商家状态
     */
    private MerchantStatusEnum status;
}
