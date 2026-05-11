package com.jinHan.gold.core.merchant.domain.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名: MerchantSmsPwsLoginCommand
 * 描述: 商家端短信验证码密码登录命令
 * 作者: xuzeyu
 * 创建时间: 2026/05/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSmsPwsLoginCommand {
    @NotNull(message = "手机号码不能为空")
    private String phone;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "验证码不能为空")
    private String smsCode;
}
