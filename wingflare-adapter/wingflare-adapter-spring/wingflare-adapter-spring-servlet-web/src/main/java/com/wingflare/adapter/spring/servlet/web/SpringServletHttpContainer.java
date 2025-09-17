package com.wingflare.adapter.spring.servlet.web;


import com.wingflare.adapter.spring.common.configure.properties.WebProperties;
import com.wingflare.api.http.HttpContainer;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.lib.core.utils.IPAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Spring Servlet环境下的Http容器实现
 */
public class SpringServletHttpContainer implements HttpContainer {

    private final WebProperties webProperties;

    public SpringServletHttpContainer(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Objects.requireNonNull(attributes, "当前上下文没有请求属性");
        return attributes.getRequest();
    }

    private boolean isTrustedProxy(String clientIp) {
        return IPAddressUtil.isIpInRange(clientIp, webProperties.getTrustedProxies());
    }

    @Override
    public String getClientIp() {
        // 检查是否可信代理环境
        boolean trustedEnvironment = isTrustedProxy(getRequest().getRemoteAddr());

        if (trustedEnvironment) {
            String[] headersToCheck = {
                    HttpHeaderConstants.REQUEST_X_FORWARDED_FOR,
                    HttpHeaderConstants.REQUEST_PROXY_CLIENT_IP,
                    HttpHeaderConstants.REQUEST_WL_PROXY_CLIENT_IP,
                    HttpHeaderConstants.REQUEST_HTTP_CLIENT_IP,
                    HttpHeaderConstants.REQUEST_X_REAL_IP,
            };

            for (String header : headersToCheck) {
                String ipAddresses = getRequest().getHeader(header);
                if (ipAddresses != null && !"unknown".equalsIgnoreCase(ipAddresses)) {
                    return ipAddresses.split(",")[0].trim();
                }
            }
        }

        return getRequest().getRemoteAddr();
    }

    @Override
    public String getParam(String name) {
        return getRequest().getParameter(name);
    }

    @Override
    public String getParam(String name, String defaultValue) {
        String value = getParam(name);
        return value != null ? value : defaultValue;
    }

    @Override
    public String getQueryString() {
        // query参数本质上也是请求参数的一种
        return getRequest().getQueryString();
    }

    @Override
    public String getRawBody() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getRequest().getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("获取请求体失败", e);
        }
    }

    @Override
    public String getHeader(String name) {
        return getRequest().getHeader(name);
    }

    @Override
    public List<String> getHeaders(String name) {
        return Collections.list(getRequest().getHeaders(name));
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = getRequest().getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = getRequest().getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    @Override
    public Map<String, List<String>> getAllHeaders() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = getRequest().getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = getRequest().getHeader(key);

                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }

                map.get(key).add(value);
            }
        }
        return map;
    }

    @Override
    public String getSessionId() {
        HttpSession session = getRequest().getSession(false);
        return session != null ? session.getId() : null;
    }

    @Override
    public Object getSessionAttr(String name) {
        HttpSession session = getRequest().getSession(false);
        return session != null ? session.getAttribute(name) : null;
    }

    @Override
    public Map<String, Object> getSessionAttrs() {
        HttpSession session = getRequest().getSession(false);

        if (session == null) {
            return Map.of();
        }

        return Collections.list(session.getAttributeNames()).stream()
                .collect(Collectors.toMap(name -> name, session::getAttribute));
    }

    @Override
    public void setSessionAttr(String name, Object value) {
        HttpSession session = getRequest().getSession();
        session.setAttribute(name, value);
    }

    @Override
    public void removeSessionAttr(String name) {
        HttpSession session = getRequest().getSession(false);
        if (session != null) {
            session.removeAttribute(name);
        }
    }

}
