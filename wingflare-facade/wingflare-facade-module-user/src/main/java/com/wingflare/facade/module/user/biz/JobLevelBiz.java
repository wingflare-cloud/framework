package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.JobLevelBO;
import com.wingflare.facade.module.user.bo.JobLevelSearchBO;
import com.wingflare.facade.module.user.dto.JobLevelDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 职级Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
public interface JobLevelBiz {

    /**
     * 查询职级列表
     */
    PageDto<JobLevelDTO> list(JobLevelSearchBO bo);

    /**
     * 查询职级详情
     */
    JobLevelDTO get(IdBo bo);

    /**
     * 通过条件查询单个职级详情
     */
    JobLevelDTO getOnlyOne(JobLevelSearchBO searchBo);

    /**
     * 删除职级
     */
    void delete(IdBo bo);

    /**
     * 新增职级
     */
    JobLevelDTO create(JobLevelBO bo);

    /**
     * 更新职级
     */
    JobLevelDTO update(JobLevelBO bo);

}