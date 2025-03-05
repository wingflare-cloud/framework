package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.JobLevelBiz;
import com.wingflare.facade.module.user.bo.JobLevelBo;
import com.wingflare.facade.module.user.bo.JobLevelSearchBo;
import com.wingflare.facade.module.user.dto.JobLevelDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 职级Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@Controller
@RequestMapping("/jobLevel")
public class JobLevelController
{

	@Resource
    private JobLevelBiz jobLevelBiz;

    /**
     * 查询职级列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<JobLevelDto> list(JobLevelSearchBo bo) throws Throwable
    {
		return jobLevelBiz.list(bo);
    }

	/**
     * 查询职级详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public JobLevelDto get(IdBo bo)
	{
		return jobLevelBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个职级详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public JobLevelDto getOnlyOne(JobLevelSearchBo searchBo) throws Throwable
	{
		return jobLevelBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除职级
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		jobLevelBiz.delete(bo);
	}

	/**
     * 新增职级
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public JobLevelDto create(@RequestBody JobLevelBo bo)
	{
		return jobLevelBiz.create(bo);
	}
	
	/**
     * 更新职级
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public JobLevelDto update(@RequestBody JobLevelBo bo)
	{
		return jobLevelBiz.update(bo);
	}
	
}
