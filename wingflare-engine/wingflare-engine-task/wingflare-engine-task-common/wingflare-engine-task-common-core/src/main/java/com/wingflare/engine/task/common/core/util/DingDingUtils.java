package com.wingflare.engine.task.common.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:54
 */
public class DingDingUtils {

    private static final Logger log = LoggerFactory.getLogger(DingDingUtils.class);

    public static final String atLabel = "@{0}";

    /**
     * 组装 DingTalkRequest
     *
     * @param title 标题
     * @param text  内容
     * @return DingTalkRequest
     */
    public static String buildSendRequest(String title, String text, List<String> ats) {
        Map<String, Object> message = new HashMap<>();
        message.put("msgtype", "markdown");
        Map<String, Object> markdown = new HashMap<>();
        markdown.put("text", StrUtil.sub(getAtText(ats, text, atLabel), 0, 4000));
        markdown.put("title", title);
        message.put("markdown", markdown);

        // 处理提到的人
        Map<String, Object> at = new HashMap<>();
        at.put("atMobiles", ats);
        at.put("isAtAll", true);
        if (CollUtil.isNotEmpty(ats)) {
            at.put("isAtAll", ats.stream().map(String::toLowerCase).anyMatch(SystemConstants.AT_ALL::equals));
        }
        message.put("at", at);
        return JsonUtil.toJsonString(message);
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

    /**
     * @param request DingTalkRequest
     */
    public static boolean sendMessage(String request, String url) {

        try {
            if (StrUtil.isBlank(url)) {
                return false;
            }

            // 发送POST请求
            HttpResponse response = HttpRequest.post(url)
                    .headerMap(getHeaders(), true)
                    .body(request)
                    .execute();

            String body = response.body();
            JsonNode bodyJson = JsonUtil.toJson(body);
            int errCode = bodyJson.get("errcode").asInt();
            if (errCode != 0) {
                TaskEngineLog.LOCAL.error("dingDingProcessNotify: DingTalk message sending failed, error code: {}, error message: {}", errCode, bodyJson.get("errmsg").asText());
                return false;
            }
            return true;
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("dingDingProcessNotify", e);
        }

        return false;
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

}
