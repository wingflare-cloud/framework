package com.wingflare.engine.task.client.retry.core.strategy;

import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.SnailJobProperties;
import com.wingflare.engine.task.client.retry.core.Report;
import com.wingflare.engine.task.client.retry.core.RetryExecutor;
import com.wingflare.engine.task.client.retry.core.RetryExecutorParameter;
import com.wingflare.engine.task.client.retry.core.event.SnailJobListener;
import com.wingflare.engine.task.client.retry.core.executor.GuavaRetryExecutor;
import com.wingflare.engine.task.client.retry.core.intercepter.RetrySiteSnapshot;
import com.wingflare.engine.task.client.retry.core.loader.SnailRetrySpiLoader;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerResultContext;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.TaskAlarmFactory;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.util.EnvironmentUtils;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.StopStrategy;
import com.github.rholder.retry.WaitStrategy;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * @author: opensnail
 * @date : 2022-03-04 14:40
 */
public abstract class AbstractRetryStrategies implements RetryStrategy {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    private static final String TEXT_MESSAGE_FORMATTER =
            "<font face=\"微软雅黑\" color=#ff0000 size=4>{}环境 重试组件异常</font>  \n" +
                    "> IP:{}  \n" +
                    "> 空间ID:{}  \n" +
                    "> 名称:{}  \n" +
                    "> 时间:{}  \n" +
                    "> 异常:{}  \n";

    private final List<SnailJobListener> snailJobListeners = SnailRetrySpiLoader.loadSnailJobListener();

    private final static Logger log = LoggerFactory.getLogger(AbstractRetryStrategies.class);

    @Autowired
    private List<Report> reports;
    @Autowired
    private SnailJobProperties snailJobProperties;

    @Override
    public RetryerResultContext openRetry(String sceneName, String executorClassName, Object[] params) {

        RetryerResultContext retryerResultContext = new RetryerResultContext();

        // 开始内存重试
        RetryExecutor<WaitStrategy, StopStrategy> retryExecutor =
                new GuavaRetryExecutor(sceneName, executorClassName);
        RetryerInfo retryerInfo = retryExecutor.getRetryerInfo();

        if (!preValidator(retryerInfo, retryerResultContext)) {
            return retryerResultContext;
        }

        RetrySiteSnapshot.setStatus(RetrySiteSnapshot.EnumStatus.RUNNING.getStatus());

        setStage();

        Retryer retryer = retryExecutor.build(getRetryExecutorParameter(retryerInfo));

        retryerResultContext.setRetryerInfo(retryerInfo);

        try {
            for (SnailJobListener snailJobListener : snailJobListeners) {
                snailJobListener.beforeRetry(sceneName, executorClassName, params);
            }

            Object result = retryExecutor.call(retryer, doGetCallable(retryExecutor, params), getRetryErrorConsumer(retryerResultContext, params), getRetrySuccessConsumer(retryerResultContext));
            retryerResultContext.setResult(result);

        } catch (Exception e) {
            log.error("Unexpected exception occurred during retry, sceneName:[{}] executorClassName:[{}]", sceneName, executorClassName, e);
            retryerResultContext.setMessage("Unexpected exception" + e.getMessage());
            // 本地重试状态为失败 远程重试状态为成功
            unexpectedError(e, retryerResultContext);

            // 预警
            sendMessage(e);
        } finally {
            // 重试调度完成
            RetrySiteSnapshot.setStatus(RetrySiteSnapshot.EnumStatus.COMPLETE.getStatus());
        }

        return retryerResultContext;
    }

    protected abstract void setStage();

    protected Consumer<Object> getRetrySuccessConsumer(RetryerResultContext retryerResultContext) {
        return o -> {

            success(retryerResultContext);

            Object result = retryerResultContext.getResult();
            RetryerInfo retryerInfo = retryerResultContext.getRetryerInfo();

            for (SnailJobListener snailJobListener : snailJobListeners) {
                snailJobListener.successOnRetry(result, retryerInfo.getScene(), retryerInfo.getExecutorClassName());
            }

            doRetrySuccessConsumer(retryerResultContext).accept(retryerResultContext);
        };
    }


    protected abstract Consumer<Object> doRetrySuccessConsumer(RetryerResultContext context);

    private Consumer<Throwable> getRetryErrorConsumer(RetryerResultContext context, Object... params) {
        return throwable -> {
            context.setThrowable(throwable);
            context.setMessage(throwable.getMessage());

            error(context);

            RetryerInfo retryerInfo = context.getRetryerInfo();
            try {
                for (SnailJobListener snailJobListener : snailJobListeners) {
                    snailJobListener
                            .failureOnRetry(retryerInfo.getScene(), retryerInfo.getExecutorClassName(), throwable);
                }
            } catch (Exception e) {
                log.error(" Failure listener mode processing failed", e);
                throw e;
            }

            doGetRetryErrorConsumer(retryerInfo, params).accept(throwable);

        };
    }

    protected abstract void error(RetryerResultContext context);

    protected abstract boolean preValidator(RetryerInfo retryerInfo, RetryerResultContext resultContext);

    protected abstract void unexpectedError(Exception e, RetryerResultContext retryerResultContext);

    protected abstract void success(RetryerResultContext retryerResultContext);

    protected abstract Consumer<Throwable> doGetRetryErrorConsumer(RetryerInfo retryerInfo, Object[] params);

    protected abstract Callable doGetCallable(RetryExecutor<WaitStrategy, StopStrategy> retryExecutor, Object[] params);

    protected abstract RetryExecutorParameter<WaitStrategy, StopStrategy> getRetryExecutorParameter(RetryerInfo retryerInfo);

    /**
     * 上报数据
     *
     * @param retryerInfo 定义重试场景的信息
     * @param params      执行参数
     */
    protected boolean doReport(final RetryerInfo retryerInfo, final Object[] params) {

        for (Report report : reports) {
            if (report.supports(retryerInfo.isAsync())) {
                return report.report(retryerInfo.getScene(), retryerInfo.getExecutorClassName(), params);
            }
        }

        return Boolean.FALSE;
    }

    private void sendMessage(Exception e) {

        try {
            ConfigRequest.Notify notify = GroupVersionCache.getRetryNotifyAttribute(RetryNotifySceneEnum.CLIENT_COMPONENT_ERROR.getNotifyScene());
            if (Objects.nonNull(notify)) {
                List<Recipient> recipients = Optional.ofNullable(notify.getRecipients()).orElse(Lists.newArrayList());

                for (final Recipient recipient : recipients) {
                    AlarmContext context = AlarmContext.build()
                            .text(TEXT_MESSAGE_FORMATTER,
                                    EnvironmentUtils.getActiveProfile(),
                                    NetUtil.getLocalIpStr(),
                                    snailJobProperties.getNamespace(),
                                    snailJobProperties.getGroup(),
                                    LocalDateTime.now().format(formatter),
                                    e.getMessage())
                            .title("retry component handling exception:[{}]", snailJobProperties.getGroup())
                            .notifyAttribute(recipient.getNotifyAttribute());
                    Optional.ofNullable(TaskAlarmFactory.getAlarmType(recipient.getNotifyType())).ifPresent(alarm -> alarm.asyncSendMessage(context));
                }
            }
        } catch (Exception e1) {
            SnailJobLog.LOCAL.error("Client failed to send component exception alert.", e1);
        }

    }
}
