package com.jinHan.gold.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* jinHan管理后台模块启动类
*
* */
@SpringBootApplication(scanBasePackages = {"com.jinHan.gold","com.affectionsboot"})
@MapperScan({
        "com.jinHan.gold",
        "com.affectionsboot.auth"
})
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class, args);
    }
}