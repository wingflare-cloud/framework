package com.wingflare.engine.task.client.retry.intercepter;


import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.client.retry.RetrySiteSnapshotContext;
import com.wingflare.engine.task.client.retry.exception.TaskRetryClientException;

/**
 * ThreadLocal实现类
 *
 * @author: opensnail
 * @date : 2023-08-09 16:34
 * @since 2.2.0
 */
public class ThreadLockRetrySiteSnapshotContext<T> implements RetrySiteSnapshotContext<T> {

    private final ThreadLocal<T> threadLocal;

    public ThreadLockRetrySiteSnapshotContext(ThreadLocal<T> threadLocal) {
        Assert.notNull(threadLocal, () -> new TaskRetryClientException("thread local can not be null"));
        this.threadLocal = threadLocal;
    }

    @Override
    public void set(T value) {
        threadLocal.set(value);
    }

    @Override
    public void remove() {
        threadLocal.remove();
    }

    @Override
    public T get() {
        return threadLocal.get();
    }
}
