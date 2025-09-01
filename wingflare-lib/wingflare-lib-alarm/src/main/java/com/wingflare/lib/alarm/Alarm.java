package com.wingflare.lib.alarm;


/**
 * @author: opensnail
 * @date : 2021-11-25 09:19
 */
public interface Alarm<T> {

    String getAlarmType();

    boolean sendMessage(T t);

}
