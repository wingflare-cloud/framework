package com.wingflare.engine.task.client.retry.core.retryer;

import com.wingflare.engine.task.client.retry.core.strategy.ExecutorMethod;
import com.wingflare.engine.task.client.retry.core.strategy.ManualRetryStrategies;
import com.wingflare.engine.task.client.retry.core.strategy.RetryStrategy;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;

/**
 * 构建重试模板对象
 *
 * @author: opensnail
 * @date : 2023-05-10 10:17
 */
public class RetryTaskTemplateBuilder {

    private Class<? extends ExecutorMethod> executorMethodClass;
    private String scene;
    private Object[] params;

    public static RetryTaskTemplateBuilder newBuilder() {
        return new RetryTaskTemplateBuilder();
    }

    public RetryTaskTemplateBuilder withScene(String scene) {
        this.scene = scene;
        return this;
    }

    public RetryTaskTemplateBuilder withExecutorMethod(Class<? extends ExecutorMethod> executorMethod) {
        this.executorMethodClass = executorMethod;
        return this;
    }

    public RetryTaskTemplateBuilder withParam(Object params) {
        this.params = new Object[]{params};
        return this;
    }

    public SnailJobTemplate build() {
        SnailJobTemplate snailJobTemplate = new SnailJobTemplate();
        snailJobTemplate.setParams(params);
        snailJobTemplate.setExecutorMethodClass(executorMethodClass);
        snailJobTemplate.setScene(scene);
        RetryStrategy retryStrategy = SnailSpringContext.getBeanByType(ManualRetryStrategies.class);
        snailJobTemplate.setRetryStrategy(retryStrategy);
        return snailJobTemplate;
    }
}
