package com.wingflare.engine.task.client.common.rpc.server;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.config.TaskProperties.RpcServerProperties;
import com.wingflare.engine.task.client.common.config.TaskProperties.ThreadPoolConfig;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.rpc.client.grpc.GrpcChannel;
import com.wingflare.engine.task.client.common.rpc.supports.handler.TaskEngineDispatcherRequestHandler;
import com.wingflare.engine.task.client.common.rpc.supports.handler.grpc.UnaryRequestHandler;
import com.wingflare.engine.task.common.core.constant.GrpcServerConstants;
import com.wingflare.engine.task.common.core.enums.RpcTypeEnum;
import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.MethodDescriptor;
import io.grpc.Server;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.ServerCalls;
import io.grpc.util.MutableHandlerRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Grpc server
 *
 * @author: opensnail
 * @date : 2024-04-12 23:03
 * @since 3.3.0
 */
public class TaskEngineGrpcServer implements Lifecycle {
    private final TaskProperties taskProperties;
    private final TaskEngineDispatcherRequestHandler taskEngineDispatcherRequestHandler;
    private volatile boolean started = false;
    private Server server;

    public TaskEngineGrpcServer(TaskProperties taskProperties, TaskEngineDispatcherRequestHandler taskEngineDispatcherRequestHandler) {
        this.taskProperties = taskProperties;
        this.taskEngineDispatcherRequestHandler = taskEngineDispatcherRequestHandler;
    }

    public TaskProperties getSnailJobProperties() {
        return taskProperties;
    }

    public TaskEngineDispatcherRequestHandler getSnailDispatcherRequestHandler() {
        return taskEngineDispatcherRequestHandler;
    }

    public boolean isStarted() {
        return started;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void start() {
        if (started || RpcTypeEnum.GRPC != taskProperties.getRpcType()) {
            return;
        }

        RpcServerProperties grpc = taskProperties.getServerRpc();

        final MutableHandlerRegistry handlerRegistry = new MutableHandlerRegistry();
        addServices(handlerRegistry, new GrpcInterceptor());
        NettyServerBuilder builder = NettyServerBuilder.forPort(GrpcChannel.getClientPort())
            .executor(createGrpcExecutor(grpc.getDispatcherTp()));

        Duration keepAliveTime = grpc.getKeepAliveTime();
        Duration keepAliveTimeOut = grpc.getKeepAliveTimeout();
        Duration permitKeepAliveTime = grpc.getPermitKeepAliveTime();

        server = builder.maxInboundMessageSize(grpc.getMaxInboundMessageSize()).fallbackHandlerRegistry(handlerRegistry)
            .compressorRegistry(CompressorRegistry.getDefaultInstance())
            .decompressorRegistry(DecompressorRegistry.getDefaultInstance())
            .keepAliveTime(keepAliveTime.toMillis(), TimeUnit.MILLISECONDS)
            .keepAliveTimeout(keepAliveTimeOut.toMillis(), TimeUnit.MILLISECONDS)
            .permitKeepAliveWithoutCalls(true)
            .permitKeepAliveTime(permitKeepAliveTime.toMillis(), TimeUnit.MILLISECONDS)
            .build();
        try {
            server.start();
            this.started = true;
            TaskEngineLog.LOCAL.info("------> wingflare-task remoting server start success, grpc = {}, port = {}",
                TaskEngineGrpcServer.class.getName(), taskProperties.getPort());
        } catch (IOException e) {
            TaskEngineLog.LOCAL.error("--------> wingflare-task remoting server error.", e);
            started = false;
            throw new TaskClientException("wingflare-task server start error");
        }
    }

    @Override
    public int startSort() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void close() {
        if (server != null) {
            server.shutdownNow();
        }
    }

    @Override
    public int closeSort() {
        return Integer.MIN_VALUE;
    }

    private void addServices(MutableHandlerRegistry handlerRegistry, ServerInterceptor... serverInterceptor) {

        // 创建服务UNARY类型定义
        ServerServiceDefinition serviceDefinition = createUnaryServiceDefinition(
            GrpcServerConstants.UNARY_SERVICE_NAME, GrpcServerConstants.UNARY_METHOD_NAME,
            new UnaryRequestHandler(taskProperties.getServerRpc().getDispatcherTp(), taskEngineDispatcherRequestHandler));
        handlerRegistry.addService(serviceDefinition);
        handlerRegistry.addService(ServerInterceptors.intercept(serviceDefinition, serverInterceptor));
    }

    public static ServerServiceDefinition createUnaryServiceDefinition(
        String serviceName,
        String methodName,
        ServerCalls.UnaryMethod<TaskGrpcRequest, GrpcResult> unaryMethod) {

        MethodDescriptor<TaskGrpcRequest, GrpcResult> methodDescriptor =
            MethodDescriptor.<TaskGrpcRequest, GrpcResult>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(serviceName, methodName))
                .setRequestMarshaller(ProtoUtils.marshaller(TaskGrpcRequest.getDefaultInstance()))
                .setResponseMarshaller(ProtoUtils.marshaller(GrpcResult.getDefaultInstance()))
                .build();

        return ServerServiceDefinition.builder(serviceName)
            .addMethod(methodDescriptor, ServerCalls.asyncUnaryCall(unaryMethod))
            .build();
    }

    private ThreadPoolExecutor createGrpcExecutor(final ThreadPoolConfig threadPool) {
        ThreadPoolExecutor grpcExecutor = new ThreadPoolExecutor(threadPool.getCorePoolSize(),
            threadPool.getMaximumPoolSize(), threadPool.getKeepAliveTime(), TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(threadPool.getQueueCapacity()),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("wingflare-task-grpc-server-executor-%d")
                .build());
        grpcExecutor.allowCoreThreadTimeOut(true);
        return grpcExecutor;
    }
}
