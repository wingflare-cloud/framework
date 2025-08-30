package com.wingflare.engine.task.common.core.model;


/**
 * @author: opensnail
 * @date : 2022-02-16 14:07
 */
public class TaskRpcResult extends Result<Object> {

    private long reqId;

    public TaskRpcResult(int status, String message, Object data, long reqId) {
        super(status, message, data);
        this.reqId = reqId;
    }

    public TaskRpcResult() {
    }

    public TaskRpcResult(Object data, long reqId) {
        super(data);
        this.reqId = reqId;
    }

    public long getReqId() {
        return reqId;
    }

    public TaskRpcResult setReqId(long reqId) {
        this.reqId = reqId;
        return this;
    }
}
