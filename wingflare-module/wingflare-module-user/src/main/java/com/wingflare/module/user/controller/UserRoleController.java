package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.UserRoleBiz;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.RoleUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wingflare.lib.standard.PageDto;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;


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

    @Resource
    private UserRoleBiz userRoleBiz;


    @RequestMapping(value="/list", method={RequestMethod.GET})
    @ResponseBody
    public PageDto<RoleUserDto> list(UserSearchBo bo)
    {
        return userRoleBiz.getUserList(bo);
    }

}
