package com.wingflare.engine.task.client.spring.starter.rpc.scan;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.common.cache.EndPointInfoCache;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.rpc.supports.scan.EndPointInfo;

import java.util.List;


/**
 * @author opensnail
 * @date 2024-04-11 22:57:03
 * @since 3.3.0
 */
public class TaskEndPointRegistrar implements Lifecycle {
    private final TaskEndPointScanner taskEndPointScanner;

    public TaskEndPointRegistrar(TaskEndPointScanner taskEndPointScanner) {
        this.taskEndPointScanner = taskEndPointScanner;
    }

    @Override
    public void start() {
        List<EndPointInfo> endPointInfos = taskEndPointScanner.doScan();
        for (EndPointInfo endPointInfo : endPointInfos) {
            if (EndPointInfoCache.isExisted(endPointInfo.getPath(), endPointInfo.getRequestMethod())) {
                throw new TaskClientException("Duplicate endpoint path: {}", endPointInfo.getPath());
            }

            EndPointInfoCache.put(endPointInfo);
        }

    }

    @Override
    public void close() {

    }
}
