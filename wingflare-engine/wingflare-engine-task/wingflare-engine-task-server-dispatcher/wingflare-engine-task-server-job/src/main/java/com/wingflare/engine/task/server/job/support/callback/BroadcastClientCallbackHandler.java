package com.wingflare.engine.task.server.job.support.callback;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.dto.InstanceKey;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.RegisterNodeInfo;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.JobExecutorResultDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import org.apache.pekko.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2023-10-07 10:24
 * @since : 2.4.0
 */
@Component
public class BroadcastClientCallbackHandler extends AbstractClientCallbackHandler {
    private static final Logger log = LoggerFactory.getLogger(BroadcastClientCallbackHandler.class);
    private final InstanceManager instanceManager;

    public BroadcastClientCallbackHandler(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.BROADCAST;
    }

    @Override
    protected void doCallback(final ClientCallbackContext context) {

        JobExecutorResultDTO jobExecutorResultDTO = JobTaskConverter.INSTANCE.toJobExecutorResultDTO(context);
        jobExecutorResultDTO.setTaskId(context.getTaskId());
        jobExecutorResultDTO.setMessage(context.getExecuteResult().getMessage());
        jobExecutorResultDTO.setResult(context.getExecuteResult().getResult());
        jobExecutorResultDTO.setTaskType(getTaskInstanceType().getType());

        ActorRef actorRef = ActorGenerator.jobTaskExecutorResultActor();
        actorRef.tell(jobExecutorResultDTO, actorRef);

    }

    @Override
    protected String chooseNewClient(ClientCallbackContext context) {
        Set<RegisterNodeInfo> nodes = StreamUtils.toSet(instanceManager.getInstanceALiveInfoSet(
                context.getNamespaceId(), context.getGroupName()), InstanceLiveInfo::getNodeInfo);
        if (CollUtil.isEmpty(nodes)) {
            log.error("No executable client information. Job ID:[{}]", context.getJobId());
            return null;
        }

        JobTask jobTask = context.getJobTask();
        String clientInfo = jobTask.getClientInfo();
        String clientId = ClientInfoUtils.clientId(clientInfo);

        InstanceKey instanceKey = Builder.of(InstanceKey::new)
                .with(InstanceKey::setNamespaceId, context.getNamespaceId())
                .with(InstanceKey::setGroupName, context.getGroupName())
                .with(InstanceKey::setHostId, clientId)
                .build();
        InstanceLiveInfo instanceLiveInfo = instanceManager.getInstanceALiveInfoSet(instanceKey);
        if (Objects.isNull(instanceLiveInfo)) {
            List<JobTask> jobTasks = jobTaskMapper.selectList(new LambdaQueryWrapper<JobTask>()
                    .eq(JobTask::getTaskBatchId, context.getTaskBatchId()));

            Set<String> remoteClientIdSet = StreamUtils.toSet(nodes, RegisterNodeInfo::getHostId);
            Sets.SetView<String> diff = Sets.difference(remoteClientIdSet, getClientIdList(jobTasks));

            String newClientId = CollUtil.getFirst(diff.stream().iterator());
            instanceKey = Builder.of(InstanceKey::new)
                    .with(InstanceKey::setNamespaceId, context.getNamespaceId())
                    .with(InstanceKey::setGroupName, context.getGroupName())
                    .with(InstanceKey::setHostId, newClientId)
                    .build();
            InstanceLiveInfo newInstanceLiveInfo = instanceManager.getInstanceALiveInfoSet(instanceKey);
            if (Objects.isNull(newInstanceLiveInfo)) {
                // 如果找不到新的客户端信息，则返回原来的客户端信息
                return clientInfo;
            }

            return ClientInfoUtils.generate(newInstanceLiveInfo.getNodeInfo());
        }

        return clientInfo;
    }

    private static Set<String> getClientIdList(List<JobTask> jobTasks) {
        return StreamUtils.toSet(jobTasks, jobTask -> ClientInfoUtils.clientId(jobTask.getClientInfo()));
    }
}
