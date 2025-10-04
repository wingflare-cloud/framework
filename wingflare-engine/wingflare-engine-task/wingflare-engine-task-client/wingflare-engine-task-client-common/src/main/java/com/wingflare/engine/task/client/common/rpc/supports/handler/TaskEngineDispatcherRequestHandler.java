package com.wingflare.engine.task.client.common.rpc.supports.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.ServiceLoaderUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.engine.task.client.common.HandlerInterceptor;
import com.wingflare.engine.task.client.common.cache.EndPointInfoCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.client.common.rpc.supports.handler.grpc.GrpcRequest;
import com.wingflare.engine.task.client.common.rpc.supports.http.HttpRequest;
import com.wingflare.engine.task.client.common.rpc.supports.http.HttpResponse;
import com.wingflare.engine.task.client.common.rpc.supports.scan.EndPointInfo;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.grpc.auto.Metadata;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.core.utils.ReflectionUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: opensnail
 * @date : 2024-04-12
 * @since : 3.3.0
 */
public class TaskEngineDispatcherRequestHandler {

    private final TaskProperties taskProperties;

    public TaskEngineDispatcherRequestHandler(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    public TaskRpcResult dispatch(GrpcRequest request) {
        TaskRpcResult taskRpcResult = new TaskRpcResult();

        HttpRequest httpRequest = request.getHttpRequest();
        HttpResponse httpResponse = request.getHttpResponse();

        List<HandlerInterceptor> handlerInterceptors = handlerInterceptors();

        TaskGrpcRequest snailJobRequest = request.getSnailJobRequest();
        EndPointInfo endPointInfo = null;
        Result resultObj = null;
        Throwable e = null;
        try {
            Metadata metadata = snailJobRequest.getMetadata();
            Map<String, String> headersMap = metadata.getHeadersMap();
            String snailJobAuth = headersMap.get(SystemConstants.SNAIL_JOB_AUTH_TOKEN);
            String configToken = Optional.ofNullable(taskProperties.getToken()).orElse(SystemConstants.DEFAULT_TOKEN);
            if (!configToken.equals(snailJobAuth)) {
                throw new TaskClientException("Authentication failed. [Please check if the configured Token is correct]");
            }

            UrlBuilder builder = UrlBuilder.ofHttp(httpRequest.getUri());
            endPointInfo = EndPointInfoCache.get(builder.getPathStr(), RequestMethod.POST);
            if (Objects.isNull(endPointInfo)) {
                throw new TaskClientException(" Cannot find corresponding processing, please check if the corresponding package is correctly introduced." +
                                                  "path:[{}] requestMethod:[{}]", builder.getPathStr());
            }

            Class<?>[] paramTypes = endPointInfo.getMethod().getParameterTypes();
            TaskGrpcRequest taskGrpcRequest = request.getSnailJobRequest();
            Object[] args = JsonUtil.parseObject(taskGrpcRequest.getBody(), Object[].class);

            Object[] deSerialize = (Object[]) deSerialize(JsonUtil.toJsonString(args), endPointInfo.getMethod(),
                httpRequest, httpResponse);

            for (final HandlerInterceptor handlerInterceptor : handlerInterceptors) {
                if (!handlerInterceptor.preHandle(httpRequest, httpResponse, endPointInfo)) {
                    return taskRpcResult;
                }
            }

            if (paramTypes.length > 0) {
                resultObj = (Result) ReflectionUtil.invokeMethod(endPointInfo.getMethod(),
                    endPointInfo.getExecutor(), deSerialize);
            } else {
                resultObj = (Result) ReflectionUtil.invokeMethod(endPointInfo.getMethod(),
                    endPointInfo.getExecutor());
            }

            for (final HandlerInterceptor handlerInterceptor : handlerInterceptors) {
                handlerInterceptor.postHandle(httpRequest, httpResponse, endPointInfo);
            }
        } catch (Throwable ex) {
            TaskEngineLog.LOCAL.error("http request error. [{}]", snailJobRequest, ex);
            taskRpcResult.setMessage(ex.getMessage()).setStatus(StatusEnum.NO.getStatus());
            e = ex;
        } finally {
            taskRpcResult.setReqId(0);
            if (Objects.nonNull(resultObj)) {
                taskRpcResult.setData(resultObj.getData())
                    .setMessage(resultObj.getMessage())
                    .setStatus(resultObj.getStatus());
            }

            for (final HandlerInterceptor handlerInterceptor : handlerInterceptors) {
                handlerInterceptor.afterCompletion(httpRequest, httpResponse, endPointInfo, e);
            }
        }

        return taskRpcResult;
    }

    private static List<HandlerInterceptor> handlerInterceptors() {
        List<HandlerInterceptor> handlerInterceptors = ServiceLoaderUtil.loadList(HandlerInterceptor.class);
        if (CollUtil.isEmpty(handlerInterceptors)) {
            return Collections.emptyList();
        }

        return handlerInterceptors.stream().sorted(Comparator.comparingInt(HandlerInterceptor::order)).collect(
                Collectors.toList());
    }

    public Object deSerialize(String infoStr, Method method,
                              HttpRequest httpRequest, HttpResponse httpResponse) throws JsonProcessingException {

        Type[] paramTypes = method.getGenericParameterTypes();
        Parameter[] parameters = method.getParameters();
        Object[] params = new Object[paramTypes.length];

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = JsonUtil.toJson(infoStr);
        if (Objects.isNull(jsonNode)) {
            TaskEngineLog.LOCAL.warn("jsonNode is null. infoStr:[{}]", infoStr);
            return params;
        }

        for (int i = 0; i < paramTypes.length; i++) {
            JsonNode node = jsonNode.get(i);
            if (Objects.nonNull(node)) {
                params[i] = mapper.readValue(node.toString(), mapper.constructType(paramTypes[i]));
                continue;
            }

            Parameter parameter = parameters[i];
            if (parameter.getType().isAssignableFrom(HttpRequest.class)) {
                params[i] = httpRequest;
                continue;
            }

            if (parameter.getType().isAssignableFrom(HttpResponse.class)) {
                params[i] = httpResponse;
            }
        }

        return params;
    }

}
