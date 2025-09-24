package com.wingflare.engine.task.client.core.executor;

import cn.hutool.core.util.ServiceLoaderUtil;
import com.wingflare.engine.task.client.common.TaskLogThreadLocal;
import com.wingflare.engine.task.client.common.TaskThreadLocal;
import com.wingflare.engine.task.client.common.threadlocal.CommonThreadLocal;
import com.wingflare.engine.task.common.core.model.JobContext;

import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2024-06-13
 * @since : sj_1.1.0
 */
public final class JobContextManager {
    private JobContextManager() {}

    private static final TaskThreadLocal<JobContext> JOB_CONTEXT_LOCAL = initThreadLocal();
    private static TaskThreadLocal<JobContext> initThreadLocal() {
        TaskThreadLocal<JobContext> taskThreadLocal = ServiceLoaderUtil.loadFirst(TaskLogThreadLocal.class);
        if (Objects.isNull(taskThreadLocal)) {
            taskThreadLocal = new CommonThreadLocal<>(new ThreadLocal<>());
        }
        return taskThreadLocal;
    }

    public static void setJobContext(JobContext jobContext) {
        JOB_CONTEXT_LOCAL.set(jobContext);
    }

    public static JobContext getJobContext() {
        return JOB_CONTEXT_LOCAL.get();
    }

    public static void removeJobContext() {
        JOB_CONTEXT_LOCAL.remove();
    }
}
