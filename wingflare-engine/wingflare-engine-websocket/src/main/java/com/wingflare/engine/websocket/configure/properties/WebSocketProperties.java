package com.wingflare.engine.websocket.configure.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2024/01/03}
 * @description websocket设置
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties {

    /**
     * websocket发送消息允许的最大响应时间，单位毫秒。0表示不限制
     */
    private int sendTimeLimit = 15 * 1000;

    /**
     * WebSocket 会话发送消息时要缓冲的最大数据量，或在使用 SockJS 后备选项时配置 HTTP 响应的最大数据量。
     * 一般来说，WebSocket 服务器期望一次从单个线程发送到单个 WebSocket 会话的消息。使用@EnableWebSocketMessageBroker配置时会自动保证这一点。
     * 如果消息发送很慢，或者至少慢于消息发送的速率，则后续消息将被缓冲，直到达到sendTimeLimit或sendBufferSizeLimit ，
     * 此时会话状态将被清除，并尝试关闭会话。
     * 请注意，关闭会话可能无法成功实际关闭物理套接字，并且还可能挂起。
     * 尤其是在使用阻塞 IO（例如 Tomcat 7 上默认配置的 Tomcat 中的 BIO 连接器）时尤其如此。
     * 因此，建议确保服务器使用非阻塞 IO（例如 Tomcat 8 上默认使用的 Tomcat NIO 连接器）。
     * 您必须使用阻塞 IO 考虑自定义操作系统级 TCP 设置，例如 Linux 上的/proc/sys/net/ipv4/tcp_retries2 。
     * 0表示不限制
     */
    private int sendBufferSizeLimit = 512 * 1024;

    /**
     * 终端白名单，白名单内的终端可以任意连接当前ws服务
     */
    private List<String> terminalWhiteList = new ArrayList<>();

    /**
     * 白名单终端秘钥
     */
    private String whiteListSecretKey = "wingflare-could-ws-secret";

    /**
     * sid盐值
     */
    private String sidSalt = "wingflare-could-ws-salt";

    /**
     * broker消息有序发布
     */
    private boolean brokerPreservePublishOrder = false;

    /**
     * client消息有序发布
     */
    private boolean clientPreservePublishOrder = false;

    /**
     * 消息代理设置
     */
    private StompProxy proxy;


    public int getSendTimeLimit() {
        return sendTimeLimit;
    }

    public void setSendTimeLimit(int sendTimeLimit) {
        this.sendTimeLimit = sendTimeLimit;
    }

    public int getSendBufferSizeLimit() {
        return sendBufferSizeLimit;
    }

    public void setSendBufferSizeLimit(int sendBufferSizeLimit) {
        this.sendBufferSizeLimit = sendBufferSizeLimit;
    }

    public List<String> getTerminalWhiteList() {
        return terminalWhiteList;
    }

    public void setTerminalWhiteList(List<String> terminalWhiteList) {
        this.terminalWhiteList = terminalWhiteList;
    }

    public String getWhiteListSecretKey() {
        return whiteListSecretKey;
    }

    public void setWhiteListSecretKey(String whiteListSecretKey) {
        this.whiteListSecretKey = whiteListSecretKey;
    }

    public String getSidSalt() {
        return sidSalt;
    }

    public void setSidSalt(String sidSalt) {
        this.sidSalt = sidSalt;
    }

    public boolean isBrokerPreservePublishOrder() {
        return brokerPreservePublishOrder;
    }

    public void setBrokerPreservePublishOrder(boolean brokerPreservePublishOrder) {
        this.brokerPreservePublishOrder = brokerPreservePublishOrder;
    }

    public boolean isClientPreservePublishOrder() {
        return clientPreservePublishOrder;
    }

    public void setClientPreservePublishOrder(boolean clientPreservePublishOrder) {
        this.clientPreservePublishOrder = clientPreservePublishOrder;
    }

    public StompProxy getProxy() {
        return proxy;
    }

    public void setProxy(StompProxy proxy) {
        this.proxy = proxy;
    }

    public static class StompProxy {

        /**
         * 是否开启消息代理
         */
        private boolean enable = true;

        /**
         * 主机
         */
        private String host;

        /**
         * 端口
         */
        private int port = 0;

        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String password;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
