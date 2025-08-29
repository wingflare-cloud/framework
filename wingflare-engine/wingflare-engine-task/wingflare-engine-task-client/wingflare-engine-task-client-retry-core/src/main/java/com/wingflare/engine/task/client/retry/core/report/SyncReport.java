package com.wingflare.engine.task.client.retry.core.report;

import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.SnailJobProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.SnailJobAlarmFactory;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.core.util.EnvironmentUtils;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * 同步上报数据
 *
 * @author opensnail
 * @date 2023-05-15
 * @since 1.3.0
 */
@Component
public class SyncReport extends AbstractReport {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    private static final String reportErrorTextMessageFormatter =
            "<font face=\"微软雅黑\" color=#ff0000 size=4>{}环境 同步上报异常</font>  \n" +
                    "> IP:{}  \n" +
                    "> 空间ID:{}  \n" +
                    "> 名称:{}  \n" +
                    "> 时间:{}  \n" +
                    "> 异常:{}  \n";

    @Autowired
    private SnailJobProperties snailJobProperties;

    @Override
    public boolean supports(boolean async) {
        return !async;
    }

    @Override
    public boolean doReport(RetryerInfo retryerInfo, Object[] params) {

        return syncReport(retryerInfo.getScene(), retryerInfo.getExecutorClassName(), params, retryerInfo.getTimeout(), retryerInfo.getUnit());
    }

    /**
     * 异步上报到服务端, 若当前处于远程重试阶段不会进行执行上报
     */
    public Boolean syncReport(String scene, String targetClassName, Object[] args, long timeout, TimeUnit unit) {

        RetryTaskRequest retryTaskRequest = buildRetryTaskDTO(scene, targetClassName, args);

        RpcClient client = RequestBuilder.<RpcClient, SnailJobRpcResult>newBuilder()
                .client(RpcClient.class)
                .async(Boolean.FALSE)
                .timeout(timeout)
                .unit(unit)
                .build();

        try {
            SnailJobRpcResult result = client.reportRetryInfo(Collections.singletonList(retryTaskRequest));
            SnailJobLog.LOCAL.debug("Data report result result:[{}]", JsonUtil.toJsonString(result));
            return (Boolean) result.getData();
        } catch (Exception e) {
            sendMessage(e);
            throw e;
        }

    }

    private void sendMessage(Throwable e) {

        try {
            ConfigRequest.Notify notify = GroupVersionCache.getRetryNotifyAttribute(RetryNotifySceneEnum.CLIENT_REPORT_ERROR.getNotifyScene());
            if (Objects.isNull(notify)) {
                return;
            }

            List<Recipient> recipients = Optional.ofNullable(notify.getRecipients()).orElse(Lists.newArrayList());
            for (final Recipient recipient : recipients) {
                AlarmContext context = AlarmContext.build()
                        .text(reportErrorTextMessageFormatter,
                                EnvironmentUtils.getActiveProfile(),
                                NetUtil.getLocalIpStr(),
                                snailJobProperties.getNamespace(),
                                snailJobProperties.getGroup(),
                                LocalDateTime.now().format(formatter),
                                e.getMessage())
                        .title("Sync reporting exception: [{}]", snailJobProperties.getGroup())
                        .notifyAttribute(recipient.getNotifyAttribute());

                Optional.ofNullable(SnailJobAlarmFactory.getAlarmType(recipient.getNotifyType())).ifPresent(alarm -> alarm.asyncSendMessage(context));
            }

        } catch (Exception e1) {
            SnailJobLog.LOCAL.error("Client failed to send component exception alarm", e1);
        }

    }

}
