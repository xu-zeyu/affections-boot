package com.jinHan.gold.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名: UserWebApplication
 * 描述: 客户端应用启动类
 * 作者: xuzeyu
 * 创建时间: 2025/01/09
 */
@SpringBootApplication(scanBasePackages = {"com.jinHan.gold","com.affectionsboot"})
@MapperScan({
        "com.jinHan.gold",
        "com.affectionsboot.auth"
})
public class UserWebApplication {
    public static void main(String[] args) {
        // 加载 .env 文件
        Dotenv dotenv = Dotenv.configure().load();
        // 将环境变量设置到系统环境中
        dotenv.entries().forEach(entry -> {
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
        SpringApplication.run(UserWebApplication.class, args);
    }
}
