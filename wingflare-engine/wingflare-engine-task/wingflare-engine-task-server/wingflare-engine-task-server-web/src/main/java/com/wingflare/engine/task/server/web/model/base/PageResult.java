package com.wingflare.engine.task.server.web.model.base;

import com.wingflare.engine.task.common.core.model.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

/**
 * @author: opensnail
 * @date : 2022-02-16 14:07
 */
public class PageResult<T> extends Result<T> {

    private long page;
    private long size;
    private long total;

    public PageResult(int status, String message, T data) {
        super(status, message, data);
    }

    public PageResult() {
        super();
    }

    public PageResult(long page, long size, long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResult(int status, String message, T data, long page, long size, long total) {
        super(status, message, data);
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResult(T data, long page, long size, long total) {
        super(data);
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResult(String message, T data, long page, long size, long total) {
        super(message, data);
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResult(int status, String message, long page, long size, long total) {
        super(status, message);
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResult(PageDTO pageDTO, T data) {
        page = pageDTO.getCurrent();
        size = pageDTO.getSize();
        total = pageDTO.getTotal();
        super.setData(data);
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
