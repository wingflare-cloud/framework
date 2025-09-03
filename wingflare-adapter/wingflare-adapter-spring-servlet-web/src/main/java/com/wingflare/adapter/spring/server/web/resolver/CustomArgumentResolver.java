package com.wingflare.adapter.spring.server.web.resolver;


import com.wingflare.lib.core.constants.FormType;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/23}
 * @description 自定义参数解析器，增强@RequestBody注解。增加x-www-form-urlencoded请求支持
 */

public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 解析Content-Type为application/json的默认解析器是RequestResponseBodyMethodProcessor
     */
    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    /**
     * 解析Content-Type为application/x-www-form-urlencoded的默认解析器是ServletModelAttributeMethodProcessor
     */
    private ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor;

    /**
     * 全参构造
     */
    public CustomArgumentResolver(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor,
                                  ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
        this.servletModelAttributeMethodProcessor = servletModelAttributeMethodProcessor;
    }

    /**
     * 当参数前有@RequestBody注解时， 解析该参数 会使用此 解析器
     * <p>
     * 注:此方法的返回值将决定:是否使用此解析器解析该参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestBody.class);
    }

    /**
     * 解析参数
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {
        final String applicationXWWWFormUrlencoded = FormType.APPLICATION_X_WWW_FORM_URLENCODED;
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new RuntimeException("request must not be null!");
        }

        String contentType = request.getContentType();
        /*
         * 如果ContentType是application/x-www-form-urlencoded，那么使用ServletModelAttributeMethodProcessor解析器
         *
         * 注:其实默认的，当系统识别到参数前有@RequestBody注解时，就会走RequestResponseBodyMethodProcessor解析器;这里就
         *    相当于在走默认的解析器前走了个判断而已。
         */
        if (applicationXWWWFormUrlencoded.equals(contentType)) {
            return servletModelAttributeMethodProcessor.resolveArgument(methodParameter,
                    modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        return requestResponseBodyMethodProcessor.resolveArgument(methodParameter,
                modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
    }

}