package com.wingflare.lib.mybatis.plus.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.constants.DataScope;
import com.wingflare.lib.mybatis.plus.wrapper.JoinWrapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 自定义Mapper
 *
 * @author shaoyuyao
 * @date 2022/8/10 14:20
 */
public interface CustomMapper<T> extends BaseMapper<T> {
    /**
     * 根据主键物理删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int forceDeleteById(Serializable id);

    /**
     * 根据主键集合批量物理删除数据
     *
     * @param idList 主键集合
     * @return 影响行数
     */
    int forceDeleteBatchByIds(@Param(Constant.COLL) Collection<?> idList);

    /**
     * 根据Wrapper条件，判断数据是否存在
     *
     * @param queryWrapper 条件
     * @return 返回结果为null表示数据不存在，否则存在
     */
    Object selectExist(@Param(Constant.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据Wrapper条件，判断数据是否存在
     *
     * @param id        主键
     * @param dataScope 数据范围 {@link DataScop}
     * @return 返回结果为null表示数据不存在，否则存在
     */
    Object selectExistById(@Param(Constant.ID) Serializable id,
                           @Param(Constant.DATA_SCOPE) String dataScope);

    /**
     * 根据Wrapper条件，判断数据是否存在
     *
     * @param queryWrapper 条件
     * @return 数据是否存在
     */
    default boolean has(@Param(Constant.WRAPPER) Wrapper<T> queryWrapper) {
        Object exist = selectExist(queryWrapper);
        return exist != null;
    }

    /**
     * 根据主键判断数据是否存在
     *
     * @param id 主键
     * @return 数据是否存在
     */
    default boolean hasById(Serializable id) {
        return hasById(id, DataScope.NORMAL);
    }

    /**
     * 根据主键判断数据是否存在
     *
     * @param id        主键
     * @param dataScope 数据范围 {@link DataScope}
     * @return 数据是否存在
     */
    default boolean hasById(@Param(Constant.ID) Serializable id,
                            @Param(Constant.DATA_SCOPE) String dataScope) {
        Object exist = selectExistById(id, dataScope);
        return exist != null;
    }

    /**
     * 根据主键查询数据
     *
     * @param id 主键
     * @return
     */
    @Override
    default T selectById(Serializable id) {
        return selectById(id, DataScope.NORMAL);
    }

    /**
     * 根据主键查询数据
     *
     * @param id        主键
     * @param dataScope 数据范围 {@link DataScope}
     * @return
     */
    T selectById(@Param(Constant.ID) Serializable id,
                 @Param(Constant.DATA_SCOPE) String dataScope);

    /**
     * 根据Wrapper条件，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 实体对象
     */
    @Override
    T selectOne(@Param(Constant.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据Wrapper条件，查询所有符合条件的记录
     *
     * @param clazz       接收数据的实体Class类型
     * @param joinWrapper
     * @param <Model>
     * @return 所有符合条件的记录列表
     */
    <Model> List<Model> selectJoinList(@Param(Constant.CLAZZ) Class<Model> clazz,
                                       @Param(Constant.WRAPPER) JoinWrapper joinWrapper);

    /**
     * 根据Wrapper条件，查询所有符合条件的记录
     *
     * @param clazz   接收数据的实体Class类型
     * @param <Model>
     * @return 所有符合条件的记录列表
     */
    default <Model> List<Model> selectJoinList(@Param(Constant.CLAZZ) Class<Model> clazz) {
        return selectJoinList(clazz, null);
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
    <P extends IPage<?>, Model> IPage<Model> selectJoinPage(P page,
                                                            @Param(Constant.CLAZZ) Class<Model> clazz,
                                                            @Param(Constants.WRAPPER) JoinWrapper joinWrapper);

    /**
     * 根据Wrapper条件，查询所有符合条件的记录并分页
     *
     * @param page
     * @param clazz   接收数据的实体Class类型
     * @param <P>
     * @param <Model>
     * @return
     */
    default <P extends IPage<?>, Model> IPage<Model> selectJoinPage(P page,
                                                                    @Param(Constant.CLAZZ) Class<Model> clazz) {
        return selectJoinPage(page, clazz, null);
    }
}
