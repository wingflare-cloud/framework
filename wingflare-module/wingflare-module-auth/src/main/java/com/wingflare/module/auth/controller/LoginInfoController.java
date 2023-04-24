package com.wingflare.module.auth.controller;


import com.wingflare.facade.module.auth.biz.LoginInfoBiz;
import com.wingflare.facade.module.auth.bo.LoginInfoBo;
import com.wingflare.facade.module.auth.bo.LoginInfoSearchBo;
import com.wingflare.facade.module.auth.dto.LoginInfoDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登陆信息Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
@Controller
@RequestMapping("/loginInfo")
public class LoginInfoController
{

	@Resource
    private LoginInfoBiz loginInfoBiz;

    /**
     * 查询登陆信息列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<LoginInfoDto> list(LoginInfoSearchBo bo) throws Throwable
    {
		return loginInfoBiz.list(bo);
    }

	/**
     * 查询登陆信息详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public LoginInfoDto get(IdBo bo)
	{
		return loginInfoBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个登陆信息详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public LoginInfoDto getOnlyOne(LoginInfoSearchBo searchBo) throws Throwable
	{
		return loginInfoBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除登陆信息
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		loginInfoBiz.delete(bo);
	}

	/**
     * 新增登陆信息
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public LoginInfoDto create(@RequestBody LoginInfoBo bo)
	{
		return loginInfoBiz.create(bo);
	}
	
	/**
     * 更新登陆信息
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public LoginInfoDto update(@RequestBody LoginInfoBo bo)
	{
		return loginInfoBiz.update(bo);
	}
	
}
