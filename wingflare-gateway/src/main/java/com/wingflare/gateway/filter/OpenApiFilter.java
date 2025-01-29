/*
package com.wingflare.gateway.filter;


import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.gateway.ErrorCode;
import com.wingflare.gateway.bo.OpenApiInputBo;
import com.wingflare.gateway.bo.OpenApiOutputBo;
import com.wingflare.gateway.exceptions.OpenApiException;
import com.wingflare.gateway.exceptions.OpenApiSignException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.utils.ValidationUtil;
import com.wingflare.lib.standard.R;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

*/
/**
 * 开放平台api过滤器
 *
 * @author naizui
 *//*

@Component
public class OpenApiFilter implements GlobalFilter, Ordered {

    @Resource
    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(OpenApiFilter.class);

    private final List<String> respBodyContentTypes = new ArrayList<String>() {{
        add("application/json");
        add("text/json");
        add("text/plain");
    }};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return handle(exchange, chain);
    }

    private OpenApiInputBo getApiInputBo(String string) {
        try {
            return objectMapper.readValue(string, OpenApiInputBo.class);
        } catch (JsonProcessingException e) {
            logger.error("开放平台参数解码异常: {}, ex: {}", e.getMessage(), ExceptionUtils.getStackTrace(e));
            throw new OpenApiException(e.getMessage());
        }
    }

    private OpenApiInputBo getApiInputBo(Map<String, String> queryParams) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(queryParams), OpenApiInputBo.class);
        } catch (JsonProcessingException e) {
            logger.error("开放平台参数解码异常: {}, ex: {}", e.getMessage(), ExceptionUtils.getStackTrace(e));
            throw new OpenApiException(e.getMessage());
        }
    }

    private <T> T getBizVal(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            logger.error("开放平台业务参数解码异常: {}, ex: {}", e.getMessage(), ExceptionUtils.getStackTrace(e));
            throw new OpenApiException(e.getMessage());
        }
    }

    private String httpBuildQuery(Map<String, Object> map, boolean sort) {
        try {
            return StringUtil.httpBuildQuery(map, sort);
        } catch (UnsupportedEncodingException e) {
            logger.error("开放平台参数拼接错误: {}, ex: {}", e.getMessage(), ExceptionUtils.getStackTrace(e));
            throw new OpenApiException(e.getMessage());
        }
    }

    public Mono<Void> handle(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = request.getMethod();

        if (method == null) {
            return null;
        }

        String methodName = method.name();
        String path = request.getURI().getPath();
        String secretKey = "456";
        String appId = "123";

        if (HttpMethod.GET.name().equalsIgnoreCase(methodName)) {
            Map<String, String> queryParams = exchange.getRequest()
                    .getQueryParams().toSingleValueMap();
            OpenApiInputBo bo = getApiInputBo(queryParams);

            if (checkSign(bo, methodName, path, secretKey)) {
                Map<String, Object> params = getBizVal(bo.getBizContent(), Map.class);
                return chain.filter(
                        exchange.mutate()
                                .request(request.mutate().uri(UriComponentsBuilder.fromUri(request.getURI())
                                        .replaceQuery(httpBuildQuery(params, true))
                                        .build(true)
                                        .toUri()).build())
                                .response(handleResponse(exchange, methodName, path, secretKey))
                                .build());
            } else {
                throw new OpenApiSignException(ErrorCode.OPEN_API_SIGNATURE_ERR);
            }
        }

        List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();
        ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
        // 处理请求参数
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            OpenApiInputBo bo = getApiInputBo(body);
            if (checkSign(bo, methodName, path, secretKey)) {
                return Mono.just(bo.getBizContent());
            } else {
                throw new OpenApiSignException(ErrorCode.OPEN_API_SIGNATURE_ERR);
            }
        });

        //创建BodyInserter修改请求体
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters
                .fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpRequestDecorator decoratedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return outputMessage.getBody();
                }
            };

            return chain.filter(
                    exchange.mutate()
                            .request(decoratedRequest)
                            .response(handleResponse(exchange, methodName, path, secretKey))
                            .build());
        }));
    }

    private ServerHttpResponseDecorator handleResponse(ServerWebExchange exchange, String methodName, String path, String secretKey) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    String originalResponseContentType = exchange
                            .getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);

                    if (responseContentMatch(originalResponseContentType)) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 合并多个流集合，解决返回体分段传输
                            DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer join = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[join.readableByteCount()];
                            join.read(content);
                            // 释放掉内存
                            DataBufferUtils.release(join);
                            String responseResult = new String(content, StandardCharsets.UTF_8);
                            byte[] newContent;
                            try {
                                R r = objectMapper.readValue(responseResult, R.class);
                                OpenApiOutputBo bo = new OpenApiOutputBo();
                                bo.setCode(r.getMsg());
                                bo.setStatus(r.getRet());
                                bo.setTimestamp(new Date());

                                if (R.RET_NO_ERR == r.getRet()) {
                                    bo.setData(objectMapper.writeValueAsString(r.getData()));
                                }

                                bo.setSign(signature(BeanUtil.beanToMap(bo), methodName, path, secretKey));
                                newContent = objectMapper.writeValueAsBytes(bo);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }

                            return bufferFactory.wrap(newContent);
                        }));
                    }
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
    }

    private boolean responseContentMatch(String contentType) {
        if (StringUtil.isBlank(contentType)) {
            return false;
        }

        for (String item : respBodyContentTypes) {
            if (StringUtil.startsWithIgnoreCase(contentType, item)) {
                return true;
            }
        }

        return false;
    }

    */
/**
     * 验证签名
     *
     * @param
     * @return
     *//*

    private boolean checkSign(OpenApiInputBo bo, String method, String path, String secretKey) {
        ValidationUtil.validateBo(bo, true);
        Map<String, Object> data = BeanUtil.beanToMap(bo);
        return signature(data, method, path, secretKey).equals(bo.getSign());
    }

    */
/**
     * 签名方法
     *
     * @param data
     * @param method
     * @param path
     * @return
     *//*

    private String signature(Map<String, Object> data, String method, String path, String secretKey) {
        data.remove("sign");
        data.put("method", method);
        data.put("path", path);
        data.put("appSecret", secretKey);

        Set<String> keysSet = data.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;

        for (Object key : keys) {
            Object value = data.get(key);
            if (value != null && StringUtil.isNotBlank(value.toString())) {
                if (first) {
                    first = false;
                } else {
                    temp.append("&");
                }
                temp.append(key).append("=");
                temp.append(value);
            }
        }

        return DigestUtils.md5Hex(temp.toString())
                .toUpperCase(Locale.ROOT);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }
}*/
