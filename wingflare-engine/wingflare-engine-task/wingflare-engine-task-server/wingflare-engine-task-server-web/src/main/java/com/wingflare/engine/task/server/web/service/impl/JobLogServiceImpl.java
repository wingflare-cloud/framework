package com.wingflare.engine.task.server.web.service.impl;


import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.constant.LogFieldConstants;
import com.wingflare.engine.task.server.common.vo.JobLogQueryVO;
import com.wingflare.engine.task.server.web.model.event.WsSendEvent;
import com.wingflare.engine.task.server.web.service.JobLogService;
import com.wingflare.engine.task.server.web.timer.JobTaskLogTimerTask;
import com.wingflare.engine.task.server.web.timer.LogTimerWheel;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.LogPageQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.lib.container.Container;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum.COMPLETED;

/**
 * @author: opensnail
 * @date : 2023-10-12 09:55
 * @since ：2.4.0
 */
@Service
public class JobLogServiceImpl implements JobLogService {
    private static final Long DELAY_MILLS = 5000L;
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final AccessTemplate accessTemplate;

    public JobLogServiceImpl(JobTaskBatchMapper jobTaskBatchMapper, AccessTemplate accessTemplate) {
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.accessTemplate = accessTemplate;
    }

    @Override
    public void getJobLogPage(JobLogQueryVO queryVO) {
        String sid = queryVO.getSid();
        LogPageQueryDO pageQueryDO = new LogPageQueryDO();
        pageQueryDO.setPage(1);
        pageQueryDO.setSize(50);
        pageQueryDO.setTaskBatchId(queryVO.getTaskBatchId());
        pageQueryDO.setTaskId(queryVO.getTaskId());
        pageQueryDO.setStartRealTime(queryVO.getStartRealTime());
        pageQueryDO.setSearchCount(true);
        PageResponseDO<JobLogMessageDO> pageResponseDO = accessTemplate.getJobLogMessageAccess()
                .listPage(pageQueryDO);

        long total = pageResponseDO.getTotal();

        int totalPage = (int) ((total + queryVO.getSize() - 1) / queryVO.getSize());

        Long lastRealTime = 0L;

        if (0 == totalPage &&
                (null != pageQueryDO.getStartRealTime() && 0 != pageQueryDO.getStartRealTime())){
            lastRealTime = pageQueryDO.getStartRealTime();
        }
        for (int i = 1; i <= totalPage;) {
            for (JobLogMessageDO jobLogMessageDO : pageResponseDO.getRows()) {
                // 循环覆盖，最后一个肯定是最大的
                lastRealTime = jobLogMessageDO.getRealTime();
                // 发生日志内容到前端
                String message = jobLogMessageDO.getMessage();
                List<Map<String, String>> logContents = JsonUtil.parseObject(message, List.class);
                logContents = logContents.stream()
                        .sorted(Comparator.comparingLong(o -> Long.parseLong(o.get(LogFieldConstants.TIME_STAMP))))
                        .toList();
                for (Map<String, String> logContent : logContents) {
                    // send发消息
                    WsSendEvent sendEvent = new WsSendEvent(this);
                    sendEvent.setSid(sid);
                    sendEvent.setMessage(JsonUtil.toJsonString(logContent));
                    Container.get(EventPublisher.class).publishEvent(sendEvent);
                }
            }

            // 继续查询下一页
            pageQueryDO.setSearchCount(false);
            pageQueryDO.setPage(++i);
            pageResponseDO = accessTemplate.getJobLogMessageAccess()
                    .listPage(pageQueryDO);
        }


        // 这里判断是否继续查询
        JobTaskBatch jobTaskBatch = jobTaskBatchMapper.selectOne(
                new LambdaQueryWrapper<JobTaskBatch>().eq(JobTaskBatch::getId, queryVO.getTaskBatchId()));

        // 结束查询
        if (Objects.isNull(jobTaskBatch)
                || (COMPLETED.contains(jobTaskBatch.getTaskBatchStatus()) &&
                jobTaskBatch.getUpdateDt().plusSeconds(15).isBefore(LocalDateTime.now()))) {
            // 发生完成标识
            WsSendEvent sendEvent = new WsSendEvent(this);
            sendEvent.setMessage("END");
            sendEvent.setSid(sid);
            Container.get(EventPublisher.class).publishEvent(sendEvent);
        } else {
            // 覆盖作为下次查询的起始条件
            queryVO.setStartRealTime(lastRealTime);
            // 继续查询
            scheduleNextAttempt(queryVO, sid);
        }


    }

    /**
     * 使用时间轮5秒再进行日志查询
     *
     * @param queryVO
     * @param sid
     */
    private void scheduleNextAttempt(JobLogQueryVO queryVO, String sid) {
        LogTimerWheel.registerWithJobTaskLog(() -> new JobTaskLogTimerTask(queryVO, sid), Duration.ofMillis(DELAY_MILLS));
    }
}
