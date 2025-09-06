package com.wingflare.engine.task.client.common.rpc.client.openapi;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ServiceLoaderUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.client.common.annotation.Header;
import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.Param;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.ExecutorTypeEnum;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.lib.container.Container;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 请求服务端
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public class HttpClientInvokeHandler<R extends Result<Object>> implements InvocationHandler {
    private final long timeout;
    private final TimeUnit unit;

    public HttpClientInvokeHandler(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TaskHttpClient taskHttpClient = loadTaskEngineHttpClient();
        Mapping mapping = method.getAnnotation(Mapping.class);
        Parameter[] parameters = method.getParameters();

        Request request = new Request();
        request.setParams(getParams(args, parameters));
        request.setMethod(mapping.method().name());
        request.setPath(mapping.path());
        request.setHeaders(getHeaderInfo(method, args));
        request.setTimeout(unit.toMillis(timeout));
        request.setBody(JsonUtil.toJsonString(args[0]));
        return taskHttpClient.execute(request);
    }

    private String getParams(Object[] args, Parameter[] parameters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(Param.class)) {
                Param param = parameter.getAnnotation(Param.class);
                sb.append(param.value()).append("=").append(args[i]);
            }
        }
        String data = sb.toString();
        if (StrUtil.isNotBlank(data)){
            return "?" + data;
        } else {
            return "";
        }
    }

    private Map<String, String> getHeaderInfo(Method method, Object[] args) {
        Map<String, String> headersMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(Header.class)) {
                Header header = parameter.getAnnotation(Header.class);
                Object o = args[i];
                if (Objects.nonNull(o)) {
                    headersMap.put(header.name().getKey(), JsonUtil.toJsonString(o));
                }
            }
        }
        TaskProperties taskProperties = Container.get(TaskProperties.class);
        TaskProperties.ServerConfig serverConfig = taskProperties.getServer();
        headersMap.put(HeadersEnum.GROUP_NAME.getKey(), taskProperties.getGroup());
        headersMap.put(HeadersEnum.HOST.getKey(), serverConfig.getHost());
        headersMap.put(HeadersEnum.NAMESPACE.getKey(), Optional.ofNullable(taskProperties.getNamespace()).orElse(
                SystemConstants.DEFAULT_NAMESPACE));
        headersMap.put(HeadersEnum.TOKEN.getKey(), Optional.ofNullable(taskProperties.getToken()).orElse(
                SystemConstants.DEFAULT_TOKEN));
        headersMap.put(HeadersEnum.SYSTEM_VERSION.getKey(), Optional.ofNullable(TaskVersion.getVersion()).orElse(
                SystemConstants.DEFAULT_CLIENT_VERSION));
        headersMap.put(HeadersEnum.EXECUTOR_TYPE.getKey(), String.valueOf(ExecutorTypeEnum.JAVA.getType()));
        return headersMap;
    }


    /**
     * 或者Http客户端
     *
     * @return {@link TaskHttpClient} 默认为RestTemplateClient
     */
    public static TaskHttpClient loadTaskEngineHttpClient() {
        TaskProperties properties = Container.get(TaskProperties.class);
        Assert.notNull(properties, () -> new TaskClientException("snail job properties is null"));
        TaskProperties.TaskOpenApiConfig openApiConfig = properties.getOpenapi();

        openApiConfig.setHost(Optional.ofNullable(openApiConfig.getHost()).orElse(properties.getServer().getHost()));

        return ServiceLoaderUtil.loadList(TaskHttpClient.class)
                .stream()
                .findAny()
                .orElse(new DefaultHttpClient(openApiConfig));
    }

}
