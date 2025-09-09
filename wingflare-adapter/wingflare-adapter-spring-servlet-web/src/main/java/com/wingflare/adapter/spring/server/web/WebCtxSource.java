package com.wingflare.adapter.spring.server.web;

import com.wingflare.adapter.spring.server.web.utils.ServletUtil;

import com.wingflare.api.core.ContextSource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date 2023/04/04 04
 * @description web环境上下文来源
 */
public class WebCtxSource implements ContextSource {

    @Override
    public Map<String, String> all() {
        HttpServletRequest httpServletRequest = ServletUtil.getRequest();
        return ServletUtil.getHeaders(httpServletRequest);
    }
}
