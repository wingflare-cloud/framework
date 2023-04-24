package com.wingflare.gateway.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description 跨域配置
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.cors")
public class CorsProperties {

    private List<String> domains = new ArrayList<>();

    private String allowHeaders = "Origin,Content-Type,Cookie,X-CSRF-TOKEN,Accept,Authorization,X-XSRF-TOKEN,Token,X-Request-Id,Locale,business-system";

    private String exposeHeaders = "Authorization,authenticated,X-Response-Id";

    private String allowMethods = "GET,POST,PATCH,PUT,OPTIONS,DELETE";

    private String allowCredentials = "true";

    public String getAllowHeaders() {
        return allowHeaders;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    public String getExposeHeaders() {
        return exposeHeaders;
    }

    public void setExposeHeaders(String exposeHeaders) {
        this.exposeHeaders = exposeHeaders;
    }

    public String getAllowMethods() {
        return allowMethods;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public String getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(String allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
