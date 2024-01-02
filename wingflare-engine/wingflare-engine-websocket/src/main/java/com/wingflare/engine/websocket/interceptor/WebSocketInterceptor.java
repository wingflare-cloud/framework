package com.wingflare.engine.websocket.interceptor;


import com.wingflare.engine.websocket.ErrorCode;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.spring.configure.properties.SystemInternalProperties;
import com.wingflare.lib.standard.utils.CtxUtil;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    @Resource
    private SystemInternalProperties systemInternalProperties;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        Assert.isTrue(accessor != null, ErrorCode.AUTH_EXPIRATION);
        CtxUtil.cxtSetter(systemInternalProperties.getCtx(), (key) -> accessor.getFirstNativeHeader(key));
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
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        ContextHolder.remove();
    }

}
