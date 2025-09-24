package com.wingflare.engine.task.client.common.rpc.client.openapi;

import com.wingflare.engine.task.common.core.model.TaskOpenApiResult;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public interface TaskHttpClient {

    /**
     * 执行请求
     *
     * @param request 请求参数
     * @return 返回结果
     */
    TaskOpenApiResult execute(Request request);

}
