package com.whstone.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RedisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisServerApplication.class, args);
    }
}
