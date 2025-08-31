package com.wingflare.engine.task.client.common;

/**
 * @author: opensnail
 * @date : 2024-06-27
 * @since : sj_1.1.0
 */
public interface TaskThreadLocal<T> {

    void set(T value);

    void remove();

    T get();

}
