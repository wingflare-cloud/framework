package com.wingflare.engine.task.server.common.rpc.client;

import com.github.rholder.retry.RetryListener;

import java.util.Map;

/**
 * author: zhangshuguang
 * date: 2025-02-17
 */
public interface TaskEngineRetryListener extends RetryListener {

    /**
     * 传递属性信息
     *
     * @return Map<String, Object>
     */
    Map<String, Object> properties();

}
