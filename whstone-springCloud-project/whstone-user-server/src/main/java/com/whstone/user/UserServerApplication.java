package com.whstone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class,scanBasePackages={"com.whstone.user"})
public class UserServerApplication {

	public static void main(String[] args){
		SpringApplication.run(UserServerApplication.class, args);
	}
}
