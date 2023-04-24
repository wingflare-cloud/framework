package com.wingflare.lib.spring.configure.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InternalApiAuthProperties
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description 内网接口认证配置项
 */
@Configuration
@ConfigurationProperties(prefix = "system.internal")
public class SystemInternalProperties {

    /**
     * 安全流量验证穿透密钥，当客户端传入的密钥匹配时内部应用会忽略内网流量验证以及应用权限验证
     */
    private String secret;

    /**
     * 全局上下文信息
     */
    private Map<String, String> ctx = new HashMap<>();

    /**
     * secToken 当secToken匹配时可以跳过上下文穿透过滤机制
     */
    private String secToken = "bc1b924da304ada0063667e60fd874f5a8938184d7b7409b5feb5f347b45411c";

    /**
     * 服务之间互相调用时向后传递的服务级上下文(例如http的服务级上下文就是http header信息)，白名单中的上下文数据会在服务间穿透传递
     */
    private List<String> ctxWhitelist = new ArrayList<>();

    /**
     * 服务自动填充上下文，配置值会作为上下文数据自动在内网服务间传递
     */
    private Map<String, String> contexts = new HashMap<>();

    /**
     * 客户端上下文白名单，客户端传入的上下文信息在白名单内会作为服务级上下文穿透传递
     */
    private List<String> outCtxWhiteList = new ArrayList<>();


    public List<String> getOutCtxWhiteList() {
        return outCtxWhiteList;
    }

    public void setOutCtxWhiteList(List<String> outCtxWhite) {
        this.outCtxWhiteList = outCtxWhite;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Map<String, String> getCtx() {
        return ctx;
    }

    public void setCtx(Map<String, String> ctx) {
        this.ctx = ctx;
    }

    public String getSecToken() {
        return secToken;
    }

    public void setSecToken(String secToken) {
        this.secToken = secToken;
    }

    public List<String> getCtxWhitelist() {
        return ctxWhitelist;
    }

    public void setCtxWhitelist(List<String> ctxWhitelist) {
        this.ctxWhitelist = ctxWhitelist;
    }

    public Map<String, String> getContexts() {
        return contexts;
    }

    public void setContexts(Map<String, String> contexts) {
        this.contexts = contexts;
    }
}
