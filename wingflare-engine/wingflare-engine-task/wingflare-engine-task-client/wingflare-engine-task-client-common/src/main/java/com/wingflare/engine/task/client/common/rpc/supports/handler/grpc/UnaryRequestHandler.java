package com.wingflare.engine.task.client.common.rpc.supports.handler.grpc;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.client.common.config.SnailJobProperties.ThreadPoolConfig;
import com.wingflare.engine.task.client.common.rpc.supports.handler.SnailDispatcherRequestHandler;
import com.wingflare.engine.task.client.common.rpc.supports.http.HttpRequest;
import com.wingflare.engine.task.client.common.rpc.supports.http.HttpResponse;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.Metadata;
import com.wingflare.engine.task.common.core.grpc.auto.SnailJobGrpcRequest;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: opensnail
 * @date : 2024-08-22
 */
public class UnaryRequestHandler implements ServerCalls.UnaryMethod<SnailJobGrpcRequest, GrpcResult> {


    private final ThreadPoolExecutor dispatcherThreadPool;
    private final SnailDispatcherRequestHandler dispatcher;

    public UnaryRequestHandler(final ThreadPoolConfig dispatcherThreadPool,
        final SnailDispatcherRequestHandler handler) {
        this.dispatcher = handler;
        this.dispatcherThreadPool = new ThreadPoolExecutor(
            dispatcherThreadPool.getCorePoolSize(), dispatcherThreadPool.getMaximumPoolSize(),
            dispatcherThreadPool.getKeepAliveTime(),
            dispatcherThreadPool.getTimeUnit(), new LinkedBlockingQueue<>(dispatcherThreadPool.getQueueCapacity()),
            new CustomizableThreadFactory("snail-grpc-server-"));
    }

    @Override
    public void invoke(final SnailJobGrpcRequest snailJobRequest, final StreamObserver<GrpcResult> streamObserver) {

        Metadata metadata = snailJobRequest.getMetadata();


        GrpcRequest grpcRequest = new GrpcRequest(new HttpResponse(), new HttpRequest(metadata.getHeadersMap(), metadata.getUri()));
        grpcRequest.setSnailJobRequest(snailJobRequest);

        // 执行任务
        dispatcherThreadPool.execute(() -> {
            SnailJobRpcResult snailJobRpcResult = null;
            try {
                snailJobRpcResult = dispatcher.dispatch(grpcRequest);
            } catch (Throwable e) {
                snailJobRpcResult = new SnailJobRpcResult(StatusEnum.NO.getStatus(), e.getMessage(), null, 0);
            } finally {
                GrpcResult grpcResult = GrpcResult.newBuilder()
                    .setStatus(Optional.ofNullable(snailJobRpcResult.getStatus()).orElse(StatusEnum.NO.getStatus()))
                    .setMessage(Optional.ofNullable(snailJobRpcResult.getMessage()).orElse(StrUtil.EMPTY))
                    .setData(JsonUtil.toJsonString(snailJobRpcResult.getData()))
                    .build();

                streamObserver.onNext(grpcResult);
                streamObserver.onCompleted();
            }
        });

    }
}
