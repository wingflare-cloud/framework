package com.wingflare.engine.task.server.web.listener;

import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.enums.WebSocketSceneEnum;
import com.wingflare.engine.task.server.common.vo.JobLogQueryVO;
import com.wingflare.engine.task.server.web.model.event.WsRequestEvent;
import com.wingflare.engine.task.server.web.model.request.RetryTaskLogMessageQueryVO;
import com.wingflare.engine.task.server.web.service.JobLogService;
import com.wingflare.engine.task.server.web.service.RetryTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.listener
 * @Project：wingflare-task
 * @Date：2025/3/18 10:56
 * @Filename：WsRequestListener
 * @since 1.5.0
 */
@Component
public class WsRequestListener {
    private final JobLogService jobLogService;

    private final RetryTaskService retryTaskService;

    private final static Logger log = LoggerFactory.getLogger(WsRequestListener.class);

    public WsRequestListener(JobLogService jobLogService, RetryTaskService retryTaskService) {
        this.jobLogService = jobLogService;
        this.retryTaskService = retryTaskService;
    }

    @Async("logQueryExecutor")
    @EventListener(classes = WsRequestEvent.class)
    public void getJobLogs(WsRequestEvent requestVO) {
        if (!WebSocketSceneEnum.JOB_LOG_SCENE.equals(requestVO.getSceneEnum())) {
            return;
        }

        log.info("getJobLogs {}", requestVO.getSid());
        String message = requestVO.getMessage();
        JobLogQueryVO jobLogQueryVO = JsonUtil.parseObject(message, JobLogQueryVO.class);
        jobLogQueryVO.setSid(requestVO.getSid());
        jobLogQueryVO.setStartRealTime(0L);
        try {
            jobLogService.getJobLogPage(jobLogQueryVO);
        } catch (Exception e) {
            log.warn("send log error", e);
        }

    }


    @Async("logQueryExecutor")
    @EventListener(classes = WsRequestEvent.class)
    public void getRetryLogs(WsRequestEvent requestVO) {
        if (!WebSocketSceneEnum.RETRY_LOG_SCENE.equals(requestVO.getSceneEnum())) {
            return;
        }

        log.info("getRetryLogs {}", requestVO.getSid());
        String message = requestVO.getMessage();
        RetryTaskLogMessageQueryVO retryTaskLogMessageQueryVO = JsonUtil.parseObject(message, RetryTaskLogMessageQueryVO.class);
        retryTaskLogMessageQueryVO.setSid(requestVO.getSid());
        retryTaskLogMessageQueryVO.setStartRealTime(0L);
        try {
            retryTaskService.getRetryTaskLogMessagePage(retryTaskLogMessageQueryVO);
        } catch (Exception e) {
            log.warn("send log error", e);
        }

    }
}
