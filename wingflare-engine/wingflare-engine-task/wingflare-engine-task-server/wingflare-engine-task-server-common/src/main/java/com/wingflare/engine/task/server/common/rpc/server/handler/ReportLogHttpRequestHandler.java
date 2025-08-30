package com.wingflare.engine.task.server.common.rpc.server.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.request.JobLogTaskRequest;
import com.wingflare.engine.task.common.model.request.RetryLogTaskRequest;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.BATCH_LOG_REPORT;

/**
 * 处理日志上报数据
 *
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-12-26
 * @since 1.0.0
 */
@Component
public class ReportLogHttpRequestHandler extends PostHttpRequestHandler {
    @Override
    public boolean supports(String path) {
        return BATCH_LOG_REPORT.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery urlQuery, HttpHeaders headers) {

        SnailJobLog.LOCAL.debug("Begin Handler Log Report Data. [{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();

        Assert.notEmpty(args, () -> new TaskServerException("The log data to be reported cannot be empty. ReqId:[{}]", retryRequest.getReqId()));

        JsonNode jsonNode = JsonUtil.toJson(args[0]);
        List<RetryLogTaskRequest> retryTasks = Lists.newArrayList();
        List<JobLogTaskRequest> jobTasks = Lists.newArrayList();
        for (final JsonNode node : jsonNode) {
            JsonNode value = node.findValue(SystemConstants.JSON_FILED_LOG_TYPE);
            if (Objects.isNull(value) || value.asText().equals(LogTypeEnum.JOB.name())) {
                jobTasks.add(JsonUtil.parseObject(node.toPrettyString(), JobLogTaskRequest.class));
                continue;
            }

            if (value.asText().equals(LogTypeEnum.RETRY.name())) {
                retryTasks.add(JsonUtil.parseObject(node.toPrettyString(), RetryLogTaskRequest.class));
            }
        }

        // 批量新增日志数据
        if (CollUtil.isNotEmpty(jobTasks)) {
            ActorRef actorRef = ActorGenerator.jobLogActor();
            actorRef.tell(jobTasks, actorRef);
        }

        if (CollUtil.isNotEmpty(retryTasks)) {
            ActorRef actorRef = ActorGenerator.logActor();
            actorRef.tell(retryTasks, actorRef);
        }

        return new TaskRpcResult(StatusEnum.YES.getStatus(), "Batch Log Retry Data Upload Processed Successfully", Boolean.TRUE, retryRequest.getReqId());
    }

}
