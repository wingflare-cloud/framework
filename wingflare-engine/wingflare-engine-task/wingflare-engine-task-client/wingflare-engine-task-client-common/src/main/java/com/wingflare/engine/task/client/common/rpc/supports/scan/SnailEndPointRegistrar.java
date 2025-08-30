package com.wingflare.engine.task.client.common.rpc.supports.scan;

import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.cache.EndPointInfoCache;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author opensnail
 * @date 2024-04-11 22:57:03
 * @since 3.3.0
 */
@Component
public class SnailEndPointRegistrar implements Lifecycle {
    private final SnailEndPointScanner snailEndPointScanner;

    public SnailEndPointRegistrar(SnailEndPointScanner snailEndPointScanner) {
        this.snailEndPointScanner = snailEndPointScanner;
    }

    @Override
    public void start() {
        List<EndPointInfo> endPointInfos = snailEndPointScanner.doScan();
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
