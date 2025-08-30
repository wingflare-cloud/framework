package com.wingflare.engine.task.server.openapi.api;

import com.wingflare.engine.task.common.model.request.StatusUpdateApiRequest;
import com.wingflare.engine.task.common.model.request.base.TriggerRetryRequest;
import com.wingflare.engine.task.common.model.response.RetryApiResponse;
import com.wingflare.engine.task.server.openapi.service.impl.RetryApiServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.*;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-25
 */
@RestController
public class RetryApi {
    private final RetryApiServiceImpl retryApiService;

    public RetryApi(RetryApiServiceImpl retryApiService) {
        this.retryApiService = retryApiService;
    }

    @GetMapping(OPENAPI_QUERY_RETRY)
    public RetryApiResponse getRetryTaskById(@RequestParam("id") Long id) {
        return retryApiService.getRetryById(id, RetryApiResponse.class);
    }

    @PutMapping(OPENAPI_UPDATE_RETRY_STATUS_V2)
    public boolean updateRetryTaskStatus(@RequestBody @Validated StatusUpdateApiRequest requestDTO) {
        return retryApiService.updateRetryStatus(requestDTO);
    }

    @PostMapping(OPENAPI_TRIGGER_RETRY_V2)
    public boolean triggerRetry(@RequestBody @Validated TriggerRetryRequest requestDTO) {
        return retryApiService.triggerRetry(requestDTO);
    }
}
