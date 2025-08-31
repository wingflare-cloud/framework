package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.CallbackConfig;
import com.wingflare.engine.task.common.model.request.DecisionConfigRequest;
import com.wingflare.engine.task.server.common.convert.JobBatchResponseVOConverter;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.JobBatchResponseVO;
import com.wingflare.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowNodeMapper;
import com.wingflare.task.datasource.template.persistence.po.Job;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import com.wingflare.task.datasource.template.persistence.po.WorkflowNode;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * OPENAPI
 * 获取定时任务批次详情
 * @since 1.5.0
 */

@Component
@Deprecated
public class OpenApiGetJobBatchDetailRequestHandler extends PostHttpRequestHandler {
    private final JobMapper jobMapper;
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final WorkflowNodeMapper workflowNodeMapper;

    public OpenApiGetJobBatchDetailRequestHandler(JobMapper jobMapper, JobTaskBatchMapper jobTaskBatchMapper, WorkflowNodeMapper workflowNodeMapper) {
        this.jobMapper = jobMapper;
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.workflowNodeMapper = workflowNodeMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_GET_JOB_BATCH_DETAIL.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("query job batch content:[{}]", content);
        TaskRequest jobRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = jobRequest.getArgs();
        Long jobBatchId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);
        Assert.notNull(jobBatchId, () -> new TaskServerException("id cannot be null"));



        JobTaskBatch jobTaskBatch = jobTaskBatchMapper.selectById(jobBatchId);
        if (Objects.isNull(jobTaskBatch)) {
            return new TaskRpcResult(null, jobRequest.getReqId());
        }

        Job job = jobMapper.selectById(jobTaskBatch.getJobId());
        JobBatchResponseVO jobBatchResponseVO = JobBatchResponseVOConverter.INSTANCE.convert(jobTaskBatch, job);

        if (jobTaskBatch.getSystemTaskType().equals(SyetemTaskTypeEnum.WORKFLOW.getType())) {
            WorkflowNode workflowNode = workflowNodeMapper.selectById(jobTaskBatch.getWorkflowNodeId());
            jobBatchResponseVO.setNodeName(workflowNode.getNodeName());

            // 回调节点
            if (SystemConstants.CALLBACK_JOB_ID.equals(jobTaskBatch.getJobId())) {
                jobBatchResponseVO.setCallback(JsonUtil.parseObject(workflowNode.getNodeInfo(), CallbackConfig.class));
                jobBatchResponseVO.setExecutionAt(jobTaskBatch.getCreateDt());
                return new TaskRpcResult(jobBatchResponseVO, jobRequest.getReqId());
            }

            // 条件节点
            if (SystemConstants.DECISION_JOB_ID.equals(jobTaskBatch.getJobId())) {
                jobBatchResponseVO.setDecision(JsonUtil.parseObject(workflowNode.getNodeInfo(), DecisionConfigRequest.class));
                jobBatchResponseVO.setExecutionAt(jobTaskBatch.getCreateDt());
                return new TaskRpcResult(jobBatchResponseVO, jobRequest.getReqId());
            }
        }

        return new TaskRpcResult(jobBatchResponseVO, jobRequest.getReqId());
    }

}
