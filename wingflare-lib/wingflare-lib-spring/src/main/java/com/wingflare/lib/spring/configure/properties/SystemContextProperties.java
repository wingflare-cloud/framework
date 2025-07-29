package com.wingflare.lib.spring.configure.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SystemContextProperties
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description 内网接口认证配置项
 */
@Configuration
@ConfigurationProperties(prefix = "system.context")
public class SystemContextProperties {

    /**
     * 安全流量验证穿透密钥，当客户端传入的密钥匹配时内部应用会忽略内网流量验证以及应用权限验证
     */
    private String passSecret;

    /**
     * secToken上下文key
     */
    private String secTokenCtxKey;

    /**
     * 全局上下文信息
     */
    private Map<String, String> globalCtx = new HashMap<>();

    /**
     * secToken 当secToken匹配时可以跳过上下文穿透过滤机制
     */
    private String secToken;

    /**
     * 服务之间互相调用时向后传递的服务级上下文(例如http的服务级上下文就是http header信息)
     */
    private List<String> transferCtx = new ArrayList<>();

    /**
     * 服务自动填充上下文，配置值会作为上下文数据自动在内网服务间传递
     */
    private Map<String, String> autoCtx = new HashMap<>();

    /**
     * 客户端上下文白名单，客户端传入的上下文信息在白名单内会作为服务级上下文穿透传递
     */
    private List<String> clientTransferCtx = new ArrayList<>();

    /**
     * 用户登录信息上下文
     */
    private String userAuthCtx = "authUser";

    public String getPassSecret() {
        return passSecret;
    }

    public void setPassSecret(String passSecret) {
        this.passSecret = passSecret;
    }

    public String getSecTokenCtxKey() {
        return secTokenCtxKey;
    }

    public void setSecTokenCtxKey(String secTokenCtxKey) {
        this.secTokenCtxKey = secTokenCtxKey;
    }

    public Map<String, String> getGlobalCtx() {
        return globalCtx;
    }

    public void setGlobalCtx(Map<String, String> globalCtx) {
        this.globalCtx = globalCtx;
    }

    public String getSecToken() {
        return secToken;
    }

    public void setSecToken(String secToken) {
        this.secToken = secToken;
    }

    public List<String> getTransferCtx() {
        return transferCtx;
    }

    public void setTransferCtx(List<String> transferCtx) {
        this.transferCtx = transferCtx;
    }

    public Map<String, String> getAutoCtx() {
        return autoCtx;
    }

    public void setAutoCtx(Map<String, String> autoCtx) {
        this.autoCtx = autoCtx;
    }

    public List<String> getClientTransferCtx() {
        return clientTransferCtx;
    }

    public void setClientTransferCtx(List<String> clientTransferCtx) {
        this.clientTransferCtx = clientTransferCtx;
    }

    public String getUserAuthCtx() {
        return userAuthCtx;
    }

    public void setUserAuthCtx(String userAuthCtx) {
        this.userAuthCtx = userAuthCtx;
    }
}
