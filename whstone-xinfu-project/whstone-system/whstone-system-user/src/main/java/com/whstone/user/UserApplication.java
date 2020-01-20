package com.whstone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import tk.mybatis.spring.annotation.MapperScan;

import java.nio.charset.Charset;


@EnableEurekaClient
@EnableFeignClients("com.whstone.common.feign")
@MapperScan(basePackages={"com.whstone.user.mapper"})
@SpringBootApplication(
        exclude= {DataSourceAutoConfiguration.class},
        scanBasePackages={"com.whstone.user","com.whstone.common.feign"}
)
public class UserApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
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

