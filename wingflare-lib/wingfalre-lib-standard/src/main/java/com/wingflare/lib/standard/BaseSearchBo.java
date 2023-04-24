package com.wingflare.lib.standard;

/**
 * 基础SearchFrom
 *
 * @author shaoyuyao
 * @date 2022/8/25 12:38
 */
public class BaseSearchBo {
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示条数
     */

    private Integer pageSize;

    /**
     * 数据范围
     */
    private String dataScope;

    public Integer getPage() {
        return page;
    }

    public BaseSearchBo setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseSearchBo setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getDataScope() {
        return dataScope;
    }

    public BaseSearchBo setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }
    
}
