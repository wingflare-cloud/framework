package com.wingflare.api.lifecycle;

/**
 * 生命周期状态枚举
 */
public enum LifecycleStatus {
    // 未运行
    UNRUN,
    // 运行中
    RUNNING,
    // 启动中
    STARTING,
    // 销毁中状态
    DESTROYING,
    // 已销毁状态
    DESTROYED
}
