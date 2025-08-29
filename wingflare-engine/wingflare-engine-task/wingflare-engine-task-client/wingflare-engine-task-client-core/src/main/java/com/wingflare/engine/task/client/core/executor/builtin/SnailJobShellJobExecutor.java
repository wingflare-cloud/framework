package com.wingflare.engine.task.client.core.executor.builtin;


import com.wingflare.engine.task.client.core.annotation.JobExecutor;
import com.wingflare.engine.task.client.core.dto.JobArgs;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import org.springframework.stereotype.Component;

@Component
@JobExecutor(name = "snailJobShellJobExecutor")
public class SnailJobShellJobExecutor extends AbstractShellExecutor {

    public ExecuteResult jobExecute(JobArgs jobArgs) {
        Object jobParams = jobArgs.getJobParams();
        ScriptParams scriptParams = JsonUtil.parseObject((String) jobParams, ScriptParams.class);
        return process(jobArgs.getJobId(), scriptParams);
    }

}
