package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.annotation.LoginUser;
import com.wingflare.engine.task.server.web.annotation.RoleEnum;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.SystemUpdateUserPasswordRequestVO;
import com.wingflare.engine.task.server.web.model.request.SystemUserQueryVO;
import com.wingflare.engine.task.server.web.model.request.SystemUserRequestVO;
import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.model.response.PermissionsResponseVO;
import com.wingflare.engine.task.server.web.model.response.SystemUserResponseVO;
import com.wingflare.engine.task.server.web.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户表 前端控制器
 *
 * @author opensnail
 * @since 2022-03-05
 */
@RestController
public class SystemUserController {

    private static final Long SUPER_ADMIN_ID = 1L;
    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PostMapping("/auth/login")
    public SystemUserResponseVO login(@RequestBody SystemUserRequestVO requestVO) {
        return systemUserService.login(requestVO);
    }

    @LoginRequired
    @GetMapping("/user/info")
    public SystemUserResponseVO getUserInfo(@LoginUser UserSessionVO systemUser) {
        return systemUserService.getUserInfo(systemUser);
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @PostMapping("/user")
    public void addUser(@RequestBody @Valid SystemUserRequestVO requestVO) {
        systemUserService.addUser(requestVO);
    }

    @LoginRequired(role = RoleEnum.USER)
    @GetMapping("/user/simple/list")
    public List<SystemUserResponseVO> getSystemUserList() {
        return systemUserService.getSystemUserList();
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @GetMapping("/user/page/list")
    public PageResult<List<SystemUserResponseVO>> getSystemUserPageList(SystemUserQueryVO systemUserQueryVO) {
        return systemUserService.getSystemUserPageList(systemUserQueryVO);
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @PutMapping("/user")
    public void update(@RequestBody @Valid SystemUserRequestVO requestVO) {
        // 1. 超级管理员(id=1)不能变更为普通用户
        if (SUPER_ADMIN_ID.equals(requestVO.getId()) && RoleEnum.isUser(requestVO.getRole())) {
            throw new SnailJobServerException("Super admin role cannot be modified");
        }
        systemUserService.update(requestVO);
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @GetMapping("/user/username/user-info")
    public SystemUserResponseVO getSystemUserByUserName(@RequestParam("username") String username) {
        return systemUserService.getSystemUserByUserName(username);
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @GetMapping("/user-permissions/{id}")
    public List<PermissionsResponseVO> getSystemUserPermissionByUserName(@PathVariable("id") Long id) {
        return systemUserService.getSystemUserPermissionByUserName(id);
    }

    @LoginRequired(role = RoleEnum.ADMIN)
    @DeleteMapping("/user/{id}")
    public boolean delUser(@PathVariable("id") Long id) {
        if (SUPER_ADMIN_ID.equals(id)) {
            throw new SnailJobServerException("Super admin cannot be deleted");
        }
        return systemUserService.delUser(id);
    }

    @LoginRequired
    @PutMapping("/update-user-password")
    public void updateUserPassword(@RequestBody @Valid SystemUpdateUserPasswordRequestVO requestVO) {
        systemUserService.updateUserPassword(requestVO);
    }

}
