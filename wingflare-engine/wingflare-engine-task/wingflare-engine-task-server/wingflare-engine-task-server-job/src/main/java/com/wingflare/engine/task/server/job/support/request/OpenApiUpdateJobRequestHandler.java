package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.convert.JobConverter;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.CronUtils;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.util.HttpHeaderUtil;
import com.wingflare.engine.task.server.common.util.TriggerIntervalUtils;
import com.wingflare.engine.task.server.common.vo.JobRequestVO;
import com.wingflare.engine.task.server.job.support.cache.ResidentTaskCache;
import com.wingflare.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.task.datasource.template.persistence.po.Job;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * OPENAPI
 * 更新定时任务
 */
@Component
@Deprecated
public class OpenApiUpdateJobRequestHandler extends PostHttpRequestHandler {
    private final JobMapper jobMapper;

    public OpenApiUpdateJobRequestHandler(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_UPDATE_JOB.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("Update job content:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        String namespace = HttpHeaderUtil.getNamespace(headers);
        JobRequestVO jobRequestVO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), JobRequestVO.class);
        if (Objects.isNull(jobRequestVO.getId())){
            TaskEngineLog.LOCAL.warn("ID cannot be empty, update failed");
            return new TaskRpcResult(false, retryRequest.getReqId());
        }

        Job job = jobMapper.selectById(jobRequestVO.getId());
        if (Objects.isNull(job)){
            TaskEngineLog.LOCAL.warn("Job is null, update failed");
            return new TaskRpcResult(false, retryRequest.getReqId());
        }

        // 判断常驻任务
        Job updateJob = JobConverter.INSTANCE.convert(jobRequestVO);
        updateJob.setResident(isResident(jobRequestVO));
        updateJob.setNamespaceId(namespace);

        // check triggerInterval
        checkTriggerInterval(jobRequestVO);

        // 工作流任务
        if (Objects.equals(jobRequestVO.getTriggerType(), SystemConstants.WORKFLOW_TRIGGER_TYPE)) {
            updateJob.setNextTriggerAt(0L);
            // 非常驻任务 > 非常驻任务
        } else if (Objects.equals(job.getResident(), StatusEnum.NO.getStatus()) && Objects.equals(
                updateJob.getResident(),
                StatusEnum.NO.getStatus())) {
            updateJob.setNextTriggerAt(calculateNextTriggerAt(updateJob, DateUtils.toNowMilli()));
        } else if (Objects.equals(job.getResident(), StatusEnum.YES.getStatus()) && Objects.equals(
                updateJob.getResident(), StatusEnum.NO.getStatus())) {
            // 常驻任务的触发时间
            long time = Optional.ofNullable(ResidentTaskCache.get(jobRequestVO.getId()))
                    .orElse(DateUtils.toNowMilli());
            updateJob.setNextTriggerAt(calculateNextTriggerAt(updateJob, time));
            // 老的是不是常驻任务 新的是常驻任务 需要使用当前时间计算下次触发时间
        } else if (Objects.equals(job.getResident(), StatusEnum.NO.getStatus()) && Objects.equals(
                updateJob.getResident(), StatusEnum.YES.getStatus())) {
            updateJob.setNextTriggerAt(DateUtils.toNowMilli());
        }

        // 禁止更新组
        updateJob.setGroupName(null);

        LambdaUpdateWrapper<Job> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Job::getId, jobRequestVO.getId());
        updateWrapper.set(Job::getOwnerId, jobRequestVO.getOwnerId());
        boolean update = 1 == jobMapper.update(updateJob, updateWrapper);
        return new TaskRpcResult(update, retryRequest.getReqId());

    }

    private void checkTriggerInterval(JobRequestVO jobRequestVO) {
        TriggerIntervalUtils.checkTriggerInterval(jobRequestVO.getTriggerInterval(), jobRequestVO.getTriggerType());
    }

    private Integer isResident(JobRequestVO jobRequestVO) {
        if (Objects.equals(jobRequestVO.getTriggerType(), SystemConstants.WORKFLOW_TRIGGER_TYPE)) {
            return StatusEnum.NO.getStatus();
        }

        if (Objects.equals(jobRequestVO.getTriggerType(), WaitStrategies.WaitStrategyEnum.FIXED.getType())) {
            if (Integer.parseInt(jobRequestVO.getTriggerInterval()) < 10) {
                return StatusEnum.YES.getStatus();
            }
        } else if (Objects.equals(jobRequestVO.getTriggerType(), WaitStrategies.WaitStrategyEnum.CRON.getType())) {
            if (CronUtils.getExecuteInterval(jobRequestVO.getTriggerInterval()) < 10 * 1000) {
                return StatusEnum.YES.getStatus();
            }
        } else if (Objects.equals(jobRequestVO.getTriggerType(), WaitStrategies.WaitStrategyEnum.POINT_IN_TIME.getType())) {
            return StatusEnum.NO.getStatus();
        } else {
            throw new TaskServerException("Unknown trigger type");
        }

        return StatusEnum.NO.getStatus();
    }

    private static Long calculateNextTriggerAt(final Job job, Long time) {
        if (Objects.equals(job.getTriggerType(), SystemConstants.WORKFLOW_TRIGGER_TYPE)) {
            return 0L;
        }

        WaitStrategy waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(job.getTriggerType());
        WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();
        waitStrategyContext.setTriggerInterval(job.getTriggerInterval());
        waitStrategyContext.setNextTriggerAt(time);
        return waitStrategy.computeTriggerTime(waitStrategyContext);
    }
}
