package com.wingflare.task.datasource.template.persistence.dataobject.common;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-03-30
 */
public class PageQueryDO {

    /**
     * 当前页码
     */
    private int page = 1;

    /**
     * 每页条数
     */
    private int size = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
