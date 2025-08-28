package com.wingflare.lib.task.alarm.strategy;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wingflare.lib.task.alarm.AlarmContext;
import com.wingflare.lib.task.alarm.attribute.QiYeWechatAttribute;
import com.wingflare.lib.task.enums.AlarmTypeEnum;
import com.wingflare.lib.task.util.DingDingUtils;
import com.wingflare.lib.task.util.JsonUtil;
import com.aizuda.snailjob.common.log.SnailJobLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 企业微信通知
 *
 * @author lizhongyuan
 */
@Component
public class QiYeWechatAlarm extends AbstractAlarm<AlarmContext> {

    private final static Logger log = LoggerFactory.getLogger(QiYeWechatAlarm.class);

    public static final String AT_LABEL = "<@{0}>";

    @Override
    public Integer getAlarmType() {
        return AlarmTypeEnum.WE_COM.getValue();
    }

    @Override
    public boolean syncSendMessage(AlarmContext context) {
        try {
            QiYeWechatAttribute qiYeWechatAttribute = JsonUtil.parseObject(context.getNotifyAttribute(), QiYeWechatAttribute.class);
            String webhookUrl = qiYeWechatAttribute.getWebhookUrl();
            if (StrUtil.isBlank(webhookUrl)) {
                log.error("Please configure the WeChat robot webhookUrl first");
                return false;
            }
            Map<String, Object> map = MapUtil.newHashMap();
            QiYeWechatMessageContent messageContent = new QiYeWechatMessageContent();
            messageContent.setContent(StrUtil.sub(DingDingUtils.getAtText(qiYeWechatAttribute.getAts(), context.getText(), AT_LABEL), 0, 4096));
            map.put("msgtype", "markdown");
            map.put("markdown", messageContent);
            HttpRequest post = HttpUtil.createPost(webhookUrl);
            HttpRequest request = post.body(JsonUtil.toJsonString(map), ContentType.JSON.toString());
            HttpResponse execute = request.execute();
            SnailJobLog.LOCAL.debug(JsonUtil.toJsonString(execute));
            if (execute.isOk()) {
                return true;
            }
            SnailJobLog.LOCAL.error("Sending Enterprise WeChat message failed: {}", execute.body());
            return false;
        } catch (Exception e) {
            log.error("Sending Enterprise WeChat message failed", e);
            return false;
        }
    }

    @Override
    public boolean asyncSendMessage(List<AlarmContext> alarmContexts) {

        for (AlarmContext alarmContext : alarmContexts) {
            asyncSendMessage(alarmContext);
        }

        return Boolean.TRUE;
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

