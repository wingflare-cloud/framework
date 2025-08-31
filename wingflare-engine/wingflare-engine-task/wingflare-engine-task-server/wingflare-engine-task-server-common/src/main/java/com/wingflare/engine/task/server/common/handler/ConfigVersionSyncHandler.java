package com.wingflare.engine.task.server.common.handler;

import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.client.CommonRpcClient;
import com.wingflare.engine.task.server.common.dto.ConfigSyncTask;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.rpc.client.RequestBuilder;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 版本同步
 *
 * @author opensnail
 * @date 2023-06-10
 * @since 1.6.0
 */
@Component
public class ConfigVersionSyncHandler implements Lifecycle, Runnable {
    private static final LinkedBlockingQueue<ConfigSyncTask> QUEUE = new LinkedBlockingQueue<>(256);
    public Thread THREAD = null;
    private final AccessTemplate accessTemplate;
    private final InstanceManager instanceManager;

    public ConfigVersionSyncHandler(AccessTemplate accessTemplate, InstanceManager instanceManager) {
        this.accessTemplate = accessTemplate;
        this.instanceManager = instanceManager;
    }

    /**
     * 添加任务
     *
     * @param groupName      组
     * @param currentVersion 当前版本号
     * @return false-队列容量已满， true-添加成功
     */
    public boolean addSyncTask(String groupName, String namespaceId, Integer currentVersion) {

        ConfigSyncTask configSyncTask = new ConfigSyncTask();
        configSyncTask.setCurrentVersion(currentVersion);
        configSyncTask.setNamespaceId(namespaceId);
        configSyncTask.setGroupName(groupName);
        return QUEUE.offer(configSyncTask);
    }

    /**
     * 同步版本
     *
     * @param groupName   组
     * @param namespaceId 空间id
     */
    public void syncVersion(String groupName, final String namespaceId) {

        try {
            Set<InstanceLiveInfo> instanceALiveInfoSet = instanceManager.getInstanceALiveInfoSet(namespaceId, groupName);
            // 同步版本到每个客户端节点
            for (final InstanceLiveInfo instanceLiveInfo : instanceALiveInfoSet) {
                ConfigRequest configRequest = accessTemplate.getGroupConfigAccess().getConfigInfo(groupName, namespaceId);
                CommonRpcClient rpcClient = RequestBuilder.<CommonRpcClient, Result>newBuilder()
                        .failover(false)
                        .failRetry(true)
                        .retryTimes(3)
                        .retryInterval(1)
                        .nodeInfo(instanceLiveInfo)
                        .client(CommonRpcClient.class)
                        .build();
                TaskEngineLog.LOCAL.info("Synchronization result [{}]", rpcClient.syncConfig(configRequest));
            }
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("version sync error. groupName:[{}]", groupName, e);
        }
    }

    @Override
    public void start() {
        THREAD = new Thread(this, "config-version-sync");
        THREAD.start();
    }

    @Override
    public void close() {
        if (Objects.nonNull(THREAD)) {
            THREAD.interrupt();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ConfigSyncTask task = QUEUE.take();
                // 远程版本号
                Integer remoteVersion = accessTemplate.getGroupConfigAccess().getConfigVersion(task.getGroupName(), task.getNamespaceId());
                if (Objects.isNull(remoteVersion) || !task.getCurrentVersion().equals(remoteVersion)) {
                    syncVersion(task.getGroupName(), task.getNamespaceId());
                }
            } catch (InterruptedException e) {
                TaskEngineLog.LOCAL.info("[{}] thread stop.", Thread.currentThread().getName());
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("client refresh expireAt error.", e);
            } finally {
                try {
                    // 防止刷的过快，休眠1s
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
