package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.db.JobLevelClassifyDO;
import com.wingflare.facade.module.user.biz.JobLevelClassifyBiz;
import com.wingflare.business.user.service.JobLevelClassifyServer;
import com.wingflare.business.user.convert.JobLevelClassifyConvert;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBO;
import com.wingflare.business.user.wrapper.JobLevelClassifyWrapper;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

/**
 * <p>
 * 职级分类表 业务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Validated
public class JobLevelClassifyBizImpl implements JobLevelClassifyBiz {

    private final JobLevelClassifyServer jobLevelClassifyServer;

    public JobLevelClassifyBizImpl(JobLevelClassifyServer jobLevelClassifyServer) {
        this.jobLevelClassifyServer = jobLevelClassifyServer;
    }

    /**
     * 查询jobLevelClassify列表
     */
    public PageDto<JobLevelClassifyDTO> list(@Valid JobLevelClassifySearchBO bo) {
        IPage<JobLevelClassifyDO> iPage = jobLevelClassifyServer.page(
                jobLevelClassifyServer.createPage(bo),
                JobLevelClassifyWrapper.getQueryWrapper(bo)
        );
        return PageUtil.convertIPage(iPage,
                JobLevelClassifyConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询${functionName}详情
     */
    @Override
    public JobLevelClassifyDTO get(@Valid @NotNull IdBo bo) {
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个jobLevelClassify详情
     */
    @Override
    public JobLevelClassifyDTO getOnlyOne(@Valid @NotNull JobLevelClassifySearchBO bo) {
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyServer.getOne(JobLevelClassifyWrapper.getQueryWrapper(bo)));
    }

    /**
     * 删除jobLevelClassify
     */
    @Override
    public JobLevelClassifyDTO delete(@Valid @NotNull IdBo bo) {
        JobLevelClassifyDO jobLevelClassifyDo = jobLevelClassifyServer.getById(bo.getId());

        if (jobLevelClassifyDo != null) {
            jobLevelClassifyServer.removeById(bo.getId());
            return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyDo);
        }

        return null;
    }

    /**
     * 新增jobLevelClassify
     */
    @Override
    @Validated({Default.class, Create.class})
    public JobLevelClassifyDTO create(@Valid @NotNull JobLevelClassifyBO bo) {
        checkJobLevelCanSave(bo, null);
        JobLevelClassifyDO jobLevelClassifyDo = JobLevelClassifyConvert.convert.boToDo(bo);
        jobLevelClassifyServer.save(jobLevelClassifyDo);
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyDo);
    }

    /**
     * 更新jobLevelClassify
     */
    @Override
    @Validated({Default.class, Update.class})
    public JobLevelClassifyDTO update(@Valid @NotNull JobLevelClassifyBO bo) {
        JobLevelClassifyDO oldJobLevelClassifyDO = jobLevelClassifyServer.getById(bo.getLevelClassifyId());

        if (oldJobLevelClassifyDO == null) {
            throw new DataNotFoundException("jobLevelClassify.data.notfound");
        }

        checkJobLevelCanSave(bo, oldJobLevelClassifyDO);
        JobLevelClassifyDO jobLevelClassifyDo = JobLevelClassifyConvert.convert.boToDo(bo);
        JobLevelClassifyDO oldField = oldJobLevelClassifyDO.setOnNew(jobLevelClassifyDo);

        if (oldField != null) {
            jobLevelClassifyServer.updateById(oldJobLevelClassifyDO);
        }

        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyDo);
    }

    /**
     * 确认职级分类是否可保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkJobLevelCanSave(JobLevelClassifyBO bo, JobLevelClassifyDO oldDo) {
        LambdaQueryWrapper<JobLevelClassifyDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.nested(q -> q.eq(StringUtil.isNotEmpty(bo.getClassifyName()), JobLevelClassifyDO::getClassifyName,
                                bo.getClassifyName())
                        .or()
                        .eq(StringUtil.isNotEmpty(bo.getClassifyCode()), JobLevelClassifyDO::getClassifyCode, bo.getClassifyCode()))
                .ne(oldDo != null, JobLevelClassifyDO::getLevelClassifyId, oldDo.getLevelClassifyId());

        Assert.isTrue(jobLevelClassifyServer.has(queryWrapper), ErrorCode.SYS_JOB_LEVEL_CLASSIFY_EXISTENT);
    }

}