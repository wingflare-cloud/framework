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
package com.wingflare.lib.mybatis.plus.injector.method.select.single;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据ID集合，批量查询数据
 *
 * @author hubin
 * @since 2018-04-06
 */
public class SelectBatchByIds extends CustomAbstractMethod {

    public SelectBatchByIds() {
        super(CustomMethod.SELECT_BATCH_BY_IDS.getMethod());
    }

    /**
     * @param name 方法名
     * @since 3.5.0
     */
    public SelectBatchByIds(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod sqlMethod = CustomMethod.SELECT_BATCH_BY_IDS;
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(sqlMethod.getSql(),
                sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                SqlScriptUtils.convertForeach("#{item}", COLL, null, "item", COMMA)), Object.class);
        return addSelectMappedStatementForTable(mapperClass, sqlSource, tableInfo);
    }
}
