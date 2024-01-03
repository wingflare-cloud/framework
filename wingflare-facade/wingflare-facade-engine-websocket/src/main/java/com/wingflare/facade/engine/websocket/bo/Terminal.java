package com.wingflare.facade.engine.websocket.bo;

import java.security.Principal;

/**
 * 终端
 * @author naizui
 */
public class Terminal implements Principal {

    /**
     * 端点sn
     */
    private String sn;

    /**
     * 端点指向
     */
    private String point;

    @Override
    public String getName() {
        return this.getSn();
    }

    public void setName(String name) {
        this.setSn(name);
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
