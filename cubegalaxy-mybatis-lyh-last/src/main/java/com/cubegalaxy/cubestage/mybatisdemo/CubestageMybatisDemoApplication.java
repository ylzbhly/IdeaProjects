package com.cubegalaxy.cubestage.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cubegalaxy.cubestage.mybatisdemo.mapper")
public class CubestageMybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CubestageMybatisDemoApplication.class,args);
    }
}
