package com.whstone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;


@EnableEurekaClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},scanBasePackages="com.whstone.user")
public class UserServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

    /**
     * 解决返回值乱码的问题
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

}

