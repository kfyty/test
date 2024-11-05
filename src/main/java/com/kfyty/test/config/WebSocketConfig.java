package com.kfyty.test.config;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/4 17:15
 * @email kfyty725@hotmail.com
 */
@Slf4j
@ServerEndpoint("/test/websocket")
public class WebSocketConfig {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        log.info("客户端已连接：{}", session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        if("ping".equals(message)) {
            this.sendMessage("pong", session);
            return;
        }
        log.info("收到客户端 {} 消息：{}", session, message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        log.info("客户端已断开连接：{}", session);
        this.closeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("客户端发生异常：{}", session, error);
        this.closeSession(session);
    }

    @SneakyThrows
    public void sendMessage(String msg, Session session) {
        if(session == null) {
            return;
        }
        synchronized (session) {
            if(session.isOpen()) {
                session.getBasicRemote().sendText(msg);
            } else {
                log.info("客户端未打开，将移除客户端：{}", session);
                this.closeSession(session);
            }
        }
    }

    public void closeSession(Session session) {
        try {
            if(session != null) {
                session.close();
            }
        } catch (Exception e) {
            log.error("close session error !", e);
        }
    }
}
