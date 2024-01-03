package com.wingflare.engine.websocket.interceptor;


import com.wingflare.engine.websocket.ErrorCode;
import com.wingflare.engine.websocket.configure.properties.WebSocketProperties;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.context.ContextHolder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import com.wingflare.abstraction.engine.websocket.WsAuthServerInterface;
import com.wingflare.facade.engine.websocket.bo.Terminal;

import javax.annotation.Resource;


@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    @Resource
    private WebSocketProperties webSocketProperties;

    @Resource
    private WsAuthServerInterface wsAuthServer;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        Assert.isTrue(accessor != null, ErrorCode.AUTH_EXPIRATION);
        Assert.isTrue(accessor.getUser() != null, ErrorCode.AUTH_EXPIRATION);

        if (!webSocketProperties.getTerminalWhiteList().contains(accessor.getUser().getName())) {
            Terminal terminal = wsAuthServer.getTerminalByUserName(accessor.getUser().getName());
            Assert.isTrue(terminal != null, ErrorCode.AUTH_EXPIRATION);
        }

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        if (headerAccessor.getCommand() == null) return;

        if (headerAccessor.getCommand() == StompCommand.DISCONNECT) {
        }
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        ContextHolder.remove();
    }

}
