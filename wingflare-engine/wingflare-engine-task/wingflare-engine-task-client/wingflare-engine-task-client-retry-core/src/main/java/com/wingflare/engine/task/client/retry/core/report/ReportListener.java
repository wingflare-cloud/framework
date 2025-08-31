package com.wingflare.engine.task.client.retry.core.report;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.StopStrategy;
import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.retry.core.RetryExecutor;
import com.wingflare.engine.task.client.retry.core.RetryExecutorParameter;
import com.wingflare.engine.task.client.retry.core.executor.GuavaRetryExecutor;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.TaskAlarmFactory;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.EnvironmentUtils;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.core.window.Listener;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * 批量异步上报
 *
 * @author: opensnail
 * @date : 2022-03-08 13:54
 * @since 1.0.0
 */
public class ReportListener implements Listener<RetryTaskRequest> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    private static final String reportErrorTextMessageFormatter =
            "<font face=\"微软雅黑\" color=#ff0000 size=4>{}环境 异步批量上报异常</font>  \n" +
                    "> IP:{}  \n" +
                    "> 空间ID:{}  \n" +
                    "> 名称:{}  \n" +
                    "> 时间:{}  \n" +
                    "> 异常:{}  \n";

    private static final RpcClient CLIENT = RequestBuilder.<RpcClient, TaskRpcResult>newBuilder()
            .client(RpcClient.class)
            .callback(nettyResult -> TaskEngineLog.LOCAL.info("Data report successfully requestId:[{}]", nettyResult.getReqId())).build();

    @Override
    public void handler(List<RetryTaskRequest> list) {
        RetryExecutor<WaitStrategy, StopStrategy> retryExecutor =
                new GuavaRetryExecutor();

        Retryer retryer = retryExecutor.build(getRetryExecutorParameter());

        try {
            retryExecutor.call(retryer, () -> {
                TaskEngineLog.LOCAL.info("Batch asynchronous reporting ... <|>{}<|>", JsonUtil.toJsonString(list));
                CLIENT.reportRetryInfo(list);
                return null;
            }, throwable -> {
                TaskEngineLog.LOCAL.error("Data report failed. <|>{}<|>", JsonUtil.toJsonString(list));
                sendMessage(throwable);
            }, o -> TaskEngineLog.LOCAL.info("Data report successful retry：<|>{}<|>", JsonUtil.toJsonString(list)));
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Data report failed. <|>{}<|>", JsonUtil.toJsonString(list), e);
        }
    }

    public RetryExecutorParameter<WaitStrategy, StopStrategy> getRetryExecutorParameter() {
        return new RetryExecutorParameter<WaitStrategy, StopStrategy>() {

            @Override
            public WaitStrategy backOff() {
                return WaitStrategies.fixedWait(2, TimeUnit.SECONDS);
            }

            @Override
            public StopStrategy stop() {
                return StopStrategies.stopAfterAttempt(10);
            }

            @Override
            public List<RetryListener> getRetryListeners() {
                return Collections.singletonList(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {

                        if (attempt.hasException()) {
                            TaskEngineLog.LOCAL.error(" SnailJob reports an exception when reporting abnormal data, attempt [{}]", attempt.getAttemptNumber(), attempt.getExceptionCause());
                        }

                    }
                });
            }

        };
    }

    private void sendMessage(Throwable e) {

        try {
            ConfigRequest.Notify notify = GroupVersionCache.getRetryNotifyAttribute(RetryNotifySceneEnum.CLIENT_REPORT_ERROR.getNotifyScene());
            if (Objects.isNull(notify)) {
                return;
            }

            TaskProperties properties = SnailSpringContext.getBean(TaskProperties.class);
            if (Objects.isNull(properties)) {
                return;
            }
            List<Recipient> recipients = Optional.ofNullable(notify.getRecipients()).orElse(Lists.newArrayList());
            for (final Recipient recipient : recipients) {
                AlarmContext context = AlarmContext.build()
                        .text(reportErrorTextMessageFormatter,
                                EnvironmentUtils.getActiveProfile(),
                                NetUtil.getLocalIpStr(),
                                properties.getNamespace(),
                                properties.getGroup(),
                                LocalDateTime.now().format(formatter),
                                e.getMessage())
                        .title("Reporting exception: [{}]", properties.getGroup())
                        .notifyAttribute(recipient.getNotifyAttribute());
                Optional.ofNullable(TaskAlarmFactory.getAlarmType(recipient.getNotifyType())).ifPresent(alarm -> alarm.asyncSendMessage(context));
            }

        } catch (Exception e1) {
            TaskEngineLog.LOCAL.error("Client failed to send component exception alarm", e1);
        }

    }
}
