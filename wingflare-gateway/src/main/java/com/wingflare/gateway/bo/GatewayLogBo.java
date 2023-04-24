package com.wingflare.gateway.bo;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @ClassName GatewayLogBo
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 网关日志bo
 */
public class GatewayLogBo {

    /**访问实例*/
    private String targetServer;

    /**请求路径*/
    private String reqPath;

    /**请求方法*/
    private String reqMethod;

    /**协议*/
    private String schema;

    /**返回状态码*/
    private Integer respStatus;

    /**请求体*/
    private String respType;

    /**请求体*/
    private String reqBody;

    /**请求参数*/
    private String reqQuery;

    /**响应体*/
    private String respData;

    /**请求ip*/
    private String ip;

    /**请求时间*/
    private Date reqTime;

    /**响应时间*/
    private Date respTime;

    /**执行时间*/
    private long executeTime;

    /**链路id*/
    private String traceId;

    /**请求头信息*/
    private String reqHeader;


    public String getTargetServer() {
        return targetServer;
    }

    public void setTargetServer(String targetServer) {
        this.targetServer = targetServer;
    }

    public String getReqPath() {
        return reqPath;
    }

    public void setReqPath(String reqPath) {
        this.reqPath = reqPath;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Integer getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(Integer respStatus) {
        this.respStatus = respStatus;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getReqQuery() {
        return reqQuery;
    }

    public void setReqQuery(String reqQuery) {
        this.reqQuery = reqQuery;
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Date getRespTime() {
        return respTime;
    }

    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(String reqHeader) {
        this.reqHeader = reqHeader;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
