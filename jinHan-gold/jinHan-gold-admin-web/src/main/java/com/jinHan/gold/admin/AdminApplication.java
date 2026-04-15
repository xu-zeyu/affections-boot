package com.jinHan.gold.admin;

import io.github.cdimascio.dotenv.Dotenv;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
* jinHan管理后台模块启动类
*
* */
@SpringBootApplication(scanBasePackages = {"com.jinHan.gold","com.affectionsboot"})
@EnableScheduling
@MapperScan({
        "com.jinHan.gold",
        "com.affectionsboot.auth"
})
public class AdminApplication {
    public static void main(String[] args) {
        // 从当前模块目录(jinHan-gold-admin-web)加载 .env 文件
        Dotenv dotenv = Dotenv.configure().directory("jinHan-gold/jinHan-gold-admin-web").load();
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
