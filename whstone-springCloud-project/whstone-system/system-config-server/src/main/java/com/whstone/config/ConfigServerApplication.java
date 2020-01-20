package com.whstone.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication(
		exclude= DataSourceAutoConfiguration.class,
		scanBasePackages={"com.whstone.config"}
)
public class ConfigServerApplication {

	public static void main(String[] args){
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
