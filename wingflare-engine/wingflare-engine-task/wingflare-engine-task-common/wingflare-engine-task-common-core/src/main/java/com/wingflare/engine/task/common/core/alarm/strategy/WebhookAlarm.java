package com.wingflare.engine.task.common.core.alarm.strategy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wingflare.lib.core.Builder;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.attribute.WebhookAttribute;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.AlarmTypeEnum;
import com.wingflare.engine.task.common.core.enums.ContentTypeEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2024-05-07 10:15
 */
@Component
public class WebhookAlarm extends AbstractAlarm<AlarmContext> {

    @Override
    public Integer getAlarmType() {
        return AlarmTypeEnum.WEBHOOK.getValue();
    }

    @Override
    public boolean syncSendMessage(AlarmContext alarmContext) {

        WebhookAttribute webhookAttribute = JsonUtil.parseObject(alarmContext.getNotifyAttribute(), WebhookAttribute.class);
        try {
            WebhookMessage webhookMessage = Builder.of(WebhookMessage::new)
                    .with(WebhookMessage::setText, alarmContext.getTitle())
                    .build();

            HttpRequest post = HttpUtil.createPost(webhookAttribute.getWebhookUrl());
            HttpRequest request = post.body(JsonUtil.toJsonString(webhookMessage),
                            ContentTypeEnum.valueOf(webhookAttribute.getContentType()).getMediaType())
                    .header(SystemConstants.SECRET, webhookAttribute.getSecret());
            HttpResponse execute = request.execute();
            TaskEngineLog.LOCAL.info("Sending Webhook alert result. webHook:[{}], result: [{}]", webhookAttribute.getWebhookUrl(), execute.body());
            if (execute.isOk()) {
                return true;
            }
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Sending Webhook alert exception. webHook:[{}]", webhookAttribute, e);
            return false;
        }
        return true;
    }

    private static class WebhookMessage {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    @Override
    public boolean asyncSendMessage(List<AlarmContext> alarmContexts) {
        for (AlarmContext alarmContext : alarmContexts) {
            asyncSendMessage(alarmContext);
        }

        return true;
    }
}
