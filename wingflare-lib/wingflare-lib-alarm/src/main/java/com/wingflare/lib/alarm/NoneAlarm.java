package com.wingflare.lib.alarm;

import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 无驱动告警
 */
public class NoneAlarm implements AlarmDrive<AlarmContext> {

    private static final Logger log = LoggerFactory.getLogger(NoneAlarm.class);

    @Override
    public String getAlarmType() {
        return "none";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        log.error("system.alarm: {}", alarmContext);
        return Boolean.TRUE;
    }

}
