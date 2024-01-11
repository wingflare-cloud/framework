package com.wingflare.engine.websocket;


public interface ErrorCode {

    /**
     * 认证异常
     */
    String AUTH_EXPIRATION = "websocket.auth.expiration";

    /**
     * 终端注册失败
     */
    String REGISTER_TERMINAL_EXPIRATION = "websocket.register.expiration";

    /**
     * 频道绑定异常
     */
    String TOPIC_BIND_EXPIRATION = "websocket.topic.bind.expiration";

}
