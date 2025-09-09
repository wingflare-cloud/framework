package com.wingflare.gateway.filter;


import com.wingflare.adapter.spring.common.configure.properties.SessionProperties;
import com.wingflare.adapter.spring.common.configure.properties.WebProperties;
import com.wingflare.api.core.Ctx;
import com.wingflare.lib.core.utils.IPAddressUtil;
import com.wingflare.lib.core.utils.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import java.net.InetSocketAddress;


public class SessionGatewayFilterFactory extends AbstractGatewayFilterFactory<SessionGatewayFilterFactory.Config> {

    @Resource
    private WebProperties webProperties;

    public SessionGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((ctx, chain) -> ctx.getSession().flatMap(webSession -> {
            ServerHttpRequest request = ctx.getRequest();
            ServerWebExchange newCtx = null;

            // 初始化新会话或校验现有会话
            if (webSession.getAttributes().isEmpty() || !webSession.getAttributes().containsKey("ip")
                    || !webSession.getAttributes().containsKey("sid")) {
                // 初始化新会话
                webSession.getAttributes().put("sid", webSession.getId());
                webSession.getAttributes().put("ip", getClientIp(ctx));
                webSession.getAttributes().put("userAgent", request.getHeaders().getFirst("User-Agent"));

                newCtx = processChain(ctx, webSession.getId());
            } else {
                Boolean shouldContinue = handleExistingSession(ctx, webSession, config);

                if (!shouldContinue) {
                    return chain.filter(ctx);
                }

                newCtx = processChain(ctx, webSession.getAttribute("sid"));
            }

            if (StringUtil.urlMatches(request.getURI().getPath(), config.getResetSessionIdUrls())) {
                return webSession.changeSessionId().then(chain.filter(newCtx));
            }

            return chain.filter(newCtx);
        }));
    }

    // 处理过滤器链调用
    private ServerWebExchange processChain(ServerWebExchange exchange, String sessionId) {
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header(webProperties.getClientIdCtxName(), sessionId).build();
        exchange.getAttributes().put(Ctx.CONTEXT_KEY_CLIENT_ID, sessionId);
        return exchange.mutate().request(mutatedRequest).build();
    }

    // 现有会话校验
    private Boolean handleExistingSession(ServerWebExchange exchange, WebSession webSession, Config config) {
        ServerHttpRequest request = exchange.getRequest();

        if (StringUtil.isNoneBlank(config.getStrictModel())) {
            String sessionUserAgent = webSession.getAttribute("userAgent");
            String currentUserAgent = request.getHeaders().getFirst("User-Agent");

            if (("simple".equals(config.getStrictModel()) || "strict".equals(config.getStrictModel())) && !ObjectUtils.nullSafeEquals(currentUserAgent, sessionUserAgent)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return false;
            }

            if ("strict".equals(config.getStrictModel())) {
                String sessionIp = webSession.getAttribute("ip");
                String currentIp = getClientIp(exchange);
                if (!ObjectUtils.nullSafeEquals(currentIp, sessionIp)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return false;
                }
            }
        }
        return true;
    }

    // 获取客户端 IP
    private String getClientIp(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        InetSocketAddress remoteAddress = request.getRemoteAddress();

        if (remoteAddress == null || remoteAddress.getAddress() == null) {
            return "0.0.0.0";
        }

        String ip = remoteAddress.getAddress().getHostAddress();
        if (isTrustedProxy(ip)) {
            String[] headersToCheck = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP"};

            for (String header : headersToCheck) {
                String ipAddresses = headers.getFirst(header);
                if (ipAddresses != null && !"unknown".equalsIgnoreCase(ipAddresses)) {
                    return ipAddresses.split(",")[0].trim();
                }
            }
        }
        return ip;
    }

    // 检查是否为受信任代理
    private boolean isTrustedProxy(String clientIp) {
        return IPAddressUtil.isIpInRange(clientIp, webProperties.getTrustedProxies());
    }


    public static class Config extends SessionProperties {

    }

}