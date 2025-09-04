package com.wingflare.engine.task.server.retry.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.server.common.enums.RetryTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.support.RetryPrePareHandler;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.pekko.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum.NOT_COMPLETE;
import static com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum.SUCCESS;
import static com.wingflare.engine.task.server.common.pekko.ActorGenerator.RETRY_TASK_PREPARE_ACTOR;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
@Component(RETRY_TASK_PREPARE_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RetryTaskPrepareActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(RetryTaskPrepareActor.class);
    private final List<RetryPrePareHandler> retryPrePareHandlers;
    private final RetryTaskMapper retryTaskMapper;

    public RetryTaskPrepareActor(List<RetryPrePareHandler> retryPrePareHandlers, RetryTaskMapper retryTaskMapper) {
        this.retryPrePareHandlers = retryPrePareHandlers;
        this.retryTaskMapper = retryTaskMapper;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RetryTaskPrepareDTO.class, prepareDTO -> {

            try {
                doPrepare(prepareDTO);
            } catch (Exception e) {
                log.error("Preprocessing node exception", e);
            } finally {
                getContext().stop(getSelf());
            }

        }).build();
    }

    /**
     * 对数据进行预处理
     *
     * @param prepareDTO RetryTaskPrepareDTO
     */
    private void doPrepare(RetryTaskPrepareDTO prepareDTO) {

        List<RetryTask> retryTasks = retryTaskMapper.selectList(
                new LambdaQueryWrapper<RetryTask>()
                        .eq(RetryTask::getRetryId, prepareDTO.getRetryId())
                        .in(RetryTask::getTaskStatus, NOT_COMPLETE)
                        .orderByAsc(RetryTask::getRetryId)
        );

        if (CollUtil.isEmpty(retryTasks)
                || Objects.isNull(prepareDTO.getRetryTaskExecutorScene())
                || RetryTaskExecutorSceneEnum.MANUAL_RETRY.getScene() == prepareDTO.getRetryTaskExecutorScene()) {
            RetryTask retryTask = new RetryTask();
            retryTask.setTaskStatus(SUCCESS.getStatus());
            retryTasks = Lists.newArrayList(retryTask);
        }

        boolean onlyTimeoutCheck = false;
        for (RetryTask retryTask : retryTasks) {
            prepareDTO.setRetryTaskId(retryTask.getId());
            prepareDTO.setOnlyTimeoutCheck(onlyTimeoutCheck);
            for (RetryPrePareHandler retryPrePareHandler : retryPrePareHandlers) {
                if (retryPrePareHandler.matches(retryTask.getTaskStatus())) {
                    retryPrePareHandler.handle(prepareDTO);
                    break;
                }
            }

            // 当存在大量待处理任务时，除了第一个任务需要执行阻塞策略，其他任务只做任务检查
            onlyTimeoutCheck = true;
        }
    }
}
