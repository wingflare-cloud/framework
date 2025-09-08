package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.OrgDepartmentBiz;
import com.wingflare.facade.module.user.bo.OrgDepartmentBO;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBO;
import com.wingflare.facade.module.user.dto.OrgDepartmentDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 机构部门Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Controller
@RequestMapping("/orgDepartment")
public class OrgDepartmentController
{

    private final OrgDepartmentBiz orgDepartmentBiz;

	public OrgDepartmentController(OrgDepartmentBiz orgDepartmentBiz) {
		this.orgDepartmentBiz = orgDepartmentBiz;
	}

	/**
     * 查询机构部门列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<OrgDepartmentDTO> list(OrgDepartmentSearchBO bo)
    {
		return orgDepartmentBiz.list(bo);
    }

	/**
     * 查询机构部门详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDepartmentDTO get(IdBo bo)
	{
		return orgDepartmentBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个机构部门详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDepartmentDTO getOnlyOne(OrgDepartmentSearchBO searchBo)
	{
		return orgDepartmentBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除机构部门
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		orgDepartmentBiz.delete(bo);
	}

	/**
     * 新增机构部门
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public OrgDepartmentDTO create(@RequestBody OrgDepartmentBO bo)
	{
		return orgDepartmentBiz.create(bo);
	}
	
	/**
     * 更新机构部门
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public OrgDepartmentDTO update(@RequestBody OrgDepartmentBO bo)
	{
		return orgDepartmentBiz.update(bo);
	}
	
}
