package com.wingflare.lib.task.alarm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:20
 */
public class SnailJobAlarmFactory {

    private static final Map<Integer, Alarm> alarmMap = new ConcurrentHashMap<>();

    public static void register(Alarm alarm) {
        alarmMap.put(alarm.getAlarmType(), alarm);
    }

    public static Alarm getAlarmType(Integer alarmType) {
        return alarmMap.get(alarmType);
    }
}
