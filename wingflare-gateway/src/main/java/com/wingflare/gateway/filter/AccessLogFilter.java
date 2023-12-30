package com.wingflare.gateway.filter;


import com.wingflare.gateway.bo.GatewayLogBo;
import com.wingflare.gateway.configure.properties.AccessLogProperties;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description access log过滤器
 */
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {

    @Resource
    private AccessLogProperties accessLogProperties;

    @Resource
    private CurrentTraceContext currentTraceContext;

    private final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    @Resource
    private ServerCodecConfigurer codecConfigurer;

    @Override
    public int getOrder() {
        return -100;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!accessLogProperties.getEnable()) {
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();
        // 请求路径
        String requestPath = request.getPath().pathWithinApplication().value();
        Route route = getGatewayRoute(exchange);

        XForwardedRemoteAddressResolver resolver = XForwardedRemoteAddressResolver
                .maxTrustedIndex(accessLogProperties.getTrustedIndex());
        InetSocketAddress inetSocketAddress = resolver.resolve(exchange);
        String ipAddress = inetSocketAddress.getAddress().toString();

        GatewayLogBo gatewayLog = new GatewayLogBo();
        gatewayLog.setSchema(request.getURI().getScheme());
        gatewayLog.setReqMethod(request.getMethodValue());
        gatewayLog.setReqPath(requestPath);
        gatewayLog.setTargetServer(route.getId());
        gatewayLog.setReqTime(new Date());
        gatewayLog.setIp(ipAddress);
        TraceContext context = currentTraceContext.context();

        if (context != null) {
            gatewayLog.setTraceId(context.spanId());
        }

        final HttpHeaders headers = request.getHeaders();

        if (CollectionUtil.isNotEmpty(accessLogProperties.getLoggerReqHeaders())
                && CollectionUtil.isNotEmpty(headers)) {
            StringBuilder headerBuilder = new StringBuilder();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                if (accessLogProperties.getLoggerReqHeaders()
                        .contains(entry.getKey().toLowerCase(Locale.ROOT))) {
                    buildMultiValueMap(headerBuilder, entry);
                }
            }

            if (headerBuilder.length() > 0) {
                gatewayLog.setReqHeader(headerBuilder.deleteCharAt(headerBuilder.length() - 1).toString());
            }
        }


        String rawQuery = request.getURI().getRawQuery();

        if (StringUtil.hasText(rawQuery)) {
            gatewayLog.setReqQuery(rawQuery);
        }

        MediaType mediaType = headers.getContentType();

        if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType)
                || MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
            return readFormData(exchange, chain, gatewayLog);
        } else {
            return writeBodyLog(exchange, chain, gatewayLog);
        }
    }

    private void buildMultiValueMap(StringBuilder headerBuilder, Map.Entry<String, List<String>> entry) {
        if (entry.getValue().size() == 1) {
            headerBuilder.append(entry.getKey())
                    .append("=").append(entry.getValue())
                    .append("&");
        } else {
            for (int i = 0; i < entry.getValue().size(); i++) {
                headerBuilder.append(entry.getKey()).append("[")
                        .append(i).append("]=")
                        .append(entry.getValue().get(i))
                        .append("&");
            }
        }
    }

    private ServerHttpRequest requestDataBuffer(ServerWebExchange exchange, DataBuffer dataBuffer) {
        DataBufferUtils.retain(dataBuffer);
        final Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(
                dataBuffer.slice(0, dataBuffer.readableByteCount())));
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                return cachedFlux;
            }

            @Override
            public MultiValueMap<String, String> getQueryParams() {
                return UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).build().getQueryParams();
            }
        };
    }

    private Mono<Void> readFormData(ServerWebExchange exchange, GatewayFilterChain chain, GatewayLogBo gatewayLog) {
        return DataBufferUtils.join(exchange.getRequest().getBody())
                .flatMap(dataBuffer -> {
                    final HttpHeaders headers = exchange.getRequest().getHeaders();
                    if (headers.getContentLength() == 0) {
                        return chain.filter(exchange);
                    }
                    ResolvableType resolvableType;
                    if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(headers.getContentType())) {
                        resolvableType = ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, Part.class);
                    } else {
                        resolvableType = ResolvableType.forClass(String.class);
                    }

                    ServerHttpRequest mutatedRequest = requestDataBuffer(exchange, dataBuffer);

                    return codecConfigurer.getReaders().stream()
                            .filter(reader -> reader.canRead(resolvableType, mutatedRequest.getHeaders().getContentType()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("no suitable HttpMessageReader."))
                            .readMono(resolvableType, mutatedRequest, Collections.emptyMap())
                            .flatMap(resolvedBody -> {
                                if (resolvedBody instanceof MultiValueMap) {
                                    MultiValueMap<String, Part> map = ((MultiValueMap) resolvedBody);
                                    if (CollectionUtil.isNotEmpty(map)) {
                                        StringBuilder builder = new StringBuilder();
                                        for (Map.Entry<String, List<Part>> entry : map.entrySet()) {
                                            if (entry.getValue().size() == 1) {
                                                builder.append(entry.getKey()).append("=");
                                                formPartBuild(builder, entry.getValue().get(0));
                                            } else {
                                                for (int i = 0; i < entry.getValue().size(); i++) {
                                                    builder.append(entry.getKey()).append("[")
                                                            .append(i).append("]=");
                                                    formPartBuild(builder, entry.getValue().get(i));
                                                }
                                            }
                                        }

                                        if (builder.length() > 0) {
                                            gatewayLog.setReqBody(builder.deleteCharAt(builder.length() - 1).toString());
                                        }
                                    }
                                } else {
                                    gatewayLog.setReqBody((String) resolvedBody);
                                }

                                ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, gatewayLog);
                                return chain.filter(exchange.mutate()
                                                .request(mutatedRequest)
                                                .response(decoratedResponse)
                                                .build())
                                        .then(Mono.fromRunnable(() -> writeAccessLog(gatewayLog)));
                            });
                });
    }


    private void formPartBuild(StringBuilder builder, Part part) {
        if (part instanceof FormFieldPart) {
            builder.append(((FormFieldPart) part).value())
                    .append("&");
        } else if (part instanceof FilePart) {
            builder.append("[Binary-data]")
                    .append("&");
        } else {
            builder.append("[Unknown-type-data]")
                    .append("&");
        }
    }

    /**
     * 解决 request body 只能读取一次问题，
     * 参考: org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory
     *
     * @param exchange
     * @param chain
     * @param gatewayLog
     * @return
     */
    @SuppressWarnings("unchecked")
    private Mono writeBodyLog(ServerWebExchange exchange, GatewayFilterChain chain, GatewayLogBo gatewayLog) {
        ServerRequest serverRequest = ServerRequest.create(exchange, codecConfigurer.getReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                .flatMap(body -> {
                    gatewayLog.setReqBody(body);
                    return Mono.just(body);
                });
        // 通过 BodyInserter 插入 body(支持修改body), 避免 request body 只能获取一次
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    // 重新封装请求
                    ServerHttpRequest decoratedRequest = requestDecorate(exchange, headers, outputMessage);
                    // 记录响应日志
                    ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, gatewayLog);
                    // 记录普通的
                    return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build())
                            .then(Mono.fromRunnable(() -> writeAccessLog(gatewayLog)));
                }));
    }

    /**
     * 打印日志
     */
    private void writeAccessLog(GatewayLogBo gatewayLog) {
        logger.info(gatewayLog.toString());
    }


    private Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }


    /**
     * 请求装饰器，重新计算 headers
     *
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers,
                                                       CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    // TODO: this causes a 'HTTP/1.1 411 Length Required'
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }


    /**
     * 记录响应日志
     * 通过 DataBufferFactory 解决响应体分段传输问题。
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, GatewayLogBo gatewayLog) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Date responseTime = new Date();
                    gatewayLog.setRespTime(responseTime);
                    // 计算执行时间
                    long executeTime = (responseTime.getTime() - gatewayLog.getReqTime().getTime());
                    String originalResponseContentType = exchange
                            .getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    gatewayLog.setExecuteTime(executeTime);
                    gatewayLog.setRespStatus(getRawStatusCode());
                    gatewayLog.setRespType(originalResponseContentType);
                    if (responseContentLog(originalResponseContentType)) {
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
                            gatewayLog.setRespData(responseResult);
                            return bufferFactory.wrap(content);
                        }));
                    }
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
    }

    private boolean responseContentLog(String contentType) {
        if (StringUtil.isBlank(contentType)) {
            return false;
        }

        for (String item : accessLogProperties.getRespBodyContentTypes()) {
            if (StringUtil.startsWithIgnoreCase(contentType, item)) {
                return true;
            }
        }

        return false;
    }

}
