package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.GroupConfigQueryVO;
import com.wingflare.engine.task.server.web.model.request.GroupConfigRequestVO;
import com.wingflare.engine.task.server.web.model.request.GroupStatusUpdateRequestVO;
import com.wingflare.engine.task.server.web.model.response.GroupConfigResponseVO;
import com.wingflare.engine.task.server.web.service.GroupConfigService;
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


/**
 * 重试组接口
 *
 * @author: opensnail
 * @date : 2021-11-22 14:38
 */
@RestController
@RequestMapping("/group")
public class GroupConfigController {
    private final GroupConfigService groupConfigService;

    public GroupConfigController(GroupConfigService groupConfigService) {
        this.groupConfigService = groupConfigService;
    }

    @RequiresLogin
    @RequiresPermissions("task.group.add")
    @PostMapping("")
    public Boolean addGroup(@RequestBody @Validated GroupConfigRequestVO groupConfigRequestVO) {
        return groupConfigService.addGroup(groupConfigRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.update")
    @PutMapping("")
    public Boolean updateGroup(@RequestBody @Validated GroupConfigRequestVO groupConfigRequestVO) {
        return groupConfigService.updateGroup(groupConfigRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.updateStatus")
    @PutMapping("status")
    public Boolean updateGroupStatus(@RequestBody @Validated GroupStatusUpdateRequestVO requestVO) {
        String groupName = requestVO.getGroupName();
        Integer groupStatus = requestVO.getGroupStatus();
        return groupConfigService.updateGroupStatus(groupName, groupStatus);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.list")
    @GetMapping("list")
    public PageResult<List<GroupConfigResponseVO>> getGroupConfigForPage(GroupConfigQueryVO queryVO) {
        return groupConfigService.getGroupConfigForPage(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.view")
    @GetMapping("{groupName}")
    public GroupConfigResponseVO getGroupConfigByGroupName(@PathVariable("groupName") String groupName) {
        return groupConfigService.getGroupConfigByGroupName(groupName);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.list")
    @PostMapping("/all/group-config/list")
    public List<GroupConfigResponseVO> getAllGroupNameList(@RequestBody List<String> namespaceIds) {
        return groupConfigService.getAllGroupConfigList(namespaceIds);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.list")
    @GetMapping("/all/group-name/list")
    public List<String> getAllGroupNameList() {
        return groupConfigService.getAllGroupNameList();
    }

    @RequiresLogin
    @RequiresPermissions("task.group.onLine")
    @GetMapping("/on-line/pods/{groupName}")
    public List<String> getOnlinePods(@PathVariable("groupName") String groupName) {
        return groupConfigService.getOnlinePods(groupName);
    }

    @RequiresLogin
    @RequiresPermissions("task.group.list")
    @GetMapping("/partition-table/list")
    public List<Integer> getTablePartitionList() {
        return groupConfigService.getTablePartitionList();
    }

    @RequiresLogin
    @RequiresPermissions("task.group.delete")
    @DeleteMapping("{groupName}")
    public boolean deleteByGroupName(@PathVariable("groupName") String groupName) {
        return groupConfigService.deleteByGroupName(groupName);
    }

}
