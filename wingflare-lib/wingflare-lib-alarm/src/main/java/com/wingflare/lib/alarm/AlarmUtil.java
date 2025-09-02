package com.wingflare.lib.alarm;


import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import com.wingflare.lib.config.ConfigUtil;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: naizui.ycx
 * @date : 2025-09-02 09:20
 */
public class AlarmUtil {

    private static volatile Map<String, AlarmDrive> alarmMap;

    private AlarmUtil() {
    }

    private static class SingletonHolder {

        private static final ThreadPoolExecutor alarmThreadPoolExecutor =
                new ThreadPoolExecutor(
                        ConfigUtil.getIntProperty("alarm.corePoolSize", 2),
                        ConfigUtil.getIntProperty("alarm.maximumPoolSize", 5),
                        ConfigUtil.getIntProperty("alarm.keepAliveTime", 10),
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(ConfigUtil.getIntProperty("alarm.keepAliveTime", Integer.MAX_VALUE)));

    }


    private static Map<String, AlarmDrive> getAlarms() {
        if (alarmMap == null) {
            synchronized (ConfigUtil.class) {
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
        SingletonHolder.alarmThreadPoolExecutor.execute(() -> asyncSendMessage(alarmType, alarmContext));
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
            SingletonHolder.alarmThreadPoolExecutor.execute(() -> asyncSendMessage(alarmType, alarmContext));
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
        SingletonHolder.alarmThreadPoolExecutor.execute(() -> asyncSendMessage(alarmContext));
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
            SingletonHolder.alarmThreadPoolExecutor.execute(() -> asyncSendMessage(alarmContext));
        }

        return Boolean.TRUE;
    }

}
