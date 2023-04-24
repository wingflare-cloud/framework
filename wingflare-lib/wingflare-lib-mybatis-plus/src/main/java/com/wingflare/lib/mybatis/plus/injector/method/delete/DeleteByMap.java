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
package com.wingflare.lib.mybatis.plus.injector.method.delete;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

/**
 * 根据columnMap 条件删除记录
 *
 * @author hubin
 * @since 2018-04-06
 */
public class DeleteByMap extends CustomAbstractMethod {

    public DeleteByMap() {
        super(CustomMethod.DELETE_BY_MAP.getMethod());
    }

    /**
     * @param name 方法名
     * @since 3.5.0
     */
    public DeleteByMap(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        CustomMethod sqlMethod = CustomMethod.LOGIC_DELETE_BY_MAP;
        if (tableInfo.isWithLogicDelete()) {
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlLogicSet(tableInfo), sqlWhereByMap(tableInfo));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Map.class);
            return addUpdateMappedStatement(mapperClass, Map.class, sqlSource);
        } else {
            sqlMethod = CustomMethod.DELETE_BY_MAP;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlWhereByMap(tableInfo));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Map.class);
            return this.addDeleteMappedStatement(mapperClass, sqlSource);
        }
    }
}
