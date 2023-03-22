package com.example.retailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class RetailerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailerApplication.class, args);
    }

}
