package com.wingflare.gateway.handler;


import com.wingflare.gateway.R;
import com.wingflare.gateway.bo.OpenApiOutputBo;
import com.wingflare.gateway.exceptions.OpenApiException;
import com.wingflare.gateway.exceptions.OpenApiSignException;
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

import java.util.Date;

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
                logger.debug("响应非200状态码: {} {}", ((ResponseStatusException) ex).getStatusCode().value(), ex.getMessage());
            }
        } else if (ex instanceof OpenApiException) {
            if (ex instanceof OpenApiSignException) {
                if (logger.isDebugEnabled()) {
                    logger.debug("开放平台签名异常");
                }
            }

            OpenApiOutputBo outputBo = new OpenApiOutputBo();
            outputBo.setCode(ex.getMessage());
            outputBo.setTimestamp(new Date());
            return WebFluxUtil.writeJSON(exchange.getResponse(), outputBo);
        } else {
            msg = "server.exception";
            logger.error("[网关异常处理]请求路径:{},异常信息:{}, 异常类: {}",
                    exchange.getRequest().getPath(), ex.getMessage(), ex.getClass().getName());
        }

        return WebFluxUtil.writeJSON(
                exchange.getResponse(), R.fail(HttpStatus.SERVICE_UNAVAILABLE.value(), msg));
    }

}
