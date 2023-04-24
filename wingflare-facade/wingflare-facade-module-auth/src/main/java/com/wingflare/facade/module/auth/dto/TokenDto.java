package com.wingflare.facade.module.auth.dto;

/**
 * @ClassName TokenDto
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 登陆业务dto
 */
public class TokenDto {

    private String refreshToken;

    private Integer expiresIn;

    private Integer refreshExpiresIn;

    private String token;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
