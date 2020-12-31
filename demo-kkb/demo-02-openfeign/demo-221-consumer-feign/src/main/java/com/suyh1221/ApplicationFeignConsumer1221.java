package com.suyh1221;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients  // 开启Feign客户端
@SpringBootApplication
public class ApplicationFeignConsumer1221 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationFeignConsumer1221.class, args);
    }
}
