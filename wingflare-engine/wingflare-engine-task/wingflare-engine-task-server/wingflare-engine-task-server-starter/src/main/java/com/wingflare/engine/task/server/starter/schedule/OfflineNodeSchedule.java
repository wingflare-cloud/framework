package com.wingflare.engine.task.server.starter.schedule;


import cn.hutool.core.collection.CollUtil;
import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.register.ServerRegister;
import com.wingflare.engine.task.server.common.schedule.AbstractSchedule;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.ServerNodeMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.ServerNode;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 删除过期下线机器
 *
 * @author: opensnail
 * @date : 2023-07-21 14:59
 * @since 2.1.0
 */
@Component
public class OfflineNodeSchedule extends AbstractSchedule implements Lifecycle {
    private final ServerNodeMapper serverNodeMapper;

    public OfflineNodeSchedule(ServerNodeMapper serverNodeMapper) {
        this.serverNodeMapper = serverNodeMapper;
    }

    @Override
    protected void doExecute() {

        try {
            // 删除内存缓存的待下线的机器
            LocalDateTime endTime = LocalDateTime.now().minusSeconds(
                    ServerRegister.DELAY_TIME + (ServerRegister.DELAY_TIME / 3));

            List<ServerNode> serverNodes = serverNodeMapper.selectList(
                    new LambdaQueryWrapper<ServerNode>().select(ServerNode::getId)
                            .le(ServerNode::getExpireAt, endTime));
            if (CollUtil.isNotEmpty(serverNodes)) {
                // 先删除DB中需要下线的机器
                serverNodeMapper.deleteByIds(StreamUtils.toSet(serverNodes, ServerNode::getId));
            }

        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Clear offline node failed", e);
        }
    }

    @Override
    public String lockName() {
        return "clearOfflineNode";
    }

    @Override
    public String lockExpire() {
        return "PT10S";
    }

    @Override
    public String lockTimeout() {
        return "PT5S";
    }

    @Override
    public void start() {
        taskScheduler.scheduleWithFixedDelay(this::execute, Instant.now(), Duration.parse("PT5S"));
    }

    @Override
    public void close() {

    }
}
