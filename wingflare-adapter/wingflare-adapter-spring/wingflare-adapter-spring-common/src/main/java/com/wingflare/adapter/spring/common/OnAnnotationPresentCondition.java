package com.wingflare.adapter.spring.common;


import com.wingflare.adapter.spring.common.annotation.ConditionalOnAnnotationPresent;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 通用条件判断类，检查启动类上是否存在指定注解
 */
public class OnAnnotationPresentCondition implements Condition, TypeFilter {

    private Class<?> mainClass;

    final class Tool {

        private static final OnAnnotationPresentCondition INSTANCE = new OnAnnotationPresentCondition();

    }

    private OnAnnotationPresentCondition() {
        // 获取当前线程的调用栈
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // 遍历调用栈，寻找包含main方法的类
        for (StackTraceElement element : stackTrace) {
            if ("main".equals(element.getMethodName())) {
                try {
                    this.mainClass = Class.forName(element.getClassName());
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取注解属性值，即需要检查的注解类型
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnAnnotationPresent.class.getName());

        if (attributes == null) {
            return false;
        }

        Class<? extends Annotation> annotationType = (Class<? extends Annotation>) attributes.get("value");

        if (annotationType == null) {
            return false;
        }

        if (Tool.INSTANCE.mainClass == null) {
            return false;
        }

        return Tool.INSTANCE.mainClass.isAnnotationPresent(annotationType);
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        return false;
    }
}
