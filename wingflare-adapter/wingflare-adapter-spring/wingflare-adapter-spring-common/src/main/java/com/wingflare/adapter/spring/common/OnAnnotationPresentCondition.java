package com.wingflare.adapter.spring.common;


import com.wingflare.adapter.spring.common.annotation.ConditionalOnAnnotationPresent;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 通用条件判断类，检查启动类上是否存在指定注解
 */
public class OnAnnotationPresentCondition implements Condition, TypeFilter {

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

        // 获取启动类
        String mainClassName = context.getEnvironment().getProperty("spring.main.main-class");
        if (mainClassName == null) {
            return false;
        }

        try {
            Class<?> mainClass = ClassUtils.forName(mainClassName, context.getClassLoader());
            // 检查启动类上是否存在指定注解
            return mainClass.isAnnotationPresent(annotationType);
        } catch (ClassNotFoundException | LinkageError e) {
            return false;
        }
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        return false;
    }
}
