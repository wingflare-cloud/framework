package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.common.dto.DistributeInstance;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.DashboardCardResponseVO;
import com.wingflare.engine.task.server.web.model.response.DashboardRetryLineResponseVO;
import com.wingflare.engine.task.server.web.model.response.ServerNodeResponseVO;
import com.wingflare.engine.task.server.web.service.DashboardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;


/**
 * 仪表盘接口
 *
 * @author: opensnail
 * @date : 2022-04-22 20:17
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private DashboardService dashBoardService;

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @GetMapping("/task-retry-job")
    @ResponseBody
    public DashboardCardResponseVO taskRetryJob() {
        return dashBoardService.taskRetryJob();
    }

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @GetMapping("/retry/line")
    @ResponseBody
    public DashboardRetryLineResponseVO retryLineList(LineQueryVO queryVO) {
        return dashBoardService.retryLineList(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @GetMapping("/job/line")
    @ResponseBody
    public DashboardRetryLineResponseVO jobLineList(JobLineQueryVo queryVO) {
        return dashBoardService.jobLineList(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @GetMapping("/pods")
    @ResponseBody
    public PageResult<List<ServerNodeResponseVO>> pods(ServerNodeQueryVO serverNodeQueryVO) {
        return dashBoardService.pods(serverNodeQueryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @PutMapping("/pods/status")
    @ResponseBody
    public Boolean updatePodsStatus(@RequestBody @Validated ServerNodeStatusUpdateRequestVO updateRequestVO) {
        return dashBoardService.updatePodsStatus(updateRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.dashboard.view")
    @PutMapping("/pods/labels")
    @ResponseBody
    public Boolean updatePodsLabels(@RequestBody @Validated ServerNodeLabelsUpdateRequestVO updateRequestVO) {
        return dashBoardService.updatePodsLabels(updateRequestVO);
    }

    @GetMapping("/consumer/bucket")
    @ResponseBody
    public Set<Integer> allConsumerGroupName() {
        return DistributeInstance.INSTANCE.getConsumerBucket();
    }

}
