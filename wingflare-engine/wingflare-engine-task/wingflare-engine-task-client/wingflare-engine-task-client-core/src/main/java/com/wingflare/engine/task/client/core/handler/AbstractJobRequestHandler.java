package com.wingflare.engine.task.client.core.handler;

import com.wingflare.engine.task.client.common.config.SnailJobProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.common.rpc.openapi.AbstractRequestHandler;
import com.wingflare.engine.task.client.core.openapi.JobOpenApiClient;
import com.wingflare.engine.task.client.core.openapi.JobOpenApiClientV2;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.model.TaskOpenApiResult;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;

/**
 * @author opensnail
 * @date 2024-09-29 20:40:10
 * @since sj_1.1.0
 */
public abstract class AbstractJobRequestHandler<R> extends AbstractRequestHandler<R> {

    @Deprecated(since = "1.7.0")
    protected JobOpenApiClient client = RequestBuilder.<JobOpenApiClient, TaskRpcResult>newBuilder()
            .client(JobOpenApiClient.class)
            .async(false)
            // 走openapi模式
            .openapi(false)
            .build();

    protected JobOpenApiClientV2 clientV2 = RequestBuilder.<JobOpenApiClientV2, TaskOpenApiResult>newBuilder()
            .client(JobOpenApiClientV2.class)
            .async(false)
            // 走openapi模式
            .openapi(true)
            .build();

    protected boolean isOpenApiV2() {
        SnailJobProperties properties = SnailSpringContext.getBean(SnailJobProperties.class);
        return properties.isOpenapiV2();
    }
}
