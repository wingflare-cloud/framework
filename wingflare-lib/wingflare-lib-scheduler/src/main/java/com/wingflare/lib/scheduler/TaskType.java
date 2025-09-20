package com.wingflare.lib.scheduler;


/**
 * 任务类型枚举
 */
public enum TaskType {

    ONCE,          // 一次性延时任务
    FIXED_RATE,    // 固定频率执行
    FIXED_DELAY,   // 固定延迟执行
    CRON           // Cron表达式任务

}
