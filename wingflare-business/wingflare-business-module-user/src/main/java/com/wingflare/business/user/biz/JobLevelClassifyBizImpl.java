package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.facade.module.user.biz.JobLevelClassifyBiz;
import com.wingflare.business.user.db.JobLevelClassifyDo;
import com.wingflare.business.user.service.JobLevelClassifyServer;
import com.wingflare.business.user.convert.JobLevelClassifyConvert;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBo;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDto;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBo;
import com.wingflare.business.user.wrapper.JobLevelClassifyWrapper;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * <p>
 * 职级分类表 业务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Component
@Validated
public class JobLevelClassifyBizImpl implements JobLevelClassifyBiz
{

    @Resource
    private JobLevelClassifyServer jobLevelClassifyServer;

    /**
     * 查询jobLevelClassify列表
     */
    public PageDto<JobLevelClassifyDto> list(@Valid JobLevelClassifySearchBo bo)
    {
        IPage<JobLevelClassifyDo> iPage = jobLevelClassifyServer.page(
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
    public JobLevelClassifyDto get(@Valid @NotNull IdBo bo)
    {
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个jobLevelClassify详情
     */
	@Override
    public JobLevelClassifyDto getOnlyOne(@Valid @NotNull JobLevelClassifySearchBo bo)
    {
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyServer.getOne(JobLevelClassifyWrapper.getQueryWrapper(bo)));
    }

    /**
     * 删除jobLevelClassify
     */
	@Override
    public JobLevelClassifyDto delete(@Valid @NotNull IdBo bo)
    {
        JobLevelClassifyDo jobLevelClassifyDo = jobLevelClassifyServer.getById(bo.getId());

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
    public JobLevelClassifyDto create(@Valid @NotNull JobLevelClassifyBo bo)
    {
        checkJobLevelCanSave(bo, null);
        JobLevelClassifyDo jobLevelClassifyDo = JobLevelClassifyConvert.convert.boToDo(bo);
        jobLevelClassifyServer.save(jobLevelClassifyDo);
        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyDo);
    }

    /**
     * 更新jobLevelClassify
     */
	@Override
    @Validated({Default.class, Update.class})
    public JobLevelClassifyDto update(@Valid @NotNull JobLevelClassifyBo bo)
    {
        JobLevelClassifyDo oldJobLevelClassifyDo = jobLevelClassifyServer.getById(bo.getLevelClassifyId());

        if (oldJobLevelClassifyDo == null) {
            throw new DataNotFoundException("jobLevelClassify.data.notfound");
        }

        checkJobLevelCanSave(bo, oldJobLevelClassifyDo);
        JobLevelClassifyDo jobLevelClassifyDo = JobLevelClassifyConvert.convert.boToDo(bo);
        JobLevelClassifyDo oldField = oldJobLevelClassifyDo.setOnNew(jobLevelClassifyDo);

        if (oldField != null) {
            jobLevelClassifyServer.updateById(oldJobLevelClassifyDo);
        }

        return JobLevelClassifyConvert.convert.doToDto(jobLevelClassifyDo);
    }

    /**
     * 确认职级分类是否可保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkJobLevelCanSave(JobLevelClassifyBo bo, JobLevelClassifyDo oldDo) {
        LambdaQueryWrapper<JobLevelClassifyDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.nested(q -> q.eq(StringUtil.isNotEmpty(bo.getClassifyName()), JobLevelClassifyDo::getClassifyName,
                        bo.getClassifyName())
                .or()
                .eq(StringUtil.isNotEmpty(bo.getClassifyCode()), JobLevelClassifyDo::getClassifyCode, bo.getClassifyCode()))
                .ne(oldDo != null, JobLevelClassifyDo::getLevelClassifyId, oldDo.getLevelClassifyId());

        Assert.isTrue(jobLevelClassifyServer.has(queryWrapper), ErrorCode.SYS_JOB_LEVEL_CLASSIFY_EXISTENT);
    }

}