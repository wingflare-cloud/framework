package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.dto.TaskLogFieldDTO;
import com.wingflare.engine.task.common.model.request.JobLogTaskRequest;
import com.wingflare.engine.task.server.common.dto.JobLogDTO;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import org.apache.pekko.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author opensnail
 * @date 2023-10-03 22:32:30
 * @since 2.4.0
 */
@Component(ActorGenerator.JOB_LOG_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobLogActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(JobLogActor.class);
    private final AccessTemplate accessTemplate;

    public JobLogActor(AccessTemplate accessTemplate) {
        this.accessTemplate = accessTemplate;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(List.class, (list -> {
                    try {
                        if (CollUtil.isEmpty(list)) {
                            return;
                        }

                        List<JobLogTaskRequest> jobLogTasks = (List<JobLogTaskRequest>) list;
                        Map<Long, List<JobLogTaskRequest>> logTaskDTOMap = jobLogTasks.
                                stream().collect(Collectors.groupingBy(JobLogTaskRequest::getTaskId, Collectors.toList()));
                        List<JobLogMessageDO> jobLogMessageList = new ArrayList<>();
                        for (List<JobLogTaskRequest> logTaskDTOList : logTaskDTOMap.values()) {
                            JobLogMessageDO jobLogMessage = JobTaskConverter.INSTANCE.toJobLogMessage(logTaskDTOList.get(0));
                            jobLogMessage.setLogNum(logTaskDTOList.size());
                            List<Map<String, String>> messageMapList = StreamUtils.toList(logTaskDTOList,
                                    taskDTO -> taskDTO.getFieldList()
                                            .stream().filter(logTaskDTO_ -> !Objects.isNull(logTaskDTO_.getValue()))
                                            .collect(Collectors.toMap(TaskLogFieldDTO::getName, TaskLogFieldDTO::getValue)));
                            jobLogMessage.setMessage(JsonUtil.toJsonString(messageMapList));
                            jobLogMessageList.add(jobLogMessage);
                        }

                        accessTemplate.getJobLogMessageAccess().insertBatch(jobLogMessageList);
                    } catch (Exception e) {
                        log.error("Saving client logs failed.", e);
                    } finally {
                        getContext().stop(getSelf());
                    }
                }))
                .match(JobLogDTO.class, (jobLogDTO -> {
                    try {
                        saveLogMessage(jobLogDTO);
                    } catch (Exception e) {
                        log.error("Saving logs failed.", e);
                    } finally {
                        getContext().stop(getSelf());
                    }
                })).build();

    }

    private void saveLogMessage(JobLogDTO jobLogDTO) {
        JobLogMessageDO jobLogMessage = JobTaskConverter.INSTANCE.toJobLogMessage(jobLogDTO);
        jobLogMessage.setLogNum(1);
        jobLogMessage.setMessage(Optional.ofNullable(jobLogDTO.getMessage()).orElse(StrUtil.EMPTY));
        jobLogMessage.setTaskId(Optional.ofNullable(jobLogMessage.getTaskId()).orElse(0L));
        accessTemplate.getJobLogMessageAccess().insert(jobLogMessage);
    }
}
