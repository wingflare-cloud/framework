package com.wingflare.engine.task.client.retry.core;

import com.wingflare.engine.task.client.retry.core.exception.RetryArgSerializeException;

import java.lang.reflect.Method;

/**
 * @author: opensnail
 * @date : 2022-03-07 15:08
 */
public interface RetryArgSerializer {

    String name();

    String serialize(Object serializeInfo);

    Object deSerialize(String infoStr, Class tClass, Method method) throws RetryArgSerializeException;
}
