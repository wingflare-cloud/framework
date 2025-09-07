package com.wingflare.engine.task.datasource.template.access.job;

import com.wingflare.engine.task.datasource.template.access.JobAccess;
import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.engine.task.datasource.template.enums.OperationTypeEnum;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobExecutorMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author：srzou
 * @Package：com.wingflare.task.datasource.template.access.job
 * @Project：wingflare-task
 * @Date：2025/6/3 9:51
 * @Filename：JobExecutorAccess
 */
@Component
public class JobExecutorAccess implements JobAccess<JobExecutor> {

    @Resource
    private JobExecutorMapper jobExecutorMapper;
    @Resource
    private Environment env;

    @Override
    public boolean supports(String operationType) {
        String url = env.getProperty("spring.datasource.url");
        return DbTypeEnum.all().contains(DbTypeEnum.modeOf(url)) && OperationTypeEnum.JOB_EXECUTORS.name().equals(operationType);
    }

    @Override
    public int insert(JobExecutor jobExecutor) {
        return jobExecutorMapper.insert(jobExecutor);
    }

    @Override
    public int insertBatch(List<JobExecutor> list) {
        return jobExecutorMapper.insertBatch(list);
    }

    @Override
    public PageDTO<JobExecutor> listPage(PageDTO<JobExecutor> queryDO, LambdaQueryWrapper<JobExecutor> query) {
        return jobExecutorMapper.selectPage(queryDO, query);
    }


    @Override
    public List<JobExecutor> list(LambdaQueryWrapper<JobExecutor> query) {
        return jobExecutorMapper.selectList(query);
    }

    @Override
    public JobExecutor one(LambdaQueryWrapper<JobExecutor> query) {
        return jobExecutorMapper.selectOne(query);
    }

    @Override
    public int update(JobExecutor jobExecutor, LambdaUpdateWrapper<JobExecutor> query) {
        return jobExecutorMapper.update(jobExecutor, query);
    }

    @Override
    public int updateById(JobExecutor jobExecutor) {
        return jobExecutorMapper.updateById(jobExecutor);
    }

    @Override
    public int delete(LambdaQueryWrapper<JobExecutor> query) {
        return jobExecutorMapper.delete(query);
    }

    @Override
    public long count(LambdaQueryWrapper<JobExecutor> query) {
        return jobExecutorMapper.selectCount(query);
    }
}
