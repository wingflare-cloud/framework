package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.SnailJobRequest;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.JobStatusUpdateRequestVO;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowMapper;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
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
    public SnailJobRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobRequest retryRequest = JsonUtil.parseObject(content, SnailJobRequest.class);
        Object[] args = retryRequest.getArgs();
        JobStatusUpdateRequestVO jobRequestVO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), JobStatusUpdateRequestVO.class);
        Workflow workflow = workflowMapper.selectOne(
                new LambdaQueryWrapper<Workflow>()
                        .select(Workflow::getId)
                        .eq(Workflow::getId, jobRequestVO.getId()));

        if (Objects.isNull(workflow)){
            SnailJobLog.LOCAL.warn("Workflow does not exist");
            return new SnailJobRpcResult(false, retryRequest.getReqId());
        }
        workflow.setWorkflowStatus(jobRequestVO.getJobStatus());
        boolean update = 1 == workflowMapper.updateById(workflow);

        return new SnailJobRpcResult(update, retryRequest.getReqId());

    }
}
