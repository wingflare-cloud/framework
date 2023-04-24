package com.wingflare.gateway.handler;


import com.wingflare.gateway.utils.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author naizui_ycx
 * @date {2021/12/16}
 * @description 网关异常处理
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof NotFoundException) {
            msg = "gateway.serverNotFound";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
        } else {
            msg = "gateway.serverInternalError";
            log.error("[网关异常处理]请求路径:{},异常信息:{}, 异常类: {}",
                    exchange.getRequest().getPath(), ex.getMessage(), ex.getClass().getName());
        }

        if (ex instanceof ResponseStatusException) {
            return ServletUtil.webFluxResponseWriter(response, ((ResponseStatusException) ex).getStatus(), msg);
        }

        return ServletUtil.webFluxResponseWriter(response, msg);
    }

}
