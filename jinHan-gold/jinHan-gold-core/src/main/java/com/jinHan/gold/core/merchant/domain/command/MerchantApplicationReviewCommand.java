package com.jinHan.gold.core.merchant.domain.command;

import lombok.Data;

/**
 * 类名: MerchantApplicationReviewCommand
 * 描述: 审核商家申请命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/06
 */
@Data
public class MerchantApplicationReviewCommand {
    /**
     * 申请ID
     */
    private Long applicationId;

    /**
     * 是否通过
     */
    private Boolean approved;

    /**
     * 拒绝原因（拒绝时必填）
     */
    private String rejectReason;

    /**
     * 审核人ID
     */
    private Long reviewedBy;
}
