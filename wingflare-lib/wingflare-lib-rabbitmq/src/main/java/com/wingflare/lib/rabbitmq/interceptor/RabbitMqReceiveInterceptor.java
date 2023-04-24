package com.wingflare.lib.rabbitmq.interceptor;


import com.wingflare.lib.core.exceptions.BusinessLogicException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.BeforeAdvice;

/**
 * @author naizui_ycx
 * @date {2022/1/15}
 * @description
 */
public class RabbitMqReceiveInterceptor implements MethodInterceptor, AfterAdvice, BeforeAdvice {

    private final Logger logger = LoggerFactory.getLogger(RabbitMqReceiveInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Message message = (Message) invocation.getArguments()[1];
        MessageProperties messageProperties = message.getMessageProperties();

        logger.info(
                "收到rabbitMQ消息: exchange: {}, routingKey: {}, msgId: {}",
                messageProperties.getReceivedExchange(),
                messageProperties.getReceivedRoutingKey(),
                messageProperties.getMessageId()
        );

        try {
            Object result = invocation.proceed();

            logger.info(
                    "rabbitMQ消息已消费: exchange: {}, routingKey: {}, msgId: {}",
                    messageProperties.getReceivedExchange(),
                    messageProperties.getReceivedRoutingKey(),
                    messageProperties.getMessageId()
            );

            return result;

        } catch (BusinessLogicException ex) {
            logger.warn(
                    "rabbitMQ消息消费异常: exchange: {}, routingKey: {}, msgId: {}, exMsg: {}",
                    messageProperties.getReceivedExchange(),
                    messageProperties.getReceivedRoutingKey(),
                    messageProperties.getMessageId(),
                    ex.getMessage()
            );

            if (logger.isDebugEnabled()) {
                logger.error(ExceptionUtils.getStackTrace(ex));
            }

            throw new AmqpRejectAndDontRequeueException(ex); // 业务异常不重试直接丢弃消息
        } catch (Throwable ex) {
            logger.error(
                    "rabbitMQ消息消费异常: exchange: {}, routingKey: {}, msgId: {}, exMsg: {}",
                    messageProperties.getReceivedExchange(),
                    messageProperties.getReceivedRoutingKey(),
                    messageProperties.getMessageId(),
                    ex.getMessage()
            );
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }
    }
}
