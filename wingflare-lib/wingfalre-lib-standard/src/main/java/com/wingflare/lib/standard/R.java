package com.wingflare.lib.standard;

import java.io.Serializable;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 通用接口数据返回类
 */
public class R<T> implements Serializable {

    public static final int RET_NO_ERR = 0;

    public static final int RET_FAIL = 500;

    public static final String RET_NO_ERR_MSG = "success";

    public static final String RET_FAIL_MSG = "serverInternalException";

    /**
     * 返回错误码
     */
    protected Integer ret;

    /**
     * 应答消息
     */
    protected String msg;

    /**
     * 应答数据
     */
    protected T data;


    public static <T> R<T> ok() {
        return r(null, RET_NO_ERR, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return r(data, RET_NO_ERR, msg);
    }

    public static <T> R<T> ok(T data) {
        return r(data, RET_NO_ERR, RET_NO_ERR_MSG);
    }

    public static <T> R<T> ok(String msg) {
        return r(null, RET_NO_ERR, msg);
    }

    public static <T> R<T> ok(int ret, String msg) {
        return r(null, ret, msg);
    }

    public static <T> R<T> fail() {
        return r(null, RET_FAIL, null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return r(data, RET_FAIL, msg);
    }

    public static <T> R<T> fail(String msg) {
        return r(null, RET_FAIL, msg);
    }

    public static <T> R<T> fail(int ret, String msg) {
        return r(null, ret, msg);
    }

    public static <T> R<T> fail(T data) {
        return r(data, RET_FAIL, RET_FAIL_MSG);
    }

    /**
     *
     * @param data
     * @param ret
     * @param msg
     *
     * @return
     */
    public static <T> R<T> r(T data, int ret, String msg)
    {
        R<T> r = new R<>();

        r.setRet(ret);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
