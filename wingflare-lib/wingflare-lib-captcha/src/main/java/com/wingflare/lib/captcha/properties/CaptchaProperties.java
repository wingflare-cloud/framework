package com.wingflare.lib.captcha.properties;


import com.wingflare.lib.captcha.CaptchaConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证码设置
 */
@Configuration
@ConfigurationProperties(prefix = "security.captcha")
public class CaptchaProperties {

    private String storagePrefix = "wf:captcha:";

    private boolean enablePatternsCache = true;

    private String secret = "wingflare-cloud-captcha-secret";

    private long securityTimeOut = 180;

    private List<CaptchaConfig> configures = new ArrayList<>();

    public String getStoragePrefix() {
        return storagePrefix;
    }

    public void setStoragePrefix(String storagePrefix) {
        this.storagePrefix = storagePrefix;
    }

    public boolean isEnablePatternsCache() {
        return enablePatternsCache;
    }

    public void setEnablePatternsCache(boolean enablePatternsCache) {
        this.enablePatternsCache = enablePatternsCache;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getSecurityTimeOut() {
        return securityTimeOut;
    }

    public void setSecurityTimeOut(long securityTimeOut) {
        this.securityTimeOut = securityTimeOut;
    }

    public List<CaptchaConfig> getConfigures() {
        return configures;
    }

    public void setConfigures(List<CaptchaConfig> configures) {
        this.configures = configures;
    }
}
