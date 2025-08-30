package com.wingflare.engine.task.server.common.dto;

import io.grpc.ManagedChannel;

import java.util.Objects;

/**
 * <p>
 * 活跃的实例信息
 * </p>
 *
 * @author opensnail
 * @date 2025-05-24
 */
public class InstanceLiveInfo implements Comparable<InstanceLiveInfo> {
    private long lastUpdateAt;
    private RegisterNodeInfo nodeInfo;
    private boolean alive;
    private ManagedChannel channel;

    public long getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(long lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public RegisterNodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(RegisterNodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ManagedChannel getChannel() {
        return channel;
    }

    public void setChannel(ManagedChannel channel) {
        this.channel = channel;
    }

    @Override
    public int compareTo(InstanceLiveInfo o) {
        if (Objects.isNull(nodeInfo) || Objects.isNull(o) || Objects.isNull(o.getNodeInfo())) {
            return 0;
        }
        return nodeInfo.compareTo(o.getNodeInfo());
    }
}
