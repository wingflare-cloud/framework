package com.wingflare.lib.rabbitmq.constant;

/**
 * @author naizui_ycx
 * @date {2022/1/12}
 * @description
 */
public interface RabbitMQCommon {

    public String DEFAULT_DEAD_LETTER_EXCHANGE = "baseDeadLetterExchange"; // 死信交换机
    public String DEFAULT_DEAD_LETTER_ROUTING_KEY = "baseDeadLetterKey"; // 死信路由key
    public String DEFAULT_MESSAGE_TTL = "30000"; // 消息生存时间

}
