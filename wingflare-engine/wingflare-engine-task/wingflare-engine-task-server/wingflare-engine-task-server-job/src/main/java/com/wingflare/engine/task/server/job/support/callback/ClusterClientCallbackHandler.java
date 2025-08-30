package com.wingflare.engine.task.server.job.support.callback;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.InstanceSelectCondition;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.JobExecutorResultDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.lib.core.Builder;
import org.apache.pekko.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author opensnail
 * @date 2023-10-03 23:12:12
 * @since 2.4.0
 */
@Component
public class ClusterClientCallbackHandler extends AbstractClientCallbackHandler {
    private static final Logger log = LoggerFactory.getLogger(ClusterClientCallbackHandler.class);
    private final InstanceManager instanceManager;

    public ClusterClientCallbackHandler(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.CLUSTER;
    }

    @Override
    protected String chooseNewClient(ClientCallbackContext context) {

        // 选择重试的节点
        InstanceSelectCondition condition = Builder.of(InstanceSelectCondition::new)
                .with(InstanceSelectCondition::setAllocKey, String.valueOf(context.getJobId()))
                .with(InstanceSelectCondition::setGroupName, context.getGroupName())
                .with(InstanceSelectCondition::setNamespaceId, context.getNamespaceId())
                .with(InstanceSelectCondition::setRouteKey, context.getJob().getRouteKey())
                .with(InstanceSelectCondition::setTargetLabels, context.getLabels())
                .build();

        InstanceLiveInfo instance = instanceManager.getALiveInstanceByRouteKey(condition);

        if (Objects.isNull(instance)) {
            log.error("No executable client information. Job ID:[{}]", context.getJobId());
            return null;
        }

        return ClientInfoUtils.generate(instance.getNodeInfo());

    }

    @Override
    protected void doCallback(ClientCallbackContext context) {

        JobExecutorResultDTO jobExecutorResultDTO = JobTaskConverter.INSTANCE.toJobExecutorResultDTO(context);
        jobExecutorResultDTO.setTaskId(context.getTaskId());
        jobExecutorResultDTO.setMessage(context.getExecuteResult().getMessage());
        jobExecutorResultDTO.setResult(context.getExecuteResult().getResult());
        jobExecutorResultDTO.setTaskType(getTaskInstanceType().getType());

        ActorRef actorRef = ActorGenerator.jobTaskExecutorResultActor();
        actorRef.tell(jobExecutorResultDTO, actorRef);

    }

}
