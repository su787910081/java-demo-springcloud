package com.suyh411;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ApplicationZuulConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationZuulConsumer.class, args);
    }

}
