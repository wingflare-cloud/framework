package com.wingflare.engine.task.server.common.vo;


import java.util.List;

/**
 * @author: opensnail
 * @date : 2023-10-12 11:22
 * @since : 2.4.0
 */
@Deprecated
public class JobLogResponseVO {

    private Long id;

    private Long nextStartId;

    private List message;

    private boolean isFinished;

    private Integer fromIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNextStartId() {
        return nextStartId;
    }

    public void setNextStartId(Long nextStartId) {
        this.nextStartId = nextStartId;
    }

    public List getMessage() {
        return message;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Integer getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(Integer fromIndex) {
        this.fromIndex = fromIndex;
    }
}
