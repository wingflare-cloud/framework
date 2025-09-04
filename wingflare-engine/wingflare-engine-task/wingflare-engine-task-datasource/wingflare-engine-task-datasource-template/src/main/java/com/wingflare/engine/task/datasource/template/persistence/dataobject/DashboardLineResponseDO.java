package com.wingflare.engine.task.datasource.template.persistence.dataobject;


/**
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-11-30
 */
public class DashboardLineResponseDO {

    /**
     * 时间x轴
     */
    private String createDt;

    /**
     * 总量，计算百分比
     */
    private Long total;

    /**
     * 重试-成功数
     */
    private Long successNum;

    /**
     * 重试-运行数
     */
    private Long runningNum;

    /**
     * 重试-最大次数
     */
    private Long maxCountNum;

    /**
     * 重试-暂停数
     */
    private Long suspendNum;

    /**
     * 定时-失败任务
     */
    private Long fail;

    /**
     * 定时-总任务数
     */
    private Long totalNum;

    /**
     * 定时-无效任务数
     */
    private Long failNum;

    /**
     * 定时-停止数
     */
    private Long stop;

    /**
     * 定时-取消数
     */
    private Long cancel;

    /**
     * 定时-成功数
     */
    private Long success;

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Long successNum) {
        this.successNum = successNum;
    }

    public Long getRunningNum() {
        return runningNum;
    }

    public void setRunningNum(Long runningNum) {
        this.runningNum = runningNum;
    }

    public Long getMaxCountNum() {
        return maxCountNum;
    }

    public void setMaxCountNum(Long maxCountNum) {
        this.maxCountNum = maxCountNum;
    }

    public Long getSuspendNum() {
        return suspendNum;
    }

    public void setSuspendNum(Long suspendNum) {
        this.suspendNum = suspendNum;
    }

    public Long getFail() {
        return fail;
    }

    public void setFail(Long fail) {
        this.fail = fail;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getFailNum() {
        return failNum;
    }

    public void setFailNum(Long failNum) {
        this.failNum = failNum;
    }

    public Long getStop() {
        return stop;
    }

    public void setStop(Long stop) {
        this.stop = stop;
    }

    public Long getCancel() {
        return cancel;
    }

    public void setCancel(Long cancel) {
        this.cancel = cancel;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }
}
