package com.whstone.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},scanBasePackages="com.whstone.monitor")
public class MonitorServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
    }

}

