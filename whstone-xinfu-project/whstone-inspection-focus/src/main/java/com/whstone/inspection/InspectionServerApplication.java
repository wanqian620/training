package com.whstone.inspection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients("com.whstone.common.feign")
@EnableHystrix
@SpringBootApplication(
        exclude= DataSourceAutoConfiguration.class,
        scanBasePackages={"com.whstone.inspection","com.whstone.common.feign"}
)
public class InspectionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InspectionServerApplication.class,args) ;
    }
}
