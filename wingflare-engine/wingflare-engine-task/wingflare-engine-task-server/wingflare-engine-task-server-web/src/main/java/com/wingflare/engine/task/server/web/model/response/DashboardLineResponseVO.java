package com.wingflare.engine.task.server.web.model.response;


/**
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-11-30
 */
public class DashboardLineResponseVO {

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

    public DashboardLineResponseVO setCreateDt(String createDt) {
        this.createDt = createDt;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public DashboardLineResponseVO setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getSuccessNum() {
        return successNum;
    }

    public DashboardLineResponseVO setSuccessNum(Long successNum) {
        this.successNum = successNum;
        return this;
    }

    public Long getRunningNum() {
        return runningNum;
    }

    public DashboardLineResponseVO setRunningNum(Long runningNum) {
        this.runningNum = runningNum;
        return this;
    }

    public Long getMaxCountNum() {
        return maxCountNum;
    }

    public DashboardLineResponseVO setMaxCountNum(Long maxCountNum) {
        this.maxCountNum = maxCountNum;
        return this;
    }

    public Long getSuspendNum() {
        return suspendNum;
    }

    public DashboardLineResponseVO setSuspendNum(Long suspendNum) {
        this.suspendNum = suspendNum;
        return this;
    }

    public Long getFail() {
        return fail;
    }

    public DashboardLineResponseVO setFail(Long fail) {
        this.fail = fail;
        return this;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public DashboardLineResponseVO setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public Long getFailNum() {
        return failNum;
    }

    public DashboardLineResponseVO setFailNum(Long failNum) {
        this.failNum = failNum;
        return this;
    }

    public Long getStop() {
        return stop;
    }

    public DashboardLineResponseVO setStop(Long stop) {
        this.stop = stop;
        return this;
    }

    public Long getCancel() {
        return cancel;
    }

    public DashboardLineResponseVO setCancel(Long cancel) {
        this.cancel = cancel;
        return this;
    }

    public Long getSuccess() {
        return success;
    }

    public DashboardLineResponseVO setSuccess(Long success) {
        this.success = success;
        return this;
    }
}
