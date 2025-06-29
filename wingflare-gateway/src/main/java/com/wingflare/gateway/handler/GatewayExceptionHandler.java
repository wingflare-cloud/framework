package com.wingflare.gateway.handler;


import com.wingflare.gateway.R;
import com.wingflare.gateway.utils.WebFluxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.*;


/**
 * @author naizui_ycx
 * @date {2021/12/16}
 * @description 网关异常处理
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GatewayExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof ResponseStatusException) {
            if (ex instanceof NotFoundException) {
                msg = "gateway.serverNotFound";
            } else {
                msg = ex.getMessage();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("响应非200状态码", e(Map.of(
                        "status", ((ResponseStatusException) ex).getStatusCode().value(),
                        "message", ex.getMessage()
                )));
            }
        } else {
            msg = "server.exception";
            logger.error("[网关异常处理]请求路径", e(Map.of(
                    "path", exchange.getRequest().getPath(),
                    "message", ex.getMessage(),
                    "class", ex.getClass().getName()
            )));
        }

        return WebFluxUtil.writeJSON(
                exchange.getResponse(), R.fail(HttpStatus.SERVICE_UNAVAILABLE.value(), msg));
    }

}
