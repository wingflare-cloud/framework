package com.wingflare.lib.spring.annotation;


import com.wingflare.lib.spring.listener.ConfigRefreshListener;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2022/3/6}
 * @description 启用配置刷新事件监听者
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ConfigRefreshListener.class})
@Documented
public @interface EnableConfigRefreshListener {
}
