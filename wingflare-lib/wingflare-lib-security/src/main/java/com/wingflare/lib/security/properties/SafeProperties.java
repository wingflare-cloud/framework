package com.wingflare.lib.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.safe")
public class SafeProperties {

    private List<String> urlPattern = new ArrayList<>();

    public List<String> getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(List<String> urlPattern) {
        this.urlPattern = urlPattern;
    }
}
