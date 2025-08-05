package com.wingflare.adapter.spring.servlet.session;


import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.NoPermissionException;
import com.wingflare.lib.core.utils.IPAddressUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.SessionProperties;
import com.wingflare.lib.spring.configure.properties.WebProperties;
import com.wingflare.lib.standard.Ctx;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * session过滤器
 */
@WebFilter
public class SessionInitializationFilter implements Filter, Ordered {

    @Resource
    private SessionProperties sessionProperties;

    @Resource
    private WebProperties webProperties;

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
                if (StringUtil.isNoneBlank(sessionProperties.getStrictModel())) {
                    if ("simple".equals(sessionProperties.getStrictModel()) || "strict".equals(sessionProperties.getStrictModel())) {
                        if (!ObjectUtils.nullSafeEquals(request.getHeader("User-Agent"), session.getAttribute("userAgent"))) {
                            throw new NoPermissionException("Illegal UserAgent");
                        }
                    }

                    if ("strict".equals(sessionProperties.getStrictModel())) {
                        if (!ObjectUtils.nullSafeEquals(getClientIp(request), session.getAttribute("ip"))) {
                            throw new NoPermissionException("Illegal IP");
                        }
                    }
                }
            }

            ContextHolder.set(Ctx.CONTEXT_KEY_CLIENT_ID, session.getAttribute("sid"));

            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {

            }
        }
    }

    private String getClientIp(HttpServletRequest request) {
        // 检查是否可信代理环境
        boolean trustedEnvironment = isTrustedProxy(request.getRemoteAddr());

        if (trustedEnvironment) {
            String[] headersToCheck = {
                    "X-Forwarded-For", "Proxy-Client-IP",
                    "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP"
            };

            for (String header : headersToCheck) {
                String ipAddresses = request.getHeader(header);
                if (ipAddresses != null && !"unknown".equalsIgnoreCase(ipAddresses)) {
                    return ipAddresses.split(",")[0].trim();
                }
            }
        }

        return request.getRemoteAddr();
    }

    private boolean isTrustedProxy(String clientIp) {
        return IPAddressUtil.isIpInRange(clientIp, webProperties.getTrustedProxies());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
