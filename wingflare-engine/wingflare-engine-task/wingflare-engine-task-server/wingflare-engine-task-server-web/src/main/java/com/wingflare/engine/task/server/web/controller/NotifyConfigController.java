package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.NotifyConfigQueryVO;
import com.wingflare.engine.task.server.web.model.request.NotifyConfigRequestVO;
import com.wingflare.engine.task.server.web.model.response.NotifyConfigResponseVO;
import com.wingflare.engine.task.server.web.service.NotifyConfigService;
import com.wingflare.engine.task.datasource.template.persistence.po.NotifyConfig;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


/**
 * 通知配置接口
 *
 * @author: opensnail
 * @date : 2022-03-03 11:32
 */
@RestController
@RequestMapping("/notify-config")
public class NotifyConfigController {
    private final NotifyConfigService notifyConfigService;

    public NotifyConfigController(NotifyConfigService notifyConfigService) {
        this.notifyConfigService = notifyConfigService;
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.list")
    @GetMapping("list")
    public PageResult<List<NotifyConfigResponseVO>> getNotifyConfigList(NotifyConfigQueryVO queryVO) {
        return notifyConfigService.getNotifyConfigList(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.list")
    @GetMapping("/all/{systemTaskType}")
    public List<NotifyConfig> getNotifyConfigBySystemTaskTypeList(@PathVariable("systemTaskType") Integer systemTaskType) {
        return notifyConfigService.getNotifyConfigBySystemTaskTypeList(systemTaskType);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.detail")
    @GetMapping("{id}")
    public NotifyConfigResponseVO getNotifyConfigDetail(@PathVariable("id") Long id) {
        return notifyConfigService.getNotifyConfigDetail(id);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.save")
    @PostMapping
    public Boolean saveNotify(@RequestBody @Validated NotifyConfigRequestVO requestVO) {
        return notifyConfigService.saveNotify(requestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.update")
    @PutMapping
    public Boolean updateNotify(@RequestBody @Validated NotifyConfigRequestVO requestVO) {
        return notifyConfigService.updateNotify(requestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.updateStatus")
    @PutMapping("/{id}/status/{status}")
    public Boolean updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        return notifyConfigService.updateStatus(id, status);
    }

    @RequiresLogin
    @RequiresPermissions("task.notify.batchDelete")
    @DeleteMapping("ids")
    public Boolean batchDeleteNotify(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return notifyConfigService.batchDeleteNotify(ids);
    }
}
