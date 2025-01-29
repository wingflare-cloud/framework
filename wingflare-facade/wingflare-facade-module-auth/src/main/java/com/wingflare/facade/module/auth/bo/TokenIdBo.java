package com.wingflare.facade.module.auth.bo;

import com.wingflare.lib.standard.annotation.security.Decrypt;

public class TokenIdBo {

    @Decrypt(type = "RSA")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
