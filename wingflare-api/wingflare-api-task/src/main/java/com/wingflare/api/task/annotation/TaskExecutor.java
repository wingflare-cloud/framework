package com.wingflare.api.task.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务执行者
 *
 * @author 奶嘴
 * @date 2025-09-04 22:18:01
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TaskExecutor {

    /**
     * 执行器名称
     */
    String name();

    /**
     * 执行器方法
     */
    String method() default "taskExecute";
}
