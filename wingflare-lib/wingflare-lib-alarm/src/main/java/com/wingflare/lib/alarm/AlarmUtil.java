package com.wingflare.lib.alarm;


import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import com.wingflare.api.thread.pool.ThreadPoolManageDrive;
import com.wingflare.lib.config.ConfigUtil;
import com.wingflare.lib.container.Container;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;


/**
 * @author: naizui.ycx
 * @date : 2025-09-02 09:20
 */
public class AlarmUtil {

    private static volatile Map<String, AlarmDrive> alarmMap;

    private AlarmUtil() {
    }


    private static Map<String, AlarmDrive> getAlarms() {
        if (alarmMap == null) {
            synchronized (AlarmUtil.class) {
                if (alarmMap == null) {
                    ServiceLoader<AlarmDrive> alarms = ServiceLoader.load(AlarmDrive.class);
                    for (AlarmDrive alarm : alarms) {
                        alarmMap.put(alarm.getAlarmType(), alarm);
                        break;
                    }
                    NoneAlarm noneAlarm = new NoneAlarm();
                    alarmMap.put(noneAlarm.getAlarmType(), noneAlarm);
                }
            }
        }

        return alarmMap;
    }

    public static AlarmDrive getAlarmType(String alarmType) {
        return getAlarms().get(alarmType);
    }

    /**
     * 同步发送告警
     *
     * @param alarmContext
     * @return
     */
    public static boolean asyncSendMessage(String alarmType, AlarmContext alarmContext) {
        if (getAlarms().containsKey(alarmType)) {
            AlarmDrive alarmDrive = getAlarms().get(alarmType);
            return alarmDrive.sendMessage(alarmContext);
        }

        throw new UnsupportedOperationException("Alarm type " + alarmType + " not supported");
    }

    /**
     * 异步告警发送方法
     *
     * @param alarmType
     * @param alarmContext
     * @return
     */
    public static boolean syncSendMessage(String alarmType, AlarmContext alarmContext) {
        Container.get(ThreadPoolManageDrive.class).execute("alarm", () -> asyncSendMessage(alarmType, alarmContext));
        return Boolean.TRUE;
    }

    /**
     * 异步批量告警
     *
     * @param alarmType
     * @param alarmContexts
     * @return
     */
    public static boolean asyncSendMessage(String alarmType, List<AlarmContext> alarmContexts) {
        for (AlarmContext alarmContext : alarmContexts) {
            Container.get(ThreadPoolManageDrive.class).execute("alarm", () -> asyncSendMessage(alarmType, alarmContext));
        }

        return Boolean.TRUE;
    }

    /**
     * 同步发送告警
     *
     * @param alarmContext
     * @return
     */
    public static boolean asyncSendMessage(AlarmContext alarmContext) {
        return asyncSendMessage(ConfigUtil.getProperty("alarm.defaultAdapter", "none"), alarmContext);
    }

    /**
     * 异步告警发送方法
     *
     * @param alarmContext
     * @return
     */
    public static boolean syncSendMessage(AlarmContext alarmContext) {
        Container.get(ThreadPoolManageDrive.class).execute("alarm", () -> asyncSendMessage(alarmContext));
        return Boolean.TRUE;
    }

    /**
     * 异步批量告警
     *
     * @param alarmContexts
     * @return
     */
    public static boolean asyncSendMessage(List<AlarmContext> alarmContexts) {
        for (AlarmContext alarmContext : alarmContexts) {
            Container.get(ThreadPoolManageDrive.class).execute("alarm", () -> asyncSendMessage(alarmContext));
        }

        return Boolean.TRUE;
    }

}
