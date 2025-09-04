package com.wingflare.engine.task.server.job.support.executor.job;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.RealJobExecutorDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2024-06-12
 * @since : sj_1.1.0
 */
@Component
public class MapReduceJobExecutor extends AbstractJobExecutor  {

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.MAP_REDUCE;
    }

    @Override
    protected void doExecute(final JobExecutorContext context) {
        List<JobTask> taskList = context.getTaskList();
        for (final JobTask jobTask : taskList) {
            if (StrUtil.isBlank(jobTask.getClientInfo())) {
                return;
            }
            RealJobExecutorDTO realJobExecutor = JobTaskConverter.INSTANCE.toRealJobExecutorDTO(context, jobTask);
            realJobExecutor.setClientId(ClientInfoUtils.clientId(jobTask.getClientInfo()));
            ActorRef actorRef = ActorGenerator.jobRealTaskExecutorActor();
            actorRef.tell(realJobExecutor, actorRef);
        }
    }
}
