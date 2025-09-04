package com.wingflare.engine.task.datasource.template.access;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.DeleteQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.ListQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.OneQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.UpdateQueryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-03-29
 */
public interface LogAccess<T> extends Access<T> {

    int insert(T t);

    int insertBatch(List<T> list);

    PageResponseDO<T> listPage(PageQueryDO queryDO);

    List<T> list(ListQueryDO queryDO);

    T one(OneQueryDO query);

    int update(T t, UpdateQueryDO query);

    int updateById(T t);

    int deleteById(Serializable id);

    int delete(DeleteQueryDO query);

    long count(LambdaQueryWrapper<T> query);

}
