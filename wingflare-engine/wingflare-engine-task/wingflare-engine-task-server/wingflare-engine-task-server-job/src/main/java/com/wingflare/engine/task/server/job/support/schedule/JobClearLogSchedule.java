package com.wingflare.engine.task.server.job.support.schedule;

import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.dto.PartitionTask;
import com.wingflare.engine.task.server.common.schedule.AbstractSchedule;
import com.wingflare.engine.task.server.common.util.PartitionTaskUtils;
import com.wingflare.engine.task.server.job.dto.JobPartitionTaskDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.task.datasource.template.persistence.mapper.JobLogMessageMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.JobLogMessage;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Job清理日志 一小时运行一次
 *
 * @author: opensnail
 * @date : 2023-07-21 13:32
 * @since 2.1.0
 */
@Component
public class JobClearLogSchedule extends AbstractSchedule implements Lifecycle {

    private final SystemProperties systemProperties;
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final JobTaskMapper jobTaskMapper;
    private final JobLogMessageMapper jobLogMessageMapper;
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;
    private final TransactionTemplate transactionTemplate;

    public JobClearLogSchedule(SystemProperties systemProperties, JobTaskBatchMapper jobTaskBatchMapper, JobTaskMapper jobTaskMapper, JobLogMessageMapper jobLogMessageMapper, WorkflowTaskBatchMapper workflowTaskBatchMapper, TransactionTemplate transactionTemplate) {
        this.systemProperties = systemProperties;
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.jobTaskMapper = jobTaskMapper;
        this.jobLogMessageMapper = jobLogMessageMapper;
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String lockName() {
        return "jobClearLog";
    }

    @Override
    public String lockAtMost() {
        return "PT4H";
    }

    @Override
    public String lockAtLeast() {
        return "PT1M";
    }

    @Override
    protected void doExecute() {
        try {
            // 清除日志默认保存天数大于零、最少保留最近一天的日志数据
            if (systemProperties.getLogStorage() < 1) {
                TaskEngineLog.LOCAL.error("job clear log storage error", systemProperties.getLogStorage());
                return;
            }
            // clean job log
            long total;
            LocalDateTime endTime = LocalDateTime.now().minusDays(systemProperties.getLogStorage());
            total = PartitionTaskUtils.process(startId -> jobTaskBatchList(startId, endTime),
                    this::processJobLogPartitionTasks, 0);

            TaskEngineLog.LOCAL.debug("Job clear success total:[{}]", total);
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("job clear log error", e);
        }
    }

    /**
     * JobLog List
     *
     * @param startId
     * @param endTime
     * @return
     */
    private List<JobPartitionTaskDTO> jobTaskBatchList(Long startId, LocalDateTime endTime) {

        List<JobTaskBatch> jobTaskBatchList = jobTaskBatchMapper.selectPage(
                        new Page<>(0, 1000, Boolean.FALSE),
                        new LambdaUpdateWrapper<JobTaskBatch>()
                                .ge(JobTaskBatch::getId, startId)
                                .le(JobTaskBatch::getCreateDt, endTime)
                                .orderByAsc(JobTaskBatch::getId))
                .getRecords();
        return JobTaskConverter.INSTANCE.toJobTaskBatchPartitionTasks(jobTaskBatchList);
    }

    /**
     * clean table JobTaskBatch & JobTask & JobLogMessage
     *
     * @param partitionTasks
     */
    public void processJobLogPartitionTasks(List<? extends PartitionTask> partitionTasks) {

        // Waiting for deletion JobTaskBatchList
        List<Long> partitionTasksIds = StreamUtils.toList(partitionTasks, PartitionTask::getId);
        if (CollectionUtils.isEmpty(partitionTasksIds)) {
            return;
        }
        List<List<Long>> idsPartition = Lists.partition(partitionTasksIds, 500);

        Set<Long> jobTaskListIds = new HashSet<>();
        Set<Long> jobLogMessageListIds = new HashSet<>();
        Set<Long> workflowBatchIds = new HashSet<>();
        for (List<Long> ids : idsPartition) {

            // Waiting for deletion JobTaskList
            List<JobTask> jobTaskList = jobTaskMapper.selectList(new LambdaQueryWrapper<JobTask>()
                    .select(JobTask::getId)
                    .in(JobTask::getTaskBatchId, ids));
            if (!CollectionUtils.isEmpty(jobTaskList)) {
                Set<Long> jobTask = jobTaskList.stream().map(JobTask::getId).collect(Collectors.toSet());
                jobTaskListIds.addAll(jobTask);
            }
            // Waiting for deletion JobLogMessageList
            List<JobLogMessage> jobLogMessageList = jobLogMessageMapper.selectList(new LambdaQueryWrapper<JobLogMessage>()
                    .select(JobLogMessage::getId)
                    .in(JobLogMessage::getTaskBatchId, ids));
            if (!CollectionUtils.isEmpty(jobLogMessageList)) {
                Set<Long> jobLogMessage = jobLogMessageList.stream().map(JobLogMessage::getId).collect(Collectors.toSet());
                jobLogMessageListIds.addAll(jobLogMessage);
            }

            // 先找出对应的 workflowTaskBatchId
            List<JobTaskBatch> jobTaskBatchList = jobTaskBatchMapper.selectList(new LambdaQueryWrapper<JobTaskBatch>().
                    select(JobTaskBatch::getWorkflowTaskBatchId)
                    .in(JobTaskBatch::getId, ids));
            if (!CollectionUtils.isEmpty(jobTaskBatchList)) {
                Set<Long> workflowTaskBatchId = jobTaskBatchList.stream().map(JobTaskBatch::getWorkflowTaskBatchId).collect(Collectors.toSet());
                workflowBatchIds.addAll(workflowTaskBatchId);
            }
        }

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(final TransactionStatus status) {

                idsPartition.forEach(jobTaskBatchMapper::deleteByIds);
                if (!CollectionUtils.isEmpty(jobTaskListIds)) {
                    Lists.partition(jobTaskListIds.stream().toList(), 500).forEach(jobTaskMapper::deleteByIds);
                }
                if (!CollectionUtils.isEmpty(jobLogMessageListIds)) {
                    Lists.partition(jobLogMessageListIds.stream().toList(), 500).forEach(jobLogMessageMapper::deleteByIds);
                }
                if (!CollectionUtils.isEmpty(workflowBatchIds)) {
                    Lists.partition(workflowBatchIds.stream().toList(), 500).forEach(workflowTaskBatchMapper::deleteByIds);
                }
            }
        });
    }

    @Override
    public void start() {
        taskScheduler.scheduleAtFixedRate(this::execute, Duration.parse("PT4H"));
    }

    @Override
    public void close() {

    }
}
