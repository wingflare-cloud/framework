package com.wingflare.lib.task.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务类
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface WFTask {

    /**
     * task name
     */
    String value();

    /**
     * init handler, invoked when TaskThread init
     */
    String init() default "";

    /**
     * destroy handler, invoked when TaskThread destroy
     */
    String destroy() default "";

}
