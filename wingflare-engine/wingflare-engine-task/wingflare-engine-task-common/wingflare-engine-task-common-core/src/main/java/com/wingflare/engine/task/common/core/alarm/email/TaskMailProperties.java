package com.wingflare.engine.task.common.core.alarm.email;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: opensnail
 * @date : 2024-05-03
 * @since : sj_1.0.0
 */
@Configuration
@ConfigurationProperties(value = "snail-job.mail")
public class TaskMailProperties implements Serializable {

    /**
     * 过滤开关
     */
    private Boolean enabled;

    /**
     * SMTP服务器域名
     */
    private String host;

    /**
     * SMTP服务端口
     */
    private Integer port;

    /**
     * 是否需要用户名密码验证
     */
    private Boolean auth;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String pass;

    /**
     * 发送方，遵循RFC-822标准
     */
    private String from;

    /**
     * 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。它将纯文本连接升级为加密连接（TLS或SSL）， 而不是使用一个单独的加密通信端口。
     */
    private Boolean starttlsEnable;

    /**
     * 使用 SSL安全连接
     */
    private Boolean sslEnable;

    /**
     * SMTP超时时长，单位毫秒，缺省值不超时
     */
    private Long timeout;

    /**
     * Socket连接超时值，单位毫秒，缺省值不超时
     */
    private Long connectionTimeout;

    /**
     * 额外的会话属性
     * eg: mail.smtp.ssl.trust
     */
    private Map<String,String> properties;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean getStarttlsEnable() {
        return starttlsEnable;
    }

    public void setStarttlsEnable(Boolean starttlsEnable) {
        this.starttlsEnable = starttlsEnable;
    }

    public Boolean getSslEnable() {
        return sslEnable;
    }

    public void setSslEnable(Boolean sslEnable) {
        this.sslEnable = sslEnable;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
