package com.wingflare.module.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.business.user.biz.JobLevelClassifyBizImpl;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDto;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBo;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * <p>
 * 职级分类表 http控制器
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Controller
@RequestMapping("JobLevelClassify")
public class JobLevelClassifyController {

    @Resource
    private JobLevelClassifyBizImpl jobLevelClassifyBizImpl;

    @RequestMapping(value="/list", method={RequestMethod.GET})
    @ResponseBody
    public PageDto<JobLevelClassifyDto> list(JobLevelClassifySearchBo bo)
    {
        return jobLevelClassifyBizImpl.list(bo);
    }

    @RequestMapping(value="/get", method={RequestMethod.GET})
    @ResponseBody
    public JobLevelClassifyDto get(IdBo bo)
    {
        return jobLevelClassifyBizImpl.get(bo);
    }

    @RequestMapping(value="/getOnlyOne", method={RequestMethod.GET})
    @ResponseBody
    public JobLevelClassifyDto getOnlyOne(JobLevelClassifySearchBo bo)
    {
        return jobLevelClassifyBizImpl.getOnlyOne(bo);
    }

    @RequestMapping(value="/create", method={RequestMethod.POST})
    @ResponseBody
    public JobLevelClassifyDto create(@RequestBody JobLevelClassifyBo bo)
    {
         return jobLevelClassifyBizImpl.create(bo);
    }

    @RequestMapping(value="/update", method={RequestMethod.PUT})
    @ResponseBody
    public JobLevelClassifyDto update(@RequestBody JobLevelClassifyBo bo)
    {
        return jobLevelClassifyBizImpl.update(bo);
    }

    @RequestMapping(value="/delete", method={RequestMethod.DELETE})
    @ResponseBody
    public JobLevelClassifyDto delete(IdBo bo)
    {
        return jobLevelClassifyBizImpl.delete(bo);
    }

}
