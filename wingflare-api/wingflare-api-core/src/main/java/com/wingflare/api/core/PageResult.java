package com.wingflare.api.core;

import java.util.List;

public class PageResult<T> {

    private String nextCursorId;

    private List<T> list;

    private boolean hasNext;

    public PageResult() {}

    public PageResult(String nextCursorId, List<T> list, boolean hasNext) {
        this.nextCursorId = nextCursorId;
        this.list = list;
        this.hasNext = hasNext;
    }

    public String getNextCursorId() {
        return nextCursorId;
    }

    public List<T> getList() {
        return list;
    }

    public void setNextCursorId(String nextCursorId) {
        this.nextCursorId = nextCursorId;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHasNext() {
        return hasNext;
    }

}
