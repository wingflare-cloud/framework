package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.JobStatusUpdateRequestVO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.WorkflowMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Workflow;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * OPENAPI
 * 更新工作流状态
 */
@Component
@Deprecated
public class OpenApiUpdateWorkFlowStatusRequestHandler extends PostHttpRequestHandler {
    private final WorkflowMapper workflowMapper;

    public OpenApiUpdateWorkFlowStatusRequestHandler(WorkflowMapper workflowMapper) {
        this.workflowMapper = workflowMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_UPDATE_WORKFLOW_STATUS.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        JobStatusUpdateRequestVO jobRequestVO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), JobStatusUpdateRequestVO.class);
        Workflow workflow = workflowMapper.selectOne(
                new LambdaQueryWrapper<Workflow>()
                        .select(Workflow::getId)
                        .eq(Workflow::getId, jobRequestVO.getId()));

        if (Objects.isNull(workflow)){
            TaskEngineLog.LOCAL.warn("Workflow does not exist");
            return new TaskRpcResult(false, retryRequest.getReqId());
        }
        workflow.setWorkflowStatus(jobRequestVO.getJobStatus());
        boolean update = 1 == workflowMapper.updateById(workflow);

        return new TaskRpcResult(update, retryRequest.getReqId());

    }
}
