package com.wingflare.adapter.alarm.dingding;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.wingflare.api.core.Charset;
import com.wingflare.api.http.HttpMethod;
import com.wingflare.api.http.HttpRequest;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉工具类
 */
public class DingDingUtil {

    public static final String atLabel = "@{0}";

    private static final Logger log = LoggerFactory.getLogger(DingDingUtil.class);

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
            at.put("isAtAll", ats.stream().map(String::toLowerCase).anyMatch("all"::equals));
        }
        message.put("at", at);
        return JSONObject.toJSONString(message);
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
     * @param content DingTalkRequest
     */
    public static boolean sendMessage(String content, String url) {

        try {
            if (StrUtil.isBlank(url)) {
                return false;
            }

            HttpRequest request = Container.get(HttpRequest.class);
            HttpResponse response = request
                    .setUrl(url)
                    .setBody(content)
                    .setCharset(Charset.UTF_8)
                    .setContentType("application/json")
                    .setMethod(HttpMethod.POST)
                    .execute();

            if (!response.isOk()) {
                log.error("dingDingProcessNotify: DingTalk message sending failed, {}", response);
                return false;
            }

            String body = response.getBody();
            JSONObject bodyJson = JSONObject.parseObject(body);
            int errCode = bodyJson.getIntValue("errcode");
            if (errCode != 0) {
                log.error("dingDingProcessNotify: DingTalk message sending failed, error code: {}, error message: {}", errCode, bodyJson.getString("errmsg"));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("dingDingProcessNotify", e);
        }

        return false;
    }

}
