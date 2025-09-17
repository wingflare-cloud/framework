package com.wingflare.adapter.spring.servlet.web;


import com.wingflare.api.core.ContextSource;
import java.util.Map;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date 2023/04/04 04
 * @description web环境上下文来源
 */
public class WebCtxSource implements ContextSource {

    private final SpringServletHttpContainer container;

    public WebCtxSource(SpringServletHttpContainer container) {
        this.container = container;
    }

    @Override
    public Map<String, String> all() {
        return container.getHeaders();
    }

}
