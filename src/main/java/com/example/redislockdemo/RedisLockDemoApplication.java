package com.example.redislockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisLockDemoApplication {
    /*添加备注*/
    public static void main(String[] args) {
        System.out.println("修复bug001");
        SpringApplication.run(RedisLockDemoApplication.class, args);
    }

}
