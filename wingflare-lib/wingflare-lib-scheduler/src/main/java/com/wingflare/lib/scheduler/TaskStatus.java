package com.wingflare.lib.scheduler;


/**
 * 任务状态枚举
 */
public enum TaskStatus {
    PENDING,     // 等待执行
    RUNNING,     // 正在执行
    COMPLETED,   // 已完成
    CANCELLED,   // 已取消
    FAILED       // 执行失败
}
