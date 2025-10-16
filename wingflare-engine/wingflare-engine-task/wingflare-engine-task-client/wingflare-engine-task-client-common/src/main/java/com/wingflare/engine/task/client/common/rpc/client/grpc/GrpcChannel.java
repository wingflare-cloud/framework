package com.wingflare.engine.task.client.common.rpc.client.grpc;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.exception.TaskRemoteException;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.ExecutorTypeEnum;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.Metadata;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.config.ConfigUtil;
import com.wingflare.lib.container.Container;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: opensnail
 * @date : 2024-08-22
 */
public final class GrpcChannel {

    private static ManagedChannel channel;
    public static void setChannel(ManagedChannel channel) {
        GrpcChannel.channel = channel;
    }

    /**
     * 服务端端口
     */
    private static final String TASK_ENGINE_SERVER_PORT = "task.server.port";
    /**
     * 服务端host
     */
    private static final String TASK_ENGINE_SERVER_HOST = "task.server.host";

    /**
     * 客户端端口
     */
    private static final String TASK_ENGINE_CLIENT_PORT = "task.port";

    private static final Integer MIN_PORT = 15000;
    private static final Integer MAX_PORT = 50000;
    private static final ReentrantLock PORT_LOCK = new ReentrantLock();
    private static final Integer RANDOM_CLIENT_PORT = -1;

    private static final String HOST_ID = IdUtil.getSnowflake().nextIdStr();
    private static final int PORT;

    static {
        PORT = Integer.parseInt(System.getProperty(TASK_ENGINE_CLIENT_PORT, String.valueOf(1789)));
    }

    /**
     * 获取服务端端口
     *
     * @return port
     */
    public static int getServerPort() {
        TaskProperties taskProperties = Container.get(TaskProperties.class);
        TaskProperties.ServerConfig serverConfig = taskProperties.getServer();

        String port = System.getProperty(TASK_ENGINE_SERVER_PORT);
        if (StrUtil.isBlank(port)) {
            System.setProperty(TASK_ENGINE_SERVER_PORT, String.valueOf(serverConfig.getPort()));
        }

        return Integer.parseInt(System.getProperty(TASK_ENGINE_SERVER_PORT));
    }

    /**
     * 获取服务端host
     *
     * @return host
     */
    public static String getServerHost() {
        TaskProperties taskProperties = Container.get(TaskProperties.class);
        TaskProperties.ServerConfig serverConfig = taskProperties.getServer();

        String host = System.getProperty(TASK_ENGINE_SERVER_HOST);
        if (StrUtil.isBlank(host)) {
            System.setProperty(TASK_ENGINE_SERVER_HOST, serverConfig.getHost());
        }

        return System.getProperty(TASK_ENGINE_SERVER_HOST);
    }

    /**
     * 获取指定的客户IP
     *
     * @return 客户端IP
     */
    public static String getClientHost() {
        TaskProperties taskProperties = Container.get(TaskProperties.class);
        return taskProperties.getHost();
    }

    /**
     * 获取客户端端口
     *
     * @return port 端口
     */
    public static Integer getClientPort() {
        TaskProperties taskProperties = Container.get(TaskProperties.class);
        Integer port = taskProperties.getPort();
        // 获取客户端指定的端口
        if (Objects.isNull(port)) {
            Long sp = ConfigUtil.getLongProperty("server.port");
            port = Optional.ofNullable(sp != null ? sp.intValue() : null).orElse(PORT);
            taskProperties.setPort(port);
            TaskEngineLog.LOCAL.info("task engine client port :{}", port);
        } else if (port.equals(RANDOM_CLIENT_PORT)) {
            // 使用随机算法获取端口
            PORT_LOCK.lock();
            try {
                // 双重检查，避免重复获取端口
                if (taskProperties.getPort().equals(RANDOM_CLIENT_PORT)) {
                    port = getAvailablePort();
                    taskProperties.setPort(port);
                    TaskEngineLog.LOCAL.info("task engine client port :{}", port);
                } else {
                    port = taskProperties.getPort();
                }
            } finally {
                PORT_LOCK.unlock();
            }
        }

        return port;
    }


    /**
     * 获取随机可用的端口
     *
     * @return 可用端口号
     */
    private static Integer getAvailablePort() {
        int port;
        do {
            port = MIN_PORT + (int) (Math.random()*(MAX_PORT - MIN_PORT));
        }while (!isPortAvailable(port));

        return port;
    }

    /**
     * 检查端口是否可以使用
     *
     * @param port 端口号
     * @return 是否可用
     */
    private static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            // 设置端口重用
            serverSocket.setReuseAddress(true);
            // 绑定端口
            serverSocket.bind(new InetSocketAddress(port));
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static ListenableFuture<GrpcResult> sendOfUnary(String path, String body, long reqId, Map<String, String> map) {
        if (channel == null) {
            return null;
        }

        TaskProperties taskProperties = Container.get(TaskProperties.class);

        // server配置不能为空
        TaskProperties.ServerConfig serverConfig = taskProperties.getServer();
        if (Objects.isNull(serverConfig)) {
            TaskEngineLog.LOCAL.error("snail job server config is null");
            return null;
        }

        Assert.notBlank(taskProperties.getGroup(),
            () -> new TaskRemoteException("The group is null, please check if your configuration is correct."));

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put(HeadersEnum.HOST_ID.getKey(), HOST_ID);
        headersMap.put(HeadersEnum.HOST_IP.getKey(), getClientHost());
        headersMap.put(HeadersEnum.GROUP_NAME.getKey(), taskProperties.getGroup());
        headersMap.put(HeadersEnum.HOST_PORT.getKey(), String.valueOf(getClientPort()));
        headersMap.put(HeadersEnum.VERSION.getKey(), String.valueOf(GroupVersionCache.getVersion()));
        headersMap.put(HeadersEnum.HOST.getKey(), serverConfig.getHost());
        headersMap.put(HeadersEnum.NAMESPACE.getKey(), Optional.ofNullable(taskProperties.getNamespace()).orElse(
            SystemConstants.DEFAULT_NAMESPACE));
        headersMap.put(HeadersEnum.TOKEN.getKey(), Optional.ofNullable(taskProperties.getToken()).orElse(
            SystemConstants.DEFAULT_TOKEN));
        headersMap.put(HeadersEnum.SYSTEM_VERSION.getKey(), Optional.ofNullable(TaskVersion.getVersion()).orElse(
                SystemConstants.DEFAULT_CLIENT_VERSION));
        headersMap.put(HeadersEnum.EXECUTOR_TYPE.getKey(), String.valueOf(ExecutorTypeEnum.JAVA.getType()));
        if (CollUtil.isNotEmpty(map)) {
            headersMap.putAll(map);
        }

        Metadata metadata = Metadata
            .newBuilder()
            .setUri(path)
            .putAllHeaders(headersMap)
            .build();
        TaskGrpcRequest jobRequest = TaskGrpcRequest
            .newBuilder()
            .setMetadata(metadata)
            .setReqId(reqId)
            .setBody(body)
            .build();

        MethodDescriptor<TaskGrpcRequest, GrpcResult> methodDescriptor =
            MethodDescriptor.<TaskGrpcRequest, GrpcResult>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName("UnaryRequest", "unaryRequest"))
                .setRequestMarshaller(ProtoUtils.marshaller(TaskGrpcRequest.getDefaultInstance()))
                .setResponseMarshaller(ProtoUtils.marshaller(GrpcResult.getDefaultInstance()))
                .build();

        // 创建动态代理调用方法
        return io.grpc.stub.ClientCalls.futureUnaryCall(
            channel.newCall(methodDescriptor, io.grpc.CallOptions.DEFAULT),
                jobRequest);
    }

}
