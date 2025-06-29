package com.wingflare.gateway.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.lib.spring.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description
 */
public class WebFluxUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebFluxUtil.class);

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

    public static <T> Mono<Void> writeJSON(ServerHttpResponse response, HttpStatusCode httpStatus, T value) {
        response.setStatusCode(httpStatus);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.create(monoSink -> {
            try {
                ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
                DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(value));
                monoSink.success(dataBuffer);
            } catch (Throwable e) {
                monoSink.error(e);
            }
        }));
    }

    public static Mono<Void> write(ServerHttpResponse response, String mediaType, Object value) {
        return write(response, mediaType, response.getStatusCode(), value.toString());
    }

    /**
     * 设置webflux模型响应
     *
     * @param response  ServerHttpResponse
     * @param mediaType mediaType
     * @param status    http状态码
     * @param body      响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> write(ServerHttpResponse response, String mediaType, HttpStatusCode status, String body) {
        response.setStatusCode(status);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, mediaType);

        try {
            DataBuffer dataBuffer = response.bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        } catch (Throwable e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("数据转换异常", e(
                    Map.of(
                            "class", e.getClass().getName(),
                            "message", e.getMessage()
                    )
            ));
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


    public static String getClientIp(ServerHttpRequest request) {
        // 首先尝试从X-Forwarded-For头部获取IP地址
        List<String> forwardedFor = request.getHeaders().get("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            // X-Forwarded-For可能包含多个由逗号和空格分隔的IP地址，第一个是原始客户端的IP地址
            return forwardedFor.get(0).split(",")[0].trim();
        }

        // 如果没有X-Forwarded-For头部，则尝试获取Remote Address
        return request.getRemoteAddress() != null ?
                request.getRemoteAddress().getAddress().getHostAddress() : null;
    }

}
