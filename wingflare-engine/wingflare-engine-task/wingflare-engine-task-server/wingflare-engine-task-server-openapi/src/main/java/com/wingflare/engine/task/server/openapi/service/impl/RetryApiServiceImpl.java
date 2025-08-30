package com.wingflare.engine.task.server.openapi.service.impl;

import com.wingflare.engine.task.server.openapi.service.RetryApiService;
import com.wingflare.engine.task.server.openapi.util.OpenApiSessionUtils;
import com.wingflare.engine.task.server.service.service.impl.AbstractRetryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-25
 */
@Service("retryApiService")
public class RetryApiServiceImpl extends AbstractRetryService implements RetryApiService {
    @Override
    protected String getNamespaceId() {
        return OpenApiSessionUtils.getNamespaceId();
    }
}
