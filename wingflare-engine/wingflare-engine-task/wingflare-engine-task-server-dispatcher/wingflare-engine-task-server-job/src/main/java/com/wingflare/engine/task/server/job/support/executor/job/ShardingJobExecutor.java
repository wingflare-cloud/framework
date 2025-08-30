package com.wingflare.engine.task.server.job.support.executor.job;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.RealJobExecutorDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-06 17:33:51
 * @since 2.4.0
 */
@Component
public class ShardingJobExecutor extends AbstractJobExecutor {

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.SHARDING;
    }

    @Override
    protected void doExecute(JobExecutorContext context) {
        List<JobTask> taskList = context.getTaskList();
        for (int i = 0; i < taskList.size(); i++) {
            JobTask jobTask = taskList.get(i);
            if (StrUtil.isBlank(jobTask.getClientInfo())) {
                return;
            }
            RealJobExecutorDTO realJobExecutor = JobTaskConverter.INSTANCE.toRealJobExecutorDTO(context, jobTask);
            realJobExecutor.setClientId(ClientInfoUtils.clientId(jobTask.getClientInfo()));
            realJobExecutor.setShardingIndex(i);
            realJobExecutor.setShardingTotal(taskList.size());
            ActorRef actorRef = ActorGenerator.jobRealTaskExecutorActor();
            actorRef.tell(realJobExecutor, actorRef);
        }

    }
}
