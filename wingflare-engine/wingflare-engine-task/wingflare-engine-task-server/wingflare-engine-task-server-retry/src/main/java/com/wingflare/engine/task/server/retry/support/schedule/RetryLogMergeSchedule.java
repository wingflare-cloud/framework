package com.wingflare.engine.task.server.retry.support.schedule;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.dto.PartitionTask;
import com.wingflare.engine.task.server.common.schedule.AbstractSchedule;
import com.wingflare.engine.task.server.common.triple.Triple;
import com.wingflare.engine.task.server.common.util.PartitionTaskUtils;
import com.wingflare.engine.task.server.retry.dto.RetryMergePartitionTaskDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskLogConverter;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskLogMessageMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTask;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTaskLogMessage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * jogLogMessage 日志合并归档
 *
 * @author zhengweilin
 * @version 3.2.0
 * @date 2024/03/15
 */
@Component
public class RetryLogMergeSchedule extends AbstractSchedule implements Lifecycle {

    private final SystemProperties systemProperties;
    private final RetryTaskMapper retryTaskMapper;
    private final RetryTaskLogMessageMapper retryTaskLogMessageMapper;
    private final TransactionTemplate transactionTemplate;

    public RetryLogMergeSchedule(SystemProperties systemProperties, RetryTaskMapper retryTaskMapper, RetryTaskLogMessageMapper retryTaskLogMessageMapper, TransactionTemplate transactionTemplate) {
        this.systemProperties = systemProperties;
        this.retryTaskMapper = retryTaskMapper;
        this.retryTaskLogMessageMapper = retryTaskLogMessageMapper;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String lockName() {
        return "retryLogMerge";
    }

    @Override
    public String lockAtMost() {
        return "PT1H";
    }

    @Override
    public String lockAtLeast() {
        return "PT1M";
    }

    @Override
    protected void doExecute() {
        try {
            // merge job log
            long total;
            LocalDateTime endTime = LocalDateTime.now().minusDays(systemProperties.getMergeLogDays());
            total = PartitionTaskUtils.process(startId -> retryLogList(startId, endTime),
                    this::processJobLogPartitionTasks, 0);

            TaskEngineLog.LOCAL.debug("job merge success total:[{}]", total);
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("job merge log error", e);
        }
    }

    /**
     * JobLog List
     *
     * @param startId
     * @param endTime
     * @return
     */
    private List<RetryMergePartitionTaskDTO> retryLogList(Long startId, LocalDateTime endTime) {

        List<RetryTask> jobTaskBatchList = retryTaskMapper.selectPage(
                new Page<>(0, 1000, Boolean.FALSE),
                new LambdaUpdateWrapper<RetryTask>()
                        .ge(RetryTask::getId, startId)
                        .in(RetryTask::getTaskStatus, Lists.newArrayList(
                                RetryStatusEnum.FINISH.getStatus(),
                                RetryStatusEnum.MAX_COUNT.getStatus()))
                        .le(RetryTask::getCreateDt, endTime)
                        .orderByAsc(RetryTask::getId)
        ).getRecords();
        return RetryTaskLogConverter.INSTANCE.toRetryMergePartitionTaskDTOs(jobTaskBatchList);
    }

    /**
     * merge job_log_message
     *
     * @param partitionTasks
     */
    public void processJobLogPartitionTasks(List<? extends PartitionTask> partitionTasks) {

        // Waiting for merge RetryTaskLog
        List<Long> ids = StreamUtils.toList(partitionTasks, PartitionTask::getId);
        if (CollUtil.isEmpty(ids)) {
            return;
        }

        // Waiting for deletion RetryTaskLogMessage
        List<RetryTaskLogMessage> retryLogMessageList = retryTaskLogMessageMapper.selectList(
                new LambdaQueryWrapper<RetryTaskLogMessage>()
                        .in(RetryTaskLogMessage::getRetryTaskId, ids));
        if (CollUtil.isEmpty(retryLogMessageList)) {
            return;
        }

        List<Map.Entry<Triple<String, String, Long>, List<RetryTaskLogMessage>>> jobLogMessageGroupList = retryLogMessageList.stream()
                .collect(
                        groupingBy(message -> Triple.of(message.getNamespaceId(), message.getGroupName(),
                                message.getRetryTaskId())))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2).toList();

        for (Map.Entry<Triple<String, String, Long>/*taskId*/, List<RetryTaskLogMessage>> jobLogMessageMap : jobLogMessageGroupList) {
            List<Long> jobLogMessageDeleteBatchIds = new ArrayList<>();

            List<String> mergeMessages = jobLogMessageMap.getValue().stream().map(k -> {
                        jobLogMessageDeleteBatchIds.add(k.getId());
                        return JsonUtil.parseObject(k.getMessage(), List.class);
                    })
                    .reduce((a, b) -> {
                        // 合并日志信息
                        List<String> list = new ArrayList<>();
                        list.addAll(a);
                        list.addAll(b);
                        return list;
                    }).get();

            // 500条数据为一个批次
            List<List<String>> partitionMessages = Lists.partition(mergeMessages,
                    systemProperties.getMergeLogNum());

            List<RetryTaskLogMessage> jobLogMessageUpdateList = new ArrayList<>();

            for (int i = 0; i < partitionMessages.size(); i++) {
                // 深拷贝
                RetryTaskLogMessage jobLogMessage = RetryTaskLogConverter.INSTANCE.toRetryTaskLogMessage(
                        jobLogMessageMap.getValue().get(0));
                List<String> messages = partitionMessages.get(i);

                jobLogMessage.setLogNum(messages.size());
                jobLogMessage.setMessage(JsonUtil.toJsonString(messages));
                jobLogMessageUpdateList.add(jobLogMessage);
            }

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(final TransactionStatus status) {
                    // 批量删除、更新日志
                    if (CollUtil.isNotEmpty(jobLogMessageDeleteBatchIds)) {
                        retryTaskLogMessageMapper.deleteBatchIds(jobLogMessageDeleteBatchIds);
                    }
                    if (CollUtil.isNotEmpty(jobLogMessageUpdateList)) {
                        retryTaskLogMessageMapper.insertBatch(jobLogMessageUpdateList);
                    }
                }
            });
        }
    }

    @Override
    public void start() {
        taskScheduler.scheduleAtFixedRate(this::execute, Duration.parse("PT1H"));
    }

    @Override
    public void close() {

    }
}
