package com.wingflare.engine.task.client.common.log.support;


import cn.hutool.core.util.ServiceLoaderUtil;
import com.wingflare.engine.task.client.common.TaskLogThreadLocal;
import com.wingflare.engine.task.client.common.TaskThreadLocal;
import com.wingflare.engine.task.client.common.log.report.LogMeta;
import com.wingflare.engine.task.client.common.threadlocal.CommonThreadLocal;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;

import java.util.Objects;

/**
 * @author wodeyangzipingpingwuqi·
 * @date 2023-12-29
 * @since 1.0.0
 */
public final class TaskLogManager {

    private static final TaskThreadLocal<LogTypeEnum> LOG_TYPE = jobLogContextLoader();
    private static final TaskThreadLocal<LogMeta> LOG_META = jobLogContextLoader();

    private static <T> TaskThreadLocal<T> jobLogContextLoader() {
        TaskThreadLocal<T> taskThreadLocal = ServiceLoaderUtil.loadFirst(TaskLogThreadLocal.class);
        if (Objects.isNull(taskThreadLocal)) {
            taskThreadLocal = new CommonThreadLocal<>(new ThreadLocal<>());
        }
        return taskThreadLocal;
    }

    private TaskLogManager() {
    }

    public static void initLogInfo(LogMeta logMeta, LogTypeEnum logType) {
        setLogMeta(logMeta);
        setLogType(logType);
    }

    public static void setLogMeta(LogMeta logMeta) {
        LOG_META.set(logMeta);
    }

    public static LogMeta getLogMeta() {
        return LOG_META.get();
    }

    public static void removeLogMeta() {
        LOG_META.remove();
    }

    public static void removeAll() {
        removeLogMeta();
        removeLogType();
    }

    public static void setLogType(LogTypeEnum logType) {
        LOG_TYPE.set(logType);
    }

    public static LogTypeEnum getLogType() {
        return LOG_TYPE.get();
    }

    public static void removeLogType() {
        LOG_TYPE.remove();
    }

}
