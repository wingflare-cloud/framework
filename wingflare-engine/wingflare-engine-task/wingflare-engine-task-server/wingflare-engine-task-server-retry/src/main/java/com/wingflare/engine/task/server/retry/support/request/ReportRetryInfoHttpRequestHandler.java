package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import com.wingflare.engine.task.server.common.enums.TaskGeneratorSceneEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.retry.service.TaskContextConverter;
import com.wingflare.engine.task.server.retry.support.generator.retry.TaskContext;
import com.wingflare.engine.task.server.retry.support.generator.retry.TaskGenerator;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.BATCH_REPORT;

/**
 * 处理上报数据
 *
 * @author: opensnail
 * @date : 2022-03-07 16:39
 * @since 1.0.0
 */
@Component
public class ReportRetryInfoHttpRequestHandler extends PostHttpRequestHandler {
    private final List<TaskGenerator> taskGenerators;
    private final AccessTemplate accessTemplate;

    public ReportRetryInfoHttpRequestHandler(List<TaskGenerator> taskGenerators, AccessTemplate accessTemplate) {
        this.taskGenerators = taskGenerators;
        this.accessTemplate = accessTemplate;
    }

    @Override
    public boolean supports(String path) {
        return BATCH_REPORT.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    @Transactional
    public TaskRpcResult doHandler(String content, UrlQuery urlQuery, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("Batch Report Retry Data. content:[{}]", content);

        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();

        try {

            TaskGenerator taskGenerator = taskGenerators.stream()
                    .filter(t -> t.supports(TaskGeneratorSceneEnum.CLIENT_REPORT.getScene()))
                    .findFirst().orElseThrow(() -> new TaskServerException("No matching task generator found"));

            Assert.notEmpty(args, () -> new TaskServerException("The reported data cannot be empty. ReqId:[{}]", retryRequest.getReqId()));
            List<RetryTaskRequest> retryTaskList = JsonUtil.parseList(JsonUtil.toJsonString(args[0]), RetryTaskRequest.class);

            SnailJobLog.LOCAL.info("begin handler report data. <|>{}<|>", JsonUtil.toJsonString(retryTaskList));

            Set<String> set = StreamUtils.toSet(retryTaskList, RetryTaskRequest::getGroupName);
            Assert.isTrue(set.size() <= 1, () -> new TaskServerException("Batch report data, the same batch can only be the same group. ReqId:[{}]", retryRequest.getReqId()));

            Map<String, List<RetryTaskRequest>> map = StreamUtils.groupByKey(retryTaskList, RetryTaskRequest::getSceneName);

            Retryer<Object> retryer = RetryerBuilder.newBuilder()
                    .retryIfException(throwable -> {
                        // 若是数据库异常则重试
                        return throwable instanceof DuplicateKeyException
                                || throwable instanceof TransactionSystemException
                                || throwable instanceof ConcurrencyFailureException
                                || throwable instanceof IOException;
                    })
                    .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                    .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                    .withRetryListener(new RetryListener() {
                        @Override
                        public <V> void onRetry(final Attempt<V> attempt) {
                            if (attempt.hasException()) {
                                SnailJobLog.LOCAL.error("Data reporting exception occurred, execute retry. ReqId:[{}] Count:[{}]",
                                        retryRequest.getReqId(), attempt.getAttemptNumber(), attempt.getExceptionCause());
                            }
                        }
                    })
                    .build();

            String namespaceId = headers.getAsString(HeadersEnum.NAMESPACE.getKey());
            String groupName = headers.getAsString(HeadersEnum.GROUP_NAME.getKey());

            GroupConfig groupConfig = accessTemplate.getGroupConfigAccess()
                    .getGroupConfigByGroupName(groupName, namespaceId);
            if (Objects.isNull(groupConfig)) {
                throw new TaskServerException(
                        "failed to report data, no group configuration found. groupName:[{}]", groupName);
            }

            retryer.call(() -> {
                map.forEach(((sceneName, retryTaskDTOS) -> {
                    TaskContext taskContext = new TaskContext();
                    taskContext.setSceneName(sceneName);
                    taskContext.setNamespaceId(namespaceId);
                    taskContext.setGroupId(groupConfig.getId());
                    taskContext.setGroupName(groupName);
                    taskContext.setInitScene(groupConfig.getInitScene());
                    taskContext.setTaskInfos(TaskContextConverter.INSTANCE.toTaskContextInfo(retryTaskDTOS));

                    // 生成任务
                    taskGenerator.taskGenerator(taskContext);
                }));

                return null;
            });

            return new TaskRpcResult(StatusEnum.YES.getStatus(), "Batch Retry Data Upload Processed Successfully", Boolean.TRUE, retryRequest.getReqId());
        } catch (Exception e) {

            Throwable throwable = e;
            if (e.getClass().isAssignableFrom(RetryException.class)) {
                RetryException re = (RetryException) e;
                throwable = re.getLastFailedAttempt().getExceptionCause();
            }

            SnailJobLog.LOCAL.error("Batch Report Retry Data Error. <|>{}<|>", args[0], throwable);
            return new TaskRpcResult(StatusEnum.YES.getStatus(), throwable.getMessage(), Boolean.FALSE, retryRequest.getReqId());
        }
    }

}
