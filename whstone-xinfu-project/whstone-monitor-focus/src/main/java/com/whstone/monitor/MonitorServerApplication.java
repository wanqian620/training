package com.whstone.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients("com.whstone.common.feign")
@EnableHystrixDashboard
@SpringBootApplication(
        exclude= DataSourceAutoConfiguration.class,
        scanBasePackages={"com.whstone.monitor","com.whstone.common.feign"}
)
public class MonitorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class,args) ;
    }
}

