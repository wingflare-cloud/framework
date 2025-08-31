package com.wingflare.engine.task.server.web.interceptor;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author opensnail
 * @date 2022-03-06
 * @since 2.0
 */
@Configuration
public class SnailJobWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Resource
    private LoginUserMethodArgumentResolver loginUserMethodArgumentResolver;
    @Resource
    private CORSInterceptor corsInterceptor;
    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
        // 配置拦截的路径
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserMethodArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");
    }

}
