package com.wingflare.lib.mybatis.plus.base;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wingflare.lib.standard.BaseSearchBo;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.constants.DataScope;
import com.wingflare.lib.mybatis.plus.wrapper.JoinWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础Service
 *
 * @author shaoyuyao
 * @date 2022/8/9 17:55
 */
public class BaseService<M extends CustomMapper<T>, T> extends ServiceImpl<M, T> {
    /**
     * 默认每页显示条数
     */
    protected Integer getDefaultPageSize() {
        return 20;
    }

    /**
     * 最大每页显示条数
     */
    protected Integer getMaxPageSize() {
        return 100;
    }

    /**
     * 根据参数创建MyBatisPlus查询入参Page对象
     *
     * @param page     当前页
     * @param pageSize 每页显示条数
     * @return {@link Page} MyBatisPlus查询入参对象
     * @author shaoyuyao
     * @date 2022/8/9 16:22
     */
    public Page createPage(Integer page, Integer pageSize) {
        if (page == null || page < 1) {
            page = 1;
        }

        if (pageSize == null || pageSize > getMaxPageSize()) {
            pageSize = getDefaultPageSize();
        }

        return new Page(page, pageSize);
    }

    /**
     * 根据参数创建MyBatisPlus查询入参Page对象
     *
     * @param from 基础SearchFrom
     * @return {@link Page} MyBatisPlus查询入参对象
     * @author shaoyuyao
     * @date 2022/8/9 16:22
     */
    public Page createPage(BaseSearchBo from) {
        if (from == null) {
            return createPage(null, null);
        }
        return createPage(from.getPage(), from.getPageSize());
    }

    /**
     * 根据主键物理删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    public boolean forceRemoveById(Serializable id) {
        return SqlHelper.retBool(baseMapper.forceDeleteById(id));
    }

    /**
     * 根据主键集合批量物理删除数据
     *
     * @param list 主键集合
     * @return 删除结果
     */
    public boolean forceRemoveBatchByIds(Collection<?> list) {
        return SqlHelper.retBool(baseMapper.forceDeleteBatchByIds(list));
    }

    /**
     * 根据Wrapper条件，判断数据是否存在
     *
     * @param queryWrapper 条件
     * @return 数据是否存在
     */
    public boolean has(Wrapper<T> queryWrapper) {
        return baseMapper.has(queryWrapper);
    }

    /**
     * 根据主键判断数据是否存在
     *
     * @param id 主键
     * @return 数据是否存在
     */
    public boolean hasById(Serializable id) {
        return baseMapper.hasById(id, DataScope.NORMAL);
    }

    /**
     * 根据主键判断数据是否存在
     *
     * @param id        主键
     * @param dataScope 数据范围 {@link DataScope}
     * @return 数据是否存在
     */
    public boolean hasById(Serializable id, String dataScope) {
        return baseMapper.hasById(id, dataScope);
    }

    /**
     * 根据主键查询数据
     *
     * @param id        主键
     */
    @Override
    public T getById(Serializable id) {
        return baseMapper.selectById(id, DataScope.NORMAL);
    }

    /**
     * 根据逐渐更新实体类
     *
     * @param entity 实体类
     *
     * @return
     */
    @Override
    public boolean updateById(T entity) {

        int ret = this.getBaseMapper().updateById(entity);

        if (ret == Constant.NOT_SQL_UPDATE_RET) {
            return true;
        }

        return SqlHelper.retBool(ret);
    }

    /**
     * 根据主键查询数据
     *
     * @param id        主键
     * @param dataScope 数据范围 {@link DataScope}
     */
    public T getById(Serializable id, String dataScope) {
        return baseMapper.selectById(id, dataScope);
    }

    /**
     * 根据Wrapper条件，查询所有符合条件的记录
     *
     * @param clazz       接收数据的实体Class类型
     * @param joinWrapper 连表查询条件对象
     * @param <Model>
     * @return 所有符合条件的记录列表
     */
    public <Model> List<Model> joinList(Class<Model> clazz, JoinWrapper joinWrapper) {
        return baseMapper.selectJoinList(clazz, joinWrapper);
    }

    /**
     * 根据Wrapper条件，查询所有符合条件的记录
     *
     * @param clazz   接收数据的实体Class类型
     * @param <Model>
     * @return 所有符合条件的记录列表
     */
    public <Model> List<Model> joinList(Class<Model> clazz) {
        return joinList(clazz, null);
    }

    /**
     * 根据Wrapper条件，查询所有符合条件的记录并分页
     *
     * @param page
     * @param clazz       接收数据的实体Class类型
     * @param joinWrapper 连表查询条件对象
     * @param <P>
     * @param <Model>
     * @return
     */
    public <P extends IPage<?>, Model> IPage<Model> joinPage(P page, Class<Model> clazz, JoinWrapper joinWrapper) {
        return baseMapper.selectJoinPage(page, clazz, joinWrapper);
    }

    /**
     * 根据Wrapper条件，查询所有符合条件的记录并分页
     *
     * @param page
     * @param clazz   接收数据的实体Class类型
     * @param <P>
     * @param <Model>
     * @return
     */
    public <P extends IPage<?>, Model> IPage<Model> joinPage(P page, Class<Model> clazz) {
        return joinPage(page, clazz, null);
    }

}