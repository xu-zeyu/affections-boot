package com.jinHan.gold.api.controller.login;

import com.affectionsboot.auth.domain.command.AdminSmsPwsLoginCommand;
import com.affectionsboot.auth.domain.command.UserSmsPwsLoginCommand;
import com.affectionsboot.auth.domain.handler.sms.AdminSmsPwsLoginHandler;
import com.affectionsboot.auth.domain.handler.sms.UserSmsPwsLoginHandler;
import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 类名: LoginController
 * 描述: 登录接口
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserSmsPwsLoginHandler userSmsPwsLoginHandler;

    @PostMapping("/sms")
    public Result<String> smsLogin(@RequestBody @Validated UserSmsPwsLoginCommand command) {
        return Result.success(userSmsPwsLoginHandler.handler(command));
    }
}
