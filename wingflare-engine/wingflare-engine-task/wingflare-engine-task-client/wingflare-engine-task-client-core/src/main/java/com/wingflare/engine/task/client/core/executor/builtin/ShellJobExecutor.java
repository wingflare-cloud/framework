package com.wingflare.engine.task.client.core.executor.builtin;


import com.wingflare.api.task.annotation.TaskExecutor;
import com.wingflare.engine.task.client.core.dto.JobArgs;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import org.springframework.stereotype.Component;

@Component
@TaskExecutor(name = "shellJobExecutor")
public class ShellJobExecutor extends AbstractShellExecutor {

    public ExecuteResult jobExecute(JobArgs jobArgs) {
        Object jobParams = jobArgs.getJobParams();
        ScriptParams scriptParams = JsonUtil.parseObject((String) jobParams, ScriptParams.class);
        return process(jobArgs.getJobId(), scriptParams);
    }

}
