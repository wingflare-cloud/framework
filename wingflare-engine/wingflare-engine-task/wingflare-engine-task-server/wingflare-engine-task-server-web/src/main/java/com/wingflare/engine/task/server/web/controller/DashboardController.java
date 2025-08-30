package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.common.dto.DistributeInstance;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.DashboardCardResponseVO;
import com.wingflare.engine.task.server.web.model.response.DashboardRetryLineResponseVO;
import com.wingflare.engine.task.server.web.model.response.ServerNodeResponseVO;
import com.wingflare.engine.task.server.web.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 仪表盘接口
 *
 * @author: opensnail
 * @date : 2022-04-22 20:17
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashBoardService;

    @LoginRequired
    @GetMapping("/task-retry-job")
    public DashboardCardResponseVO taskRetryJob() {
        return dashBoardService.taskRetryJob();
    }

    @LoginRequired
    @GetMapping("/retry/line")
    public DashboardRetryLineResponseVO retryLineList(LineQueryVO queryVO) {
        return dashBoardService.retryLineList(queryVO);
    }

    @LoginRequired
    @GetMapping("/job/line")
    public DashboardRetryLineResponseVO jobLineList(JobLineQueryVo queryVO) {
        return dashBoardService.jobLineList(queryVO);
    }

    @LoginRequired
    @GetMapping("/pods")
    public PageResult<List<ServerNodeResponseVO>> pods(ServerNodeQueryVO serverNodeQueryVO) {
        return dashBoardService.pods(serverNodeQueryVO);
    }

    @LoginRequired
    @PutMapping("/pods/status")
    public Boolean updatePodsStatus(@RequestBody @Validated ServerNodeStatusUpdateRequestVO updateRequestVO) {
        return dashBoardService.updatePodsStatus(updateRequestVO);
    }

    @LoginRequired
    @PutMapping("/pods/labels")
    public Boolean updatePodsLabels(@RequestBody @Validated ServerNodeLabelsUpdateRequestVO updateRequestVO) {
        return dashBoardService.updatePodsLabels(updateRequestVO);
    }

    @GetMapping("/consumer/bucket")
    public Set<Integer> allConsumerGroupName() {
        return DistributeInstance.INSTANCE.getConsumerBucket();
    }

}
