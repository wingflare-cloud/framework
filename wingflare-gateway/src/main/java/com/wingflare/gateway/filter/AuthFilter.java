package com.wingflare.gateway.filter;


import com.wingflare.gateway.ErrorCode;
import com.wingflare.gateway.R;
import com.wingflare.gateway.configure.properties.IgnoreWhiteProperties;
import com.wingflare.gateway.utils.MutateUtil;
import com.wingflare.gateway.utils.WebFluxRespUtil;
import com.wingflare.lib.core.exceptions.NoException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.utils.JwtUtil;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2021/01/02}
 * @description 身份认证过滤器
 */
@Component
@ConditionalOnBean({
        UserAuthUtil.class
})
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Resource
    private IgnoreWhiteProperties ignoreWhite;

    @Resource
    private UserAuthUtil userAuthUtil;

    @Resource
    private JwtUtil jwtUtil;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        Throwable throwable = null;

        try {
            String token = getToken(request);
            boolean hasToken = StringUtil.isNotEmpty(token);

            if (hasToken) {
                Claims claims = jwtUtil.parseToken(token);

                if (claims == null) {
                    throw new NoException(ErrorCode.TOKEN_EXPIRATION_OR_ERROR);
                }

                String tokenId = "";

                if (claims.containsKey(Ctx.HEADER_KEY_TOKEN_ID)) {
                    tokenId = claims.get(Ctx.HEADER_KEY_TOKEN_ID, String.class);
                }

                if (StringUtil.isBlank(tokenId)) {
                    throw new NoException(ErrorCode.TOKEN_EXPIRATION_OR_ERROR);
                }

                UserAuth userAuth = userAuthUtil.getUser(tokenId);

                if (userAuth == null || StringUtil.isEmpty(userAuth.getUserId())
                        || StringUtil.isEmpty(userAuth.getUserName())) {
                    throw new NoException(ErrorCode.TOKEN_LOGIN_EXPIRATION);
                }

                setUserCtxHeader(mutate, tokenId, userAuth);

                return chain.filter(exchange.mutate().request(mutate.build()).build());
            }
        } catch (Throwable e) {
            if (!(e instanceof NoException)) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
            throwable = e;
        }

        if (throwable != null) {
            // 如果访问地址需要验证登录状态
            if (!StringUtil.urlMatches(request.getURI().getPath(), ignoreWhite.getWhites())) {
                return unauthorizedResponse(exchange, throwable.getMessage());
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 设置用户上下文信息头
     *
     * @param mutate
     * @param tokenId
     * @param userAuth
     */
    private void setUserCtxHeader(ServerHttpRequest.Builder mutate, String tokenId, UserAuth userAuth) {
        MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_USER_ID, userAuth.getUserId());
        MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_USER_NAME, StringUtil.urlEncode(userAuth.getUserName()));
        MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_TOKEN_ID, tokenId);

        if (CollectionUtil.isNotEmpty(userAuth.getUserTypes())) {
            MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_USER_TYPE,
                    SecurityUtil.typeValueEncode(userAuth.getUserTypes()));
        }

        if (CollectionUtil.isNotEmpty(userAuth.getRoles())) {
            MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_USER_ROLES,
                    SecurityUtil.typeValueEncode(userAuth.getRoles()));
        }

        if (StringUtil.isNotBlank(userAuth.getIdentity())) {
            MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_IDENTITY, userAuth.getIdentity());
        }

        if (CollectionUtil.isNotEmpty(userAuth.getOrg())) {
            MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_ORG,
                    SecurityUtil.typeValueEncode(userAuth.getOrg()));
        }

        if (StringUtil.isNotBlank(userAuth.getCurrentOrg())) {
            MutateUtil.addHeader(mutate, Ctx.HEADER_KEY_CURRENT_ORG, userAuth.getCurrentOrg());
        }

        if (CollectionUtil.isNotEmpty(userAuth.getAttribute())) {
            userAuth.getAttribute().forEach((k, v) -> {
                if (v instanceof CharSequence) {
                    MutateUtil.addHeader(mutate, k, StringUtil.urlEncode((String) v));
                } else {
                    MutateUtil.addHeader(mutate, k, SecurityUtil.typeValueEncode(v));
                }
            });
        }
    }


    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        return WebFluxRespUtil.writeJSON(
                exchange.getResponse(), HttpStatus.UNAUTHORIZED, R.fail(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(Ctx.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtil.isNotEmpty(token) && token.startsWith(Ctx.AUTHENTICATION_PREFIX)) {
            token = token.replaceFirst(Ctx.AUTHENTICATION_PREFIX, StringUtil.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }

}
