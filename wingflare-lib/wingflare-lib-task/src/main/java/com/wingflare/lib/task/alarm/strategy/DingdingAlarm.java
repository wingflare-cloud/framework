package com.wingflare.lib.task.alarm.strategy;


import com.wingflare.lib.task.alarm.AlarmContext;
import com.wingflare.lib.task.alarm.attribute.DingDingAttribute;
import com.wingflare.lib.task.enums.AlarmTypeEnum;
import com.wingflare.lib.task.util.DingDingUtils;
import com.wingflare.lib.task.util.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:20
 */
@Component
public class DingdingAlarm extends AbstractAlarm<AlarmContext> {

    @Override
    public Integer getAlarmType() {
        return AlarmTypeEnum.DING_DING.getValue();
    }

    @Override
    public boolean syncSendMessage(AlarmContext context) {
        DingDingAttribute dingDingAttribute = JsonUtil.parseObject(context.getNotifyAttribute(), DingDingAttribute.class);
        return DingDingUtils.sendMessage(DingDingUtils.buildSendRequest(context.getTitle(), context.getText(), dingDingAttribute.getAts()), dingDingAttribute.getWebhookUrl());
    }

    @Override
    public boolean asyncSendMessage(List<AlarmContext> alarmContexts) {

        for (AlarmContext alarmContext : alarmContexts) {
            asyncSendMessage(alarmContext);
        }

        return Boolean.TRUE;
    }
}

