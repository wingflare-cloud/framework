package com.wingflare.gateway.utils;


import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 工具类
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class MutateUtil {

    public static void addHeader(ServerHttpRequest.Builder mutate, String name, String value) {
        if (value == null) {
            return;
        }
        mutate.header(name, value);
    }

    public static void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

}
