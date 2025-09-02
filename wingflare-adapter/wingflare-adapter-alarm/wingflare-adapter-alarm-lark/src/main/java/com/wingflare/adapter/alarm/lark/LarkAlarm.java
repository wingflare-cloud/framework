package com.wingflare.adapter.alarm.lark;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞书告警
 */
public class LarkAlarm implements AlarmDrive<AlarmContext> {

    private static final Logger log = LoggerFactory.getLogger(LarkAlarm.class);
    public static final String AT_LABEL = "<at id={0}></at>";

    @Override
    public String getAlarmType() {
        return "lark";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        try {
            LarkAttribute larkAttribute = JSONObject.parseObject(alarmContext.getNotifyAttribute(), LarkAttribute.class);

            Map<String, Object> map = new HashMap<>();
            map.put("header", buildHeader(alarmContext.getTitle()));
            map.put("elements", buildElements(alarmContext.getText(), larkAttribute.getAts()));

            LarkMessage larkMessage = new LarkMessage();
            larkMessage.setMsgType("interactive");
            larkMessage.setCard(map);

            HttpRequest post = HttpUtil.createPost(larkAttribute.getWebhookUrl());
            HttpRequest request = post.body(JSONObject.toJSONString(larkMessage), ContentType.JSON.toString());
            HttpResponse execute = request.execute();

            if (execute.isOk()) {
                return true;
            }

            log.error("Sending Lark message failed: {}", execute.body());
            return false;
        } catch (Exception e) {
            log.error("Sending Lark message failed", e);
            return false;
        }
    }

    private List buildElements(String text, List<String> ats) {
        Map<String, String> map = new HashMap<>();
        map.put("tag", "markdown");
        map.put("content", getAtText(text, ats));
        return Collections.singletonList(map);
    }

    private Map<String, Object> buildHeader(final String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("template", "red");

        Map<String, String> titleMap = new HashMap<>();
        titleMap.put("content", title);
        titleMap.put("tag", "plain_text");

        map.put("title", titleMap);
        return map;
    }

    private static class LarkMessage {

        private String msgType;

        private Map<String, Object> card;

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public Map<String, Object> getCard() {
            return card;
        }

        public void setCard(Map<String, Object> card) {
            this.card = card;
        }
    }

    public String getAtText(String text, List<String> ats) {
        if (CollUtil.isEmpty(ats)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(text);
        if (ats.stream().map(String::toLowerCase).anyMatch("all"::equals)) {
            sb.append(MessageFormat.format(AT_LABEL, "all"));
        } else {
            ats.stream().filter(StrUtil::isNotBlank)
                    .forEach(at -> sb.append(MessageFormat.format(AT_LABEL, at)));
        }
        return sb.toString();
    }

}
