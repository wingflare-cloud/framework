package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.collection.CollectionUtil;
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
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.prepare.workflow.TerminalWorkflowPrepareHandler;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowMapper;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * OPENAPI
 * 新增工作流任务
 */
@Component
@Deprecated
public class OpenApiTriggerWorkFlowRequestHandler extends PostHttpRequestHandler {
    private final WorkflowMapper workflowMapper;
    private final AccessTemplate accessTemplate;
    private final TerminalWorkflowPrepareHandler terminalWorkflowPrepareHandler;

    public OpenApiTriggerWorkFlowRequestHandler(WorkflowMapper workflowMapper, AccessTemplate accessTemplate, TerminalWorkflowPrepareHandler terminalWorkflowPrepareHandler) {
        this.workflowMapper = workflowMapper;
        this.accessTemplate = accessTemplate;
        this.terminalWorkflowPrepareHandler = terminalWorkflowPrepareHandler;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_TRIGGER_WORKFLOW.equals(path);
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
        JobTriggerDTO workflowDTO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), JobTriggerDTO.class);
        Workflow workflow = workflowMapper.selectById(workflowDTO.getJobId());
        Assert.notNull(workflow, () -> new TaskServerException("workflow can not be null."));

        // 将字符串反序列化为 Set
        if (StrUtil.isNotBlank(workflow.getGroupName())) {
            Set<String> namesSet = new HashSet<>(Arrays.asList(workflow.getGroupName().split(", ")));

            // 判断任务节点相关组有无关闭，存在关闭组则停止执行工作流执行
            if (CollectionUtil.isNotEmpty(namesSet)) {
                for (String groupName : namesSet) {
                    long count = accessTemplate.getGroupConfigAccess().count(
                            new LambdaQueryWrapper<GroupConfig>()
                                    .eq(GroupConfig::getGroupName, groupName)
                                    .eq(GroupConfig::getNamespaceId, workflow.getNamespaceId())
                                    .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
                    );

                    if (count <= 0){
                        TaskEngineLog.LOCAL.warn("Group [{}] is closed, manual execution is not supported.", workflow.getGroupName());
                        return new TaskRpcResult(false, retryRequest.getReqId());
                    }
                }
            }
        }

        WorkflowTaskPrepareDTO prepareDTO = WorkflowTaskConverter.INSTANCE.toWorkflowTaskPrepareDTO(workflow);
        // 设置now表示立即执行
        prepareDTO.setNextTriggerAt(DateUtils.toNowMilli());
        prepareDTO.setTaskExecutorScene(JobTaskExecutorSceneEnum.MANUAL_WORKFLOW.getType());
//        设置工作流上下文
        String tmpWfContext = workflowDTO.getTmpArgsStr();
        if (StrUtil.isNotBlank(tmpWfContext) && !JsonUtil.isEmptyJson(tmpWfContext)){
            prepareDTO.setWfContext(tmpWfContext);
        }
        terminalWorkflowPrepareHandler.handler(prepareDTO);

        return new TaskRpcResult(true, retryRequest.getReqId());

    }
}
