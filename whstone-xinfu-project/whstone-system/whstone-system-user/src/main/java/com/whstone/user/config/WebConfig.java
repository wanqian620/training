package com.whstone.user.config;

import com.whstone.user.common.interceptor.UserAccessApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public UserAccessApiInterceptor getUserAccessInterceptor(){
		return new UserAccessApiInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(getUserAccessInterceptor())
			.addPathPatterns("/api/user/**")
			.excludePathPatterns(
					"/api/user/login",
					"/api/user/logout",
					"/api/user/validate",
					"/api/user/findByMonitorId/**"
			);
		super.addInterceptors(registry);
	}
}
