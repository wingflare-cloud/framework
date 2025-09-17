package com.wingflare.adapter.spring.servlet.session;


import com.wingflare.adapter.spring.common.configure.properties.SessionProperties;
import com.wingflare.adapter.spring.common.configure.properties.WebProperties;
import com.wingflare.adapter.spring.servlet.web.SpringServletHttpContainer;
import com.wingflare.api.core.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.NoPermissionException;
import com.wingflare.lib.core.utils.StringUtil;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * session过滤器
 */
@WebFilter
public class SessionInitializationFilter implements Filter, Ordered {

    private final SessionProperties sessionProperties;

    private final WebProperties webProperties;

    private final SpringServletHttpContainer container;

    public SessionInitializationFilter(SessionProperties sessionProperties, WebProperties webProperties,
                                       SpringServletHttpContainer container) {
        this.sessionProperties = sessionProperties;
        this.webProperties = webProperties;
        this.container = container;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest request) {
            HttpSession session = request.getSession(false);

            if (session == null) {
                session = request.getSession(true);
                session.setAttribute("sid", session.getId());
                session.setAttribute("ip", container.getClientIp());
                session.setAttribute("userAgent", request.getHeader("User-Agent"));
            } else {
                if (StringUtil.isNoneBlank(sessionProperties.getStrictModel())) {
                    if ("simple".equals(sessionProperties.getStrictModel()) || "strict".equals(sessionProperties.getStrictModel())) {
                        if (!ObjectUtils.nullSafeEquals(request.getHeader("User-Agent"), session.getAttribute("userAgent"))) {
                            throw new NoPermissionException("Illegal UserAgent");
                        }
                    }

                    if ("strict".equals(sessionProperties.getStrictModel())) {
                        if (!ObjectUtils.nullSafeEquals(container.getClientIp(), session.getAttribute("ip"))) {
                            throw new NoPermissionException("Illegal IP");
                        }
                    }
                }
            }

            ContextHolder.set(Ctx.CONTEXT_KEY_CLIENT_ID, session.getAttribute("sid"));

            if (StringUtil.urlMatches(request.getRequestURI(), sessionProperties.getResetSessionIdUrls())) {
                if (!session.isNew()) {
                    Map<String, Object> sessionAttributes = new HashMap<>();
                    Enumeration<String> attributeNames = session.getAttributeNames();

                    while (attributeNames.hasMoreElements()) {
                        String attrName = attributeNames.nextElement();
                        sessionAttributes.put(attrName, session.getAttribute(attrName));
                    }

                    session.invalidate();
                    HttpSession newSession = request.getSession(true);

                    for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
                        newSession.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
