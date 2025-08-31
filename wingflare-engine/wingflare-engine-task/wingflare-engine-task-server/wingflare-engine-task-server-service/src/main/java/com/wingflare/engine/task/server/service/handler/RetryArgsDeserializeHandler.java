package com.wingflare.engine.task.server.service.handler;

import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.RetryArgsDeserializeRequest;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.InstanceSelectCondition;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.server.retry.client.RetryRpcClient;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.po.RetrySceneConfig;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-06-21
 */
@Component
public class RetryArgsDeserializeHandler {
    private final InstanceManager instanceManager;
    private final AccessTemplate accessTemplate;

    public RetryArgsDeserializeHandler(InstanceManager instanceManager, AccessTemplate accessTemplate) {
        this.instanceManager = instanceManager;
        this.accessTemplate = accessTemplate;
    }

    public String deserialize(RetryArgsDeserializeRequest retryArgsDeserializeVO) {

        RetrySceneConfig retrySceneConfig = accessTemplate.getSceneConfigAccess()
                .getSceneConfigByGroupNameAndSceneName(retryArgsDeserializeVO.getGroup(),
                        retryArgsDeserializeVO.getScene(), retryArgsDeserializeVO.getNamespaceId());

        if (Objects.isNull(retrySceneConfig)) {
            return retryArgsDeserializeVO.getArgsStr();
        }

        InstanceSelectCondition condition = Builder.of(InstanceSelectCondition::new)
                .with(InstanceSelectCondition::setAllocKey, retrySceneConfig.getSceneName())
                .with(InstanceSelectCondition::setGroupName, retrySceneConfig.getGroupName())
                .with(InstanceSelectCondition::setNamespaceId, retrySceneConfig.getNamespaceId())
                .with(InstanceSelectCondition::setRouteKey, retrySceneConfig.getRouteKey())
                .with(InstanceSelectCondition::setTargetLabels, retrySceneConfig.getLabels())
                .build();

        InstanceLiveInfo instance = instanceManager.getALiveInstanceByRouteKey(condition);
        if (Objects.isNull(instance)) {
            return retryArgsDeserializeVO.getArgsStr();
        }
        try {
            // 委托客户端反序列化重试参数
            RetryRpcClient rpcClient = RequestBuilder.<RetryRpcClient, Result>newBuilder()
                    .nodeInfo(instance)
                    .retryTimes(3)
                    .failover(false)
                    .failRetry(true)
                    .retryInterval(1)
                    .client(RetryRpcClient.class)
                    .build();

            Result result = rpcClient.deserialize(retryArgsDeserializeVO);

            if (Objects.isNull(result) || Objects.isNull(result.getData()) || result.getStatus() == StatusEnum.NO.getStatus()) {
                return retryArgsDeserializeVO.getArgsStr();
            }

            return JsonUtil.toJsonString(result.getData());
        } catch (Throwable e) {
            TaskEngineLog.LOCAL.error("deserialize is error. args:[{}]", retryArgsDeserializeVO.getArgsStr(), e);
        }

        return retryArgsDeserializeVO.getArgsStr();
    }
}
