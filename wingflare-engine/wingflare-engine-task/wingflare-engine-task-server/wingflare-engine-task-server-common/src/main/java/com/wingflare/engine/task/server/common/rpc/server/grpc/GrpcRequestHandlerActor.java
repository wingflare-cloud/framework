package com.wingflare.engine.task.server.common.rpc.server.grpc;


import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.Metadata;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.HttpRequestHandler;
import com.wingflare.engine.task.server.common.cache.CacheToken;
import com.wingflare.engine.task.server.common.dto.GrpcRequest;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.lib.container.Container;
import io.grpc.stub.StreamObserver;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import org.apache.pekko.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 处理netty客户端请求
 *
 * @author: opensnail
 * @date : 2023-07-24 09:20
 * @since 2.1.0
 */
@Component(ActorGenerator.GRPC_REQUEST_HANDLER_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GrpcRequestHandlerActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GrpcRequest.class, grpcRequest -> {
            TaskGrpcRequest taskGrpcRequest = grpcRequest.getJobRequest();
            Metadata metadata = taskGrpcRequest.getMetadata();
            final String uri = metadata.getUri();
            if (StrUtil.isBlank(uri)) {
                TaskEngineLog.LOCAL.error("uri can not be null");
                return;
            }

            Map<String, String> headersMap = metadata.getHeadersMap();
            TaskRpcResult taskRpcResult = null;
            try {
                TaskRequest request = new TaskRequest();
                String body = taskGrpcRequest.getBody();
                Object[] objects = JsonUtil.parseObject(body, Object[].class);
                request.setArgs(objects);
                request.setReqId(taskGrpcRequest.getReqId());
                taskRpcResult = doProcess(uri, JsonUtil.toJsonString(request), headersMap);
                if (Objects.isNull(taskRpcResult)) {
                    taskRpcResult = new TaskRpcResult(StatusEnum.NO.getStatus(), "Server-side exception", null,
                        taskGrpcRequest.getReqId());
                }
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("http request error. [{}]", taskGrpcRequest, e);
                taskRpcResult = new TaskRpcResult(StatusEnum.NO.getStatus(), e.getMessage(), null,
                    taskGrpcRequest.getReqId());
            } finally {
                StreamObserver<GrpcResult> streamObserver = grpcRequest.getStreamObserver();
                GrpcResult grpcResult = GrpcResult.newBuilder()
                    .setReqId(taskRpcResult.getReqId())
                    .setStatus(taskRpcResult.getStatus())
                    .setMessage(Optional.ofNullable(taskRpcResult.getMessage()).orElse(StrUtil.EMPTY))
                    .setData(JsonUtil.toJsonString(taskRpcResult.getData()))
                    .build();
                streamObserver.onNext(grpcResult);
                streamObserver.onCompleted();
                getContext().stop(getSelf());
            }


        }).build();
    }

    private TaskRpcResult doProcess(String uri, String content, Map<String, String> headersMap) {
        String groupName = headersMap.get(HeadersEnum.GROUP_NAME.getKey());
        String namespace = headersMap.get(HeadersEnum.NAMESPACE.getKey());
        String token = headersMap.get(HeadersEnum.TOKEN.getKey());

        if (StrUtil.isBlank(token) || !CacheToken.get(groupName, namespace).equals(token)) {
            throw new TaskServerException("Token authentication failed. [namespace:{} groupName:{} token:{}]",
                namespace, groupName, token);
        }

        DefaultHttpHeaders headers = new DefaultHttpHeaders();
        headersMap.forEach(headers::add);

        UrlBuilder builder = UrlBuilder.ofHttp(uri);
        Collection<HttpRequestHandler> httpRequestHandlers = Container
            .getAllMap(HttpRequestHandler.class).values();
        for (HttpRequestHandler httpRequestHandler : httpRequestHandlers) {
            if (httpRequestHandler.supports(builder.getPathStr())) {
                return httpRequestHandler.doHandler(content, builder, headers);
            }
        }

        throw new TaskServerException("No matching handler found. Path:[{}] method:[{}]", builder.getPathStr());
    }


}
