package com.wingflare.lib.datascope.entity;


import com.wingflare.lib.security.enums.Logical;

/**
 * @author naizui_ycx
 * @date {2022/1/19}
 * @description
 */
public class LogicalPayload {

    private Logical logical;

    private String type;

    private Object payload;

    public Logical getLogical() {
        return logical;
    }

    public void setLogical(Logical logical) {
        this.logical = logical;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
