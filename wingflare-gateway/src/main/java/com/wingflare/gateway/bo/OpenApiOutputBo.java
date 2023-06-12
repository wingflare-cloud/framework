package com.wingflare.gateway.bo;

import java.util.Date;

/**
 * 开放平台响应Bo
 */
public class OpenApiOutputBo {

    /**
     * 接口响应状态码
     */
    private int status;

    /**
     * 响应时间
     */
    private Date timestamp;

    /**
     * 参数签名值
     */
    private String sign;

    /**
     * 具体错误代码
     */
    private String code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 业务数据JSON串
     */
    private String data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
