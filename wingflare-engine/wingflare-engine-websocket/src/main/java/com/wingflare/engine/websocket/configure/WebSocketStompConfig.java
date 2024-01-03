package com.wingflare.engine.websocket.configure;


import com.wingflare.engine.websocket.configure.properties.WebSocketProperties;
import com.wingflare.engine.websocket.handler.ExceptionHandler;
import com.wingflare.engine.websocket.interceptor.WebSocketInterceptor;
import com.wingflare.engine.websocket.utils.WsUtil;
import com.wingflare.lib.core.utils.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import javax.annotation.Resource;


/**
 * Stomp配置文件
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    @Resource
    private WebSocketInterceptor webSocketInterceptor;

    @Resource
    private WebSocketProperties webSocketProperties;

    /**
     * 将 "/stomp" 注册为一个 STOMP 端点。这个路径与之前发送和接收消息的目的地路径有所
     * 不同。这是一个端点，客户端在订阅或发布消息到目的地路径前，要连接到该端点。
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.setErrorHandler(new ExceptionHandler())
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setSendTimeLimit(webSocketProperties.getSendTimeLimit())
                .setSendBufferSizeLimit(webSocketProperties.getSendBufferSizeLimit())
                .addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
                    @Override
                    public WebSocketHandler decorate(final WebSocketHandler handler) {
                        return new WebSocketHandlerDecorator(handler) {
                            @Override
                            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                                WsUtil.addSession(session);
                                super.afterConnectionEstablished(session);
                            }
                        };
                    }
                });
    }


    /**
     * 如果不重载它的话，将会自动配置一个简单的内存消息代理，用它来处理以"/topic"为前缀的消息
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        String[] destinationPrefixes = new String[]{"/t", "/q", "/st", "/sq", "/at", "/aq"};
        WebSocketProperties.StompProxy stompProxy = webSocketProperties.getProxy();

        if (webSocketProperties.getProxy() != null
                && stompProxy.isEnable()
                && StringUtil.isNotBlank(stompProxy.getHost())
                && stompProxy.getPort() > 0) {
            //基于RabbitMQ等第三方平台的 的STOMP消息代理
            StompBrokerRelayRegistration proxyReg = registry.enableStompBrokerRelay(destinationPrefixes)
                    .setRelayHost(stompProxy.getHost())
                    .setRelayPort(stompProxy.getPort());

            if (StringUtil.isNotBlank(stompProxy.getUserName())
                    && StringUtil.isNotBlank(stompProxy.getPassword())) {
                proxyReg.setClientLogin(stompProxy.getUserName())
                        .setSystemPasscode(stompProxy.getPassword());
            }

        } else {
            //基于内存的STOMP消息代理
            registry.enableSimpleBroker(destinationPrefixes);
        }

        registry.setPreservePublishOrder(webSocketProperties.isBrokerPreservePublishOrder());
        registry.setApplicationDestinationPrefixes("/s");
        registry.setUserDestinationPrefix("/u");
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }

}
