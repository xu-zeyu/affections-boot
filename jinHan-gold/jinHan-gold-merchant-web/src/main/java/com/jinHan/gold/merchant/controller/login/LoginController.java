package com.jinHan.gold.merchant.controller.login;

import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.jinHan.gold.core.merchant.domain.command.MerchantSmsPwsLoginCommand;
import com.jinHan.gold.core.merchant.domain.handler.MerchantSmsPwsLoginHandler;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: LoginController
 * 描述: 商家端登录接口
 * 作者: xuzeyu
 * 创建时间: 2026/05/11
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private MerchantSmsPwsLoginHandler merchantSmsPwsLoginHandler;

    @Log(value = "商家端短信验证码登录", operationType = "MERCHANT_LOGIN_SMS")
    @PostMapping("/sms")
    public Result<String> smsLogin(@RequestBody @Validated MerchantSmsPwsLoginCommand command) {
        return Result.success(merchantSmsPwsLoginHandler.handler(command));
    }
}
