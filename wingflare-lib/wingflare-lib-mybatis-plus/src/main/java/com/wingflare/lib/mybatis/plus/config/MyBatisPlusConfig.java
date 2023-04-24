package com.wingflare.lib.mybatis.plus.config;


import com.wingflare.lib.mybatis.plus.InnerInterceptorRegister;
import com.wingflare.lib.mybatis.plus.injector.CustomSqlInjector;
import com.wingflare.lib.mybatis.plus.plugin.MybatisPlusInterceptor;
import com.wingflare.lib.mybatis.plus.plugin.ResultSetHandlerInterceptor;
import com.wingflare.lib.mybatis.plus.plugin.handler.CustomMetaObjectHandler;
import com.wingflare.lib.mybatis.plus.plugin.handler.SelectHandlerInterface;
import com.wingflare.lib.mybatis.plus.plugin.handler.SelectWhereHandlerInterface;
import com.wingflare.lib.mybatis.plus.plugin.handler.SqlBeforePrepareInterface;
import com.wingflare.lib.mybatis.plus.plugin.inner.CustomInterceptor;
import com.wingflare.lib.spring.utils.SpringUtil;
import com.wingflare.lib.standard.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * MyBatisPlus配置类
 *
 * @author shaoyuyao
 * @date 2022/8/10 10:06
 */
@Configuration
public class MyBatisPlusConfig {

    @Resource
    private InnerInterceptorRegister register;

    /**
     * MybatisPlus拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        if (register != null) {
            interceptor.setInterceptors(register.getInnerInterceptors());
        }

        interceptor.addInnerInterceptor(customInterceptor());

        return interceptor;
    }

    /**
     * 结果集处理器拦截器
     */
    @Bean
    public ResultSetHandlerInterceptor resultSetHandlerInterceptor() {
        return new ResultSetHandlerInterceptor();
    }

    /**
     * 自定义拦截器
     */
    private CustomInterceptor customInterceptor() {
        return new CustomInterceptor(
                OrderUtil.asc(SpringUtil.getApplicationContext()
                        .getBeansOfType(SelectWhereHandlerInterface.class)
                        .values()),
                OrderUtil.asc(SpringUtil.getApplicationContext()
                        .getBeansOfType(SelectHandlerInterface.class)
                        .values()),
                OrderUtil.asc(SpringUtil.getApplicationContext()
                        .getBeansOfType(SqlBeforePrepareInterface.class)
                        .values())
        );
    }

    /**
     * 自定义元对象处理器，实现数据自动填充功能
     */
    @Bean
    public CustomMetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    /**
     * 自定义SQL注入器，注入自定义通用方法
     */
    @Bean
    public CustomSqlInjector customSqlInjector() {
        return new CustomSqlInjector();
    }

    @Autowired
    public MyBatisPlusConfig(SpringUtil springUtil) {
        // 让SpringContextHolder在MyBatisPlusConfig之前注入到Spring中，防止SpringContextHolder.getApplicationContext()报错
    }
}
