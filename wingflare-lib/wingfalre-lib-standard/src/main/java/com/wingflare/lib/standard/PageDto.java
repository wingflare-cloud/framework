package com.wingflare.lib.standard;


import java.io.Serializable;
import java.util.List;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 数据翻页模型
 */
public class PageDto<T> implements Serializable{
    private int page = 1;
    private int pageSize = 0;
    private List<T> list;
    private long size = 0;
    private int nowSize = 0;
    private Object attach;

    public PageDto() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getNowSize() {
        return nowSize;
    }

    public void setNowSize(int nowSize) {
        this.nowSize = nowSize;
    }

    public Object getAttach() {
        return attach;
    }

    public void setAttach(Object attach) {
        this.attach = attach;
    }
}
