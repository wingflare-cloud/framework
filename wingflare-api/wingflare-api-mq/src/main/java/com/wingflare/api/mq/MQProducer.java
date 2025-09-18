package com.wingflare.api.mq;


import java.util.Map;


public interface MQProducer {

    /**
     * 发送消息到指定主题
     * @param topic 主题名称
     * @param message 消息内容
     * @throws RuntimeException 发送过程中可能出现的异常
     */
    void send(String topic, Object message) throws RuntimeException;

    /**
     * 发送带有属性的消息到指定主题
     * @param topic 主题名称
     * @param message 消息内容
     * @param properties 消息属性键值对
     * @throws RuntimeException 发送过程中可能出现的异常
     */
    void send(String topic, Object message, Map<String, String> properties) throws RuntimeException;

}
