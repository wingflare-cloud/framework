package com.wingflare.engine.task.server.web.socket;


import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.server.common.enums.WebSocketSceneEnum;
import com.wingflare.engine.task.server.web.config.WebSocketConfigurator;
import com.wingflare.engine.task.server.web.model.event.WsRequestEvent;
import com.wingflare.engine.task.server.web.model.event.WsSendEvent;
import com.wingflare.lib.container.Container;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.socket
 * @Project：wingflare-task
 * @Date：2025/3/4 16:31
 * @Filename：LogServer
 * @since 1.5.0
 */
@Component
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfigurator.class)
public class LogServer {

    // 缓存session
    public static final ConcurrentHashMap<String, Session> USER_SESSION = new ConcurrentHashMap<>();

    private static final Logger log = LoggerFactory.getLogger(LogServer.class);

    @EventListener
    public void sendMessage(WsSendEvent message) throws IOException {
        Session session = USER_SESSION.get(message.getSid());
        if (Objects.isNull(session)) {
            log.warn("{} ws session not exist", message.getSid());
            return;
        }

        if (session.isOpen()) {
            synchronized (session) {
                session.getBasicRemote().sendText(message.getMessage());
            }
        }

    }

    @OnOpen
    public void onOpen(Session session) {
        Map<String, Object> userProperties = session.getUserProperties();
        String sid = (String) userProperties.get(WebSocketConfigurator.SID);
        USER_SESSION.put(sid, session);
        log.info("sid:[{}] websocket started", sid);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 接收请求
        Map<String, Object> userProperties = session.getUserProperties();
        String sid = (String) userProperties.get(WebSocketConfigurator.SID);
        String scene = (String) userProperties.get(WebSocketConfigurator.SCENE);
        WsRequestEvent requestVO = new WsRequestEvent(this);
        requestVO.setSceneEnum(WebSocketSceneEnum.valueOf(scene));
        requestVO.setMessage(message);
        requestVO.setSid(sid);
        Container.get(EventPublisher.class).publishEvent(requestVO);
    }


    /**
     * 连接关闭时触发
     */
    @OnClose
    public void onClose(Session session) {

        Map<String, Object> userProperties = session.getUserProperties();
        String sid = (String) userProperties.get(WebSocketConfigurator.SID);

        log.info("sid:[{}] websocket closed", sid);
        USER_SESSION.remove(sid);
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Error occurred {}", throwable);
    }

}
