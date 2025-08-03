package com.wingflare.lib.spring.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "session")
public class SessionProperties {

    private boolean enabled;

    private String strictModel;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getStrictModel() {
        return strictModel;
    }

    public void setStrictModel(String strictModel) {
        this.strictModel = strictModel;
    }
}
