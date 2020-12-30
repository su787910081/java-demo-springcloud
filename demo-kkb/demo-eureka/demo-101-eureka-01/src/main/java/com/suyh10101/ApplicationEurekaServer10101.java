package com.suyh10101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationEurekaServer10101 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEurekaServer10101.class, args);
    }
}
