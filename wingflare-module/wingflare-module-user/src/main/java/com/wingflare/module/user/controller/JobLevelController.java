package com.wingflare.module.user.controller;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestBody;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.facade.module.user.biz.JobLevelBiz;
import com.wingflare.facade.module.user.bo.JobLevelBO;
import com.wingflare.facade.module.user.bo.JobLevelSearchBO;
import com.wingflare.facade.module.user.dto.JobLevelDTO;
import com.wingflare.lib.standard.bo.IdBo;


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

    private final JobLevelBiz jobLevelBiz;

	public JobLevelController(JobLevelBiz jobLevelBiz) {
		this.jobLevelBiz = jobLevelBiz;
	}

	/**
     * 查询职级列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<JobLevelDTO> list(JobLevelSearchBO bo) throws Throwable
    {
		return jobLevelBiz.list(bo);
    }

	/**
     * 查询职级详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public JobLevelDTO get(IdBo bo)
	{
		return jobLevelBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个职级详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public JobLevelDTO getOnlyOne(JobLevelSearchBO searchBo) throws Throwable
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
	public JobLevelDTO create(@RequestBody JobLevelBO bo)
	{
		return jobLevelBiz.create(bo);
	}
	
	/**
     * 更新职级
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public JobLevelDTO update(@RequestBody JobLevelBO bo)
	{
		return jobLevelBiz.update(bo);
	}
	
}
