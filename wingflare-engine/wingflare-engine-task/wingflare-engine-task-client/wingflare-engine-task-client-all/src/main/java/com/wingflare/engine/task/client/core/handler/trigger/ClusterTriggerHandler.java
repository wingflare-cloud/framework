package com.wingflare.engine.task.client.core.handler.trigger;


public class ClusterTriggerHandler extends TriggerJobHandler<ClusterTriggerHandler>{

    public ClusterTriggerHandler(Long triggerJobId) {
        super(triggerJobId);
    }

    @Override
    public ClusterTriggerHandler addArgsStr(String argsKey, Object argsValue) {
        return super.addArgsStr(argsKey, argsValue);
    }
}
