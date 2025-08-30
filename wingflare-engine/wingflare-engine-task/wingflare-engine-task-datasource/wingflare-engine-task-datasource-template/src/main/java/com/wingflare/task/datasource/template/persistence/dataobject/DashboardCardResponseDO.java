package com.wingflare.task.datasource.template.persistence.dataobject;


import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-11-29
 */
public class DashboardCardResponseDO {

    /**
     * 定时任务
     */
    private JobTask jobTask;

    /**
     * 重试任务
     */
    private RetryTask retryTask;

    public JobTask getJobTask() {
        return jobTask;
    }

    public void setJobTask(JobTask jobTask) {
        this.jobTask = jobTask;
    }

    public RetryTask getRetryTask() {
        return retryTask;
    }

    public void setRetryTask(RetryTask retryTask) {
        this.retryTask = retryTask;
    }


    public static class RetryTask {

        // 总数
        private Long totalNum;

        // 运行中
        private Long runningNum;

        // 完成
        private Long finishNum;

        // 最大重试次数
        private Long maxCountNum;

        // 暂停重试
        private Long suspendNum;

        // 触发时间
        private LocalDateTime triggerAt;

        public Long getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Long totalNum) {
            this.totalNum = totalNum;
        }

        public Long getRunningNum() {
            return runningNum;
        }

        public void setRunningNum(Long runningNum) {
            this.runningNum = runningNum;
        }

        public Long getFinishNum() {
            return finishNum;
        }

        public void setFinishNum(Long finishNum) {
            this.finishNum = finishNum;
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

        public LocalDateTime getTriggerAt() {
            return triggerAt;
        }

        public void setTriggerAt(LocalDateTime triggerAt) {
            this.triggerAt = triggerAt;
        }
    }


    public static class JobTask {
        //成功
        private Integer successNum;
        //失败
        private Integer failNum;
        //取消
        private Integer cancelNum;
        //停止
        private Integer stopNum;
        // 总数
        private Integer totalNum;
        // 成功率
        private BigDecimal successRate;

        public Integer getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(Integer successNum) {
            this.successNum = successNum;
        }

        public Integer getFailNum() {
            return failNum;
        }

        public void setFailNum(Integer failNum) {
            this.failNum = failNum;
        }

        public Integer getCancelNum() {
            return cancelNum;
        }

        public void setCancelNum(Integer cancelNum) {
            this.cancelNum = cancelNum;
        }

        public Integer getStopNum() {
            return stopNum;
        }

        public void setStopNum(Integer stopNum) {
            this.stopNum = stopNum;
        }

        public Integer getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

        public BigDecimal getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(BigDecimal successRate) {
            this.successRate = successRate;
        }
    }
}
