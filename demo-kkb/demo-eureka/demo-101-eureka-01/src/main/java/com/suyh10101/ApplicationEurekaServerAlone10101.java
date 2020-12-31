package com.suyh10101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationEurekaServerAlone10101 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEurekaServerAlone10101.class, args);
    }
}
