package com.wingflare.gateway.filter;


import com.wingflare.gateway.utils.MutateUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.SystemInternalProperties;
import com.wingflare.lib.standard.Ctx;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 全局安全信息头处理过滤器
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 *
 */
@Component
public class SecHeaderFilter implements GlobalFilter, Ordered {

    @Resource
    private SystemInternalProperties systemInternalProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String secToken = request.getHeaders().getFirst(Ctx.HEADER_KEY_SEC_TOKEN);
        boolean skipRemoveHeader = false;

        // 判断sec token如果通过则跳过上下文过滤机制
        if (StringUtil.isNotEmpty(secToken) && StringUtil.isNotEmpty(systemInternalProperties.getSecToken())) {
            if (systemInternalProperties.getSecToken().equals(secToken)) {
                skipRemoveHeader = true;
            }
        }

        if (!skipRemoveHeader) {
            // 全局上下文信息内但不在外部上下文信息白名单的上下文信息全部移除，防止客户端伪造上下文信息引发安全问题
            for (Map.Entry<String, String> key : systemInternalProperties.getCtx().entrySet()) {
                if (!systemInternalProperties.getOutCtxWhiteList().contains(key.getKey())) {
                    MutateUtil.removeHeader(mutate, key.getKey());
                }
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }
}
