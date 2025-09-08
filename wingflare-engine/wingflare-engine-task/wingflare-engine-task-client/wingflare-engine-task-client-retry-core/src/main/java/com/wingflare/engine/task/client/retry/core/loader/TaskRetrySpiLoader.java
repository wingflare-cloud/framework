package com.wingflare.engine.task.client.retry.core.loader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import com.wingflare.engine.task.client.retry.core.RetryArgSerializer;
import com.wingflare.engine.task.client.retry.core.RetrySiteSnapshotContext;
import com.wingflare.engine.task.client.retry.core.event.SimpleTaskRetryListener;
import com.wingflare.engine.task.client.retry.core.event.TaskListener;
import com.wingflare.engine.task.client.retry.core.expression.ExpressionInvocationHandler;
import com.wingflare.engine.task.client.retry.core.intercepter.ThreadLockRetrySiteSnapshotContext;
import com.wingflare.engine.task.client.retry.core.serializer.ForySerializer;
import com.wingflare.engine.task.client.retry.core.serializer.FurySerializer;
import com.wingflare.engine.task.common.core.expression.ExpressionEngine;
import com.wingflare.engine.task.common.core.expression.ExpressionFactory;
import com.wingflare.engine.task.common.core.expression.strategy.SpELExpressionEngine;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Snail Job Spi加载器
 *
 * @author: opensnail
 * @date : 2023-08-07 18:05
 * @since 2.2.0
 */
public class TaskRetrySpiLoader {

    private TaskRetrySpiLoader() {
    }

    /**
     * 加载参数序列化SPI类
     * 若配置多个则只加载第一个
     *
     * @return {@link com.wingflare.engine.task.client.retry.core.serializer.JacksonSerializer} 默认序列化类为FurySerializer
     */
    public static RetryArgSerializer loadRetryArgSerializer() {
        return Optional.ofNullable(ServiceLoaderUtil.loadFirst(RetryArgSerializer.class)).orElse(new FurySerializer());
    }

    /**
     * 加载指定名称的参数序列化SPI类
     *
     * @return {@link com.wingflare.engine.task.client.retry.core.serializer.JacksonSerializer} 默认序列化类为JacksonSerializer
     */
    public static RetryArgSerializer loadRetryArgSerializer(String name) {
        return ServiceLoaderUtil.loadList(RetryArgSerializer.class)
                .stream()
                .filter(retryArgSerializer -> retryArgSerializer.name().equals(name))
                .findAny()
                .orElse(new ForySerializer());
    }

    /**
     * 加载重试监听器SPI类
     * 执行顺序按照文件中定义的实现类的先后顺序
     *
     * @return {@link SimpleTaskRetryListener} 默认序列化类为SimpleSnailJobListener
     */
    public static List<TaskListener> loadSnailJobListener() {
        List<TaskListener> taskListeners = ServiceLoaderUtil.loadList(TaskListener.class);
        if (CollUtil.isEmpty(taskListeners)) {
            return Collections.singletonList(new SimpleTaskRetryListener());
        }

        return taskListeners;
    }

    /**
     * 加载重试现场记录上下文SPI类
     *
     * @return {@link RetrySiteSnapshotContext} 默认序列化类为ThreadLockRetrySiteSnapshotContext
     */
    public static <T> RetrySiteSnapshotContext<T> loadRetrySiteSnapshotContext() {
        return Optional.ofNullable(ServiceLoaderUtil.loadFirst(RetrySiteSnapshotContext.class))
                .orElse(new ThreadLockRetrySiteSnapshotContext<T>(new ThreadLocal<>()));
    }

    /**
     * 表达式引擎SPI类
     *
     * @return {@link SpELExpressionEngine} 默认序列化类为SpELExpressionEngine
     */
    public static ExpressionEngine loadExpressionEngine() {
        ExpressionEngine expressionEngine = Optional.ofNullable(ServiceLoaderUtil.loadFirst(ExpressionEngine.class))
                .orElse(new SpELExpressionEngine());
        return ExpressionFactory.getExpressionEngine(new ExpressionInvocationHandler(expressionEngine));
    }

}
