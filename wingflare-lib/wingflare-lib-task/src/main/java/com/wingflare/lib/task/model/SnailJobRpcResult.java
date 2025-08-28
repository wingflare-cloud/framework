package com.wingflare.lib.task.model;


/**
 * @author: opensnail
 * @date : 2022-02-16 14:07
 */
public class SnailJobRpcResult extends Result<Object> {

    private long reqId;

    public SnailJobRpcResult(int status, String message, Object data, long reqId) {
        super(status, message, data);
        this.reqId = reqId;
    }

    public SnailJobRpcResult() {
    }

    public SnailJobRpcResult(Object data, long reqId) {
        super(data);
        this.reqId = reqId;
    }

    public long getReqId() {
        return reqId;
    }

    public SnailJobRpcResult setReqId(long reqId) {
        this.reqId = reqId;
        return this;
    }
}
