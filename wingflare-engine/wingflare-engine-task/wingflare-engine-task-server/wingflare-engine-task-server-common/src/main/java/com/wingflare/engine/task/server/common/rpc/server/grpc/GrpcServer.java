package com.wingflare.engine.task.server.common.rpc.server.grpc;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.api.lifecycle.ServerStopException;
import com.wingflare.engine.task.common.core.constant.GrpcServerConstants;
import com.wingflare.engine.task.common.core.enums.RpcTypeEnum;
import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.config.SystemProperties.RpcServerProperties;
import com.wingflare.engine.task.server.common.config.SystemProperties.ThreadPoolConfig;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
 * netty server
 *
 * @author: opensnail
 * @date : 2022-03-07 15:54
 * @since 1.0.0
 */
public class GrpcServer implements Lifecycle {

    private final SystemProperties systemProperties;
    private volatile boolean started = false;
    private Server server;

    public GrpcServer(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public SystemProperties getSystemProperties() {
        return systemProperties;
    }

    public boolean isStarted() {
        return started;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void start() {
        // 防止重复启动
        if (started) {
            return;
        }

        if (RpcTypeEnum.GRPC != systemProperties.getRpcType()) {
            return;
        }

        RpcServerProperties grpc = systemProperties.getServerRpc();

        final MutableHandlerRegistry handlerRegistry = new MutableHandlerRegistry();
        addServices(handlerRegistry, new GrpcInterceptor());
        NettyServerBuilder builder = NettyServerBuilder.forPort(systemProperties.getServerPort())
                .executor(createGrpcExecutor(grpc.getDispatcherTp()));

        Duration keepAliveTime = grpc.getKeepAliveTime();
        Duration keepAliveTimeOut = grpc.getKeepAliveTimeout();
        Duration permitKeepAliveTime = grpc.getPermitKeepAliveTime();

        server = builder.maxInboundMessageSize(grpc.getMaxInboundMessageSize()).fallbackHandlerRegistry(handlerRegistry)
                .compressorRegistry(CompressorRegistry.getDefaultInstance())
                .decompressorRegistry(DecompressorRegistry.getDefaultInstance())
                .keepAliveTime(keepAliveTime.toMillis(), TimeUnit.MILLISECONDS)
                .keepAliveTimeout(keepAliveTimeOut.toMillis(), TimeUnit.MILLISECONDS)
                .permitKeepAliveTime(permitKeepAliveTime.toMillis(), TimeUnit.MILLISECONDS)
                .permitKeepAliveWithoutCalls(true)
                .build();
        try {
            server.start();
            this.started = true;
            TaskEngineLog.LOCAL.info("------> wingflare-task remoting server start success, grpc = {}, port = {}",
                    GrpcServer.class.getName(), systemProperties.getServerPort());
        } catch (IOException e) {
            TaskEngineLog.LOCAL.error("--------> wingflare-task remoting server error.", e);
            started = false;
            throw new ServerStopException("wingflare-task server start error");
        }
    }

    @Override
    public int startSort() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int closeSort() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void close() {
        if (server != null) {
            server.shutdownNow();
        }
    }

    private void addServices(MutableHandlerRegistry handlerRegistry, ServerInterceptor... serverInterceptor) {

        // 创建服务UNARY类型定义
        ServerServiceDefinition serviceDefinition = createUnaryServiceDefinition(
                GrpcServerConstants.UNARY_SERVICE_NAME, GrpcServerConstants.UNARY_METHOD_NAME,
                new UnaryRequestHandler());
        handlerRegistry.addService(serviceDefinition);
        // unary common call register.

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
