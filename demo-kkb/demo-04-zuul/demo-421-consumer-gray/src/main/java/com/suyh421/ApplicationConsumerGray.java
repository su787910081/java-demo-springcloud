package com.suyh421;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ApplicationConsumerGray {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumerGray.class, args);
    }

}
