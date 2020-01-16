package com.whstone.demo.config.websocket;

import com.whstone.demo.handler.WebSocketHandler;
import com.whstone.demo.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: wangzh
 * @Date: 2020/1/12
 */
@EnableWebSocket
@Configuration
public class WebSocketAutoConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 指定处理器和路径
        registry.addHandler(new WebSocketHandler(), "/webSocket")
                // 指定自定义拦截器
                .addInterceptors(new WebSocketInterceptor())
                // 允许跨域
                .setAllowedOrigins("*");

    }
}
