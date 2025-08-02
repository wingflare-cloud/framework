package com.wingflare.lib.jwt.filter;

import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.jwt.ErrorCode;
import com.wingflare.lib.security.properties.AuthProperties;
import com.wingflare.lib.standard.AuthResponseDTO;
import com.wingflare.lib.standard.Ctx;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * 认证过滤器
 */
@WebFilter
public class ServletAuthFilter implements Filter, Ordered {

    @Resource
    private AuthProperties authProperties;

    @Resource
    private AuthTool authTool;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        if (servletRequest instanceof HttpServletRequest request) {
            if (!StringUtil.urlMatches(request.getRequestURI(), authProperties.getPathPrefix())) {
                AuthResponseDTO authResponseDTO = authTool.checkLogin(getToken(request), getBusinessSystem(request));

                if (StringUtil.isNotBlank(authResponseDTO.getError())) {
                    if (!StringUtil.urlMatches(request.getRequestURI(), authProperties.getWhites())) {
                        if (StringUtil.equals(authResponseDTO.getError(), ErrorCode.TOKEN_EXPIRATION)) {
                            if (StringUtil.isNotEmpty(authProperties.getRefreshTokenUrl()) && !StringUtil.isMatch(authProperties.getRefreshTokenUrl(), request.getRequestURI())) {
                                throw new BusinessLogicException(authResponseDTO.getError(), null, HttpStatus.UNAUTHORIZED.value());
                            }
                        } else {
                            throw new BusinessLogicException(authResponseDTO.getError(), null, HttpStatus.PAYMENT_REQUIRED.value());
                        }
                    }
                } else {
                    ContextHolder.set(Ctx.CONTEXT_KEY_AUTH_USER, authResponseDTO.getUserAuth());
                }
            }
        }
    }

    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(Ctx.AUTHENTICATION);

        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtil.isNotEmpty(token) && token.startsWith(authProperties.getAuthenticationPrefix())) {
            token = token.replaceFirst(authProperties.getAuthenticationPrefix(), StringUtil.EMPTY);
        }

        return token;
    }

    /**
     * 获取请求token
     */
    private String getBusinessSystem(HttpServletRequest request) {
        return request.getHeader(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
