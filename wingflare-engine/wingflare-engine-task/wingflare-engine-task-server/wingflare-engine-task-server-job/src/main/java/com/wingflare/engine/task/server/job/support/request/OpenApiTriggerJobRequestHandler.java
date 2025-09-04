package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.dto.JobTriggerDTO;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.prepare.job.TerminalJobPrepareHandler;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;


/**
 * OPENAPI
 * 调度定时任务
 */
@Component
@Deprecated
public class OpenApiTriggerJobRequestHandler extends PostHttpRequestHandler {
    private final JobMapper jobMapper;
    private final AccessTemplate accessTemplate;
    private final TerminalJobPrepareHandler terminalJobPrepareHandler;

    public OpenApiTriggerJobRequestHandler(JobMapper jobMapper, AccessTemplate accessTemplate, TerminalJobPrepareHandler terminalJobPrepareHandler) {
        this.jobMapper = jobMapper;
        this.accessTemplate = accessTemplate;
        this.terminalJobPrepareHandler = terminalJobPrepareHandler;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_TRIGGER_JOB.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("Trigger job content:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        JobTriggerDTO jobTriggerDTO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), JobTriggerDTO.class);
        Job job = jobMapper.selectById(jobTriggerDTO.getJobId());
        Assert.notNull(job, () -> new TaskServerException("job can not be null."));

        long count = accessTemplate.getGroupConfigAccess().count(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getGroupName, job.getGroupName())
                .eq(GroupConfig::getNamespaceId, job.getNamespaceId())
                .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
        );

        if (count <= 0){
            TaskEngineLog.LOCAL.warn("Group [{}] is closed, manual execution is not supported.", job.getGroupName());
            return new TaskRpcResult(false, retryRequest.getReqId());
        }
        JobTaskPrepareDTO jobTaskPrepare = JobTaskConverter.INSTANCE.toJobTaskPrepare(job);
        // 设置now表示立即执行
        jobTaskPrepare.setNextTriggerAt(DateUtils.toNowMilli());
        jobTaskPrepare.setTaskExecutorScene(JobTaskExecutorSceneEnum.MANUAL_JOB.getType());
        // 设置手动参数
        if (StrUtil.isNotBlank(jobTriggerDTO.getTmpArgsStr())) {
            jobTaskPrepare.setTmpArgsStr(jobTriggerDTO.getTmpArgsStr());
        }
        // 创建批次
        terminalJobPrepareHandler.handle(jobTaskPrepare);

        return new TaskRpcResult(true, retryRequest.getReqId());
    }
}
