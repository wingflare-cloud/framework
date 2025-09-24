package com.wingflare.engine.task.client.core.handler.update;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;

public class BroadcastUpdateHandler extends UpdateHandler<BroadcastUpdateHandler>{

    public BroadcastUpdateHandler(Long jobId) {
        super(JobTaskTypeEnum.BROADCAST, jobId);
        setR(this);
    }

    @Override
    public BroadcastUpdateHandler addArgsStr(String argsKey, Object argsValue) {
        return super.addArgsStr(argsKey, argsValue);
    }
}
