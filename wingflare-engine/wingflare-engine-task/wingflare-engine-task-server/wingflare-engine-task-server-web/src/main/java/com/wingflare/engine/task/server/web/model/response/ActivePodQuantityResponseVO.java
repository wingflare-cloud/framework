package com.wingflare.engine.task.server.web.model.response;


/**
 * @author: opensnail
 * @date : 2022-04-23 10:39
 */
public class ActivePodQuantityResponseVO {

    private Long total;

    private Long clientTotal;

    private Long serverTotal;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getClientTotal() {
        return clientTotal;
    }

    public void setClientTotal(Long clientTotal) {
        this.clientTotal = clientTotal;
    }

    public Long getServerTotal() {
        return serverTotal;
    }

    public void setServerTotal(Long serverTotal) {
        this.serverTotal = serverTotal;
    }
}
