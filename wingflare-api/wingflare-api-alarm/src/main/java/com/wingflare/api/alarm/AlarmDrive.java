package com.wingflare.api.alarm;


/**
 * @author: 奶嘴
 * @date : 2023-10-05 06:08
 */
public interface AlarmDrive<T> {

    String getAlarmType();

    boolean sendMessage(T t);

}
