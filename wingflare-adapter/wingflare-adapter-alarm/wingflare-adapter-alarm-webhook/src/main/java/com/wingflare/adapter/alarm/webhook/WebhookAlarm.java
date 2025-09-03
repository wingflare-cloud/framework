package com.wingflare.adapter.alarm.webhook;


import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import com.wingflare.api.core.Charset;
import com.wingflare.api.http.HttpMethod;
import com.wingflare.api.http.HttpRequest;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * webhook告警
 */
public class WebhookAlarm implements AlarmDrive<AlarmContext> {


    private static final Logger log = LoggerFactory.getLogger(WebhookAlarm.class);

    @Override
    public String getAlarmType() {
        return "webhook";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        WebhookAttribute webhookAttribute = JSONObject.parseObject(alarmContext.getNotifyAttribute(), WebhookAttribute.class);
        try {
            WebhookMessage webhookMessage = new WebhookMessage();
            webhookMessage.setText(alarmContext.getText());
            webhookMessage.setTitle(alarmContext.getTitle());

            HttpRequest request = Container.get(HttpRequest.class);
            HttpResponse response = request
                    .setUrl(webhookAttribute.getWebhookUrl())
                    .setBody(JSONObject.toJSONString(webhookMessage))
                    .setCharset(Charset.UTF_8)
                    .setContentType(webhookAttribute.getContentType())
                    .setMethod(HttpMethod.POST)
                    .execute();

            if (log.isDebugEnabled()) {
                log.debug("Sending Webhook alert result. webHook:[{}], result: [{}]", webhookAttribute.getWebhookUrl(), request.getBody());
            }

            if (response.isOk()) {
                return true;
            }
        } catch (Exception e) {
            log.error("Sending Webhook alert exception. webHook:[{}]", webhookAttribute, e);
            return false;
        }
        return true;
    }

    private static class WebhookMessage {

        private String title;
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
