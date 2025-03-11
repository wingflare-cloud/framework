package com.wingflare.lib.rabbitmq.aspect;


import com.wingflare.lib.rabbitmq.AmqpHelper;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import jakarta.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2022/1/4}
 * @description
 */
@Aspect
public class AmqpAspect {

    private static final Logger log = LoggerFactory.getLogger(AmqpAspect.class);

    @Resource
    private SnowflakeUtil snowflakeUtil;

    private final String exchange;
    private final String routingKey;
    private final MessageConverter messageConverter;


    AmqpAspect(RabbitTemplate rabbitTemplate) {
        this.exchange = rabbitTemplate.getExchange();
        this.routingKey = rabbitTemplate.getRoutingKey();
        this.messageConverter = rabbitTemplate.getMessageConverter();
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#send(Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.send(..)) && args(message)",
            argNames = "pjp, message")
    public Object traceRabbitSend(ProceedingJoinPoint pjp, Message message) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#send(String, Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.send(..)) && args(routingKey, message)",
            argNames = "pjp,routingKey,message")
    public Object traceRabbitSend(ProceedingJoinPoint pjp, String routingKey, Object message) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#send(String, String, Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.send(..)) && args(exchange, " +
            "routingKey, message)", argNames = "pjp,exchange, routingKey, message")
    public Object traceRabbitSend(ProceedingJoinPoint pjp, String exchange, String routingKey, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..)) " +
            "&& args(message)", argNames = "pjp,message")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, Object message) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(String, Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..)) " +
            "&& args(routingKey, message)", argNames = "pjp,routingKey,message")
    public Object traceRabbitConvertAndSend(
            ProceedingJoinPoint pjp, String routingKey, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(String, String, Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..)) && args(exchange,"
            + "routingKey, message)", argNames = "pjp,exchange,routingKey,message")
    public Object traceRabbitConvertAndSend(
            ProceedingJoinPoint pjp, String exchange, String routingKey, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..))" +
            " && args(message, messagePostProcessor)", argNames = "pjp,message,messagePostProcessor")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, Object message,
                                            MessagePostProcessor messagePostProcessor) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(String, Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..))" +
            " && args(routingKey, message, messagePostProcessor)",
            argNames = "pjp,routingKey,message,messagePostProcessor")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, String routingKey, Object message,
                                            MessagePostProcessor messagePostProcessor) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertAndSend(String, String, Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertAndSend(..))" +
            " && args(exchange, routingKey, message, messagePostProcessor)",
            argNames = "pjp,exchange,routingKey,message,messagePostProcessor")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, String exchange, String routingKey, Object message,
                                            MessagePostProcessor messagePostProcessor) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see RabbitTemplate#convertAndSend(String, Object, MessagePostProcessor, CorrelationData)
     */
    @Around(value = "execution(* org.springframework.amqp.rabbit.core.RabbitTemplate.convertAndSend(..)) " +
            "&& args(routingKey, message, messagePostProcessor, correlationData)",
            argNames = "pjp,routingKey,message,messagePostProcessor,correlationData")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, String routingKey, Object message,
                                            MessagePostProcessor messagePostProcessor, CorrelationData correlationData)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see RabbitTemplate#convertAndSend(String, String, Object, CorrelationData)
     */
    @Around(value = "execution(* org.springframework.amqp.rabbit.core.RabbitTemplate.convertAndSend(..)) " +
            "&& args(exchange, routingKey, message, correlationData)",
            argNames = "pjp,exchange,routingKey,message,correlationData")
    public Object traceRabbitConvertAndSend(ProceedingJoinPoint pjp, String exchange, String routingKey, Object message,
                                            CorrelationData correlationData)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#sendAndReceive(Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.sendAndReceive(..))" +
            " && args(message)", argNames = "pjp,message")
    public Object traceRabbitSendAndReceive(ProceedingJoinPoint pjp, Object message) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#sendAndReceive(String, Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.sendAndReceive(..))" +
            " && args(routingKey, message)", argNames = "pjp,routingKey,message")
    public Object traceRabbitSendAndReceive(ProceedingJoinPoint pjp, String routingKey, Object message) throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#sendAndReceive(String, String, Message)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.sendAndReceive(..))" +
            " && args(exchange, routingKey, message)", argNames = "pjp,exchange,routingKey,message")
    public Object traceRabbitSendAndReceive(ProceedingJoinPoint pjp, String exchange, String routingKey, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    // Intercept public methods that eventually delegate to RabbitTemplate.doSendAndReceive
    // that can't be intercepted with AspectJ as it is protected.

    /**
     * @see RabbitTemplate#sendAndReceive(String, String, Message, CorrelationData)
     */
    @Around(value = "execution(* org.springframework.amqp.rabbit.core.RabbitTemplate.sendAndReceive(..)) && args(exchange,"
            + "routingKey, message, correlationData)", argNames = "pjp,exchange,routingKey,message,correlationData")
    public Object traceRabbitSendAndReceive(
            ProceedingJoinPoint pjp, String exchange, String routingKey, Message message, CorrelationData correlationData)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(message)", argNames = "pjp,message")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(String, Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(routingKey, message)", argNames = "pjp,routingKey,message")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, String routingKey, Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(String, String, Object)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(exchange, routingKey, message)", argNames = "pjp,exchange,routingKey,message")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, String exchange, String routingKey,
                                                   Object message)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(message, messagePostProcessor)", argNames = "pjp,message,messagePostProcessor")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, Object message,
                                                   MessagePostProcessor messagePostProcessor)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 0));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(String, Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(routingKey, message, messagePostProcessor)",
            argNames = "pjp,routingKey,message,messagePostProcessor")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, String routingKey, Object message,
                                                   MessagePostProcessor messagePostProcessor)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 1));
    }

    /**
     * @see org.springframework.amqp.core.AmqpTemplate#convertSendAndReceive(String, String, Object, MessagePostProcessor)
     */
    @Around(value = "execution(* org.springframework.amqp.core.AmqpTemplate.convertSendAndReceive(..))" +
            " && args(exchange, routingKey, message, messagePostProcessor)",
            argNames = "pjp,exchange,routingKey,message,messagePostProcessor")
    public Object traceRabbitConvertSendAndReceive(ProceedingJoinPoint pjp, String exchange, String routingKey,
                                                   Object message, MessagePostProcessor messagePostProcessor)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    @Around(value = "execution(* org.springframework.amqp.rabbit.core.RabbitTemplate.convertSendAndReceive(..)) " +
            "&& args(exchange, routingKey, message, messagePostProcessor, correlationData)",
            argNames = "pjp,exchange,routingKey,message,messagePostProcessor,correlationData")
    public Object traceRabbitConvertSendAndReceive(
            ProceedingJoinPoint pjp, String exchange, String routingKey, Object message,
            MessagePostProcessor messagePostProcessor, CorrelationData correlationData)
            throws Throwable {
        return createHelper()
                .doWithFormatMessage(exchange, routingKey, message, (convertedMessage) ->
                        proceedReplacingMessage(pjp, exchange, routingKey, convertedMessage, 2));
    }

    private AmqpHelper createHelper() {
        return new AmqpHelper(snowflakeUtil, messageConverter);
    }

    private Object proceedReplacingMessage(ProceedingJoinPoint pjp, String exchange, String routingKey, Message convertedMessage, int messageArgumentIndex)
            throws Throwable {
        final Object[] args = pjp.getArgs();
        args[messageArgumentIndex] = convertedMessage;

        log.info(
                "发送rabbitMQ消息: exchange: {}, routingKey: {}, msgId: {}",
                exchange,
                routingKey,
                convertedMessage.getMessageProperties().getMessageId()
        );

        return pjp.proceed(args);
    }


}
