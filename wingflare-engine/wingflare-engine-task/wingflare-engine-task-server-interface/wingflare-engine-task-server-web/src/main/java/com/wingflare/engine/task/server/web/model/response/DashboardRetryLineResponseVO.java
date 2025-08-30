package com.wingflare.engine.task.server.web.model.response;

import com.wingflare.engine.task.server.web.model.base.PageResult;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-04-22 20:27
 */
public class DashboardRetryLineResponseVO {

    /**
     * 任务列表
     */
    private PageResult<List<Task>> taskList;

    /**
     * 排名列表
     */
    private List<Rank> rankList;

    /**
     * 折线图列表
     */
    private List<DashboardLineResponseVO> dashboardLineResponseDOList;

    public PageResult<List<Task>> getTaskList() {
        return taskList;
    }

    public void setTaskList(PageResult<List<Task>> taskList) {
        this.taskList = taskList;
    }

    public List<Rank> getRankList() {
        return rankList;
    }

    public void setRankList(List<Rank> rankList) {
        this.rankList = rankList;
    }

    public List<DashboardLineResponseVO> getDashboardLineResponseDOList() {
        return dashboardLineResponseDOList;
    }

    public void setDashboardLineResponseDOList(List<DashboardLineResponseVO> dashboardLineResponseDOList) {
        this.dashboardLineResponseDOList = dashboardLineResponseDOList;
    }


    public static class Task {
        private String groupName;
        private Integer run;
        private Integer total;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Integer getRun() {
            return run;
        }

        public void setRun(Integer run) {
            this.run = run;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }


    public static class Rank {
        private String name;
        private String total;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
