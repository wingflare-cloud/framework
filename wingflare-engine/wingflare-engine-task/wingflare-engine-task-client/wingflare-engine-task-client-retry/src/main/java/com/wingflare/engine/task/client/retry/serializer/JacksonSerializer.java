package com.wingflare.engine.task.client.retry.serializer;


import com.wingflare.engine.task.client.retry.RetryArgSerializer;
import com.wingflare.engine.task.client.retry.exception.RetryArgSerializeException;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Jackson序列化
 *
 * @author: opensnail
 * @date : 2022-03-07 15:08
 */
public class JacksonSerializer implements RetryArgSerializer {

    @Override
    public String name() {
        return "jackson";
    }

    @Override
    public String serialize(Object serializeInfo) {
        return JsonUtil.toJsonString(serializeInfo);
    }

    @Override
    public Object deSerialize(String infoStr, Class tClass, Method method) throws RetryArgSerializeException {

        try {
            Type[] paramTypes = method.getGenericParameterTypes();

            Object[] params = new Object[paramTypes.length];

            ObjectMapper mapper = JsonUtil.JsonMapper.jacksonObjectMapper();
            JsonNode jsonNode = JsonUtil.toJson(infoStr);
            if (Objects.isNull(jsonNode)) {
                TaskEngineLog.LOCAL.warn("jsonNode is null. infoStr:[{}]", infoStr);
                return params;
            }

            for (int i = 0; i < paramTypes.length; i++) {
                JsonNode node = jsonNode.get(i);
                if (Objects.nonNull(node)) {
                    params[i] = mapper.readValue(node.toString(), mapper.constructType(paramTypes[i]));
                }
            }
            return params;
        } catch (Exception e) {
            throw new RetryArgSerializeException(e);
        }

    }
}
