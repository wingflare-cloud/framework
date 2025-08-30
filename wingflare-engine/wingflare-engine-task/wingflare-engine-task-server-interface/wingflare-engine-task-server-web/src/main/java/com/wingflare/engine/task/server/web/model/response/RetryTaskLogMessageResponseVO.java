package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-28 09:09
 */
public class RetryTaskLogMessageResponseVO {

    private Long id;

    /**
     * 客户端信息
     */
    private String clientInfo;

    private LocalDateTime createDt;

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

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
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
