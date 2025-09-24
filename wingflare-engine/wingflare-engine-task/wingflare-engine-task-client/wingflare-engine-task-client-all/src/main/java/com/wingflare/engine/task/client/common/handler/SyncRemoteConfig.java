package com.wingflare.engine.task.client.common.handler;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: opensnail
 * @date : 2024-05-08
 * @since : sj_1.0.0
 */
@Component
public class SyncRemoteConfig implements Lifecycle {
    private static RpcClient CLIENT;
    private static final ScheduledExecutorService SCHEDULE_EXECUTOR = Executors.newSingleThreadScheduledExecutor(
            r -> new Thread(r, "sync-remote-config"));

    @Override
    public void start() {
        CLIENT = RequestBuilder.<RpcClient, TaskRpcResult>newBuilder()
                .client(RpcClient.class)
                .timeout(1000L)
                .callback(rpcResult -> {
                    if (Objects.isNull(rpcResult.getData())) {
                        TaskEngineLog.LOCAL.debug("Configuration retrieval result is null");
                        return;
                    }

                    GroupVersionCache.setConfig(
                            JsonUtil.parseObject(rpcResult.getData().toString(), ConfigRequest.class));
                }).build();

        SCHEDULE_EXECUTOR.scheduleAtFixedRate(() -> {
            try {
                CLIENT.syncRemoteConfig();
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("Notification configuration failed", e);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void close() {
    }
}
