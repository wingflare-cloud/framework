package com.wingflare.engine.task.client.retry.handler;


import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.common.rpc.openapi.AbstractRequestHandler;
import com.wingflare.engine.task.client.retry.openapi.RetryOpenApiClient;
import com.wingflare.engine.task.client.retry.openapi.RetryOpenApiClientV2;
import com.wingflare.engine.task.common.core.model.TaskOpenApiResult;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.lib.container.Container;

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
        TaskProperties properties = Container.get(TaskProperties.class);
        return properties.isOpenapiV2();
    }
}
