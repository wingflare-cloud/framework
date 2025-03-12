/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wingflare.lib.mybatis.plus.plugin;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.toolkit.PropertyMapper;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.injector.JoinResultType;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author miemie
 * @since 3.4.0
 */
@SuppressWarnings({"rawtypes"})
@Intercepts(
        {
                @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
                @Signature(type = StatementHandler.class, method = "getBoundSql", args = {}),
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class MybatisPlusInterceptor implements Interceptor {

    private List<InnerInterceptor> interceptors = new ArrayList<>();

    /**
     * 连表查询方法使用的MappedStatement缓存
     */
    private static final Map<String, Map<Configuration, MappedStatement>> JOIN_MS_CACHE = new ConcurrentHashMap<>();

    private static final List<ResultMapping> EMPTY_RESULT_MAPPING_LIST = new ArrayList<>(0);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (CollectionUtil.isNotEmpty(interceptors)) {
            Object target = invocation.getTarget();
            Object[] args = invocation.getArgs();
            if (target instanceof Executor) {
                final Executor executor = (Executor) target;
                Object parameter = args[1];
                boolean isUpdate = args.length == 2;
                MappedStatement ms = (MappedStatement) args[0];
                if (!isUpdate && ms.getSqlCommandType() == SqlCommandType.SELECT) {
                    ms = newMappedStatement(parameter, ms);

                    RowBounds rowBounds = (RowBounds) args[2];
                    ResultHandler resultHandler = (ResultHandler) args[3];
                    BoundSql boundSql;
                    if (args.length == 4) {
                        boundSql = ms.getBoundSql(parameter);
                    } else {
                        // 几乎不可能走进这里面,除非使用Executor的代理对象调用query[args[6]]
                        boundSql = (BoundSql) args[5];
                    }
                    for (InnerInterceptor query : interceptors) {
                        if (!query.willDoQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql)) {
                            return Collections.emptyList();
                        }
                        query.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
                    }
                    CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
                    return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
                } else if (isUpdate) {
                    for (InnerInterceptor update : interceptors) {
                        if (!update.willDoUpdate(executor, ms, parameter)) {
                            return Constant.NOT_SQL_UPDATE_RET;
                        }
                        update.beforeUpdate(executor, ms, parameter);
                    }
                }
            } else {
                // StatementHandler
                final StatementHandler sh = (StatementHandler) target;
                // 目前只有StatementHandler.getBoundSql方法args才为null
                if (null == args) {
                    for (InnerInterceptor innerInterceptor : interceptors) {
                        innerInterceptor.beforeGetBoundSql(sh);
                    }
                } else {
                    Connection connections = (Connection) args[0];
                    Integer transactionTimeout = (Integer) args[1];
                    for (InnerInterceptor innerInterceptor : interceptors) {
                        innerInterceptor.beforePrepare(sh, connections, transactionTimeout);
                    }
                }
            }
        }

        return invocation.proceed();
    }

    /**
     * 创建一个MappedStatement对象
     */
    public MappedStatement newMappedStatement(Object parameter, MappedStatement ms) {
        if (parameter == null) {
            return ms;
        }

        Map parameterMap = parameter instanceof Map ? (Map) parameter : ObjectUtil.objectToMap(parameter);
        if (!parameterMap.containsKey(Constant.CLAZZ)) {
            return ms;
        }

        Class<?> clazz = (Class<?>) parameterMap.get(Constant.CLAZZ);

        if (clazz == null) {
            return ms;
        }

        List<ResultMap> resultMaps = ms.getResultMaps();

        if (CollectionUtils.isNotEmpty(resultMaps) && resultMaps.get(0).getType() == JoinResultType.class) {
            return newMappedStatement(ms, clazz);
        }

        return ms;
    }

    /**
     * 创建一个MappedStatement对象
     */
    public MappedStatement newMappedStatement(MappedStatement originalMs, Class<?> resultType) {
        String id = originalMs.getId() + Constant.DASH + resultType.getName();
        Map<Configuration, MappedStatement> msMap = JOIN_MS_CACHE.get(id);
        if (CollectionUtil.isNotEmpty(msMap)) {
            MappedStatement ms = msMap.get(originalMs.getConfiguration());
            if (ms != null) {
                return ms;
            }
        }

        MappedStatement.Builder msBuilder = new MappedStatement.Builder(
                originalMs.getConfiguration(),
                originalMs.getId(),
                originalMs.getSqlSource(),
                originalMs.getSqlCommandType()
        );

        msBuilder
                .resource(originalMs.getResource())
                .fetchSize(originalMs.getFetchSize())
                .timeout(originalMs.getTimeout())
                .statementType(originalMs.getStatementType())
                .keyGenerator(originalMs.getKeyGenerator())
                .databaseId(originalMs.getDatabaseId())
                .lang(originalMs.getLang())
                .resultOrdered(originalMs.isResultOrdered())
                .resultSetType(originalMs.getResultSetType())
                .flushCacheRequired(originalMs.isFlushCacheRequired())
                .useCache(originalMs.isUseCache())
                .cache(originalMs.getCache());

        if (CollectionUtil.isNotEmpty(originalMs.getKeyProperties())) {
            msBuilder.keyProperty(String.join(Constant.COMMA, originalMs.getKeyProperties()));
        }

        if (CollectionUtil.isNotEmpty(originalMs.getKeyColumns())) {
            msBuilder.keyColumn(String.join(Constant.COMMA, originalMs.getKeyColumns()));
        }

        if (CollectionUtil.isNotEmpty(originalMs.getResultSets())) {
            msBuilder.resultSets(String.join(Constant.COMMA, originalMs.getResultSets()));
        }

        msBuilder.resultMaps(newResultMaps(originalMs, resultType));

        MappedStatement newMappedStatement = msBuilder.build();

        if (msMap == null) {
            msMap = new ConcurrentHashMap<>(1);
        }

        msMap.put(originalMs.getConfiguration(), newMappedStatement);
        JOIN_MS_CACHE.put(id, msMap);

        return newMappedStatement;
    }

    /**
     * 创建一个List<ResultMap>对象
     */
    private List<ResultMap> newResultMaps(MappedStatement ms, Class<?> resultType) {
        List<ResultMap> resultMaps = new ArrayList<>(1);
        resultMaps.add(newResultMap(ms, resultType));
        return resultMaps;
    }

    /**
     * 创建一个ResultMap对象
     */
    private ResultMap newResultMap(MappedStatement ms, Class<?> resultType) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(resultType);
        if (tableInfo != null && tableInfo.isAutoInitResultMap() && tableInfo.getEntityType() == resultType) {
            return ms.getConfiguration().getResultMap(tableInfo.getResultMap());
        }

        return new ResultMap.Builder(
                ms.getConfiguration(),
                ms.getId() + "-Inline",
                resultType,
                EMPTY_RESULT_MAPPING_LIST
        ).build();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor || target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void addInnerInterceptor(InnerInterceptor innerInterceptor) {
        this.interceptors.add(innerInterceptor);
    }

    public List<InnerInterceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

    /**
     * 使用内部规则,拿分页插件举个栗子:
     * <p>
     * - key: "@page" ,value: "com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor"
     * - key: "page:limit" ,value: "100"
     * <p>
     * 解读1: key 以 "@" 开头定义了这是一个需要组装的 `InnerInterceptor`, 以 "page" 结尾表示别名
     * value 是 `InnerInterceptor` 的具体的 class 全名
     * 解读2: key 以上面定义的 "别名 + ':'" 开头指这个 `value` 是定义的该 `InnerInterceptor` 属性需要设置的值
     * <p>
     * 如果这个 `InnerInterceptor` 不需要配置属性也要加别名
     */
    @Override
    public void setProperties(Properties properties) {
        PropertyMapper pm = PropertyMapper.newInstance(properties);
        Map<String, Properties> group = pm.group(StringPool.AT);
        group.forEach((k, v) -> {
            InnerInterceptor innerInterceptor = ClassUtils.newInstance(k);
            innerInterceptor.setProperties(v);
            addInnerInterceptor(innerInterceptor);
        });
    }

    public void setInterceptors(List<InnerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
