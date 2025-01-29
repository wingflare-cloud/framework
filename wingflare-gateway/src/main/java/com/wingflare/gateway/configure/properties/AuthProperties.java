package com.wingflare.gateway.configure.properties;

import com.wingflare.lib.standard.Ctx;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2021/12/17}
 * @description
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.auth")
public class AuthProperties
{
    /**
     * 认证信息放行白名单配置
     */
    private List<String> whites = new ArrayList<>();

    private String authenticationPrefix = Ctx.AUTHENTICATION_PREFIX;

    private String refreshTokenUrl;

    public List<String> getWhites()
    {
        return whites;
    }

    public void setWhites(List<String> whites)
    {
        this.whites = whites;
    }

    public String getAuthenticationPrefix() {
        return authenticationPrefix;
    }

    public void setAuthenticationPrefix(String authenticationPrefix) {
        this.authenticationPrefix = authenticationPrefix;
    }

    public String getRefreshTokenUrl() {
        return refreshTokenUrl;
    }

    public void setRefreshTokenUrl(String refreshTokenUrl) {
        this.refreshTokenUrl = refreshTokenUrl;
    }
}
