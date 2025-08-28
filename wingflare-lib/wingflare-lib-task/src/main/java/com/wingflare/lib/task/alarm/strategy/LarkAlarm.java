package com.wingflare.lib.task.alarm.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.task.alarm.AlarmContext;
import com.wingflare.lib.task.alarm.attribute.LarkAttribute;
import com.wingflare.lib.task.constant.SystemConstants;
import com.wingflare.lib.task.enums.AlarmTypeEnum;
import com.wingflare.lib.task.util.JsonUtil;
import com.aizuda.snailjob.common.log.SnailJobLog;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞书通知
 *
 * @author: opensnail
 * @date : 2021-11-25 09:20
 * @since 1.4.0
 */
@Component
public class LarkAlarm extends AbstractAlarm<AlarmContext> {

    private final static Logger log = LoggerFactory.getLogger(LarkAlarm.class);

    public static final String AT_LABEL = "<at id={0}></at>";

    @Override
    public Integer getAlarmType() {
        return AlarmTypeEnum.LARK.getValue();
    }

    @Override
    public boolean syncSendMessage(AlarmContext context) {
        try {
            LarkAttribute larkAttribute = JsonUtil.parseObject(context.getNotifyAttribute(), LarkAttribute.class);

            Map<String, Object> map = new HashMap<>();
            map.put("header", buildHeader(context.getTitle()));
            map.put("elements", buildElements(context.getText(), larkAttribute.getAts()));

            LarkMessage builder = Builder.of(LarkMessage::new)
                    .with(LarkMessage::setMsgType, "interactive")
                    .with(LarkMessage::setCard, map)
                    .build();

            HttpRequest post = HttpUtil.createPost(larkAttribute.getWebhookUrl());
            HttpRequest request = post.body(JsonUtil.toJsonString(builder), ContentType.JSON.toString());
            HttpResponse execute = request.execute();
            if (execute.isOk()) {
                return true;
            }
            SnailJobLog.LOCAL.error("Sending Lark message failed: {}", execute.body());
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

    @Override
    public boolean asyncSendMessage(List<AlarmContext> notifierElements) {

        for (AlarmContext notifierElement : notifierElements) {
            asyncSendMessage(notifierElement);
        }

        return Boolean.TRUE;
    }


    private static class LarkMessage {

        @JsonProperty("msg_type")
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
        if (ats.stream().map(String::toLowerCase).anyMatch(SystemConstants.AT_ALL::equals)) {
            sb.append(MessageFormat.format(AT_LABEL, SystemConstants.AT_ALL));
        } else {
            ats.stream().filter(StrUtil::isNotBlank)
                    .forEach(at -> sb.append(MessageFormat.format(AT_LABEL, at)));
        }
        return sb.toString();
    }
}

