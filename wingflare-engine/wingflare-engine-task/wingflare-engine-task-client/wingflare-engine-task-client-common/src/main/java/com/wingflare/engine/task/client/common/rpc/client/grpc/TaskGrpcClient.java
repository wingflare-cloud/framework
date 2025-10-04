package com.wingflare.engine.task.client.common.rpc.client.grpc;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.config.TaskProperties.RpcClientProperties;
import com.wingflare.engine.task.client.common.config.TaskProperties.ThreadPoolConfig;
import com.wingflare.engine.task.client.common.event.TaskChannelReconnectEvent;
import com.wingflare.engine.task.common.core.enums.RpcTypeEnum;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.container.Container;
import io.grpc.ConnectivityState;
import io.grpc.DecompressorRegistry;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author: opensnail
 * @date : 2024-08-22
 */
public class TaskGrpcClient implements Lifecycle {
    private ManagedChannel channel;
    private final TaskProperties taskProperties;
    private static final ScheduledExecutorService SCHEDULE_EXECUTOR = Executors.newSingleThreadScheduledExecutor(
            r -> new Thread(r, "sj-client-check"));

    public TaskGrpcClient(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @Override
    public void start() {
        if (RpcTypeEnum.GRPC != taskProperties.getRpcType()) {
            return;
        }

        channel = connection();
        GrpcChannel.setChannel(channel);
        TaskEngineLog.LOCAL.info("grpc client started connect to server");

        // 连接检测
        SCHEDULE_EXECUTOR.scheduleAtFixedRate(() -> {
            ConnectivityState state = channel.getState(true);
            if (state == ConnectivityState.TRANSIENT_FAILURE) {
                try {
                    // 抛出重连事件
                    Container.get(EventPublisher.class).publishEvent(new TaskChannelReconnectEvent());

                    channel = connection();
                    GrpcChannel.setChannel(channel);
                } catch (Exception e) {
                    TaskEngineLog.LOCAL.error("reconnect error ", e);
                }
            }
        }, 0, 10, TimeUnit.SECONDS);

    }

    @Override
    public int startSort() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void close() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
    }

    @Override
    public int closeSort() {
        return Integer.MIN_VALUE;
    }

    public ManagedChannel connection() {
        RpcClientProperties clientRpc = taskProperties.getClientRpc();
        // 创建 gRPC 频道
        String serverHost = GrpcChannel.getServerHost();
        return NettyChannelBuilder.forAddress(serverHost, GrpcChannel.getServerPort())
                .executor(createGrpcExecutor(serverHost))
                .decompressorRegistry(DecompressorRegistry.getDefaultInstance())
                .maxInboundMessageSize(clientRpc.getMaxInboundMessageSize())
                .keepAliveTime(clientRpc.getKeepAliveTime().toMillis(), TimeUnit.MILLISECONDS)
                .keepAliveTimeout(clientRpc.getKeepAliveTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .idleTimeout(clientRpc.getIdleTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .usePlaintext().enableRetry().maxRetryAttempts(16)
                .build();
    }

    private ThreadPoolExecutor createGrpcExecutor(String serverIp) {
        RpcClientProperties clientRpc = taskProperties.getClientRpc();
        ThreadPoolConfig threadPool = clientRpc.getClientTp();
        serverIp = serverIp.replaceAll("%", "-");
        ThreadPoolExecutor grpcExecutor = new ThreadPoolExecutor(threadPool.getCorePoolSize(),
            threadPool.getMaximumPoolSize(), threadPool.getKeepAliveTime(), TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(threadPool.getQueueCapacity()),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("wingflare-task-grpc-client-executor-" + serverIp + "-%d")
                .build());
        grpcExecutor.allowCoreThreadTimeOut(true);
        return grpcExecutor;
    }
}
