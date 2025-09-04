package com.wingflare.api.task.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 奶嘴
 * @date : 2025-07-22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapExecutor {

    /**
     * 任务名称
     *
     * @return
     */
    String taskName() default "ROOT_MAP";

}
