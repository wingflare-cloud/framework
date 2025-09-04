package com.wingflare.engine.task.server.job.support.callback;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.RegisterNodeInfo;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.JobExecutorResultDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-06-13 21:22x
 * @since : sj_1.1.0
 */
@Component
public class MapReduceClientCallbackHandler extends AbstractClientCallbackHandler {
    private final JobTaskMapper jobTaskMapper;
    private final InstanceManager instanceManager;

    public MapReduceClientCallbackHandler(JobTaskMapper jobTaskMapper, InstanceManager instanceManager) {
        this.jobTaskMapper = jobTaskMapper;
        this.instanceManager = instanceManager;
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.MAP_REDUCE;
    }

    @Override
    protected void doCallback(final ClientCallbackContext context) {
        JobTask jobTask = jobTaskMapper.selectOne(new LambdaQueryWrapper<JobTask>()
                .eq(JobTask::getId, context.getTaskId()));
        Assert.notNull(jobTask, () -> new TaskServerException("job task is null"));

        JobExecutorResultDTO jobExecutorResultDTO = JobTaskConverter.INSTANCE.toJobExecutorResultDTO(context);
        jobExecutorResultDTO.setTaskId(context.getTaskId());
        jobExecutorResultDTO.setMessage(context.getExecuteResult().getMessage());
        jobExecutorResultDTO.setResult(context.getExecuteResult().getResult());
        jobExecutorResultDTO.setTaskType(getTaskInstanceType().getType());
        jobExecutorResultDTO.setIsLeaf(jobTask.getLeaf());
        ActorRef actorRef = ActorGenerator.jobTaskExecutorResultActor();
        actorRef.tell(jobExecutorResultDTO, actorRef);
    }

    @Override
    protected String chooseNewClient(ClientCallbackContext context) {
        Set<InstanceLiveInfo> instanceALiveInfoSet = instanceManager.getInstanceALiveInfoSet(
                context.getNamespaceId(), context.getGroupName(), context.getLabels());
        Set<RegisterNodeInfo> nodes = StreamUtils.toSet(instanceALiveInfoSet, InstanceLiveInfo::getNodeInfo);
        if (CollUtil.isEmpty(nodes)) {
            TaskEngineLog.LOCAL.error("No executable client information. Job ID:[{}]", context.getJobId());
            return null;
        }

        RegisterNodeInfo serverNode = RandomUtil.randomEle(nodes.toArray(new RegisterNodeInfo[0]));
        return ClientInfoUtils.generate(serverNode);
    }
}
