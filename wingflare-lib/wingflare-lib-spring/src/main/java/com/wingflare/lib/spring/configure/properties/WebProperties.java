package com.wingflare.lib.spring.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "web")
public class WebProperties {

    private Set<String> trustedProxies;

    private String clientIdCtxName = "X-Client-Id";

    public Set<String> getTrustedProxies() {
        return trustedProxies;
    }

    public void setTrustedProxies(Set<String> trustedProxies) {
        this.trustedProxies = trustedProxies;
    }

    public String getClientIdCtxName() {
        return clientIdCtxName;
    }

    public void setClientIdCtxName(String clientIdCtxName) {
        this.clientIdCtxName = clientIdCtxName;
    }
}
