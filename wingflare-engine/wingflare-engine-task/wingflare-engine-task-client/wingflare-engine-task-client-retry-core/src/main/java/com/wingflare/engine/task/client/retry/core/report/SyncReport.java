package com.wingflare.engine.task.client.retry.core.report;

import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import com.google.common.collect.Lists;
import com.wingflare.lib.alarm.AlarmUtil;
import com.wingflare.lib.config.ConfigUtil;
import jakarta.annotation.Resource;
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

    @Resource
    private TaskProperties taskProperties;

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

        RpcClient client = RequestBuilder.<RpcClient, TaskRpcResult>newBuilder()
                .client(RpcClient.class)
                .async(Boolean.FALSE)
                .timeout(timeout)
                .unit(unit)
                .build();

        try {
            TaskRpcResult result = client.reportRetryInfo(Collections.singletonList(retryTaskRequest));
            TaskEngineLog.LOCAL.debug("Data report result result:[{}]", JsonUtil.toJsonString(result));
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
                                ConfigUtil.getProfiles(),
                                NetUtil.getLocalIpStr(),
                                taskProperties.getNamespace(),
                                taskProperties.getGroup(),
                                LocalDateTime.now().format(formatter),
                                e.getMessage())
                        .title("Sync reporting exception: [{}]", taskProperties.getGroup())
                        .notifyAttribute(recipient.getNotifyAttribute());

                Optional.ofNullable(AlarmUtil.getAlarmType(recipient.getNotifyType()))
                        .ifPresent(alarm -> AlarmUtil.asyncSendMessage(recipient.getNotifyType(), context));
            }

        } catch (Exception e1) {
            TaskEngineLog.LOCAL.error("Client failed to send component exception alarm", e1);
        }

    }

}
