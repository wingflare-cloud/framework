package com.wingflare.facade.module.auth.bo;


import com.wingflare.api.security.annotation.Decrypt;

public class TokenIdBO {

    @Decrypt(type = "RSA")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
