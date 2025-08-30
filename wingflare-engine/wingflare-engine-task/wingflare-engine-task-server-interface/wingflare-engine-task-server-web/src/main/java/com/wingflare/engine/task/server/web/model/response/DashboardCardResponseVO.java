package com.wingflare.engine.task.server.web.model.response;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-11-29
 */
public class DashboardCardResponseVO {

    /**
     * 定时任务
     */
    private JobTask jobTask;

    /**
     * 工作流任务
     */
    private WorkFlowTask workFlowTask;

    /**
     * 重试任务
     */
    private RetryTask retryTask;

    /**
     * 重试任务折线图
     */
    private List<RetryTaskBar> retryTaskBarList;

    /**
     * 在线服务
     */
    private OnLineService onLineService = new OnLineService();

    public JobTask getJobTask() {
        return jobTask;
    }

    public void setJobTask(JobTask jobTask) {
        this.jobTask = jobTask;
    }

    public WorkFlowTask getWorkFlowTask() {
        return workFlowTask;
    }

    public void setWorkFlowTask(WorkFlowTask workFlowTask) {
        this.workFlowTask = workFlowTask;
    }

    public RetryTask getRetryTask() {
        return retryTask;
    }

    public void setRetryTask(RetryTask retryTask) {
        this.retryTask = retryTask;
    }

    public List<RetryTaskBar> getRetryTaskBarList() {
        return retryTaskBarList;
    }

    public void setRetryTaskBarList(List<RetryTaskBar> retryTaskBarList) {
        this.retryTaskBarList = retryTaskBarList;
    }

    public OnLineService getOnLineService() {
        return onLineService;
    }

    public void setOnLineService(OnLineService onLineService) {
        this.onLineService = onLineService;
    }


    public static class OnLineService {
        private Long total;

        private Long clientTotal;

        private Long serverTotal;

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public Long getClientTotal() {
            return clientTotal;
        }

        public void setClientTotal(Long clientTotal) {
            this.clientTotal = clientTotal;
        }

        public Long getServerTotal() {
            return serverTotal;
        }

        public void setServerTotal(Long serverTotal) {
            this.serverTotal = serverTotal;
        }
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


    public static class WorkFlowTask {
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


    public static class RetryTaskBar {
        /**
         * 时间x轴
         */
        private String x;

        /**
         * 任务总数y轴
         */
        private Long taskTotal;

        public String getX() {
            return x;
        }

        public RetryTaskBar setX(String x) {
            this.x = x;
            return this;
        }

        public Long getTaskTotal() {
            return taskTotal;
        }

        public RetryTaskBar setTaskTotal(Long taskTotal) {
            this.taskTotal = taskTotal;
            return this;
        }
    }
}
