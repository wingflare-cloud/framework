package com.wingflare.engine.task.client.core.executor.builtin;


import com.wingflare.api.task.annotation.TaskExecutor;
import com.wingflare.api.task.JobArgs;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;



@TaskExecutor(name = "ShellJobExecutor")
public class ShellJobExecutor extends AbstractShellExecutor {

    public ExecuteResult taskExecute(JobArgs jobArgs) {
        Object jobParams = jobArgs.getJobParams();
        ScriptParams scriptParams = JsonUtil.parseObject((String) jobParams, ScriptParams.class);
        return process(jobArgs.getJobId(), scriptParams);
    }

}
