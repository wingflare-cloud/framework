package com.wingflare.gateway;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingflare.lib.core.constants.DateFormat;

import java.util.Date;

/**
 * @author naizui_ycx
 * @date {2021/12/30}
 * @description
 */
public class R<T> extends com.wingflare.lib.standard.R<T> {

    /**
     * 响应状态码
     */
    protected String code;

    /**
     * 时间字段
     */
    @JsonFormat(pattern= DateFormat.PATTERN_CLASSICAL)
    protected Date timestamp;

    /**
     * 请求id
     */
    protected String traceId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
