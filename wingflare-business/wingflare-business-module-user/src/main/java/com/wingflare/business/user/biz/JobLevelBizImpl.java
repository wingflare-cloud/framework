package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.JobLevelConvert;
import com.wingflare.business.user.db.JobLevelDO;
import com.wingflare.business.user.service.JobLevelClassifyServer;
import com.wingflare.business.user.service.JobLevelServer;
import com.wingflare.business.user.wrapper.JobLevelWrapper;
import com.wingflare.facade.module.user.biz.JobLevelBiz;
import com.wingflare.facade.module.user.bo.JobLevelBO;
import com.wingflare.facade.module.user.bo.JobLevelSearchBO;
import com.wingflare.facade.module.user.dto.JobLevelDTO;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


/**
 * 职级Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@Validated
public class JobLevelBizImpl implements JobLevelBiz {

    private final JobLevelServer jobLevelServer;

    private final JobLevelClassifyServer jobLevelClassifyServer;

    public JobLevelBizImpl(JobLevelServer jobLevelServer, JobLevelClassifyServer jobLevelClassifyServer) {
        this.jobLevelServer = jobLevelServer;
        this.jobLevelClassifyServer = jobLevelClassifyServer;
    }

    /**
     * 查询职级列表
     */
    @Override
    public PageDto<JobLevelDTO> list(@Valid JobLevelSearchBO bo) {
        IPage<JobLevelDO> iPage = jobLevelServer.page(
                jobLevelServer.createPage(bo),
                JobLevelWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                JobLevelConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询职级详情
     */
    @Override
    public JobLevelDTO get(@Valid @NotNull IdBo bo) {
        return JobLevelConvert.convert.doToDto(
                jobLevelServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个职级详情
     */
    @Override
    public JobLevelDTO getOnlyOne(@Valid @NotNull JobLevelSearchBO searchBo) {
        return JobLevelConvert.convert.doToDto(
                jobLevelServer.getOne(
                        JobLevelWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除职级
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        JobLevelDO jobLevelDo = jobLevelServer.getById(bo.getId());

        if (jobLevelDo != null) {
            jobLevelServer.removeById(bo.getId());
        }
    }

    /**
     * 新增职级
     */
    @Override
    @Validated({Default.class, Create.class})
    public JobLevelDTO create(@Valid @NotNull JobLevelBO bo) {
        checkJobLevelCanSave(bo, null);
        JobLevelDO jobLevelDo = JobLevelConvert.convert.boToDo(bo);
        jobLevelServer.save(jobLevelDo);
        return JobLevelConvert.convert.doToDto(jobLevelDo);
    }

    /**
     * 更新职级
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public JobLevelDTO update(@Valid @NotNull JobLevelBO bo) {
        JobLevelDO oldJobLevelDO = jobLevelServer.getById(bo.getJobLevelId());

        if (oldJobLevelDO == null) {
            throw new DataNotFoundException("jobLevel.data.notfound");
        }

        checkJobLevelCanSave(bo, oldJobLevelDO);
        JobLevelDO jobLevelDo = JobLevelConvert.convert.boToDo(bo);
        oldJobLevelDO.setOnNew(jobLevelDo);
        jobLevelServer.updateById(oldJobLevelDO);
        return JobLevelConvert.convert.doToDto(jobLevelDo);
    }

    /**
     * 确定职级是否允许保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkJobLevelCanSave(JobLevelBO bo, JobLevelDO oldDo) {
        JobLevelSearchBO searchBo = new JobLevelSearchBO()
                .setEq_levelName(bo.getLevelName());

        if (oldDo != null) {
            searchBo.setNeq_jobLevelId(oldDo.getJobLevelId());
        }

        Assert.isTrue(jobLevelClassifyServer.hasById(bo.getLevelClassifyId()), ErrorCode.SYS_JOB_LEVEL_CLASSIFY_NON_EXISTENT);
        Assert.isFalse(has(searchBo), ErrorCode.SYS_JOB_LEVEL_NAME_REPEAT);
    }

    /**
     * 判断是否存在符合条件的职级
     *
     * @param bo 查询参数
     * @return 职级
     */
    public boolean has(JobLevelSearchBO bo) {
        return jobLevelServer.has(
                JobLevelWrapper.getQueryWrapper(bo)
        );
    }

}