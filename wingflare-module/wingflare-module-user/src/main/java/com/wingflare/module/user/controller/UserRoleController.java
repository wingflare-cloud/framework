package com.wingflare.module.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.business.user.biz.UserRoleBizImpl;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import com.wingflare.facade.module.user.bo.UserRoleBo;
import com.wingflare.facade.module.user.bo.UserRoleSearchBo;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("userRole")
public class UserRoleController {

    @Resource
    private UserRoleBizImpl userRoleBizImpl;

    @RequestMapping(value="/list", method={RequestMethod.GET})
    @ResponseBody
    public PageDto<UserRoleDto> list(UserRoleSearchBo bo)
    {
        return userRoleBizImpl.list(bo);
    }

    @RequestMapping(value="/get", method={RequestMethod.GET})
    @ResponseBody
    public UserRoleDto get(IdBo bo)
    {
        return userRoleBizImpl.get(bo);
    }

    @RequestMapping(value="/getOnlyOne", method={RequestMethod.GET})
    @ResponseBody
    public UserRoleDto getOnlyOne(UserRoleSearchBo bo)
    {
        return userRoleBizImpl.getOnlyOne(bo);
    }

    @RequestMapping(value="/create", method={RequestMethod.POST})
    @ResponseBody
    public UserRoleDto create(@RequestBody UserRoleBo bo)
    {
         return userRoleBizImpl.create(bo);
    }

    @RequestMapping(value="/update", method={RequestMethod.PUT})
    @ResponseBody
    public UserRoleDto update(@RequestBody UserRoleBo bo)
    {
        return userRoleBizImpl.update(bo);
    }

    @RequestMapping(value="/delete", method={RequestMethod.DELETE})
    @ResponseBody
    public UserRoleDto delete(IdBo bo)
    {
        return userRoleBizImpl.delete(bo);
    }

}
