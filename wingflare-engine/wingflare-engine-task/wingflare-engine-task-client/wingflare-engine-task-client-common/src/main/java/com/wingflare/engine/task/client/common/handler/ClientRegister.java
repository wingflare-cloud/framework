package com.wingflare.engine.task.client.common.handler;

import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.config.SnailJobProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.constant.SystemConstants.BEAT;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.log.SnailJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: oepnsnail
 * @date : 2024-07-17
 * @since : 1.1.1
 */
@Component
public class ClientRegister implements Lifecycle {
    private static final ScheduledExecutorService SCHEDULE_EXECUTOR = Executors.newSingleThreadScheduledExecutor(
        r -> new Thread(r, "sj-client-register"));
    public static RpcClient CLIENT;
    public static final int REGISTER_TIME = 10;
    @Autowired
    private SnailJobProperties snailJobProperties;

    @Override
    public void start() {
        CLIENT = RequestBuilder.<RpcClient, SnailJobRpcResult>newBuilder()
                .client(RpcClient.class)
                .callback(
                        rpcResult -> {
                            if (StatusEnum.NO.getStatus().equals(rpcResult.getStatus())) {
                                SnailJobLog.LOCAL.error("heartbeat check requestId:[{}] message:[{}]", rpcResult.getReqId(), rpcResult.getMessage());
                            }
                        })
                .build();

        Map<String, String> labels = snailJobProperties.getLabels();

        Pair<String, String> defaultLabel = SystemConstants.DEFAULT_LABEL;
        labels.put(defaultLabel.getKey(), defaultLabel.getValue());

        SCHEDULE_EXECUTOR.scheduleAtFixedRate(() -> {
            try {
                CLIENT.beat(BEAT.PING, labels);
            } catch (Exception e) {
                SnailJobLog.LOCAL.error("Heartbeat sending failed", e);
            }
        }, 0, REGISTER_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void close() {

    }
}
