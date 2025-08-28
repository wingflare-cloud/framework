package com.wingflare.lib.task.alarm.strategy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.task.alarm.AlarmContext;
import com.wingflare.lib.task.alarm.attribute.WebhookAttribute;
import com.wingflare.lib.task.constant.SystemConstants;
import com.wingflare.lib.task.enums.AlarmTypeEnum;
import com.wingflare.lib.task.enums.ContentTypeEnum;
import com.wingflare.lib.task.util.JsonUtil;
import com.aizuda.snailjob.common.log.SnailJobLog;
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
            HttpRequest request = post.body(JsonUtil.toJsonString(webhookMessage), ContentTypeEnum.valueOf(webhookAttribute.getContentType()).getMediaType().toString())
                    .header(SystemConstants.SECRET, webhookAttribute.getSecret());
            HttpResponse execute = request.execute();
            SnailJobLog.LOCAL.info("Sending Webhook alert result. webHook:[{}], result: [{}]", webhookAttribute.getWebhookUrl(), execute.body());
            if (execute.isOk()) {
                return true;
            }
        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Sending Webhook alert exception. webHook:[{}]", webhookAttribute, e);
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
