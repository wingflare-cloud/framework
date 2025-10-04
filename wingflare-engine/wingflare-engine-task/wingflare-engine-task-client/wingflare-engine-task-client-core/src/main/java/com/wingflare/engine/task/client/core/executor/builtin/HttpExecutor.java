package com.wingflare.engine.task.client.core.executor.builtin;


import com.wingflare.api.task.annotation.TaskExecutor;
import com.wingflare.engine.task.client.core.dto.JobArgs;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@TaskExecutor(name = "HttpExecutor")
public class HttpExecutor extends AbstractHttpExecutor {

    public ExecuteResult taskExecute(JobArgs jobArgs) {
        Object jobParams = jobArgs.getJobParams();
        HttpParams httpParams = JsonUtil.parseObject((String) jobParams, HttpParams.class);
        if (Objects.nonNull(jobArgs.getWfContext())) {
            httpParams.setWfContext(jobArgs.getWfContext());
        }
        httpParams.setMethod(httpParams.getMethod().toUpperCase());
        Map<String, String> headers = (Objects.isNull(httpParams.getHeaders()) || httpParams.getHeaders().isEmpty()) ? new HashMap<>() : httpParams.getHeaders();
        httpParams.setHeaders(headers);
        return process(httpParams);
    }

}
