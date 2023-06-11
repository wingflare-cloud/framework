package com.wingflare.gateway.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.lib.spring.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description
 */
public class WebFluxRespUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebFluxRespUtil.class);

    /**
     * 设置webflux模型JSON响应
     *
     * @param response
     * @param value
     * @return
     */
    public static <T> Mono<Void> writeJSON(ServerHttpResponse response, T value) {
        return writeJSON(response, response.getStatusCode(), value);
    }

    public static <T> Mono<Void> writeJSON(ServerHttpResponse response, HttpStatus httpStatus, T value) {
        return write(response, MediaType.APPLICATION_JSON_VALUE, httpStatus,
                () -> monoSink -> {
                    try {
                        ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
                        DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(value));
                        monoSink.success(dataBuffer);
                    } catch (Throwable e) {
                        monoSink.error(e);
                    }
                });
    }

    public static Mono<Void> write(ServerHttpResponse response, String mediaType, Object value) {
        return write(response, mediaType, response.getStatusCode(), value.toString());
    }

    public static <T> Mono<Void> write(ServerHttpResponse response, String mediaType, Supplier<Consumer<MonoSink<T>>> callback) {
        return write(response, mediaType, response.getStatusCode(), callback);
    }

    public static <T> Mono<Void> write(ServerHttpResponse response, String mediaType, HttpStatus status,
                                       Supplier<Consumer<MonoSink<T>>> callback) {
        response.setStatusCode(status);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, mediaType);
        return response.writeWith(Mono.create(MonoSink -> callback.get()));
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param mediaType mediaType
     * @param status      http状态码
     * @param body        响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> write(ServerHttpResponse response, String mediaType, HttpStatus status, String body) {
        response.setStatusCode(status);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, mediaType);

        try {
            DataBuffer dataBuffer = response.bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        } catch (Throwable e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("数据转换异常: [{}], {}", e.getClass().getName(), e.getMessage());
        }

        return response.writeWith(Mono.empty());
    }

    /**
     * 设置webflux空响应
     *
     * @param response
     * @return
     */
    public static Mono<Void> writeEmpty(ServerHttpResponse response) {
        return response.writeWith(Mono.empty());
    }

}
