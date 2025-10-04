package com.wingflare.engine.task.client.common.client;


import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.TaskEndPoint;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.ConfigRequest;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.SYNC_CONFIG;

/**
 * SnailJob 通用EndPoint
 *
 * @author: opensnail
 * @date : 2022-03-09 16:33
 */
@TaskEndPoint
public class TaskCommonEndPoint {

    /**
     * 同步版本
     */
    @Mapping(path = SYNC_CONFIG, method = RequestMethod.POST)
    public Result syncVersion(ConfigRequest configRequest) {
        GroupVersionCache.setConfig(configRequest);
        return new Result();
    }

}
