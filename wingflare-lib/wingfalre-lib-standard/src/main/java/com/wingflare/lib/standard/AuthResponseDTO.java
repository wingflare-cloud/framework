package com.wingflare.lib.standard;

import com.wingflare.lib.standard.model.UserAuth;

/**
 * 认证返回DTO
 */
public class AuthResponseDTO {

    private String error;

    private UserAuth userAuth;

    public String getError() {
        return error;
    }

    public AuthResponseDTO setError(String error) {
        this.error = error;
        return this;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public AuthResponseDTO setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
        return this;
    }

}
