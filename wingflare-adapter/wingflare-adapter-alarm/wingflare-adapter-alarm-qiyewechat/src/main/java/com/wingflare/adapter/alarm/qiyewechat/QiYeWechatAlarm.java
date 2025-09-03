package com.wingflare.adapter.alarm.qiyewechat;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
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

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 企业微信告警
 */
public class QiYeWechatAlarm implements AlarmDrive<AlarmContext> {

    private final static Logger log = LoggerFactory.getLogger(QiYeWechatAlarm.class);

    public static final String AT_LABEL = "<@{0}>";

    @Override
    public String getAlarmType() {
        return "qiyewechat";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        try {
            QiYeWechatAttribute qiYeWechatAttribute = JSONObject.parseObject(alarmContext.getNotifyAttribute(), QiYeWechatAttribute.class);
            String webhookUrl = qiYeWechatAttribute.getWebhookUrl();
            if (StrUtil.isBlank(webhookUrl)) {
                log.error("Please configure the WeChat robot webhookUrl first");
                return false;
            }
            Map<String, Object> map = MapUtil.newHashMap();
            QiYeWechatMessageContent messageContent = new QiYeWechatMessageContent();
            messageContent.setContent(StrUtil.sub(getAtText(qiYeWechatAttribute.getAts(), alarmContext.getText(), AT_LABEL), 0, 4096));
            map.put("msgtype", "markdown");
            map.put("markdown", messageContent);

            HttpRequest request = Container.get(HttpRequest.class);
            HttpResponse response = request
                    .setUrl(webhookUrl)
                    .setBody(JSONObject.toJSONString(map))
                    .setCharset(Charset.UTF_8)
                    .setContentType("application/json")
                    .setMethod(HttpMethod.POST)
                    .execute();

            if (response.isOk()) {
                return true;
            }

            log.error("Sending Enterprise WeChat message failed: {}", response);
            return false;
        } catch (Exception e) {
            log.error("Sending Enterprise WeChat message failed", e);
            return false;
        }
    }

    public static String getAtText(List<String> ats, String text, String atLabel) {

        if (CollUtil.isEmpty(ats)) {
            return text;
        }

        StringBuilder sb = new StringBuilder(text);
        sb.append(StrUtil.CRLF);
        ats.stream().filter(StrUtil::isNotBlank)
                .forEach(at -> sb.append(MessageFormat.format(atLabel, at)));

        return sb.toString();
    }

    private static class QiYeWechatMessageContent {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
