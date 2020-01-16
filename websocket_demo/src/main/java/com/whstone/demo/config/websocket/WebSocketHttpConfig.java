package com.whstone.demo.config.websocket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @Author: wangzh
 * @Date: 2019/12/11
 */
public class WebSocketHttpConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //将webSocket连接request放入节点中
        sec.getUserProperties().put(HandshakeRequest.class.getName(), request);
    }
}
