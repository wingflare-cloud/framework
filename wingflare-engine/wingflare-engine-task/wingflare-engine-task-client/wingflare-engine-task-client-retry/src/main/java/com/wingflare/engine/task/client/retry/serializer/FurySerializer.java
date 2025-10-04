package com.wingflare.engine.task.client.retry.serializer;

import com.wingflare.engine.task.client.retry.RetryArgSerializer;
import com.wingflare.engine.task.client.retry.exception.RetryArgSerializeException;
import com.wingflare.engine.task.common.core.util.ForyUtil;

import java.lang.reflect.Method;

/**
 * @sse: com.wingflare.engine.task.client.retry.serializer.ForySerializer
 */
@Deprecated
public class FurySerializer implements RetryArgSerializer {

    @Override
    public String name() {
        return "fury";
    }

    @Override
    public String serialize(Object serializeInfo) {
        return ForyUtil.serialize(serializeInfo);
    }

    @Override
    public Object deSerialize(String infoStr, Class tClass, Method method) throws RetryArgSerializeException {
        try {
            return ForyUtil.deserialize(infoStr);
        } catch (Exception e) {
            throw new RetryArgSerializeException(e);
        }
    }
}
