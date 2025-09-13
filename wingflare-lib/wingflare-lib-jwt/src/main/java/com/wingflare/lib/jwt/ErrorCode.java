package com.wingflare.lib.jwt;

public interface ErrorCode {

    /**
     * 令牌过期或错误
     */
    String TOKEN_EXPIRATION_OR_ERROR = "jwt.token.expirationOrError";

    /**
     * 登录状态已过期
     */
    String TOKEN_LOGIN_EXPIRATION = "jwt.login.expiration";

    /**
     * token过期
     */
    String TOKEN_EXPIRATION = "jwt.token.expiration";

    /**
     * 未登录
     */
    String NOT_LOGIN = "jwt.login.notLogin";

}
