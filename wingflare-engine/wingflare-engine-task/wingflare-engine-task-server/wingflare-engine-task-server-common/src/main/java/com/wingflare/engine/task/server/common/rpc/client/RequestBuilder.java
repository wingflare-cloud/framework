package com.wingflare.engine.task.server.common.rpc.client;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.server.common.dto.GrpcClientInvokeConfig;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.RegisterNodeInfo;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.rpc.client.grpc.GrpcClientInvokeHandlerV2;
import com.wingflare.lib.core.Builder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 构建请求类型
 *
 * @author: opensnail
 * @date : 2023-06-19 16:47
 * @since 2.0.0
 */
public class RequestBuilder<T, R> {

    private Class<T> clintInterface;
    private RegisterNodeInfo nodeInfo;
    private InstanceLiveInfo instanceLiveInfo;
    private boolean failRetry;
    private int retryTimes = 3;
    private int retryInterval = 1;
    private SnailJobRetryListener retryListener = new SimpleRetryListener();
    private boolean failover;
    private int routeKey;
    private String allocKey;
    private String targetLabels;
    private Integer executorTimeout;

    public static <T, R> RequestBuilder<T, R> newBuilder() {
        return new RequestBuilder<>();
    }

    public RequestBuilder<T, R> client(Class<T> clintInterface) {
        this.clintInterface = clintInterface;
        return this;
    }

    public RequestBuilder<T, R> nodeInfo(InstanceLiveInfo nodeInfo) {
        this.instanceLiveInfo = nodeInfo;
        return this;
    }

    public RequestBuilder<T, R> executorTimeout(Integer executorTimeout) {
        if (Objects.isNull(executorTimeout)) {
            return this;
        }

        Assert.isTrue(executorTimeout > 0, () -> new TaskServerException("executorTimeout can not less 0"));
        this.executorTimeout = executorTimeout;
        return this;
    }

    public RequestBuilder<T, R> failRetry(boolean failRetry) {
        this.failRetry = failRetry;
        return this;
    }

    public RequestBuilder<T, R> retryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public RequestBuilder<T, R> retryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
        return this;
    }

    public RequestBuilder<T, R> retryListener(SnailJobRetryListener retryListener) {
        this.retryListener = retryListener;
        return this;
    }

    public RequestBuilder<T, R> failover(boolean failover) {
        this.failover = failover;
        return this;
    }

    public RequestBuilder<T, R> routeKey(int routeKey) {
        this.routeKey = routeKey;
        return this;
    }

    public RequestBuilder<T, R> allocKey(String allocKey) {
        this.allocKey = allocKey;
        return this;
    }

    public RequestBuilder<T, R> targetLabels(String targetLabels) {
        this.targetLabels = targetLabels;
        return this;
    }

    public T build() {
        if (Objects.isNull(clintInterface)) {
            throw new TaskServerException("clintInterface cannot be null");
        }

        Assert.notNull(instanceLiveInfo, () -> new TaskServerException("instanceLiveInfo cannot be null"));
        Assert.notNull(instanceLiveInfo.getNodeInfo(), () -> new TaskServerException("nodeInfo cannot be null"));
        Assert.notBlank(instanceLiveInfo.getNodeInfo().getNamespaceId(), () -> new TaskServerException("namespaceId cannot be null"));

        if (failover) {
            Assert.isTrue(routeKey > 0, () -> new TaskServerException("routeKey cannot be null"));
            Assert.notBlank(allocKey, () -> new TaskServerException("allocKey cannot be null"));
        }
        try {
            clintInterface = (Class<T>) Class.forName(clintInterface.getName());
        } catch (Exception e) {
            throw new TaskServerException("class not found exception to: [{}]", clintInterface.getName());
        }

        GrpcClientInvokeConfig grpcClientInvokeConfig = Builder.of(GrpcClientInvokeConfig::new)
                .with(GrpcClientInvokeConfig::setInstanceLiveInfo, instanceLiveInfo)
                .with(GrpcClientInvokeConfig::setFailRetry, failRetry)
                .with(GrpcClientInvokeConfig::setRetryTimes, retryTimes)
                .with(GrpcClientInvokeConfig::setRetryInterval, retryInterval)
                .with(GrpcClientInvokeConfig::setRetryListener, retryListener)
                .with(GrpcClientInvokeConfig::setRouteKey, routeKey)
                .with(GrpcClientInvokeConfig::setAllocKey, allocKey)
                .with(GrpcClientInvokeConfig::setFailover, failover)
                .with(GrpcClientInvokeConfig::setExecutorTimeout, executorTimeout)
                .with(GrpcClientInvokeConfig::setAsync, false)
                .with(GrpcClientInvokeConfig::setTargetLabels, targetLabels)
                .build();

        InvocationHandler invocationHandler = new GrpcClientInvokeHandlerV2(grpcClientInvokeConfig);

        return (T) Proxy.newProxyInstance(clintInterface.getClassLoader(),
                new Class[]{clintInterface}, invocationHandler);
    }

}
