package com.jinHan.gold.admin;

import io.github.cdimascio.dotenv.Dotenv;
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
    public static void main(String[] args) {
        // 加载 .env 文件
        Dotenv dotenv = Dotenv.configure().load();
        // 将环境变量设置到系统环境中
        dotenv.entries().forEach(entry -> {
            System.out.println(entry);
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
        SpringApplication.run(AdminApplication.class, args);
    }
}