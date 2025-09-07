package com.wingflare.engine.task.server.retry.support.schedule;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.schedule.AbstractSchedule;
import com.wingflare.engine.task.server.common.triple.Triple;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetrySummaryMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySummary;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Retry Dashboard
 *
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-11-28 13:46
 * @since 2.1.0
 */
@Component
public class RetrySummarySchedule extends AbstractSchedule implements Lifecycle {
    private final RetryMapper retryMapper;
    private final RetrySummaryMapper retrySummaryMapper;
    private final SystemProperties systemProperties;

    public RetrySummarySchedule(RetryMapper retryMapper, RetrySummaryMapper retrySummaryMapper, SystemProperties systemProperties) {
        this.retryMapper = retryMapper;
        this.retrySummaryMapper = retrySummaryMapper;
        this.systemProperties = systemProperties;
    }

    @Override
    public String lockName() {
        return "retrySummaryDashboard";
    }

    @Override
    public String lockExpire() {
        return "PT1M";
    }

    @Override
    public String lockTimeout() {
        return "PT20S";
    }

    @Override
    protected void doExecute() {
        try {
            for (int i = 0; i < systemProperties.getSummaryDay(); i++) {

                // 重试按日实时查询统计数据（00:00:00 - 23:59:59）
                LocalDateTime todayFrom = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusDays(-i);
                LocalDateTime todayTo = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).plusDays(-i);
                LambdaQueryWrapper<Retry> wrapper = new LambdaQueryWrapper<Retry>()
                        .between(Retry::getCreateDt, todayFrom, todayTo)
                        .groupBy(Retry::getNamespaceId, Retry::getGroupName, Retry::getSceneName);
                List<DashboardRetryResponseDO> dashboardRetryResponseDOList = retryMapper.selectRetrySummaryList(wrapper);
                if (CollUtil.isEmpty(dashboardRetryResponseDOList)) {
                    continue;
                }

                // insertOrUpdate
                List<RetrySummary> retrySummaryList = retrySummaryList(todayFrom, dashboardRetryResponseDOList);

                Set<String> groupNames = Sets.newHashSet();
                Set<String> namespaceIds = Sets.newHashSet();
                Set<String> sceneNames = Sets.newHashSet();
                for (final RetrySummary retrySummary : retrySummaryList) {
                    groupNames.add(retrySummary.getGroupName());
                    namespaceIds.add(retrySummary.getNamespaceId());
                    sceneNames.add(retrySummary.getSceneName());
                }

                List<RetrySummary> retrySummaries = retrySummaryMapper.selectList(new LambdaQueryWrapper<RetrySummary>()
                        .in(RetrySummary::getGroupName, groupNames)
                        .in(RetrySummary::getNamespaceId, namespaceIds)
                        .in(RetrySummary::getSceneName, sceneNames)
                        .eq(RetrySummary::getTriggerAt, todayFrom)
                );

                Map<Triple<String, String, LocalDateTime>, RetrySummary> summaryMap = StreamUtils.toIdentityMap(
                        retrySummaries,
                        retrySummary -> Triple.of(mergeKey(retrySummary), retrySummary.getSceneName(), retrySummary.getTriggerAt()));

                List<RetrySummary> waitInserts = Lists.newArrayList();
                List<RetrySummary> waitUpdates = Lists.newArrayList();
                for (final RetrySummary retrySummary : retrySummaryList) {
                    if (Objects.isNull(summaryMap.get(Triple.of(mergeKey(retrySummary), retrySummary.getSceneName(), retrySummary.getTriggerAt())))) {
                        waitInserts.add(retrySummary);
                    } else {
                        waitUpdates.add(retrySummary);
                    }
                }

                int insertTotalRetrySummary = 0;
                if (CollUtil.isNotEmpty(waitInserts)) {
                    insertTotalRetrySummary = retrySummaryMapper.insertBatch(waitInserts);
                }

                int updateTotalRetrySummary = 0;
                if (CollUtil.isNotEmpty(waitUpdates)) {
                    updateTotalRetrySummary = retrySummaryMapper.updateBatch(waitUpdates);
                }

                TaskEngineLog.LOCAL.debug("retry summary dashboard success todayFrom:[{}] todayTo:[{}] insertTotalRetrySummary:[{}] updateTotalRetrySummary:[{}]", todayFrom, todayTo, insertTotalRetrySummary, updateTotalRetrySummary);
            }
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("retry summary dashboard log error", e);
        }
    }

    private String mergeKey(final RetrySummary retrySummary) {
        return retrySummary.getGroupName() + retrySummary.getNamespaceId();
    }

    private List<RetrySummary> retrySummaryList(LocalDateTime triggerAt, List<DashboardRetryResponseDO> dashboardRetryResponseDOList) {
        List<RetrySummary> retrySummaryList = new ArrayList<>();
        for (DashboardRetryResponseDO dashboardRetryResponseDO : dashboardRetryResponseDOList) {
            RetrySummary retrySummary = new RetrySummary();
            retrySummary.setTriggerAt(triggerAt);
            retrySummary.setNamespaceId(dashboardRetryResponseDO.getNamespaceId());
            retrySummary.setGroupName(dashboardRetryResponseDO.getGroupName());
            retrySummary.setSceneName(dashboardRetryResponseDO.getSceneName());
            retrySummary.setRunningNum(dashboardRetryResponseDO.getRunningNum());
            retrySummary.setFinishNum(dashboardRetryResponseDO.getFinishNum());
            retrySummary.setMaxCountNum(dashboardRetryResponseDO.getMaxCountNum());
            retrySummary.setSuspendNum(dashboardRetryResponseDO.getSuspendNum());
            retrySummaryList.add(retrySummary);
        }
        return retrySummaryList;
    }

    @Override
    public void start() {
        taskScheduler.scheduleAtFixedRate(this::execute, Duration.parse("PT1M"));
    }

    @Override
    public void close() {

    }
}
