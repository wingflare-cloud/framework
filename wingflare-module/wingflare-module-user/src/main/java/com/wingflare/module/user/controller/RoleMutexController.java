package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.RoleMutexBiz;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RoleMutexBo;
import com.wingflare.facade.module.user.bo.RoleMutexSearchBo;
import com.wingflare.facade.module.user.dto.RoleMutexDto;
import com.wingflare.lib.standard.PageDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统角色互斥关系Controller
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@Controller
@RequestMapping("/roleMutex")
public class RoleMutexController
{

	@Resource
    private RoleMutexBiz roleMutexBiz;

    /**
     * 查询系统角色互斥关系列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<RoleMutexDto> list(RoleMutexSearchBo bo) throws Throwable
    {
		return roleMutexBiz.list(bo);
    }

	/**
     * 查询系统角色互斥关系详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public RoleMutexDto get(IdBo bo)
	{
		return roleMutexBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统角色互斥关系详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public RoleMutexDto getOnlyOne(RoleMutexSearchBo searchBo) throws Throwable
	{
		return roleMutexBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统角色互斥关系
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		roleMutexBiz.delete(bo);
	}

	/**
     * 新增系统角色互斥关系
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public RoleMutexDto create(@RequestBody RoleMutexBo bo)
	{
		return roleMutexBiz.create(bo);
	}
	
	/**
     * 更新系统角色互斥关系
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public RoleMutexDto update(@RequestBody RoleMutexBo bo)
	{
		return roleMutexBiz.update(bo);
	}
	
}
