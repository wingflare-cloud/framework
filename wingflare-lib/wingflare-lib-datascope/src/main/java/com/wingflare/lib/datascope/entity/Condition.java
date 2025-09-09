package com.wingflare.lib.datascope.entity;


import com.wingflare.api.security.enums.Logical;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2022/1/19}
 * @description
 */
public class Condition {

    private String name;

    private Logical logical;

    private String filterExp;

    private List<LogicalPayload> payloadList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Logical getLogical() {
        return logical;
    }

    public void setLogical(Logical logical) {
        this.logical = logical;
    }

    public List<LogicalPayload> getPayloadList() {
        return payloadList;
    }

    public void setPayloadList(List<LogicalPayload> payloadList) {
        this.payloadList = payloadList;
    }

    public String getFilterExp() {
        return filterExp;
    }

    public void setFilterExp(String filterExp) {
        this.filterExp = filterExp;
    }
}
