package com.wingflare.gateway.configure.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description access log设置
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "accesslog")
public class AccessLogProperties {

    /**打印body部分的response content-type类型*/
    private List<String> respBodyContentTypes = new ArrayList<String>(){{
        add("application/json");
        add("application/xml");
        add("text/json");
        add("text/xml");
        add("text/plain");
    }};

    /**是否启禁用access log*/
    private Boolean enable = false;

    /**信任代理层级*/
    private Integer trustedIndex = 1;

    /**信需要日志记录的请求头信息*/
    private List<String> loggerReqHeaders = new ArrayList<>();


    public List<String> getRespBodyContentTypes() {
        return respBodyContentTypes;
    }

    public void setRespBodyContentTypes(List<String> respBodyContentTypes) {
        this.respBodyContentTypes = respBodyContentTypes;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getTrustedIndex() {
        return trustedIndex;
    }

    public void setTrustedIndex(Integer trustedIndex) {
        this.trustedIndex = trustedIndex;
    }

    public List<String> getLoggerReqHeaders() {
        return loggerReqHeaders;
    }

    public void setLoggerReqHeaders(List<String> loggerReqHeaders) {
        this.loggerReqHeaders = loggerReqHeaders;
    }
}
