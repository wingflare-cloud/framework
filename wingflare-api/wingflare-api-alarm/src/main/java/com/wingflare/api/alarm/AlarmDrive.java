package com.wingflare.api.alarm;


/**
 * @author: opensnail
 * @date : 2021-11-25 09:19
 */
public interface AlarmDrive<T> {

    String getAlarmType();

    boolean sendMessage(T t);

}
