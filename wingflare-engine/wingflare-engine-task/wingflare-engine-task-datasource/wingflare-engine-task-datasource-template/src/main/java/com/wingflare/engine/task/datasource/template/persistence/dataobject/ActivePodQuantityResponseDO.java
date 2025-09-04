package com.wingflare.engine.task.datasource.template.persistence.dataobject;


/**
 * @author: xiaowoniu
 * @date : 2022-04-23 10:39
 * @since : 2.5.0
 */
public class ActivePodQuantityResponseDO {

    private Long total;

    private Integer nodeType;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }
}
