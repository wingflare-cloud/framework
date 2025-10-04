package com.wingflare.engine.task.client.retry;


/**
 * 重试现场记录上下文
 * 默认实现see: {@link com.wingflare.engine.task.client.retry.intercepter.ThreadLockRetrySiteSnapshotContext}
 *
 * @author: opensnail
 * @date : 2023-08-09 16:25
 */
public interface RetrySiteSnapshotContext<T> {

    void set(T value);

    void remove();

    T get();
}
