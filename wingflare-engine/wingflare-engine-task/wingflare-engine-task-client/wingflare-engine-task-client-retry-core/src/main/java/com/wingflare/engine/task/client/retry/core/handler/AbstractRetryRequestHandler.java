package com.wingflare.engine.task.client.retry.core.handler;


import com.wingflare.engine.task.client.common.config.SnailJobProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.common.rpc.openapi.AbstractRequestHandler;
import com.wingflare.engine.task.client.retry.core.openapi.RetryOpenApiClient;
import com.wingflare.engine.task.client.retry.core.openapi.RetryOpenApiClientV2;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.model.TaskOpenApiResult;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;

public abstract class AbstractRetryRequestHandler<T> extends AbstractRequestHandler<T> {
    RetryOpenApiClient client = RequestBuilder.<RetryOpenApiClient, TaskRpcResult>newBuilder()
            .client(RetryOpenApiClient.class)
            .async(false)
            .openapi(false)
            .build();

    RetryOpenApiClientV2 clientV2 = RequestBuilder.<RetryOpenApiClientV2, TaskOpenApiResult>newBuilder()
            .client(RetryOpenApiClientV2.class)
            .async(false)
            .openapi(true)
            .build();

    protected boolean isOpenApiV2() {
        SnailJobProperties properties = SnailSpringContext.getBean(SnailJobProperties.class);
        return properties.isOpenapiV2();
    }
}
