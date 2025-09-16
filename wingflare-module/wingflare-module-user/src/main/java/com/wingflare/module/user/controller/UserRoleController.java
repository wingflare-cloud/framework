package com.wingflare.module.user.controller;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.facade.module.user.biz.UserRoleBiz;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.RoleUserDTO;


/**
 * <p>
 * 系统用户角色表 http控制器
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@Controller
@RequestMapping("/role/user")
public class UserRoleController {

    private final UserRoleBiz userRoleBiz;

    public UserRoleController(UserRoleBiz userRoleBiz) {
        this.userRoleBiz = userRoleBiz;
    }

    @RequestMapping(value="/list", method={RequestMethod.GET})
    @ResponseBody
    public PageDto<RoleUserDTO> list(UserSearchBO bo)
    {
        return userRoleBiz.getUserList(bo);
    }

}
