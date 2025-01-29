package com.wingflare.gateway.controller;


import com.wingflare.gateway.utils.WebFluxUtil;
import com.wingflare.lib.core.constants.HttpHeader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/basis")
public class BasisController {

    @GetMapping("/certificate")
    public Mono<String> setSession(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();

        return exchange.getSession()
                .doOnNext(session -> {
                    session.getAttributes().put("userAgent", request.getHeaders().getFirst(HttpHeader.REQUEST_USER_AGENT));
                    session.getAttributes().put("clientIp", WebFluxUtil.getClientIp(request));
                })
                .then(Mono.just("Success"));
    }

}
