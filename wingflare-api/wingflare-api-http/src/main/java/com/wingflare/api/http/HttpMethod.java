package com.wingflare.api.http;


import java.util.Arrays;
import java.util.Optional;

/**
 * HTTP方法枚举类，定义了标准的HTTP请求方法
 * 包含HTTP/1.1及扩展的常用方法
 */
public enum HttpMethod {
    // 基本HTTP方法
    GET,
    HEAD,
    POST,
    PUT,
    DELETE,
    CONNECT,
    OPTIONS,
    TRACE,

    // 扩展HTTP方法
    PATCH,
    PURGE,
    LINK,
    UNLINK;

    /**
     * 获取HTTP方法名称
     * @return 方法名称
     */
    public String getMethod() {
        return name();
    }

    /**
     * 根据方法名称获取对应的枚举值
     * @param method 方法名称（不区分大小写）
     * @return 对应的枚举值，如未找到则返回空Optional
     */
    public static Optional<HttpMethod> getByMethod(String method) {
        if (method == null || method.trim().isEmpty()) {
            return Optional.empty();
        }

        return Arrays.stream(values())
                .filter(m -> m.name().equalsIgnoreCase(method.trim()))
                .findFirst();
    }

}
