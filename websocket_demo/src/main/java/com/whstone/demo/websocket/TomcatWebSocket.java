package com.whstone.demo.websocket;

import com.whstone.demo.config.websocket.WebSocketHttpConfig;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangzh
 * @Date: 2019/12/6
 */
@Component
@ServerEndpoint(value = "/tomcat/webSocket/{name}", configurator = WebSocketHttpConfig.class)
public class TomcatWebSocket {

    /**
     * 保存每个客户端连接会话对象
     */
    private static Map<String, TomcatWebSocket> clients = new ConcurrentHashMap<>();

    /**
     * 会话对象
     */
    private Session session;

    private String name;

    /**
     * 连接建立成功回调方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String name, EndpointConfig config) {
        //获取请求session
        HandshakeRequest request = (HandshakeRequest) config.getUserProperties().get(HandshakeRequest.class.getName());
        HttpSession httpSession = (HttpSession) request.getHttpSession();

        //TODO do something


        this.session = session;
        this.name = name;
        clients.put(name, this);

        clients.forEach((k, v) -> {
            sendMessage(name + "与服务端连接成功", v.session);
        });

    }

    /**
     * 连接关闭回调方法
     */
    @OnClose
    public void onClose() {
        clients.remove(name);
    }

    /**
     * 收到当前客户端消息后的回调方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        clients.forEach((k, v) ->
                sendMessage(message, v.session)
        );
    }

    /**
     * 发送错误的回调方法
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        clients.remove(name);
    }

    /**
     * 通过连接会话对象发送数据
     *
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message, Session session) {
        session.getAsyncRemote().sendText(message);
    }

}
