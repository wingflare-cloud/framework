package com.wingflare.lib.rabbitmq.constant;

/**
 * @author naizui_ycx
 * @date {2022/1/12}
 * @description
 */
public interface RabbitMQHeader {

    /**
     * 消息生存时间，单位毫秒
     */
    public String MESSAGE_TTL = "x-message-ttl";

    /**
     * 死信交换机
     */
    public String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    /**
     * 死信路由key
     */
    public String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**
     * 死信首次交换机
     */
    public String FIRST_DEATH_EXCHANGE = "x-first-death-exchange";

    /**
     * 死信首次队列
     */
    public String FIRST_DEATH_QUEUE = "x-first-death-queue";

    /**
     * 延迟消息头
     */
    public String X_DELAY = "x-delay";

}
