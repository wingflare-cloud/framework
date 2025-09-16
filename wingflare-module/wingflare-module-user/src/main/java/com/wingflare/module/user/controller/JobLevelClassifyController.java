package com.wingflare.module.user.controller;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestBody;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.business.user.biz.JobLevelClassifyBizImpl;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBO;


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

    private final JobLevelClassifyBizImpl jobLevelClassifyBizImpl;

    public JobLevelClassifyController(JobLevelClassifyBizImpl jobLevelClassifyBizImpl) {
        this.jobLevelClassifyBizImpl = jobLevelClassifyBizImpl;
    }

    @RequestMapping(value="/list", method={RequestMethod.GET})
    @ResponseBody
    public PageDto<JobLevelClassifyDTO> list(JobLevelClassifySearchBO bo)
    {
        return jobLevelClassifyBizImpl.list(bo);
    }

    @RequestMapping(value="/get", method={RequestMethod.GET})
    @ResponseBody
    public JobLevelClassifyDTO get(IdBo bo)
    {
        return jobLevelClassifyBizImpl.get(bo);
    }

    @RequestMapping(value="/getOnlyOne", method={RequestMethod.GET})
    @ResponseBody
    public JobLevelClassifyDTO getOnlyOne(JobLevelClassifySearchBO bo)
    {
        return jobLevelClassifyBizImpl.getOnlyOne(bo);
    }

    @RequestMapping(value="/create", method={RequestMethod.POST})
    @ResponseBody
    public JobLevelClassifyDTO create(@RequestBody JobLevelClassifyBO bo)
    {
         return jobLevelClassifyBizImpl.create(bo);
    }

    @RequestMapping(value="/update", method={RequestMethod.PUT})
    @ResponseBody
    public JobLevelClassifyDTO update(@RequestBody JobLevelClassifyBO bo)
    {
        return jobLevelClassifyBizImpl.update(bo);
    }

    @RequestMapping(value="/delete", method={RequestMethod.DELETE})
    @ResponseBody
    public JobLevelClassifyDTO delete(IdBo bo)
    {
        return jobLevelClassifyBizImpl.delete(bo);
    }

}
