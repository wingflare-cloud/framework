package com.wingflare.engine.task.server.retry.support.dispatch;

import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.RetryCallbackRequest;
import com.wingflare.engine.task.server.common.dto.*;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.server.common.rpc.client.TaskEngineRetryListener;
import com.wingflare.engine.task.server.common.rpc.client.grpc.GrpcClientInvokeHandlerV2;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.client.RetryRpcClient;
import com.wingflare.engine.task.server.retry.dto.RequestCallbackExecutorDTO;
import com.wingflare.engine.task.server.retry.dto.RetryExecutorResultDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.RetryTaskLogConverter;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import com.wingflare.task.datasource.template.persistence.po.RetryTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryException;
import com.google.common.collect.Maps;
import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;
import java.util.Objects;

/**
 * @author opensnail
 * @date 2023-10-06 16:42:08
 * @since 2.4.0
 */
@Component(ActorGenerator.REAL_CALLBACK_EXECUTOR_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestCallbackClientActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(RequestCallbackClientActor.class);
    private final RetryTaskMapper retryTaskMapper;
    private final RetryMapper retryMapper;
    private final InstanceManager instanceManager;

    public RequestCallbackClientActor(RetryTaskMapper retryTaskMapper, RetryMapper retryMapper, InstanceManager instanceManager) {
        this.retryTaskMapper = retryTaskMapper;
        this.retryMapper = retryMapper;
        this.instanceManager = instanceManager;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RequestCallbackExecutorDTO.class, executorDTO -> {
            try {
                doCallback(executorDTO);
            } catch (Exception e) {
                log.error("Client request exception occurred", e);
            } finally {
                getContext().stop(getSelf());
            }
        }).build();
    }

    private void doCallback(RequestCallbackExecutorDTO executorDTO) {
        long nowMilli = DateUtils.toNowMilli();
        // 检查客户端是否存在
        InstanceLiveInfo instanceLiveInfo = instanceManager.getInstanceALiveInfoSet(Builder.of(InstanceKey::new)
                .with(InstanceKey::setNamespaceId, executorDTO.getNamespaceId())
                .with(InstanceKey::setGroupName, executorDTO.getGroupName())
                .with(InstanceKey::setHostId, executorDTO.getClientId())
                .build());
        if (Objects.isNull(instanceLiveInfo)) {
            taskExecuteFailure(executorDTO, "Client does not exist");
            JobLogMetaDTO jobLogMetaDTO = RetryTaskConverter.INSTANCE.toJobLogDTO(executorDTO);
            jobLogMetaDTO.setTimestamp(nowMilli);
            TaskEngineLog.REMOTE.error("RetryTaskId:[{}] Task scheduling failed. Reason: No executable client <|>{}<|>", executorDTO.getRetryTaskId(),
                    jobLogMetaDTO);
            return;
        }

        RetryCallbackRequest retryCallbackRequest = RetryTaskConverter.INSTANCE.toRetryCallbackDTO(executorDTO);
        Retry retry = retryMapper.selectOne(new LambdaQueryWrapper<Retry>()
                .select(Retry::getRetryStatus, Retry::getId)
                .eq(Retry::getId, executorDTO.getParentId()));
        if (Objects.isNull(retry)) {
            JobLogMetaDTO jobLogMetaDTO = RetryTaskConverter.INSTANCE.toJobLogDTO(executorDTO);
            jobLogMetaDTO.setTimestamp(nowMilli);
            TaskEngineLog.REMOTE.error("RetryTaskId:[{}] Task scheduling failed. Reason: Retry task does not exist <|>{}<|>", executorDTO.getRetryTaskId(),
                    jobLogMetaDTO);
            return;
        }
        retryCallbackRequest.setRetryStatus(retry.getRetryStatus());
        try {

            // 构建请求客户端对象
            RetryRpcClient rpcClient = buildRpcClient(instanceLiveInfo, executorDTO);
            Result<Boolean> dispatch = rpcClient.callback(retryCallbackRequest);
            if (dispatch.getStatus() == StatusEnum.YES.getStatus()) {
                TaskEngineLog.LOCAL.info("RetryTaskId:[{}] Task scheduled successfully.", executorDTO.getRetryTaskId());
            } else {
                // 客户端返回失败，则认为任务执行失败
                TaskEngineLog.LOCAL.error("RetryTaskId:[{}] Task scheduling failed. Msg:[{}]", executorDTO.getRetryTaskId(), dispatch.getMessage());
                taskExecuteFailure(executorDTO, dispatch.getMessage());
            }

        } catch (Exception e) {
            Throwable throwable;
            if (e.getClass().isAssignableFrom(RetryException.class)) {
                RetryException re = (RetryException) e;
                throwable = re.getLastFailedAttempt().getExceptionCause();
            } else if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
                UndeclaredThrowableException re = (UndeclaredThrowableException) e;
                throwable = re.getUndeclaredThrowable();
            } else {
                throwable = e;
            }

            RetryLogMetaDTO retryTaskLogDTO = RetryTaskLogConverter.INSTANCE.toRetryLogMetaDTO(executorDTO);
            retryTaskLogDTO.setTimestamp(nowMilli);
            TaskEngineLog.REMOTE.error("RetryTaskId:[{}] Task scheduling failed. <|>{}<|>", retryTaskLogDTO.getRetryTaskId(),
                    retryTaskLogDTO, throwable);

            taskExecuteFailure(executorDTO, throwable.getMessage());

        }

    }

    public class RetryExecutorRetryListener implements TaskEngineRetryListener {

        private final Map<String, Object> properties;
        private final RequestCallbackExecutorDTO executorDTO;

        public RetryExecutorRetryListener(final RequestCallbackExecutorDTO executorDTO) {
            this.executorDTO = executorDTO;
            this.properties = Maps.newHashMap();
        }

        @Override
        public <V> void onRetry(final Attempt<V> attempt) {
            // 负载节点
            if (attempt.hasException()) {
                JobLogMetaDTO jobLogMetaDTO = RetryTaskConverter.INSTANCE.toJobLogDTO(executorDTO);
                jobLogMetaDTO.setTimestamp(DateUtils.toNowMilli());
                TaskEngineLog.REMOTE.error("Task scheduling failed attempt retry. Task instance ID:[{}] retryCount:[{}]. <|>{}<|>",
                        executorDTO.getRetryTaskId(), attempt.getAttemptNumber(), jobLogMetaDTO, attempt.getExceptionCause());
                return;
            }

            // 更新最新负载节点
            if (attempt.hasResult() && attempt.getAttemptNumber() > 1) {
                Map<String, Object> properties = properties();
                InstanceLiveInfo instanceLiveInfo = (InstanceLiveInfo) properties.get(GrpcClientInvokeHandlerV2.NEW_INSTANCE_LIVE_INFO);
                if (Objects.nonNull(instanceLiveInfo)) {
                    RegisterNodeInfo nodeInfo = instanceLiveInfo.getNodeInfo();
                    RetryTask retryTask = new RetryTask();
                    retryTask.setId(executorDTO.getRetryTaskId());
                    RegisterNodeInfo realNodeInfo = new RegisterNodeInfo();
                    realNodeInfo.setHostIp(nodeInfo.getHostIp());
                    realNodeInfo.setHostPort(nodeInfo.getHostPort());
                    realNodeInfo.setHostId(nodeInfo.getHostId());
                    retryTask.setClientInfo(ClientInfoUtils.generate(realNodeInfo));
                    retryTaskMapper.updateById(retryTask);
                }
            }

        }

        @Override
        public Map<String, Object> properties() {
            return properties;
        }
    }

    private RetryRpcClient buildRpcClient(InstanceLiveInfo instanceLiveInfo, RequestCallbackExecutorDTO executorDTO) {
        return RequestBuilder.<RetryRpcClient, Result>newBuilder()
                .nodeInfo(instanceLiveInfo)
                .failRetry(true)
                .failover(true)
                .retryTimes(3)
                .retryInterval(1)
                .routeKey(executorDTO.getRouteKey())
                .allocKey(String.valueOf(executorDTO.getRetryTaskId()))
                .retryListener(new RetryExecutorRetryListener(executorDTO))
                .client(RetryRpcClient.class)
                .targetLabels(executorDTO.getLabels())
                .build();
    }

    /**
     * 更新是执行状态
     *
     * @param executorDTO RequestRetryExecutorDTO
     * @param message     失败原因
     */
    private static void taskExecuteFailure(RequestCallbackExecutorDTO executorDTO, String message) {
        ActorRef actorRef = ActorGenerator.retryTaskExecutorResultActor();
        RetryExecutorResultDTO executorResultDTO = RetryTaskConverter.INSTANCE.toRetryExecutorResultDTO(executorDTO);
        executorResultDTO.setExceptionMsg(message);
        executorResultDTO.setTaskStatus(RetryTaskStatusEnum.FAIL.getStatus());
        actorRef.tell(executorResultDTO, actorRef);
    }
}
