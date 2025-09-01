package com.wingflare.lib.alarm;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:20
 */
public class TaskAlarmFactory {

    private static final Map<String, Alarm> alarmMap = new ConcurrentHashMap<>();
    protected static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void register(Alarm alarm) {
        alarmMap.put(alarm.getAlarmType(), alarm);
    }

    public static Alarm getAlarmType(String alarmType) {
        return alarmMap.get(alarmType);
    }

    public static boolean asyncSendMessage(String alarmType, AlarmContext alarmContext) {
        if (alarmMap.containsKey(alarmType)) {
            Alarm alarm = alarmMap.get(alarmType);
            return alarm.sendMessage(alarmContext);
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
        threadPoolExecutor.execute(() -> asyncSendMessage(alarmType, alarmContext));
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
            threadPoolExecutor.execute(() -> asyncSendMessage(alarmType, alarmContext));
        }

        return Boolean.TRUE;
    }

}
