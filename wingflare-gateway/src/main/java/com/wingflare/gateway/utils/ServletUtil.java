package com.wingflare.gateway.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.lib.spring.utils.SpringUtil;
import com.wingflare.lib.standard.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description
 */
public class ServletUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServletUtil.class);

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        return webFluxResponseWriter(response, response.getStatusCode(), value, R.RET_NO_ERR);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, response.getStatusCode(), value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value) {
        return webFluxResponseWriter(response, status, value, status.value());
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param code        响应状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType,
                                                   HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
        Object result;

        if (value instanceof R) {
            result = value;
        } else {
            result = R.ok(code, value.toString());
        }

        try {
            DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
            return response.writeWith(Mono.just(dataBuffer));
        } catch (Throwable e) {
            logger.error("数据转换异常: [{}], {}", e.getClass().getName(), e.getMessage());
        }

        return response.writeWith(Mono.empty());
    }

}
