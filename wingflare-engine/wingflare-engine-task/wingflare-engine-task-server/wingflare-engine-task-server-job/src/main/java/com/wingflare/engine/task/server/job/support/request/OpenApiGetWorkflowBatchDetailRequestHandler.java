package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.model.request.JobTaskConfigRequest;
import com.wingflare.engine.task.server.common.convert.JobBatchResponseVOConverter;
import com.wingflare.engine.task.server.common.convert.WorkflowConverter;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.handler.WorkflowHandler;
import com.wingflare.engine.task.server.common.vo.JobBatchResponseVO;
import com.wingflare.engine.task.server.common.vo.WorkflowDetailResponseVO;
import com.wingflare.engine.task.server.job.support.cache.MutableGraphCache;
import com.wingflare.task.datasource.template.persistence.mapper.*;
import com.wingflare.task.datasource.template.persistence.po.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.graph.MutableGraph;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * OPENAPI
 * 获取工作流任务批次详情
 * @since 1.5.0
 */
@Component
@Deprecated
public class OpenApiGetWorkflowBatchDetailRequestHandler extends PostHttpRequestHandler {
    private static final Integer WORKFLOW_DECISION_FAILED_STATUS = 98;
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;
    private final WorkflowMapper workflowMapper;
    private final WorkflowNodeMapper workflowNodeMapper;
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final WorkflowHandler webWorkflowHandlerOpenApi;
    private final JobMapper jobMapper;

    public OpenApiGetWorkflowBatchDetailRequestHandler(WorkflowTaskBatchMapper workflowTaskBatchMapper, WorkflowMapper workflowMapper, WorkflowNodeMapper workflowNodeMapper, JobTaskBatchMapper jobTaskBatchMapper, WorkflowHandler webWorkflowHandlerOpenApi, JobMapper jobMapper) {
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
        this.workflowMapper = workflowMapper;
        this.workflowNodeMapper = workflowNodeMapper;
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.webWorkflowHandlerOpenApi = webWorkflowHandlerOpenApi;
        this.jobMapper = jobMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_GET_WORKFLOW_BATCH_DETAIL.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("query workflow batch content:[{}]", content);
        TaskRequest jobRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = jobRequest.getArgs();
        Long workflowBatchId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);
        Assert.notNull(workflowBatchId, () -> new TaskServerException("id cannot be null"));

        WorkflowTaskBatch workflowTaskBatch = workflowTaskBatchMapper.selectOne(
                new LambdaQueryWrapper<WorkflowTaskBatch>()
                        .eq(WorkflowTaskBatch::getId, workflowBatchId));
        if (Objects.isNull(workflowTaskBatch)) {
            return new TaskRpcResult(null, jobRequest.getReqId());
        }

        Workflow workflow = workflowMapper.selectById(workflowTaskBatch.getWorkflowId());

        WorkflowDetailResponseVO responseVO = WorkflowConverter.INSTANCE.convert(workflow);
        responseVO.setWorkflowBatchStatus(workflowTaskBatch.getTaskBatchStatus());
        responseVO.setWfContext(workflowTaskBatch.getWfContext());
        List<WorkflowNode> workflowNodes = workflowNodeMapper.selectList(new LambdaQueryWrapper<WorkflowNode>()
                .eq(WorkflowNode::getDeleted, StatusEnum.NO.getStatus())
                .eq(WorkflowNode::getWorkflowId, workflow.getId()));

        List<Job> jobs = jobMapper.selectList(
                new LambdaQueryWrapper<Job>()
                        .in(Job::getId, StreamUtils.toSet(workflowNodes, WorkflowNode::getJobId)));

        Map<Long, Job> jobMap = StreamUtils.toIdentityMap(jobs, Job::getId);

        List<JobTaskBatch> alJobTaskBatchList = jobTaskBatchMapper.selectList(
                new LambdaQueryWrapper<JobTaskBatch>()
                        .eq(JobTaskBatch::getWorkflowTaskBatchId, workflowBatchId)
                        .orderByDesc(JobTaskBatch::getId));

        Map<Long, List<JobTaskBatch>> jobTaskBatchMap = StreamUtils.groupByKey(alJobTaskBatchList,
                JobTaskBatch::getWorkflowNodeId);
        List<WorkflowDetailResponseVO.NodeInfo> nodeInfos = WorkflowConverter.INSTANCE.convertList(workflowNodes);

        String flowInfo = workflowTaskBatch.getFlowInfo();
        MutableGraph<Long> graph = MutableGraphCache.getOrDefault(workflowBatchId, flowInfo);

        Set<Long> allNoOperationNode = Sets.newHashSet();
        Map<Long, WorkflowDetailResponseVO.NodeInfo> workflowNodeMap = nodeInfos.stream()
                .peek(nodeInfo -> {

                    JobTaskConfigRequest jobTask = nodeInfo.getJobTask();
                    if (Objects.nonNull(jobTask)) {
                        jobTask.setJobName(jobMap.getOrDefault(jobTask.getJobId(), new Job()).getJobName());
                    }

                    List<JobTaskBatch> jobTaskBatchList = jobTaskBatchMap.get(nodeInfo.getId());
                    if (CollUtil.isNotEmpty(jobTaskBatchList)) {
                        jobTaskBatchList = jobTaskBatchList.stream()
                                .sorted(Comparator.comparingInt(JobTaskBatch::getTaskBatchStatus))
                                .collect(Collectors.toList());
                        nodeInfo.setJobBatchList(
                                JobBatchResponseVOConverter.INSTANCE.convertListToJobBatchList(jobTaskBatchList));

                        // 取第最新的一条状态
                        JobTaskBatch jobTaskBatch = jobTaskBatchList.get(0);
                        if (JobOperationReasonEnum.WORKFLOW_DECISION_FAILED.getReason()
                                == jobTaskBatch.getOperationReason()) {
                            // 前端展示使用
                            nodeInfo.setTaskBatchStatus(WORKFLOW_DECISION_FAILED_STATUS);
                        } else {
                            nodeInfo.setTaskBatchStatus(jobTaskBatch.getTaskBatchStatus());
                        }

                        if (jobTaskBatchList.stream()
                                .filter(Objects::nonNull)
                                .anyMatch(OpenApiGetWorkflowBatchDetailRequestHandler::isNoOperation)) {
                            // 当前节点下面的所有节点都是无需处理的节点
                            Set<Long> allDescendants = MutableGraphCache.getAllDescendants(graph, nodeInfo.getId());
                            allNoOperationNode.addAll(allDescendants);
                        } else {
                            // 删除被误添加的节点
                            allNoOperationNode.remove(nodeInfo.getId());
                        }

                    } else {
                        if (JobTaskBatchStatusEnum.NOT_SUCCESS.contains(workflowTaskBatch.getTaskBatchStatus())) {
                            allNoOperationNode.add(nodeInfo.getId());
                        }
                    }
                })
                .collect(Collectors.toMap(WorkflowDetailResponseVO.NodeInfo::getId, Function.identity()));

        for (Long noOperationNodeId : allNoOperationNode) {
            WorkflowDetailResponseVO.NodeInfo nodeInfo = workflowNodeMap.get(noOperationNodeId);
            List<JobTaskBatch> jobTaskBatches = jobTaskBatchMap.get(nodeInfo.getId());

            if (CollUtil.isNotEmpty(jobTaskBatches)) {
                jobTaskBatches = jobTaskBatches.stream()
                        .sorted(Comparator.comparingInt(JobTaskBatch::getTaskBatchStatus))
                        .collect(Collectors.toList());
                nodeInfo.setJobBatchList(
                        JobBatchResponseVOConverter.INSTANCE.convertListToJobBatchList(jobTaskBatches));
            } else {
                JobBatchResponseVO jobBatchResponseVO = new JobBatchResponseVO();
                JobTaskConfigRequest jobTask = nodeInfo.getJobTask();
                if (Objects.nonNull(jobTask)) {
                    jobBatchResponseVO.setJobId(jobTask.getJobId());
                }
                // 只为前端展示提供
//                nodeInfo.setTaskBatchStatus(NOT_HANDLE_STATUS);
//                jobBatchResponseVO.setTaskBatchStatus(NOT_HANDLE_STATUS);
//                jobBatchResponseVO.setOperationReason(JobOperationReasonEnum.WORKFLOW_NODE_NO_REQUIRED.getReason());
                nodeInfo.setJobBatchList(Lists.newArrayList(jobBatchResponseVO));
            }
        }

        try {
            // 反序列化构建图
            WorkflowDetailResponseVO.NodeConfig config = webWorkflowHandlerOpenApi.buildNodeConfig(graph, SystemConstants.ROOT,
                    new HashMap<>(), workflowNodeMap);
            responseVO.setNodeConfig(config);
        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Deserialization failed. json:[{}]", flowInfo, e);
            throw new TaskServerException("Failed to query workflow batch details");
        }

        return new TaskRpcResult(responseVO, jobRequest.getReqId());
    }

    private static boolean isNoOperation(JobTaskBatch i) {
        return JobOperationReasonEnum.WORKFLOW_SUCCESSOR_SKIP_EXECUTION.contains(i.getOperationReason())
                || i.getTaskBatchStatus() == JobTaskBatchStatusEnum.STOP.getStatus();
    }

}
