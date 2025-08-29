package com.wingflare.engine.task.common.core.model;


import com.wingflare.engine.task.common.core.util.JsonUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author opensnail
 * @date 2022-03-08
 * @since 2.0
 */
public class SnailJobRequest {

    private static final AtomicLong REQUEST_ID = new AtomicLong(0);

    private long reqId;

    private Object[] args;

    public SnailJobRequest(Object... args) {
        this.args = args;
        this.reqId = newId();
    }

    private static long newId() {
        return REQUEST_ID.getAndIncrement();
    }

    public SnailJobRequest() {
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
