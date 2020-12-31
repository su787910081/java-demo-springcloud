package com.suyh10101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationEurekaServerCluster10102 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEurekaServerCluster10102.class, args);
    }
}
