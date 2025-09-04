package com.wingflare.engine.task.server.job.support.stop;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.dto.RealStopTaskInstanceDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-02 12:59:53
 * @since 2.4.0
 */
@Component
public class ClusterTaskStopHandler extends AbstractJobTaskStopHandler {


    @Override
    public JobTaskTypeEnum getTaskType() {
        return JobTaskTypeEnum.CLUSTER;
    }

    @Override
    public void doStop(TaskStopJobContext context) {
        List<JobTask> jobTasks = context.getJobTasks();

        RealStopTaskInstanceDTO taskInstanceDTO = JobTaskConverter.INSTANCE.toRealStopTaskInstanceDTO(context);
        taskInstanceDTO.setClientId(ClientInfoUtils.clientId(jobTasks.get(0).getClientInfo()));

        ActorRef actorRef = ActorGenerator.jobRealStopTaskInstanceActor();
        actorRef.tell(taskInstanceDTO, actorRef);

    }


}
