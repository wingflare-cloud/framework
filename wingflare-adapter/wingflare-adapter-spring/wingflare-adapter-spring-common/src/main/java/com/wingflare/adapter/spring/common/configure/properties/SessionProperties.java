package com.wingflare.adapter.spring.common.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "session")
public class SessionProperties {

    private String strictModel;

    private List<String> resetSessionIdUrls = new ArrayList<>();

    public String getStrictModel() {
        return strictModel;
    }

    public void setStrictModel(String strictModel) {
        this.strictModel = strictModel;
    }

    public List<String> getResetSessionIdUrls() {
        return resetSessionIdUrls;
    }

    public void setResetSessionIdUrls(List<String> resetSessionIdUrls) {
        this.resetSessionIdUrls = resetSessionIdUrls;
    }
}
