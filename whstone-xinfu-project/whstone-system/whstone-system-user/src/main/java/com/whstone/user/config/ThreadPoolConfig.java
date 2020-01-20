package com.whstone.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: wangzh
 * @Date: 2019/9/30
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public TaskExecutor createThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(500);
        //配置最大线程数
        executor.setMaxPoolSize(1000);
        //配置队列大小
        executor.setQueueCapacity(2000);
        executor.setThreadNamePrefix("cloud-");
        return executor;
    }
}
