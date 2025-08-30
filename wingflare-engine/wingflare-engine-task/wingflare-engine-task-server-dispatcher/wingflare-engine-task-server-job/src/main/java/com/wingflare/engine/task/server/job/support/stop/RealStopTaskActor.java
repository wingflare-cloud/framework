package com.wingflare.engine.task.server.job.support.stop;

import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.StopJobRequest;
import com.wingflare.engine.task.server.common.dto.InstanceKey;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.server.job.client.JobRpcClient;
import com.wingflare.engine.task.server.job.dto.RealStopTaskInstanceDTO;
import com.wingflare.lib.core.Builder;
import org.apache.pekko.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2023-10-07 10:45
 * @since : 2.4.0
 */
@Component(ActorGenerator.JOB_REAL_STOP_TASK_INSTANCE_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RealStopTaskActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(RealStopTaskActor.class);
    private final InstanceManager instanceManager;

    public RealStopTaskActor(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RealStopTaskInstanceDTO.class, realStopTaskInstanceDTO -> {
            try {
                doStop(realStopTaskInstanceDTO);
            } catch (Exception e) {
                log.error("Stopping task execution failed. [{}]", JsonUtil.toJsonString(realStopTaskInstanceDTO), e);
            } finally {
                getContext().stop(getSelf());
            }
        }).build();
    }

    private void doStop(final RealStopTaskInstanceDTO realStopTaskInstanceDTO) {

        // 检查客户端是否存在
        InstanceLiveInfo instanceLiveInfo = instanceManager.getInstanceALiveInfoSet(Builder.of(InstanceKey::new)
                .with(InstanceKey::setNamespaceId, realStopTaskInstanceDTO.getNamespaceId())
                .with(InstanceKey::setGroupName, realStopTaskInstanceDTO.getGroupName())
                .with(InstanceKey::setHostId, realStopTaskInstanceDTO.getClientId())
                .build());
        if (Objects.nonNull(instanceLiveInfo)) {
            // 不用关心停止的结果，若服务端尝试终止失败,客户端会兜底进行关闭
            requestClient(realStopTaskInstanceDTO, instanceLiveInfo);
        }
    }

    private Result<Boolean> requestClient(RealStopTaskInstanceDTO realStopTaskInstanceDTO, InstanceLiveInfo instanceLiveInfo) {
        JobRpcClient rpcClient = RequestBuilder.<JobRpcClient, Result>newBuilder()
                .nodeInfo(instanceLiveInfo)
                .failRetry(Boolean.TRUE)
                .retryTimes(3)
                .retryInterval(1)
                .client(JobRpcClient.class)
                .build();

        StopJobRequest stopJobRequest = new StopJobRequest();
        stopJobRequest.setTaskBatchId(realStopTaskInstanceDTO.getTaskBatchId());
        stopJobRequest.setJobId(realStopTaskInstanceDTO.getJobId());
        stopJobRequest.setGroupName(realStopTaskInstanceDTO.getGroupName());
        return rpcClient.stop(stopJobRequest);
    }
}
