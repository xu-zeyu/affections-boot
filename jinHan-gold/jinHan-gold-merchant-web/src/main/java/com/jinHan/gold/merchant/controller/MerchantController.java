package com.jinHan.gold.merchant.controller;

import com.affectionsboot.common.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家端测试控制器
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("欢迎使用商家端系统");
    }
}
