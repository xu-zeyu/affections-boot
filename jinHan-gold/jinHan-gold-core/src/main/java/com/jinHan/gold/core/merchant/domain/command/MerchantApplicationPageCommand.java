package com.jinHan.gold.core.merchant.domain.command;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplication;
import com.jinHan.gold.core.merchant.domain.model.MerchantApplicationStatusEnum;
import lombok.Data;

/**
 * 类名: MerchantApplicationPageCommand
 * 描述: 商家申请分页查询命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
public class MerchantApplicationPageCommand {
    /**
     * 分页对象
     */
    private Page<MerchantApplication> page;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 申请状态
     */
    private MerchantApplicationStatusEnum status;
}
