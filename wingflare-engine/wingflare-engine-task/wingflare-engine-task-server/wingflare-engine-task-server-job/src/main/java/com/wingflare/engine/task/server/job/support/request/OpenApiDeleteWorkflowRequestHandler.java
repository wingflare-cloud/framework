package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.util.HttpHeaderUtil;
import com.wingflare.task.datasource.template.persistence.mapper.JobSummaryMapper;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowMapper;
import com.wingflare.task.datasource.template.persistence.po.JobSummary;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


/**
 * OPENAPI
 * 删除工作流
 */
@Component
@Deprecated
public class OpenApiDeleteWorkflowRequestHandler extends PostHttpRequestHandler {
    private final WorkflowMapper workflowMapper;
    private final JobSummaryMapper jobSummaryMapper;

    public OpenApiDeleteWorkflowRequestHandler(WorkflowMapper workflowMapper, JobSummaryMapper jobSummaryMapper) {
        this.workflowMapper = workflowMapper;
        this.jobSummaryMapper = jobSummaryMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_DELETE_WORKFLOW.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("Delete job content:[{}]", content);
        TaskRequest request = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = request.getArgs();
        Set<Long> ids = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Set.class);
        String namespaceId = HttpHeaderUtil.getNamespace(headers);

        Assert.isTrue(ids.size() == workflowMapper.delete(
                new LambdaQueryWrapper<Workflow>()
                        .eq(Workflow::getNamespaceId, namespaceId)
                        .eq(Workflow::getWorkflowStatus, StatusEnum.NO.getStatus())
                        .in(Workflow::getId, ids)
        ), () -> new TaskServerException("Failed to delete workflow task, please check if the task status is closed"));

        List<JobSummary> jobSummaries = jobSummaryMapper.selectList(new LambdaQueryWrapper<JobSummary>()
                .select(JobSummary::getId)
                .in(JobSummary::getBusinessId, ids)
                .eq(JobSummary::getNamespaceId, namespaceId)
                .eq(JobSummary::getSystemTaskType, SyetemTaskTypeEnum.WORKFLOW.getType())
        );
        if (CollUtil.isNotEmpty(jobSummaries)) {
            Assert.isTrue(jobSummaries.size() ==
                            jobSummaryMapper.deleteByIds(StreamUtils.toSet(jobSummaries, JobSummary::getId)),
                    () -> new TaskServerException("Summary table deletion failed")
            );
        }
        return new TaskRpcResult(true, request.getReqId());
    }
}
