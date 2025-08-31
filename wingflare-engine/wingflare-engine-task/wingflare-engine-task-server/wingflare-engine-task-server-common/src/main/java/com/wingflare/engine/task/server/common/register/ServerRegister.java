package com.wingflare.engine.task.server.common.register;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.NodeTypeEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.TaskEngineNetworkUtils;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.cache.CacheConsumerGroup;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.convert.RegisterNodeInfoConverter;
import com.wingflare.engine.task.server.common.dto.ServerNodeExtAttrs;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.task.datasource.template.persistence.po.ServerNode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 服务端注册
 *
 * @author opensnail
 * @date 2023-06-07
 * @since 1.6.0
 */
@Component(ServerRegister.BEAN_NAME)
public class ServerRegister extends AbstractRegister {
    public static final String BEAN_NAME = "serverRegister";
    private final ScheduledExecutorService serverRegisterNode = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "server-register-node"));
    public static final int DELAY_TIME = 30;
    public static final String CURRENT_CID;
    public static final String GROUP_NAME = "DEFAULT_SERVER";
    public static final String NAMESPACE_ID = "DEFAULT_SERVER_NAMESPACE_ID";
    private final InstanceManager instanceManager;
    private final SystemProperties systemProperties;
    private final ServerProperties serverProperties;
    private final TaskEngineNetworkUtils taskEngineNetworkUtils;

    static {
        CURRENT_CID = IdUtil.getSnowflakeNextIdStr();
    }

    public ServerRegister(InstanceManager instanceManager, SystemProperties systemProperties, ServerProperties serverProperties, TaskEngineNetworkUtils taskEngineNetworkUtils) {
        this.instanceManager = instanceManager;
        this.systemProperties = systemProperties;
        this.serverProperties = serverProperties;
        this.taskEngineNetworkUtils = taskEngineNetworkUtils;
    }

    @Override
    public boolean supports(int type) {
        return getNodeType().equals(type);
    }

    @Override
    protected void beforeProcessor(RegisterContext context) {
        // 新增扩展参数
        ServerNodeExtAttrs serverNodeExtAttrs = new ServerNodeExtAttrs();
        serverNodeExtAttrs.setWebPort(serverProperties.getPort());
        serverNodeExtAttrs.setSystemVersion(TaskVersion.getVersion());

        context.setGroupName(GROUP_NAME);
        context.setHostId(CURRENT_CID);

        context.setHostIp(Optional.ofNullable(systemProperties.getServerHost()).filter(StrUtil::isNotBlank).orElse(taskEngineNetworkUtils.findPreferredHostAddress()));
        context.setHostPort(systemProperties.getServerPort());
        context.setContextPath(Optional.ofNullable(serverProperties.getServlet().getContextPath()).orElse(StrUtil.EMPTY));
        context.setNamespaceId(NAMESPACE_ID);
        context.setExtAttrs(JsonUtil.toJsonString(serverNodeExtAttrs));
    }

    @Override
    protected LocalDateTime getExpireAt() {
        return LocalDateTime.now().plusSeconds(DELAY_TIME);
    }

    @Override
    protected boolean doRegister(RegisterContext context, ServerNode serverNode) {
        refreshExpireAt(Lists.newArrayList(serverNode));
        return Boolean.TRUE;
    }


    @Override
    protected void afterProcessor(final ServerNode serverNode) {
        try {
            // 同步当前POD消费的组的节点信息
            // netty的client只会注册到一个服务端，若组分配的和client连接的不是一个POD则会导致当前POD没有其他客户端的注册信息
            ConcurrentMap<String /*groupName*/, Set<String>/*namespaceId*/> allConsumerGroupName = CacheConsumerGroup.getAllConsumerGroupName();
            if (CollUtil.isNotEmpty(allConsumerGroupName)) {
                Set<String> namespaceIdSets = StreamUtils.toSetByFlatMap(allConsumerGroupName.values(), Set::stream);
                if (CollUtil.isEmpty(namespaceIdSets)) {
                    return;
                }

                List<ServerNode> serverNodes = serverNodeMapper.selectList(
                        new LambdaQueryWrapper<ServerNode>()
                                .eq(ServerNode::getNodeType, NodeTypeEnum.CLIENT.getType())
                                .in(ServerNode::getNamespaceId, namespaceIdSets)
                                .in(ServerNode::getGroupName, allConsumerGroupName.keySet()));
                for (final ServerNode node : serverNodes) {
                    // 刷新全量本地缓存
                    instanceManager.registerOrUpdate(RegisterNodeInfoConverter.INSTANCE.toRegisterNodeInfo(node));
                    // 刷新过期时间
                    CacheConsumerGroup.addOrUpdate(node.getGroupName(), node.getNamespaceId());
                }
            }
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Client refresh failed", e);
        }
    }

    @Override
    protected Integer getNodeType() {
        return NodeTypeEnum.SERVER.getType();
    }

    @Override
    public void start() {
        TaskEngineLog.LOCAL.info("ServerRegister start");

        serverRegisterNode.scheduleAtFixedRate(() -> {
            try {
                this.register(new RegisterContext());
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("Server-side registration failed", e);
            }
        }, 0, DELAY_TIME * 2 / 3, TimeUnit.SECONDS);

    }

    @Override
    public void close() {
        TaskEngineLog.LOCAL.info("ServerRegister close");
    }
}
