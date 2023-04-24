package com.wingflare.adapter.spring.server.web.configure;


import com.wingflare.adapter.spring.server.web.resolver.CustomArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2021/12/23}
 * @email chenxi2511@qq.com
 * @description 参数解析配置
 */
@Configuration
public class ConfigArgumentResolvers {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public ConfigArgumentResolvers(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    private void addArgumentResolvers() {
        // 获取到的是不可变的集合
        List<HandlerMethodArgumentResolver> argumentResolvers =
                requestMappingHandlerAdapter.getArgumentResolvers();
        CustomArgumentResolver customArgumentResolver =
                getMyHandlerMethodArgumentResolver(argumentResolvers);
        // ha.getArgumentResolvers()获取到的是不可变的集合,所以我们需要新建一个集合来放置参数解析器
        List<HandlerMethodArgumentResolver> myArgumentResolvers =
                new ArrayList<>(argumentResolvers.size() + 2);
        // 将自定义的解析器，放置在第一个； 并保留原来的解析器
        myArgumentResolvers.add(customArgumentResolver);
        myArgumentResolvers.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(myArgumentResolvers);
    }

    /**
     * 获取CustomArgumentResolver实例
     */
    private CustomArgumentResolver getMyHandlerMethodArgumentResolver(
            List<HandlerMethodArgumentResolver> argumentResolversList) {
        // 解析Content-Type为application/json的默认解析器
        RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor = null;
        // 解析Content-Type为application/x-www-form-urlencoded的默认解析器
        ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor = null;

        if (argumentResolversList == null) {
            throw new RuntimeException("argumentResolverList must not be null!");
        }

        for (HandlerMethodArgumentResolver argumentResolver : argumentResolversList) {
            if (requestResponseBodyMethodProcessor != null && servletModelAttributeMethodProcessor != null) {
                break;
            }
            if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) argumentResolver;
                continue;
            }
            if (argumentResolver instanceof ServletModelAttributeMethodProcessor) {
                servletModelAttributeMethodProcessor = (ServletModelAttributeMethodProcessor) argumentResolver;
            }
        }

        if (requestResponseBodyMethodProcessor == null || servletModelAttributeMethodProcessor == null) {
            throw new RuntimeException("requestResponseBodyMethodProcessor and "
                    + " servletModelAttributeMethodProcessor must not be null!");
        }

        return new CustomArgumentResolver(requestResponseBodyMethodProcessor, servletModelAttributeMethodProcessor);
    }

}
