package com.wingflare.gateway;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 错误代码
 */
public interface ErrorCode {

    /**
     * 令牌过期或错误
     */
    String TOKEN_EXPIRATION_OR_ERROR = "gateway.token.expirationOrError";

    /**
     * 登录状态已过期
     */
    String TOKEN_LOGIN_EXPIRATION = "gateway.login.expiration";

    /**
     * 禁止访问
     */
    String NO_ACCESS = "gateway.noAccess";

    /**
     * 系统维护中
     */
    String SYSTEM_MAINTENANCE = "gateway.system.maintenance";

    /**
     * 验证码为空
     */
    String CAPTCHA_EMPTY = "gateway.captcha.isEmpty";

    /**
     * 验证码已失效或异常
     */
    String CAPTCHA_INVALID = "gateway.captcha.invalid";

    /**
     * 验证码错误
     */
    String CAPTCHA_ERROR = "gateway.captcha.error";

    /**
     * 验证码生成错误
     */
    String CAPTCHA_GENERATE_ERROR = "gateway.captcha.generateError";

    /**
     * 开放平台签名错误
     */
    String OPEN_API_SIGNATURE_ERR = "openApi.signature.err";

}
