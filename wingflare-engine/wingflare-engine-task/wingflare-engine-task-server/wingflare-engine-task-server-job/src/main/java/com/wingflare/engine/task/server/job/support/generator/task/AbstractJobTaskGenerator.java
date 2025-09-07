package com.wingflare.engine.task.server.job.support.generator.task;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.InitializingBean;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-02 13:08:14
 * @since 2.4.0
 */
public abstract class AbstractJobTaskGenerator implements JobTaskGenerator, InitializingBean {

    @Resource
    private JobTaskMapper jobTaskMapper;

    @Resource
    private Environment environment;

    @Override
    public List<JobTask> generate(JobTaskGenerateContext context) {
        return doGenerate(context);
    }

    protected abstract List<JobTask> doGenerate(JobTaskGenerateContext context);

    @Override
    public void afterPropertiesSet() throws Exception {
        JobTaskGeneratorFactory.registerTaskInstance(getTaskInstanceType(), this);
    }

    protected void batchSaveJobTasks(List<JobTask> jobTasks) {
        String url = environment.getProperty("spring.datasource.url");
        DbTypeEnum dbType = DbTypeEnum.modeOf(url);
        // ORACLE 批次插入不能直接返回id，因此此处特殊处理
        // 后期版本会对snail-job-datasource进行重构，在考虑此处的兼容逻辑
        if (Sets.newHashSet(DbTypeEnum.ORACLE.getDb(), DbTypeEnum.SQLSERVER.getDb())
                .contains(dbType.getDb())) {
            // sqlserver oracle 不支持返回批量id,故暂时先这样处理
            for (JobTask jobTask : jobTasks) {
                Assert.isTrue(1 == jobTaskMapper.insert(jobTask), () -> new TaskServerException("Adding new task instance failed"));
            }
        } else {
            Assert.isTrue(jobTasks.size() == jobTaskMapper.insertBatch(jobTasks), () -> new TaskServerException("Adding new task instance failed"));
        }
    }
}
