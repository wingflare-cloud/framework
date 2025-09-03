package com.wingflare.api.http;

import java.time.Instant;

/**
 * Cookie抽象接口，定义了Cookie的基本操作
 */
public interface HttpCookie {

    /**
     * 获取Cookie名称
     * @return Cookie名称
     */
    String getName();

    /**
     * 设置Cookie值
     * @param value Cookie值
     */
    HttpCookie setValue(String value);

    /**
     * 获取Cookie值
     * @return Cookie值
     */
    String getValue();

    /**
     * 设置Cookie过期时间
     * @param expiry 过期时间(Instant)
     */
    HttpCookie setExpiry(Instant expiry);

    /**
     * 获取Cookie过期时间
     * @return 过期时间(Instant)，如果为null表示会话结束时过期
     */
    Instant getExpiry();

    /**
     * 设置Cookie路径
     * @param path 路径
     */
    HttpCookie setPath(String path);

    /**
     * 获取Cookie路径
     * @return 路径
     */
    String getPath();

    /**
     * 设置Cookie所属域
     * @param domain 域
     */
    HttpCookie setDomain(String domain);

    /**
     * 获取Cookie所属域
     * @return 域
     */
    String getDomain();

    /**
     * 设置Cookie是否仅通过安全连接传输
     * @param secure true表示仅安全连接，false表示不限制
     */
    HttpCookie setSecure(boolean secure);

    /**
     * 判断Cookie是否仅通过安全连接传输
     * @return true表示仅安全连接，false表示不限制
     */
    boolean isSecure();

    /**
     * 设置Cookie的HttpOnly属性
     * @param httpOnly true表示仅HTTP协议可访问，false表示允许脚本访问
     */
    HttpCookie setHttpOnly(boolean httpOnly);

    /**
     * 判断Cookie是否为HttpOnly
     * @return true表示仅HTTP协议可访问，false表示允许脚本访问
     */
    boolean isHttpOnly();

    /**
     * 设置Cookie的SameSite属性
     * @param sameSite SameSite属性值
     */
    HttpCookie setSameSite(String sameSite);

    /**
     * 获取Cookie的SameSite属性
     * @return SameSite属性值
     */
    String getSameSite();

    /**
     * 将Cookie转换为HTTP响应头格式的字符串
     * @return 符合HTTP规范的Cookie字符串
     */
    String toHeaderString();

}
