package com.wingflare.engine.task.server.common.register;

import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.client.CommonRpcClient;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.UpdateClientInfoDTO;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.rpc.client.RequestBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 客户端刷新注册注册
 *
 * @author opensnail
 * @since 1.6.0
 */
@Component
public class UpdateClientRegister {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000));
    private final InstanceManager instanceManager;

    public UpdateClientRegister(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    public boolean updateClientInfo(UpdateClientInfoDTO clientInfoDTO) {
        Set<InstanceLiveInfo> instanceALiveInfoSet = instanceManager.getInstanceALiveInfoSet(ServerRegister.NAMESPACE_ID, ServerRegister.GROUP_NAME);
        instanceALiveInfoSet = instanceALiveInfoSet.stream().filter(info -> !info.getNodeInfo().getHostId().equals(ServerRegister.CURRENT_CID)).collect(Collectors.toSet());

        if (null != clientInfoDTO) {
            // 更新本地标签
            instanceManager.updateInstanceLabels(clientInfoDTO);
        }

        if (!instanceALiveInfoSet.isEmpty()) {
            List<Boolean> results = new ArrayList<>();
            instanceALiveInfoSet.stream().map(info -> threadPoolExecutor.submit((Callable<Result>) () -> {
                CommonRpcClient serverRpcClient = buildRpcClient(info);
                return serverRpcClient.updateClientInfo(clientInfoDTO);
            })).forEach(future -> {
                try {
                    Result<Boolean> result = future.get(1, TimeUnit.SECONDS);
                    if (StatusEnum.NO.getStatus() == result.getStatus()
                            || (Objects.nonNull(result.getData()) && !result.getData())) {
                        TaskEngineLog.LOCAL.error("update client info error. msg:[{}]", result.getMessage());
                        results.add(false);
                    } else {
                        results.add(true);
                    }
                } catch (Throwable e) {
                    TaskEngineLog.LOCAL.error("update client info error", e);
                    results.add(false);
                }
            });

            return results.stream().allMatch(Boolean::booleanValue);
        }

        return true;
    }

    private CommonRpcClient buildRpcClient(InstanceLiveInfo info) {

        int maxRetryTimes = 3;
        return RequestBuilder.<CommonRpcClient, Result>newBuilder().nodeInfo(info).failRetry(true).retryTimes(maxRetryTimes).client(CommonRpcClient.class).build();
    }
}
