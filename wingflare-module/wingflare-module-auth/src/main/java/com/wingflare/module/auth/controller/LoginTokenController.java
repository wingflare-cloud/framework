package com.wingflare.module.auth.controller;


import com.wingflare.facade.module.auth.biz.LoginTokenBiz;
import com.wingflare.facade.module.auth.bo.LoginTokenBo;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.facade.module.auth.dto.LoginTokenDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登陆tokenController
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
@Controller
@RequestMapping("/loginToken")
public class LoginTokenController
{

	@Resource
    private LoginTokenBiz loginTokenBiz;

    /**
     * 查询登陆token列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<LoginTokenDto> list(LoginTokenSearchBo bo)
    {
		return loginTokenBiz.list(bo);
    }

	/**
     * 查询登陆token详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public LoginTokenDto get(IdBo bo)
	{
		return loginTokenBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个登陆token详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public LoginTokenDto getOnlyOne(LoginTokenSearchBo searchBo)
	{
		return loginTokenBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除登陆token
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		loginTokenBiz.delete(bo);
	}

	/**
     * 新增登陆token
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public LoginTokenDto create(@RequestBody LoginTokenBo bo)
	{
		return loginTokenBiz.create(bo);
	}
	
	/**
     * 更新登陆token
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public LoginTokenDto update(@RequestBody LoginTokenBo bo)
	{
		return loginTokenBiz.update(bo);
	}
	
}
