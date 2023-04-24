package com.wingflare.lib.mybatis.plus.annotation;


import com.wingflare.lib.mybatis.plus.plugin.handler.DynamicTableNameWhereHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2023/01/07}
 * @description 开启动态表名插件
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DynamicTableNameWhereHandler.class)
public @interface EnableDynamicTable {
}
