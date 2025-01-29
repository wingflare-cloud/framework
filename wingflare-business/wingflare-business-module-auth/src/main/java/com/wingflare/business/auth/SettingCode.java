package com.wingflare.business.auth;

public interface SettingCode {

    /**
     * 用户登录token有效期，秒级
     */
    String TOKEN_EXPIRE_TIME = "TOKEN_EXPIRE_TIME";

    /**
     * 刷新token最大有效期，秒级为零不限制
     */
    String MAX_REFRESH_TOKEN_EXPIRE_TIME = "MAX_REFRESH_TOKEN_EXPIRE_TIME";

}
