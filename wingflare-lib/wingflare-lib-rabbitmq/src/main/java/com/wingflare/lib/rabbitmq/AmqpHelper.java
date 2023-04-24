package com.wingflare.lib.rabbitmq;


import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author naizui_ycx
 * @date {2022/1/14}
 * @description
 */
public class AmqpHelper {

    private final MessageConverter messageConverter;

    private SnowflakeUtil snowflakeUtil;


    public AmqpHelper(SnowflakeUtil snowflakeUtil, MessageConverter messageConverter) {
        this.snowflakeUtil = snowflakeUtil;
        this.messageConverter = messageConverter;
    }

    public <T> T doWithFormatMessage(
            String exchange,
            String routingKey,
            Object message,
            ProceedFunction<T> proceedCallback
    ) throws Throwable {
        return proceedCallback.apply(convertMessageIfNecessary(message));
    }

    private Message convertMessageIfNecessary(final Object object) {
        Message message = null;

        if (object instanceof Message) {
            message = (Message) object;
        } else {
            message = messageConverter.toMessage(object, new MessageProperties());
        }

        if (StringUtil.isEmpty(message.getMessageProperties().getMessageId())) {
            message.getMessageProperties().setMessageId(snowflakeUtil.nextStringId());
        }

        return message;
    }

    public interface ProceedFunction<T> {
        T apply(Message t) throws Throwable;
    }

}
