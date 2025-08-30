package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.cache.CacheConsumerGroup;
import com.wingflare.engine.task.server.common.convert.RegisterNodeInfoConverter;
import com.wingflare.engine.task.server.common.handler.GetHttpRequestHandler;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.register.ClientRegister;
import com.wingflare.task.datasource.template.persistence.po.ServerNode;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.GET_REG_NODES_AND_REFRESH;
import static com.wingflare.engine.task.server.common.register.ClientRegister.DELAY_TIME;

/**
 * 获取服务端缓存的客户端节点 并刷新本地时间
 *
 */
@Component
public class GetRegNodesPostHttpRequestHandler extends GetHttpRequestHandler {
    private final InstanceManager instanceManager;

    public GetRegNodesPostHttpRequestHandler(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    @Override
    public boolean supports(String path) {
        return GET_REG_NODES_AND_REFRESH.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("Client Callback Request. content:[{}]", content);

        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);


        List<ServerNode> refreshCache = getAndRefreshCache();
        String json = null;
        if (CollUtil.isNotEmpty(refreshCache)){
            json = JsonUtil.toJsonString(refreshCache);
        }
        return new TaskRpcResult(json, retryRequest.getReqId());
    }

    public  List<ServerNode> getAndRefreshCache() {
        // 获取当前所有需要续签的node
        List<ServerNode> expireNodes = ClientRegister.getExpireNodes();
        if (Objects.nonNull(expireNodes)) {
            // 进行本地续签
            for (final ServerNode serverNode : expireNodes) {
                serverNode.setExpireAt(LocalDateTime.now().plusSeconds(DELAY_TIME));
                // 刷新全量本地缓存
                instanceManager.registerOrUpdate(RegisterNodeInfoConverter.INSTANCE.toRegisterNodeInfo(serverNode));
                // 刷新过期时间
                CacheConsumerGroup.addOrUpdate(serverNode.getGroupName(), serverNode.getNamespaceId());
            }
        }
        return expireNodes;
    }


}
