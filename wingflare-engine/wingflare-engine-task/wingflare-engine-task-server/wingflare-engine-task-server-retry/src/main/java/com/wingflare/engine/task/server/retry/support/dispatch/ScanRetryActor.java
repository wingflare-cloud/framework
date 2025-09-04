package com.wingflare.engine.task.server.retry.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.dto.PartitionTask;
import com.wingflare.engine.task.server.common.dto.ScanTask;
import com.wingflare.engine.task.server.common.enums.RetryTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.util.PartitionTaskUtils;
import com.wingflare.engine.task.server.retry.dto.RetryPartitionTask;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.handler.RateLimiterHandler;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
@Component(ActorGenerator.SCAN_RETRY_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScanRetryActor extends AbstractActor {
    public static final ConcurrentSkipListSet<String> REPEATED_PULL= new ConcurrentSkipListSet<>(new ArrayList<>());
    private static final Logger log = LoggerFactory.getLogger(ScanRetryActor.class);
    private final SystemProperties systemProperties;
    private final AccessTemplate accessTemplate;
    private final RetryMapper retryMapper;
    private final GroupConfigMapper groupConfigMapper;
    private final RateLimiterHandler rateLimiterHandler;



    public ScanRetryActor(SystemProperties systemProperties, AccessTemplate accessTemplate, RetryMapper retryMapper, GroupConfigMapper groupConfigMapper, RateLimiterHandler rateLimiterHandler) {
        this.systemProperties = systemProperties;
        this.accessTemplate = accessTemplate;
        this.retryMapper = retryMapper;
        this.groupConfigMapper = groupConfigMapper;
        this.rateLimiterHandler = rateLimiterHandler;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ScanTask.class, config -> {

            try {
                doScan(config);
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("Data scanner processing exception. [{}]", config, e);
            } finally {
                REPEATED_PULL.remove(config.getBucketStr());
                getContext().stop(getSelf());
            }

        }).build();

    }

    private void doScan(final ScanTask scanTask) {
        PartitionTaskUtils.process(startId -> listAvailableTasks(startId, scanTask.getBuckets()),
                this::processRetryPartitionTasks, this::stopCondition, 0);
    }

    /**
     * 拉取任务停止判断
     *
     * @param partitionTasks RetryPartitionTask
     * @return true-停止拉取 false-继续拉取
     */
    private boolean stopCondition(List<? extends PartitionTask> partitionTasks) {
        if (CollectionUtils.isEmpty(partitionTasks)) {
            return true;
        }

        if (!rateLimiterHandler.tryAcquire(partitionTasks.size())) {
            log.warn("Current node triggers current limit");
            return true;
        }

        return false;
    }

    private void processRetryPartitionTasks(List<? extends PartitionTask> partitionTasks) {
        if (CollUtil.isEmpty(partitionTasks)) {
            return;
        }

        // 批次查询场景
        Map<Long, RetrySceneConfig> sceneConfigMap = getSceneConfigMap(partitionTasks);

        List<Retry> waitUpdateRetries = new ArrayList<>();
        List<RetryTaskPrepareDTO> waitExecRetries = new ArrayList<>();
        for (PartitionTask task : partitionTasks) {
            RetryPartitionTask retryPartitionTask = (RetryPartitionTask) task;
            RetrySceneConfig retrySceneConfig = sceneConfigMap.get(retryPartitionTask.getSceneId());
            if (Objects.isNull(retrySceneConfig)) {
                continue;
            }

            processRetry(retryPartitionTask, retrySceneConfig, waitExecRetries, waitUpdateRetries);

        }

        if (CollUtil.isEmpty(waitUpdateRetries)) {
            return;
        }

        // 批量更新
        retryMapper.updateBatchNextTriggerAtById(waitUpdateRetries);

        for (RetryTaskPrepareDTO retryTaskPrepareDTO: waitExecRetries) {
            // 准备阶段执行
            ActorRef actorRef = ActorGenerator.retryTaskPrepareActor();
            actorRef.tell(retryTaskPrepareDTO, actorRef);
        }

    }

    /**
     * 查询场景配置或者退避策略
     *
     * @param partitionTasks 待处理任务列表
     * @return <SceneId, RetrySceneConfig>
     */
    private Map<Long, RetrySceneConfig> getSceneConfigMap(final List<? extends PartitionTask> partitionTasks) {
        Set<Long> sceneIdSet = StreamUtils.toSet(partitionTasks,
                partitionTask -> ((RetryPartitionTask) partitionTask).getSceneId());
        List<RetrySceneConfig> retrySceneConfigs = accessTemplate.getSceneConfigAccess()
                .list(new LambdaQueryWrapper<RetrySceneConfig>()
                        .select(RetrySceneConfig::getBackOff, RetrySceneConfig::getTriggerInterval,
                                RetrySceneConfig::getBlockStrategy, RetrySceneConfig::getSceneName,
                                RetrySceneConfig::getCbTriggerType, RetrySceneConfig::getCbTriggerInterval,
                                RetrySceneConfig::getExecutorTimeout, RetrySceneConfig::getId)
                        .eq(RetrySceneConfig::getSceneStatus, StatusEnum.YES.getStatus())
                        .in(RetrySceneConfig::getId, sceneIdSet));
        return StreamUtils.toIdentityMap(retrySceneConfigs, RetrySceneConfig::getId);
    }

    private void processRetry(RetryPartitionTask partitionTask, RetrySceneConfig retrySceneConfig, List<RetryTaskPrepareDTO> waitExecRetries, List<Retry> waitUpdateRetries) {
        Retry retry = new Retry();
        retry.setNextTriggerAt(calculateNextTriggerTime(partitionTask, retrySceneConfig));
        retry.setId(partitionTask.getId());
        waitUpdateRetries.add(retry);

        RetryTaskPrepareDTO retryTaskPrepareDTO = RetryTaskConverter.INSTANCE.toRetryTaskPrepareDTO(partitionTask);
        retryTaskPrepareDTO.setBlockStrategy(retrySceneConfig.getBlockStrategy());
        retryTaskPrepareDTO.setExecutorTimeout(retrySceneConfig.getExecutorTimeout());
        retryTaskPrepareDTO.setRetryTaskExecutorScene(RetryTaskExecutorSceneEnum.AUTO_RETRY.getScene());
        waitExecRetries.add(retryTaskPrepareDTO);
    }

    protected Long calculateNextTriggerTime(RetryPartitionTask partitionTask, RetrySceneConfig retrySceneConfig) {
        // 更新下次触发时间

        WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();

        long now = DateUtils.toNowMilli();
        long nextTriggerAt = partitionTask.getNextTriggerAt();
        if ((nextTriggerAt + DateUtils.toEpochMilli(SystemConstants.SCHEDULE_PERIOD)) < now) {
            nextTriggerAt = now;
            partitionTask.setNextTriggerAt(nextTriggerAt);
        }

        waitStrategyContext.setNextTriggerAt(nextTriggerAt);
        waitStrategyContext.setDelayLevel(partitionTask.getRetryCount() + 1);

        // 更新触发时间, 任务进入时间轮
        WaitStrategy waitStrategy;
        if (SyetemTaskTypeEnum.CALLBACK.getType().equals(partitionTask.getTaskType())) {
            waitStrategyContext.setTriggerInterval(retrySceneConfig.getCbTriggerInterval());
            waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(retrySceneConfig.getCbTriggerType());
        } else {
            waitStrategyContext.setTriggerInterval(retrySceneConfig.getTriggerInterval());
            waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(retrySceneConfig.getBackOff());
        }

        return waitStrategy.computeTriggerTime(waitStrategyContext);
    }

    public List<RetryPartitionTask> listAvailableTasks(Long startId, Set<Integer> buckets) {
        List<Retry> retries = accessTemplate.getRetryAccess()
                .listPage(new PageDTO<>(0, systemProperties.getRetryPullPageSize(), Boolean.FALSE),
                        new LambdaQueryWrapper<Retry>()
                                .select(Retry::getId, Retry::getNextTriggerAt, Retry::getGroupName, Retry::getRetryCount,
                                        Retry::getSceneName, Retry::getNamespaceId, Retry::getTaskType,
                                        Retry::getSceneId, Retry::getGroupId)
                                .eq(Retry::getRetryStatus, RetryStatusEnum.RUNNING.getStatus())
                                .in(Retry::getBucketIndex, buckets)
                                .le(Retry::getNextTriggerAt, DateUtils.toNowMilli() + DateUtils.toEpochMilli(SystemConstants.SCHEDULE_PERIOD))
                                .gt(Retry::getId, startId)
                                .orderByAsc(Retry::getId))
                .getRecords();

        // 过滤已关闭的组
        if (CollUtil.isNotEmpty(retries)) {
            List<Long> groupConfigs = StreamUtils.toList(
                    groupConfigMapper.selectList(new LambdaQueryWrapper<GroupConfig>()
                            .select(GroupConfig::getId)
                            .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
                            .in(GroupConfig::getId, StreamUtils.toSet(retries, Retry::getGroupId))),
                    GroupConfig::getId);
            retries = retries.stream().filter(retry -> groupConfigs.contains(retry.getGroupId())).collect(Collectors.toList());
        }

        return RetryTaskConverter.INSTANCE.toRetryPartitionTasks(retries);
    }
}
