package com.wingflare.lib.task.model;


import com.wingflare.lib.task.constant.SystemConstants;

/**
 * snail-job 请求头信息
 *
 * @author: opensnail
 * @date : 2022-04-16 22:20
 */
public class SnailJobHeaders {

    /**
     * 是否是重试流量
     */
    private boolean isRetry;

    /**
     * 重试下发的ID
     */
    private String retryId;

    /**
     * 调用链超时时间 单位毫秒(ms)
     */
    private long ddl = SystemConstants.DEFAULT_DDL;

    public boolean isRetry() {
        return isRetry;
    }

    public void setRetry(boolean retry) {
        isRetry = retry;
    }

    public String getRetryId() {
        return retryId;
    }

    public void setRetryId(String retryId) {
        this.retryId = retryId;
    }

    public long getDdl() {
        return ddl;
    }

    public void setDdl(long ddl) {
        this.ddl = ddl;
    }
}
