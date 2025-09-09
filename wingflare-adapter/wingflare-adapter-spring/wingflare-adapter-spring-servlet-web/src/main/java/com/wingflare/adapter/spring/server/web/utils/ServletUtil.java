package com.wingflare.adapter.spring.server.web.utils;


import com.wingflare.lib.core.utils.ConvertUtil;
import com.wingflare.lib.core.utils.StringUtil;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2021/12/16}
 * @email chenxi2511@qq.com
 * @description Servlet工具类
 */
public class ServletUtil {

    public static String getClientIpAddr() {
        HttpServletRequest request = getRequest();

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

        return request.getRemoteAddr();
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return ConvertUtil.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return ConvertUtil.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return ConvertUtil.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name) {
        return ConvertUtil.toBool(getRequest().getParameter(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return ConvertUtil.toBool(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        HttpSession httpSession = getRequest().getSession();
        Assert.notNull(httpSession, "httpSession is null");
        return httpSession;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes, "requestAttributes is null");
        return (ServletRequestAttributes) attributes;
    }

    public static String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StringUtil.isEmpty(value)) {
            return StringUtil.EMPTY;
        }
        return StringUtil.urlDecode(value);
    }

    public static String getHeader(String name) {
        String value = getRequest().getHeader(name);
        if (StringUtil.isEmpty(value)) {
            return StringUtil.EMPTY;
        }
        return StringUtil.urlDecode(value);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtil.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtil.inStringIgnoreCase(ajax, "json", "xml");
    }

}