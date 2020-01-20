package com.whstone.inspection.config;

import com.whstone.inspection.interceptor.UserAccessApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
			.addPathPatterns("/api/inspection/**");
		super.addInterceptors(registry);
	}
}
