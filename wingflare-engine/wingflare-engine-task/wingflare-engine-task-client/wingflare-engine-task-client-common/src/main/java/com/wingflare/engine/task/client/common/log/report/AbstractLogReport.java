package com.wingflare.engine.task.client.common.log.report;

import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.LogReport;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.window.SlidingRingWindow;
import com.wingflare.engine.task.common.core.window.Listener;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.model.request.LogTaskRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.InitializingBean;
import jakarta.annotation.Resource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author xiaowoniu
 * @date 2024-03-20 22:56:53
 * @since 3.2.0
 */
public abstract class AbstractLogReport<T extends LogTaskRequest> implements Lifecycle, InitializingBean, LogReport {

    @Resource
    private TaskProperties taskProperties;
    private SlidingRingWindow<LogTaskRequest> slidingWindow;

    @Override
    public void report(LogContentDTO logContentDTO) {
        slidingWindow.add(buildLogTaskDTO(logContentDTO));
    }

    protected abstract T buildLogTaskDTO(LogContentDTO logContentDTO);

    @Override
    public void start() {
        if (Objects.nonNull(slidingWindow)) {
            return;
        }

        TaskProperties.LogSlidingWindowConfig logSlidingWindow = taskProperties.getLogSlidingWindow();

        Listener<LogTaskRequest> reportLogListener = new ReportLogListener();
        ChronoUnit chronoUnit = logSlidingWindow.getChronoUnit();
        Duration duration = Duration.of(logSlidingWindow.getDuration(), chronoUnit);
        slidingWindow= new SlidingRingWindow<>(duration, logSlidingWindow.getTotalThreshold(), Lists.newArrayList(reportLogListener));
    }

    @Override
    public void close() {
        if (Objects.isNull(slidingWindow)) {
            return;
        }

        TaskEngineLog.LOCAL.info("AsyncReport Log about to shutdown");
        slidingWindow.shutdown();
        TaskEngineLog.LOCAL.info("AsyncReport Log has been shutdown");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogReportFactory.add(this);
    }
}
