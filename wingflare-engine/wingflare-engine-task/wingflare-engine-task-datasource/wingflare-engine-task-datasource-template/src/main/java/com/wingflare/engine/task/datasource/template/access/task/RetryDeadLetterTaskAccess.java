package com.wingflare.engine.task.datasource.template.access.task;

import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.engine.task.datasource.template.enums.OperationTypeEnum;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryDeadLetterMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryDeadLetter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-08-06 10:18:02
 * @since 2.2.0
 */
@Component
public class RetryDeadLetterTaskAccess extends AbstractTaskAccess<RetryDeadLetter> {

    @Resource
    private RetryDeadLetterMapper retryDeadLetterMapper;

    @Override
    public boolean supports(String operationType) {
        DbTypeEnum dbType = getDbType();
        return OperationTypeEnum.RETRY_DEAD_LETTER.name().equals(operationType)
                && ALLOW_DB.contains(dbType.getDb());
    }

    @Override
    protected int doUpdate(RetryDeadLetter retryDeadLetter, LambdaUpdateWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.update(retryDeadLetter, query);
    }

    @Override
    protected int doInsertBatch(List<RetryDeadLetter> list) {
        return retryDeadLetterMapper.insertBatch(list);
    }

    @Override
    protected RetryDeadLetter doOne(LambdaQueryWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.selectOne(query);
    }

    @Override
    protected PageDTO<RetryDeadLetter> doListPage(final PageDTO<RetryDeadLetter> PageDTO,
                                                  final LambdaQueryWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.selectPage(PageDTO, query);
    }

    @Override
    protected long doCount(final LambdaQueryWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.selectCount(query);
    }

    @Override
    protected int doInsert(RetryDeadLetter retryDeadLetter) {
        return retryDeadLetterMapper.insert(retryDeadLetter);
    }

    @Override
    protected int doDelete(LambdaQueryWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.delete(query);
    }

    @Override
    protected int doUpdateById(RetryDeadLetter retryDeadLetter) {
        return retryDeadLetterMapper.updateById(retryDeadLetter);
    }

    @Override
    protected List<RetryDeadLetter> doList(LambdaQueryWrapper<RetryDeadLetter> query) {
        return retryDeadLetterMapper.selectList(query);
    }
}
