package com.wingflare.engine.task.server.job.support.executor.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
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
 * @date 2023-10-03 22:12:40
 * @since 2.4.0
 */
@Component
public class ClusterJobExecutor extends AbstractJobExecutor {

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.CLUSTER;
    }

    @Override
    protected void doExecute(JobExecutorContext context) {

        // 调度客户端
        List<JobTask> taskList = context.getTaskList();
        if (CollUtil.isEmpty(taskList)) {
            throw new SnailJobServerException("No executable job task");
        }

        JobTask jobTask = taskList.get(0);
        if (StrUtil.isBlank(jobTask.getClientInfo())) {
           return;
        }
        RealJobExecutorDTO realJobExecutor = JobTaskConverter.INSTANCE.toRealJobExecutorDTO(context, jobTask);
        realJobExecutor.setClientId(ClientInfoUtils.clientId(jobTask.getClientInfo()));
        ActorRef actorRef = ActorGenerator.jobRealTaskExecutorActor();
        actorRef.tell(realJobExecutor, actorRef);

    }

}
