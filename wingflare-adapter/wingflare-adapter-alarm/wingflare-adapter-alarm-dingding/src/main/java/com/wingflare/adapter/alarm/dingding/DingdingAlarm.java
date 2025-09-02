package com.wingflare.adapter.alarm.dingding;

import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;

/**
 * 钉钉告警
 */
public class DingdingAlarm implements AlarmDrive<AlarmContext> {
    @Override
    public String getAlarmType() {
        return "dingding";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        DingDingAttribute dingDingAttribute = JSONObject.parseObject(alarmContext.getNotifyAttribute(), DingDingAttribute.class);
        return DingDingUtil.sendMessage(DingDingUtil.buildSendRequest(alarmContext.getTitle(),
                alarmContext.getText(), dingDingAttribute.getAts()), dingDingAttribute.getWebhookUrl());
    }

}
