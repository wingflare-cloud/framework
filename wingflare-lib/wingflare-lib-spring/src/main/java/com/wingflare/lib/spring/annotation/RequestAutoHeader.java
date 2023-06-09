package com.wingflare.lib.spring.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * feign客户端请求自动补充头信息
 *
 * @author naizui_ycx
 * @date Sun Feb 05 16:44:49 CST 2023
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAutoHeader {
}
