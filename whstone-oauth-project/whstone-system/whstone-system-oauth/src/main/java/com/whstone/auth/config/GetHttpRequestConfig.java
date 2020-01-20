package com.whstone.auth.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @Author: Mr.Gx
 * @Date: 2019/12/3
 */
public class GetHttpRequestConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //将webSocket连接request放入节点中
        sec.getUserProperties().put(HandshakeRequest.class.getName(), request);
    }
}
