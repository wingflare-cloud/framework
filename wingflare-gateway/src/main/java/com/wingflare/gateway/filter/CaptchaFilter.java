package com.wingflare.gateway.filter;


import com.wingflare.abstraction.lib.captcha.bo.ClientInfo;
import com.wingflare.gateway.utils.MutateUtil;
import com.wingflare.lib.captcha.service.CaptchaServer;
import com.wingflare.lib.captcha.support.CaptchaCtx;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.constants.HttpHeader;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.Ctx;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;

/**
 * 验证码过滤器
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
/*@Component*/
public class CaptchaFilter implements GlobalFilter, Ordered {

    @Resource
    private CaptchaServer captchaServer;


    @Override
    public int getOrder() {
        return -300;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String name = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_NAME_TRANSFER_KEY);
        Integer index = null;
        String captchaId = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_ID_TRANSFER_KEY);

        if (StringUtil.isNotEmpty(name)) {
            String dateTime = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_DATE_TIME_TRANSFER_KEY);
            String hash = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_HASH_TRANSFER_KEY);
            index = captchaServer.captchaNameOf(name, captchaId, dateTime, hash);
        }

        if (index == null) {
            index = captchaServer.captchaIndexOf(request.getURI().getPath());
        }

        // 地址未配置验证码防护直接执行正常流程
        if (index == null) {
            return chain.filter(exchange);
        }

        ClientInfo info = Builder.of(ClientInfo::new)
                .with(ClientInfo::setIpaddr, request.getRemoteAddress().getAddress().toString())
                .with(ClientInfo::setUserAgent, request.getHeaders().getFirst(HttpHeader.REQUEST_USER_AGENT))
                .with(ClientInfo::setSystemCode, request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM))
                .with(ClientInfo::setCaptchaId, captchaId)
                .with(ClientInfo::setCaptchaType, captchaServer.getType(index))
                .build();

        String captcha = request.getHeaders().getFirst(CaptchaCtx.CAPTCHA_VALUE_TRANSFER_KEY);

        if (!captchaServer.check(index, info, captcha, true)) {
            throw new BusinessLogicException("sec.captcha.mismatch");
        }

        if (StringUtil.isNotEmpty(name)) {
            MutateUtil.addHeader(mutate, CaptchaCtx.CAPTCHA_CHECK_NAME_TRANSFER_KEY, name);
        }

        return chain.filter(exchange);
    }
}
