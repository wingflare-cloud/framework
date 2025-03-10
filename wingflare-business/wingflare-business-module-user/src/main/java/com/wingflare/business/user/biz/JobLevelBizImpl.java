package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.JobLevelConvert;
import com.wingflare.business.user.db.JobLevelDo;
import com.wingflare.business.user.service.JobLevelClassifyServer;
import com.wingflare.business.user.service.JobLevelServer;
import com.wingflare.business.user.wrapper.JobLevelWrapper;
import com.wingflare.facade.module.user.biz.JobLevelBiz;
import com.wingflare.facade.module.user.bo.JobLevelBo;
import com.wingflare.facade.module.user.bo.JobLevelSearchBo;
import com.wingflare.facade.module.user.dto.JobLevelDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 职级Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@Component
@Validated
public class JobLevelBizImpl implements JobLevelBiz {

    @Resource
    private JobLevelServer jobLevelServer;

    @Resource
    private JobLevelClassifyServer jobLevelClassifyServer;

    /**
     * 查询职级列表
     */
    @Override
    public PageDto<JobLevelDto> list(@Valid JobLevelSearchBo bo) {
        IPage<JobLevelDo> iPage = jobLevelServer.page(
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
    public JobLevelDto get(@Valid @NotNull IdBo bo) {
        return JobLevelConvert.convert.doToDto(
                jobLevelServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个职级详情
     */
    @Override
    public JobLevelDto getOnlyOne(@Valid @NotNull JobLevelSearchBo searchBo) {
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
        JobLevelDo jobLevelDo = jobLevelServer.getById(bo.getId());

        if (jobLevelDo != null) {
            jobLevelServer.removeById(bo.getId());
        }
    }

    /**
     * 新增职级
     */
    @Override
    @Validated({Default.class, Create.class})
    public JobLevelDto create(@Valid @NotNull JobLevelBo bo) {
        checkJobLevelCanSave(bo, null);
        JobLevelDo jobLevelDo = JobLevelConvert.convert.boToDo(bo);
        jobLevelServer.save(jobLevelDo);
        return JobLevelConvert.convert.doToDto(jobLevelDo);
    }

    /**
     * 更新职级
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public JobLevelDto update(@Valid @NotNull JobLevelBo bo) {
        JobLevelDo oldJobLevelDo = jobLevelServer.getById(bo.getJobLevelId());

        if (oldJobLevelDo == null) {
            throw new DataNotFoundException("jobLevel.data.notfound");
        }

        checkJobLevelCanSave(bo, oldJobLevelDo);
        JobLevelDo jobLevelDo = JobLevelConvert.convert.boToDo(bo);
        oldJobLevelDo.setOnNew(jobLevelDo);
        jobLevelServer.updateById(oldJobLevelDo);
        return JobLevelConvert.convert.doToDto(jobLevelDo);
    }

    /**
     * 确定职级是否允许保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkJobLevelCanSave(JobLevelBo bo, JobLevelDo oldDo) {
        JobLevelSearchBo searchBo = new JobLevelSearchBo()
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
    public boolean has(JobLevelSearchBo bo) {
        return jobLevelServer.has(
                JobLevelWrapper.getQueryWrapper(bo)
        );
    }

}