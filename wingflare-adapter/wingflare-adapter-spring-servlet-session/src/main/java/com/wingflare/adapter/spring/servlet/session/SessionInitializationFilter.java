package com.wingflare.adapter.spring.servlet.session;


import com.wingflare.lib.core.exceptions.NoPermissionException;
import com.wingflare.lib.core.utils.StringUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * session过滤器
 */
public class SessionInitializationFilter implements Filter, Ordered {

    @Value("${web.trustedProxies:}")
    private String trustedProxies;

    @Value("${web.session.strictModel:}")
    private String strictModel;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest request) {
            HttpSession session = request.getSession(false);

            if (session == null) {
                session = request.getSession(true);
                session.setAttribute("sid", session.getId());
                session.setAttribute("ip", getClientIp(request));
                session.setAttribute("userAgent", request.getHeader("User-Agent"));
            } else {
                if (StringUtil.isNoneBlank(strictModel)) {
                    if ("simple".equals(strictModel) || "strict".equals(strictModel)) {
                        if (!ObjectUtils.nullSafeEquals(request.getHeader("User-Agent"), session.getAttribute("userAgent"))) {
                            throw new NoPermissionException("Illegal UserAgent");
                        }
                    }

                    if ("strict".equals(strictModel)) {
                        if (!ObjectUtils.nullSafeEquals(getClientIp(request), session.getAttribute("ip"))) {
                            throw new NoPermissionException("Illegal IP");
                        }
                    }
                }
            }
        }
    }

    private String getClientIp(HttpServletRequest request) {
        // 检查是否可信代理环境
        boolean trustedEnvironment = isTrustedProxy(request);

        if (trustedEnvironment) {
            // X-Forwarded-For：Squid 服务代理
            String ipAddresses = request.getHeader("X-Forwarded-For");

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // Proxy-Client-IP：apache 服务代理
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // X-Real-IP：nginx服务代理
                ipAddresses = request.getHeader("X-Real-IP");
            }

            return StringUtil.hasText(ipAddresses) &&
                    !"unknown".equalsIgnoreCase(ipAddresses)
                    ? ipAddresses.split(",")[0].trim()
                    : request.getRemoteAddr();
        } else {
            return request.getRemoteAddr();
        }
    }

    private boolean isTrustedProxy(HttpServletRequest request) {
        // 实际项目中应结合IP白名单/网段判断
        return trustedProxies.equals("all") ||
                Arrays.asList(trustedProxies.split(",")).contains(request.getRemoteAddr());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
