package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ljc
 */
@SpringBootApplication
public class Application {
    /**
     * com.example.Application.main():
     * 主程序入口
     * @author ljc
     * @date 2022/4/20~12:00
     * @param args 参数列表
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}