package com.wingflare.lib.standard;


/**
 * @author naizui_ycx
 * @date {2022/1/4}
 * @email chenxi2511@qq.com
 * @description 事件工具类接口
 */
public interface EventUtil {


    Event publishEvent(String eventName);

    Event publishEvent(String eventName, Object source);

    Event publishEvent(String eventName, Object source, Object evData);

    Event publishEvent(String eventName, Object source, Object evData, String evStatus);

    /**
     * 发布事件
     *
     * @param eventName 事件名
     * @param source    事件源
     * @param evData    事件数据
     * @param evStatus  事件状态
     * @param evVersion 事件版本
     *
     * @return
     */
    Event publishEvent(String eventName, Object source, Object evData, String evStatus, String evVersion);

    /**
     * 发布事件
     *
     * @param event
     *
     * @return
     */
    Event publishEvent(Event event);

}
