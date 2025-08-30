package com.wingflare.engine.task.server.web.model.response;


/**
 * @author: opensnail
 * @date : 2022-04-22 20:27
 */
public class TaskQuantityResponseVO {

    private Long total;

    private Long running;

    private Long finish;

    private Long maxRetryCount;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getRunning() {
        return running;
    }

    public void setRunning(Long running) {
        this.running = running;
    }

    public Long getFinish() {
        return finish;
    }

    public void setFinish(Long finish) {
        this.finish = finish;
    }

    public Long getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(Long maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
}
