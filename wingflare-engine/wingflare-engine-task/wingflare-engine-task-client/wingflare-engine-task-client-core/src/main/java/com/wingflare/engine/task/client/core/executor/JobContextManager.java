package com.wingflare.engine.task.client.core.executor;

import cn.hutool.core.util.ServiceLoaderUtil;
import com.wingflare.engine.task.client.common.SnailJobLogThreadLocal;
import com.wingflare.engine.task.client.common.SnailThreadLocal;
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

    private static final SnailThreadLocal<JobContext> JOB_CONTEXT_LOCAL = initThreadLocal();
    private static SnailThreadLocal<JobContext> initThreadLocal() {
        SnailThreadLocal<JobContext> snailThreadLocal = ServiceLoaderUtil.loadFirst(SnailJobLogThreadLocal.class);
        if (Objects.isNull(snailThreadLocal)) {
            snailThreadLocal = new CommonThreadLocal<>(new ThreadLocal<>());
        }
        return snailThreadLocal;
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
